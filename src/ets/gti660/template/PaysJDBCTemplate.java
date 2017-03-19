package ets.gti660.template;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ets.gti660.dao.PaysDAO;
import ets.gti660.mapper.PaysMapper;
import ets.gti660.model.Pays;

public class PaysJDBCTemplate implements PaysDAO{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Pays getPays(Integer id) {
		String SQL = "select * from Pays where id = ?";
		Pays pays = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new PaysMapper());
		return pays;
	}

	@Override
	public List<Pays> listPays() {
		String SQL = "select * from Pays";
		List<Pays> pays = jdbcTemplateObject.query(SQL, new PaysMapper());
		return pays;
	}

}
