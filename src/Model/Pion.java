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
		int l = position.getLigne();
		int c = position.getColonne();

		// Détection de la zone centrale (croisement)
		boolean zoneCentrale = (
				(l >= 4 && l <= 7 && c >= 4 && c <= 7) || // cœur central
						(l == 8 && c >= 4 && c <= 7) || // entrée du bas
						(l == 3 && c >= 4 && c <= 7) || // entrée du haut
						(c == 4 && l >= 4 && l <= 7) || // entrée gauche
						(c == 7 && l >= 4 && l <= 7)    // entrée droite
		);

		// Direction personnalisée selon position et joueur
		int dirL = 0, dirC = 0;
		switch (joueur.getCouleur()) {
			case 0 -> { // Blanc
				if (zoneCentrale) dirC = -1;  // vers le haut
				else dirC = -1;               // vers la gauche
			}
			case 1 -> { // Rouge
				if (zoneCentrale) dirC = 1;   // vers la droite
				else dirC = 1;                // vers le bas
			}
			case 2 -> { // Noir
				if (zoneCentrale) dirC = 1;   // vers le bas
				else dirC = -1;               // vers la gauche
			}
		}

		// Avance simple
		Case avant = plateau.getCase(l + dirL, c + dirC);
		if (avant != null && avant.estValide() && avant.getPiece() == null) {
			deplacements.add(avant);

			// Avance de 2 cases si sur ligne de départ
			boolean caseDepart = switch (joueur.getCouleur()) {
				case 0 -> !zoneCentrale && c == 10; // Blancs à droite
				case 1 -> !zoneCentrale && l == 0;  // Rouges en haut
				case 2 -> !zoneCentrale && c == 6;  // Noirs à gauche
				default -> false;
			};
			Case deuxAvance = plateau.getCase(l + 2 * dirL, c + 2 * dirC);
			if (caseDepart && deuxAvance != null && deuxAvance.estValide() && deuxAvance.getPiece() == null) {
				deplacements.add(deuxAvance);
			}
		}

		// Captures diagonales normales
		int[][] diagonales = {
				{dirL - 1, dirC}, {dirL + 1, dirC},
				{dirL, dirC - 1}, {dirL, dirC + 1}
		};

		for (int[] d : diagonales) {
			Case cible = plateau.getCase(l + d[0], c + d[1]);
			if (cible != null && cible.estValide() && cible.getPiece() != null &&
					cible.getPiece().getJoueur() != joueur) {
				deplacements.add(cible);
			}
		}

		return deplacements;
	}

	@Override
	public char getSymbole() {
		return 'P';
	}
}
