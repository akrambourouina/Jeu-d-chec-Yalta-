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

	public void initialiserPiecesPourJoueur(Joueur joueur, int ligneBase, int lignePions) {
		List<Piece> pieces = new ArrayList<>();

		// Colonnes 1 à 8 (0 à 7)
		pieces.add(PieceFactory.creer(TypePiece.TOUR, getCase(ligneBase, 0), joueur));
		pieces.add(PieceFactory.creer(TypePiece.CAVALIER, getCase(ligneBase, 1), joueur));
		pieces.add(PieceFactory.creer(TypePiece.FOU, getCase(ligneBase, 2), joueur));
		pieces.add(PieceFactory.creer(TypePiece.REINE, getCase(ligneBase, 3), joueur));
		pieces.add(PieceFactory.creer(TypePiece.ROI, getCase(ligneBase, 4), joueur));
		pieces.add(PieceFactory.creer(TypePiece.FOU, getCase(ligneBase, 5), joueur));
		pieces.add(PieceFactory.creer(TypePiece.CAVALIER, getCase(ligneBase, 6), joueur));
		pieces.add(PieceFactory.creer(TypePiece.TOUR, getCase(ligneBase, 7), joueur));

		// 8 Pions en dessous
		for (int j = 0; j < 8; j++) {
			pieces.add(PieceFactory.creer(TypePiece.PION, getCase(lignePions, j), joueur));
		}

		joueur.setPieces(pieces);
	}


	public void afficherPlateauAvecPieces() {
		for (int i = 0; i < 12; i++) {
			System.out.print((char) ('A' + i) + " ");
			for (int j = 0; j < 12; j++) {
				Case c = cases[i][j];
				if (!c.estValide()) {
					System.out.print("  ");
				} else if (c.getPiece() != null) {
					System.out.print(c.getPiece().getSymbole() + " ");
				} else {
					System.out.print("• ");
				}
			}
			System.out.println();
		}
		System.out.print("  ");
		for (int j = 0; j < 12; j++) {
			System.out.print((j + 1) + " ");
		}
		System.out.println();
	}
}
