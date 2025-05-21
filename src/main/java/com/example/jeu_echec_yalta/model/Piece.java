package com.example.jeu_echec_yalta.model;

import java.util.List;

import static com.example.jeu_echec_yalta.model.TypePiece.*;

public abstract class Piece {
	protected Case position;
	protected Joueur joueur;

	public Piece(Case position, Joueur joueur) {
		this.position = position;
		this.joueur = joueur;
		this.position.setPiece(this);
	}
	public Piece(Case position, Joueur joueur, boolean affecterCase) {
		this.position = position;
		this.joueur = joueur;
		if (affecterCase && this.position != null) {
			this.position.setPiece(this);
		}
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
	private TypePiece type;

	public TypePiece getSymbole1() {
		return type;
	}
	public abstract TypePiece getType();


	public String getSymboleUnicode() {
		TypePiece type = getType(); // C'est ici que tu récupères le bon type
		int couleur = joueur.getCouleur();

		return switch (type) {
			case ROI -> switch (couleur) {
				case 0 -> "♔"; // Blanc
				case 1, 2 -> "♚"; // Rouge et Noir
				default -> "?";
			};
			case REINE -> switch (couleur) {
				case 0 -> "♕";
				case 1, 2 -> "♛";
				default -> "?";
			};
			case TOUR -> switch (couleur) {
				case 0 -> "♖";
				case 1, 2 -> "♜";
				default -> "?";
			};
			case FOU -> switch (couleur) {
				case 0 -> "♗";
				case 1, 2 -> "♝";
				default -> "?";
			};
			case CAVALIER -> switch (couleur) {
				case 0 -> "♘";
				case 1, 2 -> "♞";
				default -> "?";
			};
			case PION -> switch (couleur) {
				case 0 -> "♙";
				case 1, 2 -> "♟";
				default -> "?";
			};
		};
	}





}