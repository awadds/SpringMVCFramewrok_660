package ets.gti660.template;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ets.gti660.dao.ConnectionDAO;

public class ConnectionJDBCTemplate implements ConnectionDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private Connection con; // attention ceci est l'objet sql.Connection et non le model Connection.java

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public Boolean testConnection() {
		Boolean isClosed = true;
		
		try {
			con = jdbcTemplateObject.getDataSource().getConnection();
			isClosed = con.isClosed(); // check if connexion is closed will
										// return true if the connection is
										// closed
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isClosed;
		
	}

	@Override
	public String[] getServerInfo() {
		// String result ="\n URL: ";
		String result[] = new String[2];
		if(con != null){
			try {
				result[0] = con.getMetaData().getURL();
				result[1] = con.getMetaData().getUserName();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return result;
		}
		else{
			return null;
		}
	}
	
	public void closeConnection(){
		try {
			con.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
