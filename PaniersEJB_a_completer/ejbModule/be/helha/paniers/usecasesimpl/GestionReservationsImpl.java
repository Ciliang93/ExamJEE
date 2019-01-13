package be.helha.paniers.usecasesimpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.helha.paniers.dao.PanierDao;
import be.helha.paniers.dao.ReservationDao;
import be.helha.paniers.domaine.Panier;
import be.helha.paniers.domaine.Reservation;
import be.helha.paniers.usecases.GestionReservations;

@Stateless
public class GestionReservationsImpl  implements GestionReservations {

	@EJB
	private ReservationDao reservationDao;
	
	@EJB
	private PanierDao panierDao;
	
	@Override
	public List<Panier> listePaniersTries() {
		List<Panier> listTrieePaniers = panierDao.listeTriee();
		return listTrieePaniers;
	}

	@Override
	public Reservation enregistrer(Reservation reservation) {
		Reservation reservationDb = null;
		reservationDb = reservationDao.enregistrer(reservation);
		return reservationDb;
	}
}