package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tour extends Piece {
	public Tour(Case position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public List<Case> getDeplacementsPossibles(Plateau plateau) {
		List<Case> deplacements = new ArrayList<>();
		Direction[] directions = {Direction.NORD, Direction.SUD, Direction.EST, Direction.OUEST};
		Case caseDepart = this.getPosition();
		int zoneInitiale = getZone(caseDepart); // zone du point de départ


		for (Direction dirInitiale : directions) {
			Direction dir = dirInitiale;
			Case courant = caseDepart;
			boolean dejaInverse = false;
			Set<Case> dejaVues = new HashSet<>();


			while (true) {
				Case suivant = courant.getVoisin(dir);
				if (suivant == null || !suivant.estValide()) {
					break;
				}

				if (dejaVues.contains(suivant)) {
					break;
				}
				dejaVues.add(suivant);

				int zoneSuivante = getZone(suivant);

				// Inverser direction une seule fois si on change de territoire
				if (!dejaInverse && zoneSuivante != zoneInitiale && zoneSuivante != -1) {
					dir = directionOpposee(dir);
					dejaInverse = true;

					// Vérifie et ajoute la case d’inversion si valide
					if (suivant.getPiece() == null) {
						deplacements.add(suivant);
					} else if (suivant.getPiece().getJoueur() != this.joueur) {
						deplacements.add(suivant);
					}
					courant = suivant;
					continue;
				}


				if (suivant.getPiece() != null) {
					if (suivant.getPiece().getJoueur() == this.joueur) {
						break;
					} else {
						deplacements.add(suivant);
						break;
					}
				}

				deplacements.add(suivant);
				courant = suivant;
			}
		}

		return deplacements;
	}









	// Méthode pour déterminer si une case est dans le territoire adverse
	/*private boolean estDansTerritoireAdverse(Case c) {
		int ligne=c.getLigne();
		int colonne=c.getColonne();
		if (this.joueur.getCouleur() == 0) {
			boolean zoneJoueurNoir = (ligne >= 8 && ligne < 12 && colonne > 7) || (ligne >= 4 && ligne < 8 && colonne > 7);
			return !zoneJoueurNoir;
		} else if (this.joueur.getCouleur() == 1) {
			return !(ligne >= 0 && ligne <= 7 && colonne >= 0 && colonne <= 3);
		}else if (this.joueur.getCouleur() == 2) {
			boolean zoneJoueurRouge = (ligne >= 8 && ligne < 12 && colonne >= 4 && colonne < 8) || (ligne >= 0 && ligne < 4 && colonne >= 4 && colonne < 8);
			return !zoneJoueurRouge;
		}
		return false;
	}*/

	// Méthode pour déterminer si une case est dans le territoire adverse
/*	private boolean estDansTerritoireAdverse(Case c) {
		int ligne=c.getLigne();
		int colonne=c.getColonne();
		if (this.joueur.getCouleur() == 0) {
			return  (0<=c.getLigne()&&c.getLigne()<4||c.getLigne()>3&&c.getLigne()<8&&c.getColonne()<4||c.getLigne()>7 &&c.getLigne()<12 && c.getColonne()<8);

		} else if (this.joueur.getCouleur() == 1) {
			return !(ligne >= 0 && ligne <= 7 && colonne >= 0 && colonne <= 3);
		}else if (this.joueur.getCouleur() == 2) {
			return  (3<c.getLigne()&&c.getLigne()<8||c.getLigne()>=0&&c.getLigne()<4&&c.getColonne()<4||c.getLigne()>7 &&c.getLigne()<12 && c.getColonne()>7||c.getLigne()>7 &&c.getLigne()<12 && c.getColonne()<4);
		}
		return false;
	}*/


	private int getZone(Case c) {
		int ligne = c.getLigne();
		int colonne = c.getColonne();


		if (colonne >= 0 && colonne <= 3 && ligne >= 0 && ligne <= 7) {
			return 1;
		}


		if (colonne >= 4 && colonne <= 7 && (
				(ligne >= 0 && ligne <= 3) ||
						(ligne >= 8 && ligne <= 11)
		)) {
			return 2;
		}


		if (colonne >= 8 && colonne <= 11 && (
				(ligne >= 4 && ligne <= 7) ||
						(ligne >= 8 && ligne <= 11)
		)) {
			return 0;
		}

		return -1;
	}


	private Direction directionOpposee(Direction dir) {
		return switch (dir) {
			case NORD -> Direction.SUD;
			case SUD -> Direction.NORD;
			case EST -> Direction.OUEST;
			case OUEST -> Direction.EST;
			default -> null; // les diagonales ne sont pas concernées
		};
	}

	@Override
	public char getSymbole() {
		return 'T';
	}
}
