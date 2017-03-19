package ets.gti660.template;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import ets.gti660.dao.PretDAO;
import ets.gti660.mapper.PretMapper;
import ets.gti660.model.Pret;

public class PretJDBCTemplate implements PretDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(float cout, Date debut, Date fin, int idCarte, int idClient, int idFilm) {
		String SQL = "insert into Pret (cout, debut, fin, id_carte, id_client, id_film) " + "values (?, ?, ?, ?, ?, ?)";
		try {
			jdbcTemplateObject.update(SQL, new Object[] { cout, debut, fin, idCarte, idClient, idFilm });
		} catch (DataAccessException exception) {
			System.out.println(exception.getStackTrace());
		}

	}

	@Override
	public List<Pret> getPretsByIdClient(int idClient) {
		List<Pret> prets = new ArrayList<Pret>();
		String SQL = "select * from Pret where id_client = ?";
		try {
			prets = jdbcTemplateObject.query(SQL, new Object[] { idClient }, new PretMapper());
		} catch (DataAccessException exception) {
			System.out.println(exception.getStackTrace());
		}
		return prets;
	}
}
