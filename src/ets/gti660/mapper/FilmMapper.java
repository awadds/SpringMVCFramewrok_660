package ets.gti660.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ets.gti660.model.Film;

public class FilmMapper implements RowMapper<Film> {
	@Override
	public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
		Film film = new Film();
		film.setId(rs.getInt("id"));
		film.setTitre(rs.getString("titre"));
		film.setAnnee(rs.getString("annee"));
		film.setDuree(rs.getInt("duree"));
		film.setResume(rs.getString("resume"));
		film.setPoster(rs.getString("poster"));
		return film;
	}
}
