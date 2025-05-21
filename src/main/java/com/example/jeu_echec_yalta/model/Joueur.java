package com.example.jeu_echec_yalta.model;

import java.util.List;
import java.util.Objects;

public class Joueur {
	private String nom;
	private int couleur;
	private List<Piece> pieces;

	public Joueur(String nom, int couleur) {
		this.nom = nom;
		this.couleur = couleur;
	}

	public String getNom() { return nom; }
	public int getCouleur() { return couleur; }
	public List<Piece> getPieces() { return pieces; }
	public void setPieces(List<Piece> pieces) { this.pieces = pieces; }

	public Piece getRoi() {
		for (Piece p : pieces) {
			if (p.getType() == TypePiece.ROI) {
				return p;
			}
		}
		return null; // Roi mort ou pas encore placé
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Joueur joueur = (Joueur) o;
		return couleur == joueur.couleur && Objects.equals(nom, joueur.nom) && Objects.equals(pieces, joueur.pieces);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom, couleur, pieces);
	}
}
