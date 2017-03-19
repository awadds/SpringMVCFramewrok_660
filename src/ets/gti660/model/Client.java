package ets.gti660.model;

import java.sql.Date;

public class Client {
	private int id;
	private String nomFamille;
	private String prenom;
	private String courriel;
	private String tel;
	private Date anniversaire;
	private String adresse;
	private String ville;
	private String province;
	private String codePostal;
	private String forfait;

	public Client() {

	}

	public Client(int id, String nomFamille, String prenom, String courriel, String tel, Date anniversaire,
			String adresse, String ville, String province, String codePostal, String forfait) {

		this.id = id;
		this.nomFamille = nomFamille;
		this.prenom = prenom;
		this.courriel = courriel;
		this.tel = tel;
		this.anniversaire = anniversaire;
		this.adresse = adresse;
		this.ville = ville;
		this.province = province;
		this.codePostal = codePostal;
		this.forfait = forfait;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomFamille() {
		return nomFamille;
	}

	public void setNomFamille(String nomFamille) {
		this.nomFamille = nomFamille;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getAnniversaire() {
		return anniversaire;
	}

	public void setAnniversaire(Date anniversaire) {
		this.anniversaire = anniversaire;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getForfait() {
		return forfait;
	}

	public void setForfait(String forfait) {
		this.forfait = forfait;
	}

}
