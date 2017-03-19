package ets.gti660.template;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import ets.gti660.dao.CompteUtilisateurDAO;
import ets.gti660.mapper.CompteUtilisateurMapper;
import ets.gti660.model.CompteUtilisateur;

public class CompteUtilisateurJDBCTemplate implements CompteUtilisateurDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public CompteUtilisateur getCompteUtilisateur(Integer id) {
		String SQL = "select * from CompteUtilisateur where id = ?";
		CompteUtilisateur compteUtilisateur = jdbcTemplateObject.queryForObject(SQL, new Object[] { id },
				new CompteUtilisateurMapper());
		return compteUtilisateur;
	}

	@Override
	public CompteUtilisateur getCompteUtilisateurByAttributes(String courriel, String mot_de_passe)
			throws DataAccessException {
		String SQL = "select * from CompteUtilisateur where courriel = ? and mot_de_passe = ?";
		CompteUtilisateur compteUtilisateur = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { courriel, mot_de_passe }, new CompteUtilisateurMapper());
		if (compteUtilisateur != null) {
			return compteUtilisateur;
		} else {
			return null;
		}
	}

	@Override
	public List<CompteUtilisateur> listCompteUtilisateur() {
		String SQL = "select * from CompteUtilisateur";
		List<CompteUtilisateur> compteUtilisateurs = jdbcTemplateObject.query(SQL, new CompteUtilisateurMapper());
		return compteUtilisateurs;
	}

}
