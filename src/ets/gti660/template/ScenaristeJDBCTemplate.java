package ets.gti660.template;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ets.gti660.dao.ScenaristeDAO;
import ets.gti660.mapper.LangueMapper;
import ets.gti660.mapper.ScenaristeMapper;
import ets.gti660.model.Langue;
import ets.gti660.model.Scenariste;

public class ScenaristeJDBCTemplate implements ScenaristeDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void delete(Integer id) {
		String SQL = "delete from Langue where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
		return;
	}

	@Override
	public Scenariste getScenariste(Integer id) {
		String SQL = "select * from Scenariste where id = ?";
		Scenariste scenariste = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new ScenaristeMapper());
		return scenariste;
	}

	@Override
	public List<Scenariste> listScenaristes() {
		String SQL = "select * from Scenariste";
		List<Scenariste> scenaristes = jdbcTemplateObject.query(SQL, new ScenaristeMapper());
		return scenaristes;
	}
}
