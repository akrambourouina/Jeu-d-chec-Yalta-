package Model;

import java.util.ArrayList;
import java.util.List;

public class Pion extends Piece {
	public Pion(Case position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public List<Case> getDeplacementsPossibles(Plateau plateau) {
		List<Case> deplacements = new ArrayList<>();
		Case depart = this.getPosition();
		int zoneDepart = getZone(depart);

		// Direction d’avance par défaut (dans son territoire)
		Direction avance = Direction.NORD;
		Direction[] captures = switch (joueur.getCouleur()) {
			case 0 -> new Direction[]{Direction.NORD_OUEST, Direction.NORD_EST};
			case 1 -> new Direction[]{Direction.NORD_OUEST, Direction.NORD_EST};
			case 2 -> new Direction[]{Direction.NORD_OUEST, Direction.NORD_EST};
			default -> new Direction[0];
		};

		// Si on est dans un territoire adverse, inverser les directions
		if (isInTerritoireAdverse(zoneDepart)) {
			avance = directionOpposee(avance);
			for (int i = 0; i < captures.length; i++) {
				captures[i] = directionOpposee(captures[i]);
			}
		}

		// Avance normale
		Case suivant = depart.getVoisin(avance);
		if (suivant != null && suivant.estValide() && suivant.getPiece() == null) {
			deplacements.add(suivant);

			// Double pas si case de départ
			boolean estEnCaseDepart = switch (joueur.getCouleur()) {
				case 0 -> position.getColonne() == 10; // blanc
				case 1 -> position.getColonne() == 1;    // rouge
				case 2 -> position.getColonne() == 6;  // noir
				case 3 -> position.getColonne() == 10 && position.getLigne() == 8;
				default -> false;
			};

			if (estEnCaseDepart) {
				Case deuxNord = suivant.getVoisin(Direction.NORD);
				if (deuxNord != null && deuxNord.getPiece() == null) {
					deplacements.add(deuxNord);
				}
			}
		}

		// Captures
		for (Direction dir : captures) {
			Case cible = depart.getVoisin(dir);
			if (cible != null && cible.estValide() && cible.getPiece() != null &&
					cible.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
				deplacements.add(cible);
			}
		}

		if (depart.toString().equals("I5")) {
			Case caseD4 = plateau.getCase(3, 3); // E4
			if (caseD4 != null && caseD4.estValide() &&caseD4.getPiece() != null && caseD4.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
					deplacements.add(caseD4);

			}
		}

		if (depart.toString().equals("I9")) {
			Case caseE4 = plateau.getCase(4, 3); // E4
			if (caseE4 != null && caseE4.estValide() &&caseE4.getPiece() != null && caseE4.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
				deplacements.add(caseE4);

			}
		}

		if (depart.toString().equals("D4")) {
			Case caseE9 = plateau.getCase(4, 8); // E9
			if (caseE9 != null && caseE9.estValide() &&caseE9.getPiece() != null && caseE9.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
				deplacements.add(caseE9);

			}
		}

		if (depart.toString().equals("E4")) {
			Case caseD5 = plateau.getCase(3, 4); // D5
			if (caseD5 != null && caseD5.estValide() &&caseD5.getPiece() != null && caseD5.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
				deplacements.add(caseD5);

			}
		}

		if (depart.toString().equals("D5")) {
			Case caseI9 = plateau.getCase(8, 8); // I9
			if (caseI9 != null && caseI9.estValide() &&caseI9.getPiece() != null && caseI9.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
				deplacements.add(caseI9);

			}
		}

		if (depart.toString().equals("E9")) {
			Case caseI5 = plateau.getCase(8, 4); // I5
			if (caseI5 != null && caseI5.estValide() &&caseI5.getPiece() != null && caseI5.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
				deplacements.add(caseI5);

			}
		}

		return deplacements;
	}

	private boolean isInTerritoireAdverse(int zone) {
		return zone != this.joueur.getCouleur();
	}

	private int getZone(Case c) {
		int ligne = c.getLigne();
		int colonne = c.getColonne();
		if (colonne >= 0 && colonne <= 3 && ligne >= 0 && ligne <= 7) return 1; // Rouge
		if (colonne >= 4 && colonne <= 7 && ((ligne >= 0 && ligne <= 3) || (ligne >= 8 && ligne <= 11))) return 2; // Blanc
		if (colonne >= 8 && colonne <= 11 && ((ligne >= 4 && ligne <= 7) || (ligne >= 8 && ligne <= 11))) return 0; // Noir
		return -1;
	}

	private Direction directionOpposee(Direction dir) {
		return switch (dir) {
			case NORD -> Direction.SUD;
			case SUD -> Direction.NORD;
			case EST -> Direction.OUEST;
			case OUEST -> Direction.EST;
			case NORD_EST -> Direction.SUD_OUEST;
			case NORD_OUEST -> Direction.SUD_EST;
			case SUD_EST -> Direction.NORD_OUEST;
			case SUD_OUEST -> Direction.NORD_EST;
		};
	}

	@Override
	public char getSymbole() {
		return 'P';
	}
}
