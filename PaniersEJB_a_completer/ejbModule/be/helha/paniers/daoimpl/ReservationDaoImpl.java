package be.helha.paniers.daoimpl;

import javax.ejb.Stateless;

import be.helha.paniers.dao.ReservationDao;
import be.helha.paniers.domaine.Reservation;

@Stateless
public class ReservationDaoImpl extends DaoImpl<Integer,Reservation> implements ReservationDao {
	private static final long serialVersionUID = 1L;
}
