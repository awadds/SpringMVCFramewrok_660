package ets.gti660.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ets.gti660.model.Scenariste;

public class ScenaristeMapper implements RowMapper<Scenariste> {
	@Override
	public Scenariste mapRow(ResultSet rs, int rowNum) throws SQLException {
		Scenariste scenariste = new Scenariste();
		scenariste.setId(rs.getInt("id"));
		scenariste.setNom(rs.getString("nom"));
		return scenariste;
	}
}
