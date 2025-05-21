package com.example.jeu_echec_yalta.model;

public class PieceFactory {
	public static Piece creer(TypePiece type, Case position, Joueur joueur) {
		return switch (type) {
			case TOUR -> new Tour(position, joueur);
			case ROI -> new Roi(position, joueur);
			case FOU -> new Fou(position, joueur);
			case CAVALIER -> new Cavalier(position, joueur);
			case PION -> new Pion(position, joueur);
			case REINE -> new Reine(position, joueur);

		};
	}
}