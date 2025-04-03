package Model;

import java.util.List;

public abstract class Piece {
	protected Case position;
	protected Joueur joueur;

	public Piece(Case position, Joueur joueur) {
		this.position = position;
		this.joueur = joueur;
		this.position.setPiece(this);
	}

	public Case getPosition() { return position; }
	public void setPosition(Case nouvellePosition) {
		this.position.setPiece(null);
		this.position = nouvellePosition;
		nouvellePosition.setPiece(this);
	}
	public String getSymboleAvecJoueur() {
     return "" + getSymbole() + joueur.getCouleur();
      }

	public Joueur getJoueur() { return joueur; }

	public abstract List<Case> getDeplacementsPossibles(Plateau plateau);
	public abstract char getSymbole();
}