package Model;

import java.util.ArrayList;
import java.util.List;

public class Reine extends Piece {
	public Reine(Case position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public List<Case> getDeplacementsPossibles(Plateau plateau) {
		List<Case> deplacements = new ArrayList<>();
		for (Direction dir : Direction.values()) {
			int i = position.getLigne() + dir.dLigne;
			int j = position.getColonne() + dir.dColonne;
			while (true) {
				Case cible = plateau.getCase(i, j);
				if (cible == null || !cible.estValide() || cible.getPiece() != null) break;
				deplacements.add(cible);
				i += dir.dLigne;
				j += dir.dColonne;
			}
		}
		return deplacements;
	}

	@Override
	public char getSymbole() {
		return 'Q';
	}
}
