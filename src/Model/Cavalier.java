package Model;

import java.util.ArrayList;
import java.util.List;

public class Cavalier extends Piece {
	public Cavalier(Case position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public List<Case> getDeplacementsPossibles(Plateau plateau) {
		List<Case> deplacements = new ArrayList<>();
		int[][] mouvements = {
				{-2, -1}, {-2, 1}, {2, -1}, {2, 1},
				{-1, -2}, {-1, 2}, {1, -2}, {1, 2}
		};
		for (int[] m : mouvements) {
			int i = position.getLigne() + m[0];
			int j = position.getColonne() + m[1];
			Case cible = plateau.getCase(i, j);
			if (cible != null && cible.estValide() && cible.getPiece() == null) {
				deplacements.add(cible);
			}
		}
		return deplacements;
	}

	@Override
	public char getSymbole() {
		return 'C';
	}
}
