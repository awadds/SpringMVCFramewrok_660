package ets.gti660.dao;

import java.util.List;

import javax.sql.DataSource;

import ets.gti660.model.Pays;

public interface PaysDAO {

	public void setDataSource(DataSource ds);

	public Pays getPays(Integer id);

	public List<Pays> listPays();
}
