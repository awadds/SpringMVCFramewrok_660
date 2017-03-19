package ets.gti660.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ets.gti660.model.CarteCredit;
import ets.gti660.model.Client;
import ets.gti660.model.CompteUtilisateur;
import ets.gti660.model.Film;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccessData {

	final int NO_USER_ID = -1;
	private int userID;
	private List<CarteCredit> cartes;
	private List<Emprunt> panier;
	private Client client;
	private CompteUtilisateur compte;
	private boolean sessionActive = false;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public CompteUtilisateur getCompte() {
		return compte;
	}

	public void setCompte(CompteUtilisateur compte) {
		this.compte = compte;
	}

	public void setCartes(List<CarteCredit> cartes) {
		this.cartes = cartes;
	}

	public List<CarteCredit> getCartes() {
		return cartes;
	}

	public List<Emprunt> getCart() {
		return panier;
	}

	public void setCart(List<Emprunt> cart) {
		this.panier = cart;
	}

	public void addCart(Emprunt l) {
		panier.add(l);
	}

	public void deleteCart(int id) {
		for (Emprunt l : panier) {
			if (id == l.getFilm().getId()) {
				panier.remove(l);
				return;
			}
		}
	}

	public List<Film> getCartFilms() {
		List<Film> f = new ArrayList<Film>();
		for (Emprunt l : panier) {
			f.add(l.getFilm());
		}
		return f;
	}

	public void emptyAll() {
		userID = NO_USER_ID;
		cartes = null;
		panier = null;
		client = null;
		compte = null;

	}

	public boolean isSessionActive() {
		return sessionActive;
	}

	public void setSessionActive(boolean b) {
		sessionActive = b;
	}

}