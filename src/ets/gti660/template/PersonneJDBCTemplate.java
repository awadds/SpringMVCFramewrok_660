package ets.gti660.template;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ets.gti660.dao.PersonneDAO;
import ets.gti660.mapper.FilmMapper;
import ets.gti660.mapper.PersonneMapper;
import ets.gti660.model.Film;
import ets.gti660.model.Personne;

public class PersonneJDBCTemplate implements PersonneDAO{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public Personne getPersonne(Integer id) {
		String SQL = "select * from Personne where id = ?";
		Personne personne = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new PersonneMapper());
		return personne;
	}

	@Override
	public List<Personne> listPersonnes() {
		String SQL = "select * from Personne";
		List<Personne> personnes = jdbcTemplateObject.query(SQL, new PersonneMapper());
		return personnes;
	}

	@Override
	public List<Personne> getPersonneByName(String nom) {
		String SQL = "select * from Personne where nom LIKE ?";
		List<Personne> personnes = jdbcTemplateObject.query(SQL, new Object[] { "%" + nom + "%" }, new PersonneMapper());
		return personnes;
	}

	@Override
	public List<Personne> getPersonneActeurByFilm(int id) {
		String SQL = "select * from Personne, Role where Personne.id = Role.id_personne AND Role.id_film = ?";
		List<Personne> personnes = jdbcTemplateObject.query(SQL, new Object[] { id }, new PersonneMapper());
		return personnes;
	}
	
	@Override
	public List<Personne> getPersonneRealisateurByFilm(int id) {
		String SQL = "select * from Personne, Realisateur where Personne.id = Realisateur.id_personne AND Realisateur.id_film = ?";
		List<Personne> personnes = jdbcTemplateObject.query(SQL, new Object[] { id }, new PersonneMapper());
		return personnes;
	}
}
