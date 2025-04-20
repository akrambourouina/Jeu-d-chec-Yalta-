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

		System.out.println(">>> Tour en " + position + " (" + getSymboleAvecJoueur() + ")");

		for (Direction dirInitiale : directions) {
			System.out.println(">> Direction de départ : " + dirInitiale);
			Case courant = this.position;
			Direction directionActuelle = dirInitiale;
			boolean dejaInverse = false;

			while (true) {
				boolean dansAdverse = estDansTerritoireAdverse(courant);
				System.out.println("   - Case actuelle : " + courant);
				System.out.println("   - Dans territoire adverse ? " + dansAdverse);

				Direction dirLogique = directionLocale(dirInitiale, dansAdverse);
				System.out.println("   - Direction logique : " + dirLogique);

				Case suivant = courant.getVoisin(dirLogique);
				if (suivant == null || !suivant.estValide()) {
					System.out.println("   ❌ Case suivante invalide → arrêt");
					break;
				}

				System.out.println("   ✅ Avance vers : " + suivant);

				if (suivant.getPiece() != null) {
					if (suivant.getPiece().getJoueur() != this.joueur) {
						System.out.println("   ⚔️ Capture sur " + suivant);
						deplacements.add(suivant);
					} else {
						System.out.println("   🛑 Bloqué par une pièce alliée en : " + suivant);
					}
					break;
				}

				deplacements.add(suivant);
				System.out.println("   ➕ Ajout : " + suivant);

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
