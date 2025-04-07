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
				{1,1,1,1,1,1,1,1,0,0,0,0},
				{1,1,1,1,1,1,1,1,0,0,0,0},
				{1,1,1,1,1,1,1,1,0,0,0,0},
				{1,1,1,1,1,1,1,1,0,0,0,0},
				{1,1,1,1,0,0,0,0,1,1,1,1},
				{1,1,1,1,0,0,0,0,1,1,1,1},
				{1,1,1,1,0,0,0,0,1,1,1,1},
				{1,1,1,1,0,0,0,0,1,1,1,1},
				{0,0,0,0,1,1,1,1,1,1,1,1},
				{0,0,0,0,1,1,1,1,1,1,1,1},
				{0,0,0,0,1,1,1,1,1,1,1,1},
				{0,0,0,0,1,1,1,1,1,1,1,1}
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

	public void initialiserVoisins() {
		for (int ligne = 0; ligne < 12; ligne++) {
			for (int col = 0; col < 12; col++) {
				Case c = getCase(ligne, col);
				if (c == null || !c.estValide()) continue;

				// Adaptation des directions au système personnalisé :
				for (Direction dir : Direction.values()) {
					int ni = ligne;
					int nj = col;

					// Dans ce système, NORD/SUD affectent les colonnes
					// et EST/OUEST affectent les lignes
					switch (dir) {
						case NORD -> nj = col + 1;
						case SUD -> nj = col - 1;
						case EST -> ni = ligne + 1;
						case OUEST -> ni = ligne - 1;
						case NORD_EST -> {
							ni = ligne + 1;
							nj = col + 1;
						}
						case NORD_OUEST -> {
							ni = ligne - 1;
							nj = col + 1;
						}
						case SUD_EST -> {
							ni = ligne + 1;
							nj = col - 1;
						}
						case SUD_OUEST -> {
							ni = ligne - 1;
							nj = col - 1;
						}
					}

					Case voisine = getCase(ni, nj);
					if (voisine != null && voisine.estValide()) {
						c.setVoisin(dir, voisine);
					}
				}

				// Connexions spéciales de la zone centrale :
				//E4
				if (ligne >= 4 && ligne <= 7 && col == 3) {
					c.setVoisin(Direction.NORD, getCase(4, 8));
					c.setVoisin(Direction.SUD, getCase(4, 2));
					c.setVoisin(Direction.EST, getCase(5, 3));
					c.setVoisin(Direction.OUEST, getCase(3, 3));
				}
				// I9
				if (ligne >= 8 && ligne <= 11 && col == 8) {
					c.setVoisin(Direction.NORD, getCase(8, 4));
					c.setVoisin(Direction.SUD, getCase(8, 9));
					c.setVoisin(Direction.EST, getCase(9, 8));
					c.setVoisin(Direction.OUEST, getCase(4, 8));

				}
				// I5
				if (ligne == 8 && col ==4) {
					c.setVoisin(Direction.NORD, getCase(4, 3));
					c.setVoisin(Direction.SUD, getCase(8, 5));
					c.setVoisin(Direction.EST, getCase(3, 4));
					c.setVoisin(Direction.OUEST, getCase(9, 4));

				}
				//E9
				if (ligne == 4 && col == 8) {
					c.setVoisin(Direction.NORD, getCase(4, 3));
					c.setVoisin(Direction.SUD, getCase(4, 9));
					c.setVoisin(Direction.EST, getCase(8, 8));
					c.setVoisin(Direction.OUEST, getCase(5,8 ));
				}
				//D5
				if (ligne == 3 && col == 4) {
					c.setVoisin(Direction.NORD, getCase(3, 3));
					c.setVoisin(Direction.SUD, getCase(3, 5));
					c.setVoisin(Direction.EST, getCase(2, 4));
					c.setVoisin(Direction.OUEST, getCase(8,4 ));
				}
				//D4
				if (ligne == 3 && col == 3) {
					c.setVoisin(Direction.NORD, getCase(3, 4));
					c.setVoisin(Direction.SUD, getCase(3, 2));
					c.setVoisin(Direction.EST, getCase(2, 3));
					c.setVoisin(Direction.OUEST, getCase(4,3));
				}


			}
		}
	}




	public void initialiserJoueurBasYalta(Joueur joueur) {
		List<Piece> pieces = new ArrayList<>();

		// Lignes E à L (4 à 11)
		// Colonne 11 = pièces principales
		// Colonne 10 = pions

		// Ordre d'en bas vers le haut (L -> E)
		int[] lignes = {11, 10, 9, 8, 7, 6, 5, 4}; // L, K, J, I, H, G, F, E
		TypePiece[] ordre = {
				TypePiece.TOUR,
				TypePiece.CAVALIER,
				TypePiece.FOU,
				TypePiece.REINE,
				TypePiece.TOUR,
				TypePiece.CAVALIER,
				TypePiece.FOU,
				TypePiece.ROI
		};

		for (int i = 0; i < 8; i++) {
			// pièces principales en colonne 11
			pieces.add(PieceFactory.creer(ordre[i], getCase(lignes[i], 11), joueur));
			// pions en colonne 10
			pieces.add(PieceFactory.creer(TypePiece.PION, getCase(lignes[i], 10), joueur));
		}
		pieces.add(PieceFactory.creer(TypePiece.PION, getCase(8, 8), joueur));

		joueur.setPieces(pieces);
	}


	public void initialiserJoueurGaucheYalta(Joueur joueur) {
		List<Piece> pieces = new ArrayList<>();
		int[] lignes = {7,6,5,4,3,2,1,0};
		TypePiece[] ordre = {TypePiece.TOUR, TypePiece.CAVALIER, TypePiece.FOU, TypePiece.REINE, TypePiece.ROI, TypePiece.FOU, TypePiece.CAVALIER, TypePiece.TOUR};
		for (int i = 0; i < 8; i++) {
			pieces.add(PieceFactory.creer(ordre[i], getCase(lignes[i], 0), joueur));
			pieces.add(PieceFactory.creer(TypePiece.PION, getCase(lignes[i], 1), joueur));
		}
		joueur.setPieces(pieces);
	}

	public void initialiserJoueurHautYalta(Joueur joueur) {
		List<Piece> pieces = new ArrayList<>();
		//int ligneBase = 0, lignePions = 1;
		int[] lignes = {11,10,9,8,3,2,1,0};
		TypePiece[] ordre = {TypePiece.TOUR, TypePiece.CAVALIER, TypePiece.FOU, TypePiece.REINE, TypePiece.ROI, TypePiece.FOU, TypePiece.CAVALIER, TypePiece.TOUR};
		for (int i = 0; i < 8; i++) {
			//pieces.add(PieceFactory.creer(ordre[i], getCase(ligneBase, colonnes[i]), joueur));
			//pieces.add(PieceFactory.creer(TypePiece.PION, getCase(lignePions, colonnes[i]), joueur));
			pieces.add(PieceFactory.creer(ordre[i], getCase(lignes[i], 7), joueur));
			// pions en colonne 10
			pieces.add(PieceFactory.creer(TypePiece.PION, getCase(lignes[i], 6), joueur));
		}
		joueur.setPieces(pieces);
	}

	public void afficherPlateauAvecPieces() {
		for (int i = 0; i < 12; i++) {
			System.out.print((char) ('A' + i) + " ");
			for (int j = 0; j < 12; j++) {
				Case c = cases[i][j];
				if (!c.estValide()) {
					System.out.print("   ");
				} else if (c.getPiece() != null) {
					System.out.print(String.format("%-3s", c.getPiece().getSymboleAvecJoueur()));
				} else {
					System.out.print("•  ");
				}
			}
			System.out.println();
		}
		System.out.print("   ");
		for (int j = 0; j < 12; j++) {
			System.out.print((j + 1) + (j + 1 < 10 ? "  " : " "));
		}
		System.out.println();
	}
}