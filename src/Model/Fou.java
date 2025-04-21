package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fou extends Piece {
	public Fou(Case position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public List<Case> getDeplacementsPossibles(Plateau plateau) {
		List<Case> deplacements = new ArrayList<>();
		Direction[] directions = {
				Direction.NORD_EST, Direction.NORD_OUEST,
				Direction.SUD_EST, Direction.SUD_OUEST
		};

		Case caseDepart = this.getPosition();
		int zoneInitiale = getZone(caseDepart);

		for (Direction dirInitiale : directions) {
			Direction dir = dirInitiale;
			Case courant = caseDepart;
			boolean dejaInverse = false;
			Set<Case> dejaVues = new HashSet<>();

			while (true) {
				Case suivant = courant.getVoisin(dir);
				if (suivant == null || !suivant.estValide()) break;

				if (dejaVues.contains(suivant)) break;
				dejaVues.add(suivant);

				int zoneSuivante = getZone(suivant);

				if (!dejaInverse && zoneSuivante != zoneInitiale && zoneSuivante != -1) {
					dir = directionOpposeeDiagonale(dir);
					dejaInverse = true;

					if (suivant.getPiece() == null) {
						deplacements.add(suivant);
					} else if (suivant.getPiece().getJoueur() != this.joueur) {
						deplacements.add(suivant);
					}
					courant = suivant;
					continue;
				}

				if (suivant.getPiece() != null) {
					if (suivant.getPiece().getJoueur() == this.joueur) break;
					else {
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

		if (colonne >= 4 && colonne <= 7 && (
				(ligne >= 0 && ligne <= 3) || (ligne >= 8 && ligne <= 11))) return 2;

		if (colonne >= 8 && colonne <= 11 && (
				(ligne >= 4 && ligne <= 7) || (ligne >= 8 && ligne <= 11))) return 0;

		return -1;
	}

	@Override
	public char getSymbole() {
		return 'F';
	}
}
