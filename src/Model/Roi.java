package Model;

import java.util.ArrayList;
import java.util.List;

public class Roi extends Piece {
	public Roi(Case position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public List<Case> getDeplacementsPossibles(Plateau plateau) {
		List<Case> deplacements = new ArrayList<>();
		for (Direction dir : Direction.values()) {
			int i = position.getLigne() + dir.dLigne;
			int j = position.getColonne() + dir.dColonne;
			Case cible = plateau.getCase(i, j);
			if (cible != null && cible.estValide() && cible.getPiece() == null) {
				deplacements.add(cible);
			}
		}
		return deplacements;
	}

	@Override
	public char getSymbole() {
		return 'R';
	}
}
