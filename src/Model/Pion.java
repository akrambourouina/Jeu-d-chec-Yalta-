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
		int direction = -1; // dépendra du joueur
		int i = position.getLigne() + direction;
		int j = position.getColonne();
		Case avant = plateau.getCase(i, j);
		if (avant != null && avant.estValide() && avant.getPiece() == null) {
			deplacements.add(avant);
		}
		return deplacements;
	}

	@Override
	public char getSymbole() {
		return 'P';
	}
}
