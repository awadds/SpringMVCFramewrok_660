package ets.gti660.model;

public class Film {
	private int id;
	private String titre;
	private String annee;
	private int duree;
	private String resume;
	private String poster;

	public void setId(int id) {
		this.id = id;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getTitre() {
		return titre;
	}

	public int getId() {
		return id;
	}

	public String getAnnee() {
		return annee;
	}

	public String getResume() {
		return resume;
	}

	public String getPoster() {
		return poster;
	}

	public int getDuree() {
		return duree;
	}

}
