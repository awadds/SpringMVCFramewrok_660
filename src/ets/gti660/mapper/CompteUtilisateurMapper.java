package ets.gti660.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ets.gti660.model.CompteUtilisateur;

public class CompteUtilisateurMapper implements RowMapper<CompteUtilisateur> {
	@Override
	public CompteUtilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
		CompteUtilisateur compteUtilisateur = new CompteUtilisateur();
		compteUtilisateur.setId(rs.getInt("id_client"));
		compteUtilisateur.setCourriel(rs.getString("courriel"));
		compteUtilisateur.setMotDePasse(rs.getString("mot_de_passe"));
		return compteUtilisateur;
	}
}
