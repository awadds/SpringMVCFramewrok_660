package ets.gti660.dao;

import java.util.List;

import javax.sql.DataSource;

import ets.gti660.model.Langue;

public interface LangueDAO {

	public void setDataSource(DataSource ds);

	public Langue getLangue(Integer id);

	public List<Langue> listLangues();
}
