package ets.gti660.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ets.gti660.model.Personne;

public class PersonneMapper implements RowMapper<Personne> {
	@Override
	public Personne mapRow(ResultSet rs, int rowNum) throws SQLException {
		Personne personne = new Personne();
		personne.setId(rs.getInt("id"));
		personne.setNom(rs.getString("nom"));
		personne.setAnniversaire(rs.getDate("anniversaire"));
		personne.setLieu(rs.getString("lieu"));
		personne.setPhoto(rs.getString("photo"));
		personne.setBio(rs.getString("bio"));
		return personne;
	}
}
