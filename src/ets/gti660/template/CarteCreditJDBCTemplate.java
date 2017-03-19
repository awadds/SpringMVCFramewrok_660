package ets.gti660.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ets.gti660.dao.CarteCreditDAO;
import ets.gti660.mapper.CarteCreditMapper;
import ets.gti660.model.CarteCredit;

public class CarteCreditJDBCTemplate implements CarteCreditDAO{

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);	
	}

	@Override
	public CarteCredit getCarteCredit(Integer id) {
		String SQL = "select * from CLIENT where ID = ?";
		CarteCredit carte = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new CarteCreditMapper());
		return carte;
	}

	@Override
	public List<CarteCredit> listCarteCredit() {
		String SQL = "select * from CarteCredit";
		List<CarteCredit> cartes = jdbcTemplateObject.query(SQL, new CarteCreditMapper());
		return cartes;
	}

	@Override
	public void delete(Integer id) {
		String SQL = "delete from CarteCredit where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
		return;
		
	}

	public List<CarteCredit> getCartesByIdClient(int id_client) {
		String SQL = "select * from CarteCredit where id_client = ?";
		List<CarteCredit> cartes = jdbcTemplateObject.query(SQL, new Object[] { id_client }, new CarteCreditMapper());
		return cartes;
	}

	@Override
	public List<String> listType() {
		String SQL = "select distinct TypeCarte from CarteCredit";
		List<String> forfaits = new ArrayList<String>();
		List<Map<String, Object>> f = jdbcTemplateObject.queryForList(SQL);
		for (Map<String, Object> o : f){
			forfaits.add((String)o.get("TypeCarte"));
		}		
		return forfaits;
	}

	public void update(CarteCredit c) {
			String numero = c.getNumero();
			String expMois = c.getExpMois();
			String expAnnee = c.getExpAnnee();
			String typeCarte = c.getTypeCarte();
			int idClient = c.getIdClient();
			int id = c.getId();
			String SQL = "update CarteCredit " + "set numero = ?, " + "expMois = ?, " + "expAnnee = ?, " + "typeCarte = ?, "
					+ "id_Client = ? "
					+ "where id = ?";
			jdbcTemplateObject.update(SQL,
					new Object[] { numero, expMois, expAnnee, typeCarte, idClient, id });
		
		
	}

}
