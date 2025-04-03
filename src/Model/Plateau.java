package Model;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
	private Case[][] cases;

	public Plateau() {
		cases = new Case[12][12];
		initialiserCases();
	}

	private void initialiserCases() {
		int[][] grilleValide = {
              // 1 2 3 4 5 6 7 8 910 1112
				{1,1,1,1,1,1,1,1,0,0,0,0},//a
				{1,1,1,1,1,1,1,1,0,0,0,0},//b
				{1,1,1,1,1,1,1,1,0,0,0,0},//c
				{1,1,1,1,1,1,1,1,0,0,0,0},//d
				{1,1,1,1,0,0,0,0,1,1,1,1},//e
				{1,1,1,1,0,0,0,0,1,1,1,1},//f
				{1,1,1,1,0,0,0,0,1,1,1,1},//g
				{1,1,1,1,0,0,0,0,1,1,1,1},//h
				{0,0,0,0,1,1,1,1,1,1,1,1},//i
				{0,0,0,0,1,1,1,1,1,1,1,1},//j
				{0,0,0,0,1,1,1,1,1,1,1,1},//k
				{0,0,0,0,1,1,1,1,1,1,1,1}//l
		};

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				boolean valide = grilleValide[i][j] == 1;
				cases[i][j] = new Case(i, j, valide);
			}
		}
	}

	public Case getCase(int ligne, int colonne) {
		if (ligne < 0 || ligne >= 12 || colonne < 0 || colonne >= 12) return null;
		return cases[ligne][colonne];
	}

	public void afficherPlateau() {
		for (int i = 0; i < 12; i++) {
			System.out.print((char)('A' + i) + " ");
			for (int j = 0; j < 12; j++) {
				System.out.print(cases[i][j].estValide() ? "• " : "  ");
			}
			System.out.println();
		}
		System.out.print("  ");
		for (int j = 0; j < 12; j++) {
			System.out.print((j + 1) + " ");
		}
		System.out.println();
	}

	public List<Case> getVoisins(Case c) {
		List<Case> voisins = new ArrayList<>();

		for (Direction dir : Direction.values()) {
			int i = c.getLigne() + dir.dLigne;
			int j = c.getColonne() + dir.dColonne;
			Case voisine = getCase(i, j);
			if (voisine != null && voisine.estValide()) {
				voisins.add(voisine);
			}
		}
		return voisins;
	}
}
