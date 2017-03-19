package ets.gti660.model;

import java.sql.Date;

public class Personne {

	int id;
	String nom;
	Date anniversaire;
	String lieu;
	String photo;
	String bio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getAnniversaire() {
		return anniversaire;
	}

	public void setAnniversaire(Date anniversaire) {
		this.anniversaire = anniversaire;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
}
