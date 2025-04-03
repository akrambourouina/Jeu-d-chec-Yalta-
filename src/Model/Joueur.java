package Model;

import java.util.List;

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
}
