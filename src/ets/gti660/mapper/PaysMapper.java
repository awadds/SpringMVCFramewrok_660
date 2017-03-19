package ets.gti660.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ets.gti660.model.Pays;

public class PaysMapper implements RowMapper<Pays> {
	@Override
	public Pays mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pays pays = new Pays();
		pays.setId(rs.getInt("id"));
		pays.setNom(rs.getString("pays"));
		return pays;
	}
}
