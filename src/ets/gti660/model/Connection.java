package ets.gti660.model;

import java.sql.Date;

public class Connection {
	
	//private final String ArrStatus[] = {"Fermée", "Actif", "Indéterminée"};
	private final String ArrStatus[] = {"Closed", "Active", "Unknown"};
	
	private boolean active = false;	// True = active, False = inactive
	private String lastChecked;		// Nombre de seconde écoulé depuis la dernière tentative de vérification
	private String status;			// fermée, en cours, indéterminée
	
	private static Connection connection = new Connection();
	
	private Connection(){}
	
	public static Connection getInstance(){
			return connection;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getLastChecked() {
		return lastChecked;
	}

	public void setLastChecked(String lastChecked) {
		this.lastChecked = lastChecked;
	}

	public String getStatus() {
		this.status = ArrStatus[2]; // indéterminée par défaut
		
		if(active == false){
			this.status = ArrStatus[0];
		}
		else{
			this.status = ArrStatus[1];
		}
		
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}		
	

}
