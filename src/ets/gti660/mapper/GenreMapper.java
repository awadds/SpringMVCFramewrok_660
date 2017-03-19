package ets.gti660.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ets.gti660.model.Genre;

public class GenreMapper implements RowMapper<Genre> {
	@Override
	public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
		Genre genre = new Genre();
		genre.setId(rs.getInt("id"));
		genre.setNom(rs.getString("genre"));
		return genre;
	}
}
