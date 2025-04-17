package Model;

import java.util.ArrayList;
import java.util.List;

public class Tour extends Piece {
	public Tour(Case position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public List<Case> getDeplacementsPossibles(Plateau plateau) {
		List<Case> deplacements = new ArrayList<>();//list
		Direction[] directions = {Direction.NORD, Direction.SUD, Direction.EST, Direction.OUEST};

		for (Direction dirInitiale : directions) {
			Case courant = this.position;
			Direction directionActuelle = dirInitiale;
			boolean dejaInverse = false;

			while (true) {
				Direction dirLogique = directionLocale(dirInitiale, estDansTerritoireAdverse(courant));
				Case suivant = courant.getVoisin(dirLogique);

				if (suivant == null || !suivant.estValide()) break;

				if (suivant.getPiece() != null) {
					if (suivant.getPiece().getJoueur() != this.joueur) {
						deplacements.add(suivant);
					}
					break;
				}

				deplacements.add(suivant);
				courant = suivant;
			}

		}

		return deplacements;
	}
	private Direction directionLocale(Direction globale, boolean inverse) {
		return inverse ? directionOpposee(globale) : globale;
	}


	// Méthode pour déterminer si une case est dans le territoire adverse
	private boolean estDansTerritoireAdverse(Case c) {
		if (this.joueur.getCouleur() == 0) {
			return  (0<=c.getLigne()&&c.getLigne()<4||c.getLigne()>3&&c.getLigne()<8&&c.getColonne()<8||c.getLigne()>7 &&c.getLigne()<12 && c.getColonne()<8);

		} else if (this.joueur.getCouleur() == 1) {
			return  (0<=c.getLigne()&&c.getLigne()<4&&c.getColonne()>=4&&c.getColonne()<8||c.getLigne()>7&&c.getLigne()<12&&c.getColonne()>7||c.getLigne()>3 &&c.getLigne()<8 && c.getColonne()>7);
		}else if (this.joueur.getCouleur() == 2) {
			return  (3<c.getLigne()&&c.getLigne()<8&& c.getColonne()<4||c.getLigne()>=0&&c.getLigne()<4&&c.getColonne()<4||c.getLigne()>7 &&c.getLigne()<12 && c.getColonne()>7);
		}
		return false;
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
