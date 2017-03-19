package ets.gti660.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ets.gti660.model.Langue;

public class LangueMapper implements RowMapper<Langue> {
	@Override
	public Langue mapRow(ResultSet rs, int rowNum) throws SQLException {
		Langue langue = new Langue();
		langue.setId(rs.getInt("id"));
		langue.setNom(rs.getString("langue"));
		return langue;
	}
}
