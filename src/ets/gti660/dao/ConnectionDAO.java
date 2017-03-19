package ets.gti660.dao;

import javax.sql.DataSource;

public interface ConnectionDAO {

	public void setDataSource(DataSource dataSource);

	public Object testConnection();

	public String[] getServerInfo();

}
