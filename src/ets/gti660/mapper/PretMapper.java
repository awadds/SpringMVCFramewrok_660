package ets.gti660.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ets.gti660.model.Pret;

public class PretMapper implements RowMapper<Pret> {
	@Override
	public Pret mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pret pret = new Pret();
		pret.setId(rs.getInt("id"));
		pret.setCout(rs.getFloat("cout"));
		pret.setDebut(rs.getDate("debut"));
		pret.setFin(rs.getDate("fin"));
		pret.setIdCarte(rs.getInt("id_carte"));
		pret.setIdClient(rs.getInt("id_client"));
		pret.setIdFilm(rs.getInt("id_film"));
		return pret;
	}
}
