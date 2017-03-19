package ets.gti660.dao;

import java.util.List;

import javax.sql.DataSource;

import ets.gti660.model.Personne;

public interface PersonneDAO {

	public void setDataSource(DataSource ds);

	public Personne getPersonne(Integer id);

	public List<Personne> listPersonnes();

	public List<Personne> getPersonneByName(String nom);

	public List<Personne> getPersonneActeurByFilm(int id);

	public List<Personne> getPersonneRealisateurByFilm(int id);
}
