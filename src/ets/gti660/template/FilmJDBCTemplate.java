package ets.gti660.template;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ets.gti660.dao.FilmDAO;
import ets.gti660.mapper.FilmMapper;
import ets.gti660.model.Film;

public class FilmJDBCTemplate implements FilmDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public Film getFilm(Integer id) {
		String SQL = "select * from Film where id = ?";
		Film film = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new FilmMapper());
		return film;
	}

	@Override
	public List<Film> listFilms() {
		String SQL = "select * from Film";
		List<Film> films = jdbcTemplateObject.query(SQL, new FilmMapper());
		return films;
	}

	@Override
	public List<Film> getFilmByName(String titre) {
		String SQL = "select * from Film where titre = ?";
		List<Film> films = jdbcTemplateObject.query(SQL, new Object[] { titre }, new FilmMapper());
		return films;
	}

	@Override
	public List<Film> getFilmByYear(String year) {
		String SQL = "select * from Film where annee = ?";
		List<Film> films = jdbcTemplateObject.query(SQL, new Object[] { year }, new FilmMapper());
		return films;
	}

	@Override
	public List<Film> getFilmByGenre(int genreID) {
		String SQL = "select * from Film, FilmGenre where Film.id = FilmGenre.id_film AND FilmGenre.id_genre = ?";
		List<Film> films = jdbcTemplateObject.query(SQL, new Object[] { genreID }, new FilmMapper());
		return films;
	}

	@Override
	public List<Film> getFilmByLangue(int langueID) {
		String SQL = "select * from Film, FilmLangue where Film.id = FilmLangue.id_film AND FilmLangue.id_langue = ?";
		List<Film> films = jdbcTemplateObject.query(SQL, new Object[] { langueID }, new FilmMapper());
		return films;
	}

	@Override
	public List<Film> getFilmByPays(int paysID) {
		String SQL = "select * from Film, FilmPays where Film.id = FilmPays.id_film AND FilmPays.id_pays = ?";
		List<Film> films = jdbcTemplateObject.query(SQL, new Object[] { paysID }, new FilmMapper());
		return films;
	}

	@Override
	public List<Film> getFilmByPersonneActeur(int id) {
		String SQL = "select * from Film, Role where Film.id = Role.id_film AND Role.id_personne = ?";
		List<Film> films = jdbcTemplateObject.query(SQL, new Object[] { id }, new FilmMapper());
		return films;
	}

	@Override
	public List<Film> getFilmByPersonneRealisateur(int id) {
		String SQL = "select * from Film, Realisateur where Film.id = Realisateur.id_film AND Realisateur.id_personne = ?";
		List<Film> films = jdbcTemplateObject.query(SQL, new Object[] { id }, new FilmMapper());
		return films;
	}

	@Override
	public List<Film> getFilmByPersonneActeur(String search) {
		String SQL = "select * from Film, Role, Personne where Film.id = Role.id_film AND Role.id_personne = Personne.id AND Personne.nom = ?";
		List<Film> films = jdbcTemplateObject.query(SQL, new Object[] { search }, new FilmMapper());
		return films;
	}

	@Override
	public List<Film> getFilmByPersonneRealisateur(String search) {
		String SQL = "select * from Film, Realisateur, Personne where Film.id = Realisateur.id_film AND Realisateur.id_personne = Personne.id AND Personne.nom = ?";
		List<Film> films = jdbcTemplateObject.query(SQL, new Object[] { search }, new FilmMapper());
		return films;
	}

	@Override
	public List<Film> getFilmByDuration(int min, int max) {
		String SQL = "select * from Film where duree > ? and duree < ?";
		List<Film> films = jdbcTemplateObject.query(SQL, new Object[] { min, max }, new FilmMapper());
		return films;
	}

	@Override
	public List<Film> getFilmByPersonneScenariste(String search) {
		String SQL = "select * from Film, FilmScenariste, Scenariste where Film.id = FilmScenariste.id_film AND FilmScenariste.id_scenariste = Scenariste.id AND Scenariste.nom = ?";
		List<Film> films = jdbcTemplateObject.query(SQL, new Object[] { search }, new FilmMapper());
		return films;
	}

	@Override
	public List<Film> getFilmByResume(String search) {
		String SQL = "select * from Film where resume like ?";
		List<Film> films = jdbcTemplateObject.query(SQL, new Object[] { "%" + search + "%" }, new FilmMapper());
		return films;
	}

}
