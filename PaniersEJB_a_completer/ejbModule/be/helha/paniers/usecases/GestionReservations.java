package be.helha.paniers.usecases;

import java.util.List;

import javax.ejb.Remote;
import be.helha.paniers.domaine.Panier;
import be.helha.paniers.domaine.Reservation;

@Remote
public interface GestionReservations {

	List<Panier> listePaniersTries();

	Reservation enregistrer(Reservation reservation);
	
}
