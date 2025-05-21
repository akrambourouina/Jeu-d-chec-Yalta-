package com.example.jeu_echec_yalta.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cavalier extends Piece {

	public Cavalier(Case position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public List<Case> getDeplacementsPossibles(Plateau plateau) {
		List<Case> deplacements = new ArrayList<>();
		Case depart = this.getPosition();

		Direction[] directionsTour = {Direction.NORD, Direction.SUD, Direction.EST, Direction.OUEST};
		Direction[] directionsFou = {
				Direction.NORD_EST, Direction.NORD_OUEST,
				Direction.SUD_EST, Direction.SUD_OUEST
		};

		// TOUR → FOU
		for (Direction dirTour : directionsTour) {
			Case caseTour = depart.getVoisin(dirTour);
			if (caseTour == null || !caseTour.estValide()) continue;

			for (Direction dirFou : directionsFou) {
				Case caseFinale = caseTour.getVoisin(dirFou);
				if (caseFinale == null || !caseFinale.estValide()) continue;

				if (estEloignee(depart, caseFinale) && estAccessible(caseFinale)) {
					deplacements.add(caseFinale);
				}


			}
		}

		if (depart.toString().equals("E9")) {
			Case caseI6 = plateau.getCase(8, 5); // I6
			Case caseC4 = plateau.getCase(2, 3); // C4
			if (caseI6 != null && caseI6.estValide()) {
				if (caseI6.getPiece() == null || caseI6.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
					deplacements.add(caseI6);
				}
			}
			if (caseC4 != null && caseC4.estValide()) {
				if (caseC4.getPiece() == null || caseC4.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
					deplacements.add(caseC4);
				}
			}
		}

		return deplacements;
	}




	private boolean estEloignee(Case origine, Case destination) {
		int dL = Math.abs(destination.getLigne() - origine.getLigne());
		int dC = Math.abs(destination.getColonne() - origine.getColonne());
		return dL > 1 || dC > 1; // s'éloigne de la case d'origine
	}

	private boolean estAccessible(Case c) {
		return c.getPiece() == null ||
				c.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur();
	}

	private Direction directionOpposee(Direction dir) {
		return switch (dir) {
			case NORD -> Direction.SUD;
			case SUD -> Direction.NORD;
			case EST -> Direction.OUEST;
			case OUEST -> Direction.EST;
			default -> null;
		};
	}

	private Direction directionOpposeeDiagonale(Direction dir) {
		return switch (dir) {
			case NORD_EST -> Direction.SUD_OUEST;
			case SUD_OUEST -> Direction.NORD_EST;
			case NORD_OUEST -> Direction.SUD_EST;
			case SUD_EST -> Direction.NORD_OUEST;
			default -> null;
		};
	}

	private int getZone(Case c) {
		int ligne = c.getLigne();
		int colonne = c.getColonne();
		if (colonne >= 0 && colonne <= 3 && ligne >= 0 && ligne <= 7) return 1;
		if (colonne >= 4 && colonne <= 7 && ((ligne >= 0 && ligne <= 3) || (ligne >= 8 && ligne <= 11))) return 2;
		if (colonne >= 8 && colonne <= 11 && ((ligne >= 4 && ligne <= 7) || (ligne >= 8 && ligne <= 11))) return 0;
		return -1;
	}

	@Override
	public char getSymbole() {
		return 'C';
	}

	public TypePiece getType() {
		return TypePiece.CAVALIER;
	}
}
