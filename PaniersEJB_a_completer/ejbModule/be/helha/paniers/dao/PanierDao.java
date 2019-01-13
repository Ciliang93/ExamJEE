package be.helha.paniers.dao;


import java.util.List;

import javax.ejb.Local;

import be.helha.paniers.domaine.Panier;

@Local
public interface PanierDao extends Dao<Integer, Panier> {
	// liste des paniers triés sur le prix par ordre croisant
	List<Panier> listeTriee();
}
