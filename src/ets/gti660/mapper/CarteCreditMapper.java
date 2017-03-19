package ets.gti660.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ets.gti660.model.CarteCredit;

public class CarteCreditMapper implements RowMapper<CarteCredit> {
	@Override
	public CarteCredit mapRow(ResultSet rs, int rowNum) throws SQLException {
		CarteCredit carte = new CarteCredit();
		carte.setId(rs.getInt("id"));
		carte.setIdClient(rs.getInt("id_client"));
		carte.setNumero(rs.getString("numero"));
		carte.setExpMois(rs.getString("expmois"));
		carte.setExpAnnee(rs.getString("expannee"));
		carte.setTypeCarte(rs.getString("typecarte"));
		return carte;
	}
}
