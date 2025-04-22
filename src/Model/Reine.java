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

		Fou fouTemp = new Fou(this.position, this.joueur);
		deplacements.addAll(fouTemp.getDeplacementsPossibles(plateau));

		Tour tourTemp = new Tour(this.position, this.joueur);
		deplacements.addAll(tourTemp.getDeplacementsPossibles(plateau));

		return deplacements;
	}

	@Override
	public char getSymbole() {
		return 'Q';
	}
}
