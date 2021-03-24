package it.polito.spellchecker.model;

public class RichWord {
	
	String parola;
	boolean corretto;
	
	public RichWord(String parola, boolean corretto) {
		super();
		this.parola = parola;
		this.corretto = corretto;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public boolean isCorretto() {
		return corretto;
	}

	public void setCorretto(boolean corretto) {
		this.corretto = corretto;
	}
	
	
	

}
