package ets.gti660.dao;

import java.util.List;

import javax.sql.DataSource;

import ets.gti660.model.Scenariste;

public interface ScenaristeDAO {

	public void setDataSource(DataSource ds);

	public Scenariste getScenariste(Integer id);

	public List<Scenariste> listScenaristes();

	public void delete(Integer id);
}
