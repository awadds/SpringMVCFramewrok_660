package ets.gti660.model;

public class CompteUtilisateur {

	private int id_client;
	private String courriel;
	private String mot_de_passe;

	public CompteUtilisateur() {

	}

	public void setId(int id) {
		this.id_client = id;
	}

	public int getId() {
		return id_client;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;

	}

	public String getCourriel() {
		return courriel;
	}

	public void setMotDePasse(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;

	}

	public String getMotDePasse() {
		return mot_de_passe;
	}

}
