package ets.gti660.dao;

import java.util.List;

import javax.sql.DataSource;

import ets.gti660.model.Film;

public interface FilmDAO {
	
	public void setDataSource(DataSource ds);

	public Film getFilm(Integer id);

	public List<Film> listFilms();

	public List<Film> getFilmByName(String search);

	public List<Film> getFilmByYear(String year);

	public List<Film> getFilmByGenre(int genreID);

	public List<Film> getFilmByPays(int paysID);

	public List<Film> getFilmByLangue(int langueID);

	public List<Film> getFilmByPersonneActeur(int id);

	public List<Film> getFilmByPersonneRealisateur(int id);

	public List<Film> getFilmByPersonneActeur(String search);

	public List<Film> getFilmByPersonneRealisateur(String search);

	public List<Film> getFilmByDuration(int min, int max);

	public List<Film> getFilmByPersonneScenariste(String search);

	public List<Film> getFilmByResume(String search);
}
