package ets.gti660.dao;

import java.util.List;

import javax.sql.DataSource;

import ets.gti660.model.CompteUtilisateur;

public interface CompteUtilisateurDAO {

	public void setDataSource(DataSource ds);

	public CompteUtilisateur getCompteUtilisateur(Integer id);

	public List<CompteUtilisateur> listCompteUtilisateur();

	public CompteUtilisateur getCompteUtilisateurByAttributes(String courriel, String mot_de_passe);
}
