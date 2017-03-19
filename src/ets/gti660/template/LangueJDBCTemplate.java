package ets.gti660.template;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ets.gti660.dao.LangueDAO;
import ets.gti660.mapper.LangueMapper;
import ets.gti660.model.Langue;

public class LangueJDBCTemplate implements LangueDAO{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public Langue getLangue(Integer id) {
		String SQL = "select * from Langue where id = ?";
		Langue langue = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new LangueMapper());
		return langue;
	}

	@Override
	public List<Langue> listLangues() {
		String SQL = "select * from Langue";
		List<Langue> langues = jdbcTemplateObject.query(SQL, new LangueMapper());
		return langues;
	}

}
