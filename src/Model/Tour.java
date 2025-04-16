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
				Case suivant = courant.getVoisin(directionActuelle);
				if (suivant == null || !suivant.estValide()) break;

				if (suivant.getPiece() != null) {
					if (suivant.getPiece().getJoueur() != this.joueur) {
						deplacements.add(suivant); // capture
					}
					break;
				}
				if(!deplacements.contains(suivant))
				{deplacements.add(suivant);}
				courant = suivant;

				// Inverser direction UNE FOIS à l’entrée dans territoire adverse
				if (!dejaInverse && estDansTerritoireAdverse(courant)) {
					directionActuelle = directionOpposee(directionActuelle);
					dejaInverse = true;
				}
			}
		}

		return deplacements;
	}

	// Méthode pour déterminer si une case est dans le territoire adverse
	private boolean estDansTerritoireAdverse(Case c) {
		int ligne = c.getLigne();
		int colonne = c.getColonne();

		// Exemple de définition du territoire adverse pour le joueur 0
		if (this.joueur.getCouleur() == 0) {
			return  (0<=c.getLigne()&&c.getLigne()<4||c.getLigne()>3&&c.getLigne()<8&&c.getColonne()<8||c.getLigne()>7 &&c.getLigne()<12 && c.getColonne()<8);

		} else {
			// Définir le territoire adverse pour le joueur 1
			return (ligne >= 0 && ligne <= 3 && colonne >= 0 && colonne <= 3);
		}
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
