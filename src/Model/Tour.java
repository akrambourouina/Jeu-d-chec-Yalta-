package Model;

import java.util.ArrayList;
import java.util.List;

public class Tour extends Piece {
	public Tour(Case position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public List<Case> getDeplacementsPossibles(Plateau plateau) {
		List<Case> deplacements = new ArrayList<>();
		Direction[] directions = {Direction.NORD, Direction.SUD, Direction.EST, Direction.OUEST};

		for (Direction dir : directions) {
			Case courant = this.position;
			Direction entree = directionOpposee(dir);

			while (true) {
				Case suivant = courant.getVoisin(dir);
				if (suivant == null || !suivant.estValide()) break;

				// Vérifie que tu es bien ressorti du suivant par l’opposé de là où tu es rentré
				if (suivant.getVoisin(entree) != courant) break;

				// Si la case a une pièce
				if (suivant.getPiece() != null) {
					if (suivant.getPiece().getJoueur() != this.joueur) {
						deplacements.add(suivant); // capture
					}
					break;
				}

				deplacements.add(suivant);
				courant = suivant;
			}
		}

		return deplacements;
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
