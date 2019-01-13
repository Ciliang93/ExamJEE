package be.helha.paniers.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.helha.paniers.domaine.Constantes;
import be.helha.paniers.domaine.Panier;
import be.helha.paniers.domaine.Reservation;
import be.helha.paniers.usecases.GestionReservations;

@WebServlet(urlPatterns="/index.html", name="index.html")
public class ReservationPaniers  extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionReservations gestionReservation;
	
	private int i = 0;
	private Panier panierAReserve = null;
	private String message = "";
	private boolean operation = false;
	private String paramValue = null;
	private String utilisateur = null;
	private String telephone = null;
	private List<Panier> listPanier;
	private Calendar dateReservation = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		message = "S�lectionnez les paniers.";
		operation = true;
		listPanier = gestionReservation.listePaniersTries();
		afficherVue(utilisateur,telephone,listPanier,message,operation,request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			message = "";
			operation = true;
			paramValue = request.getParameter(Constantes.PARAM_PANIERS_RESERVES);
			utilisateur = request.getParameter(Constantes.PARAM_NOM);
			telephone = request.getParameter(Constantes.PARAM_TELEPHONE);
			listPanier = (List<Panier>) gestionReservation.listePaniersTries();
			dateReservation = Calendar.getInstance();
			
			if(utilisateur == null || utilisateur.trim().isEmpty()) {
				message = "Veuillez introduire un nom.";
				operation = false;
				afficherVue(utilisateur,telephone,listPanier,message,operation,request,response);
			} else if(telephone == null || telephone.trim().isEmpty()) {
				message = "Veuillez introduire un num�ro de t�l�phone.";
				operation = false;
				afficherVue(utilisateur,telephone,listPanier,message,operation,request,response);
			} else if(paramValue == null || paramValue.trim().isEmpty()) {
				message = "Aucun panier s�lectionner";
				operation = false;
				afficherVue(utilisateur,telephone,listPanier,message,operation,request,response);
			} else {
				for(Panier p : listPanier) {
					if(i == Integer.parseInt(paramValue)) panierAReserve = p;					
					i++;
				}
				i=0;
				Reservation reservation = new Reservation(utilisateur,telephone,dateReservation,panierAReserve);
				Reservation reservationDb = gestionReservation.enregistrer(reservation);
				operation = true;
				message = "La r�servation a �t� enregistr�e. Voici son num�ro : " + reservationDb.getId();
				request.setAttribute(Constantes.ATT_RESERVATION, reservationDb);
				afficherVue(utilisateur,telephone,listPanier,message,operation,request,response);
			}
	}

	private void afficherVue(String nom, String telephone, List<Panier> paniersTries, String message, boolean operationReussie, ServletRequest request, ServletResponse response) throws ServletException, IOException {
		if(nom == null || nom.trim().isEmpty()) { 
			request.setAttribute(Constantes.ATT_LISTE_PANIERS, paniersTries);
			request.setAttribute(Constantes.ATT_MESSAGE, message);
			request.setAttribute(Constantes.ATT_OPERATION_REUSSIE, operationReussie);
			this.getServletContext().getNamedDispatcher(Constantes.VUE_INDEX).forward(request, response);
		} else {
			request.setAttribute(Constantes.ATT_NOM, nom);
			request.setAttribute(Constantes.ATT_TELEPHONE, telephone);
			request.setAttribute(Constantes.ATT_LISTE_PANIERS, paniersTries);
			request.setAttribute(Constantes.ATT_MESSAGE, message);
			request.setAttribute(Constantes.ATT_OPERATION_REUSSIE, operationReussie);
			
			this.getServletContext().getNamedDispatcher(Constantes.VUE_INDEX).forward(request, response);
		}
	}
}