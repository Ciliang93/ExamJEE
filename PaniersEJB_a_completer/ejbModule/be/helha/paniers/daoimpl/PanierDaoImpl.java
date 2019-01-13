package be.helha.paniers.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import be.helha.paniers.dao.PanierDao;
import be.helha.paniers.domaine.Constantes;
import be.helha.paniers.domaine.Panier;

@Stateless
public class PanierDaoImpl extends DaoImpl<Integer,Panier> implements PanierDao {
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = Constantes.UNIT_NAME)
	private EntityManager entityManager;
	
	@Override
	public List<Panier> listeTriee() {
		try {
			String query = "SELECT p FROM Panier p ORDER BY p.prix";
			TypedQuery<Panier> tquery =  entityManager.createQuery(query,Panier.class);
			return tquery.getResultList();
		} catch(Exception e) {
			e.getMessage();
		}
		return null;
	}
}