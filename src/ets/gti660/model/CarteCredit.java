package ets.gti660.model;

public class CarteCredit {
	
	private int id;
	private int idClient;
	private String numero;
	private String expMois;
	private String expAnnee;
	private String typeCarte;
	
	public CarteCredit(int id, int idClient, String numero, String expMois, String expAnnee, String typeCarte){
		this.id = id;
		this.idClient = idClient;
		this.numero = numero;
		this.expMois = expMois;
		this.expAnnee = expAnnee;
		this.typeCarte = typeCarte;
		
	}
	public CarteCredit() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getExpMois() {
		return expMois;
	}
	public void setExpMois(String expMois) {
		this.expMois = expMois;
	}
	public String getExpAnnee() {
		return expAnnee;
	}
	public void setExpAnnee(String expAnnee) {
		this.expAnnee = expAnnee;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTypeCarte() {
		return typeCarte;
	}
	public void setTypeCarte(String typeCarte) {
		this.typeCarte = typeCarte;
	}
}
