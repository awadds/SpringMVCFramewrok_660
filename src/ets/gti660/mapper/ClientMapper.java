package ets.gti660.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ets.gti660.model.Client;

public class ClientMapper implements RowMapper<Client>{
	@Override
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException{
		Client client = new Client();
		client.setId(rs.getInt("id"));
		client.setNomFamille(rs.getString("nom_famille"));
		client.setPrenom(rs.getString("prenom"));
		client.setTel(rs.getString("tel"));
		client.setAnniversaire(rs.getDate("anniversaire"));
		client.setAdresse(rs.getString("adresse"));
		client.setVille(rs.getString("ville"));
		client.setProvince(rs.getString("province"));
		client.setCodePostal(rs.getString("code_postal"));
		client.setForfait(rs.getString("forfait"));
		return client;
	}
}
