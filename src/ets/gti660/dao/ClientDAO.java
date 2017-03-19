package ets.gti660.dao;

import java.util.List;

import javax.sql.DataSource;

import ets.gti660.model.Client;

public interface ClientDAO {

	public void setDataSource(DataSource ds);

	public Client getClient(Integer id);

	public List<Client> listClient();

	public List<String> listForfait();

}
