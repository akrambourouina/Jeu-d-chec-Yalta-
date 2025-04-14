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
		//Direction avance = null;
		//Direction[] captures = new Direction[2];

		// Le pion avance vers son "NORD" défini dynamiquement
		Case nord = position.getVoisin(Direction.NORD);
		if (nord != null && nord.getPiece() == null) {
			deplacements.add(nord);

			// Test pour avancer de 2 cases depuis la position de départ
			boolean estEnCaseDepart = switch (joueur.getCouleur()) {
				case 0 -> position.getColonne() == 10; // blanc
				case 1 -> position.getColonne() == 1;    // rouge
				case 2 -> position.getColonne() == 6;  // noir
				case 3 -> position.getColonne() == 10 && position.getLigne() == 8;
				default -> false;
			};

			if (estEnCaseDepart) {
				Case deuxNord = nord.getVoisin(Direction.NORD);
				if (deuxNord != null && deuxNord.getPiece() == null) {
					deplacements.add(deuxNord);
				}
			}
		}


		// Captures possibles sur les côtés du "nord"
		// Captures possibles sur les diagonales (en fonction du joueur)
		Direction[] directionsCaptures = switch (joueur.getCouleur()) {
			case 0 -> new Direction[]{Direction.NORD_OUEST, Direction.SUD_OUEST}; // Blanc
			case 1 -> new Direction[]{Direction.SUD_OUEST, Direction.SUD_EST};   // Rouge
			case 2 -> new Direction[]{Direction.NORD_EST, Direction.SUD_EST};   // Noir
			default -> new Direction[0];
		};

		for (Direction dir : directionsCaptures) {
			Case cible = position.getVoisin(dir);
			if (cible != null && cible.getPiece() != null &&
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
