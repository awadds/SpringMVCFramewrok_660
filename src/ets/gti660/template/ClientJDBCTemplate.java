package ets.gti660.template;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ets.gti660.dao.ClientDAO;
import ets.gti660.mapper.ClientMapper;
import ets.gti660.model.Client;

public class ClientJDBCTemplate implements ClientDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public Client getClient(Integer id) {
		String SQL = "select * from CLIENT where ID = ?";
		Client client = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new ClientMapper());
		return client;
	}

	@Override
	public List<Client> listClient() {
		String SQL = "select * from Client";
		List<Client> clients = jdbcTemplateObject.query(SQL, new ClientMapper());
		return clients;
	}

	@Override
	public List<String> listForfait() {
		String SQL = "select distinct FORFAIT from Client";
		List<String> forfaits = new ArrayList<String>();
		List<Map<String, Object>> f = jdbcTemplateObject.queryForList(SQL);
		for (Map<String, Object> o : f) {
			forfaits.add((String) o.get("Forfait"));
		}
		return forfaits;
	}

	public void update(Client c) {
		String nom = c.getNomFamille();
		String prenom = c.getPrenom();
		String tel = c.getTel();
		Date anniversaire = c.getAnniversaire();
		String adresse = c.getAdresse();
		String ville = c.getVille();
		String province = c.getProvince();
		String codePostal = c.getCodePostal();
		String forfait = c.getForfait();
		int id = c.getId();
		String SQL = "update Client " + "set nom_famille = ?, " + "prenom = ?, " + "tel = ?, " + "anniversaire = ?, "
				+ "adresse = ?, " + "ville = ?, " + "province = ?, " + "code_postal = ?, " + "forfait = ? "
				+ "where id = ?";
		jdbcTemplateObject.update(SQL,
				new Object[] { nom, prenom, tel, anniversaire, adresse, ville, province, codePostal, forfait, id });

	}

}
