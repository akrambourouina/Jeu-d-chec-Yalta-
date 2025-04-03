package Model;

import java.util.ArrayList;
import java.util.List;

public class Fou extends Piece {
	public Fou(Case position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public List<Case> getDeplacementsPossibles(Plateau plateau) {
		List<Case> deplacements = new ArrayList<>();
		Direction[] directions = { Direction.NORD_EST, Direction.NORD_OUEST, Direction.SUD_EST, Direction.SUD_OUEST };
		for (Direction dir : directions) {
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
		return 'F';
	}
}
