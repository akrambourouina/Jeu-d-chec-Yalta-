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

		// Orientation selon le joueur
		int directionL = 0;
		int directionC = 0;

		switch (joueur.getCouleur()) {
			case 0: directionC = -1; break; // Blancs (vers la gauche)
			case 1: directionC = 1; break;  // Rouges (vers le bas)
			case 2: directionC = -1; break;  // Noirs (vers la droite)
		}

		// Avance simple
		Case avant = plateau.getCase(l + directionL, c + directionC);
		if (avant != null && avant.estValide() && avant.getPiece() == null) {
			deplacements.add(avant);
		}

		// Captures diagonales
		int[][] diagonales = {
				{directionL - 1, directionC},
				{directionL + 1, directionC}
		};

		for (int[] d : diagonales) {
			Case diag = plateau.getCase(l + d[0], c + d[1]);
			if (diag != null && diag.estValide() && diag.getPiece() != null &&
					diag.getPiece().getJoueur() != joueur) {
				deplacements.add(diag);
			}
		}

		System.out.println("Test pion " + joueur.getCouleur() + " en " + position);
		System.out.println("Case avant = " + (l + directionL) + "," + (c + directionC));
		System.out.println("Case trouvée : " + avant);
		System.out.println("Est valide ? " + (avant != null && avant.estValide()));
		System.out.println("Occupée ? " + (avant != null && avant.getPiece() != null));


		return deplacements;
	}


	@Override
	public char getSymbole() {
		return 'P';
	}


}
