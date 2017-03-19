package ets.gti660.dao;

import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import ets.gti660.model.Pret;

public interface PretDAO {

	public void setDataSource(DataSource ds);

	public void insert(float cout, Date debut, Date fin, int idCarte, int idClient, int idFilm);

	public List<Pret> getPretsByIdClient(int idClient);
}
