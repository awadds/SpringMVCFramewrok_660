package ets.gti660.dao;

import java.util.List;

import javax.sql.DataSource;

import ets.gti660.model.Genre;

public interface GenreDAO {

	public void setDataSource(DataSource ds);

	public Genre getGenre(Integer id);

	public List<Genre> listGenres();
}
