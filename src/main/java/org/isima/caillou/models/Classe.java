package org.isima.caillou.models;

public enum Classe {

	// Objets directement construits
	TROPBON("TROP BON", "GREEN"), 
	BON("BON", "LIGHT GREEN"), 
	MANGEABLE("MANGEABLE", "YELLOW"), 
	MOUI("MOUI", "ORANGE"),
	DEGUEU("DEGUEU", "RED");

	private String classe;
	private String couleur;

	Classe(String classe, String couleur) {
		this.classe = classe;
		this.couleur = couleur;
	}

	public String getClasse() {
		return classe;
	}

	public String getCouleur() {
		return couleur;
	}	

}