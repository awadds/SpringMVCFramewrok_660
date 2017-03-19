package ets.gti660.data;

import java.sql.Date;

import ets.gti660.model.Film;

public class Emprunt {
	Film film;
	Date debut;
	Date fin;
	float cout;

	public Emprunt(Film film, Date debut, Date fin, float cout) {
		this.film = film;
		this.debut = debut;
		this.fin = fin;
		this.cout = cout;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public float getCout() {
		return cout;
	}

	public void setCout(float cout) {
		this.cout = cout;
	}
}
