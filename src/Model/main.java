package Model;

import java.util.List;

class Main {
	public static void main(String[] args) {
		Plateau plateau = new Plateau();

		Case cible = plateau.getCase(0, 0); // A1
		if (cible != null && cible.estValide()) {
			System.out.println("Case cible : " + cible);
			List<Case> voisins = plateau.getVoisins(cible);
			System.out.println("Voisins valides :");
			for (Case v : voisins) {
				System.out.println(" - " + v);
			}
		} else {
			System.out.println("La case E5 est invalide !");
		}
/*
		Joueur joueur0 = new Joueur("Blanc", 0);   // En bas
		Joueur joueur1 = new Joueur("Rouge", 1);   // À gauche
		Joueur joueur2 = new Joueur("Noir", 2);    // En haut

		plateau.initialiserJoueurBasYalta(joueur0);
		plateau.initialiserJoueurGaucheYalta(joueur1);
		plateau.initialiserJoueurHautYalta(joueur2);

		plateau.afficherPlateauAvecPieces();

		Piece pion = plateau.getCase(7, 10).getPiece(); // Un pion rouge
		List<Case> moves = pion.getDeplacementsPossibles(plateau);
		System.out.println("Déplacements possibles du pion " + pion.getSymboleAvecJoueur() + " en " + pion.getPosition() + " :");
		for (Case c : moves) {
			System.out.println(" - " + c); // toString() appelé ici
		}*/


		Joueur joueur0 = new Joueur("Blanc", 0);   // En bas
		Joueur joueur1 = new Joueur("Rouge", 1);   // À gauche
		Joueur joueur2 = new Joueur("Noir", 2);    // En haut

		plateau.initialiserJoueurBasYalta(joueur0);
		plateau.initialiserJoueurGaucheYalta(joueur1);
		plateau.initialiserJoueurHautYalta(joueur2);

		plateau.afficherPlateauAvecPieces();

		System.out.println("\n===== TEST DES DÉPLACEMENTS DES PIONS =====");

		// ⚪ Pion blanc (doit aller vers la gauche)
		//testDeplacementPion(plateau, 8, 8);  // pion blanc en I11

		// 🔴 Pion rouge (doit aller vers le bas)
		//testDeplacementPion(plateau, 6, 1);   // pion rouge en G2

		// ⚫ Pion noir (doit aller vers la droite)
		//testDeplacementPion(plateau, 2, 6);   // pion noir en C7

		plateau.initialiserVoisins();

		Case caseTest = plateau.getCase(9, 5); // I9

		System.out.println("Case test : " + caseTest);

		for (Direction dir : Direction.values()) {
			Case voisin = caseTest.getVoisin(dir);
			if (voisin != null) {
				System.out.println(" - " + dir + " → " + voisin);
			}

		}


		/////////////////////////////getdeplacement

		// On place une pièce ennemie à I9
		Case cible1 = plateau.getCase(8, 9);
		cible1.setPiece(new Fou(cible1, new Joueur("Rouge", 1))); // Un fou ennemi


		//Piece pion = plateau.getCase(9, 10).getPiece(); // pion blanc
		//List<Case> moves = pion.getDeplacementsPossibles(plateau);
		//System.out.println("Déplacements possibles de " + pion.getSymboleAvecJoueur() + " en " + pion.getPosition() + " :");
		//for (Case c : moves) {
		//	System.out.println(" - " + c);
		//}


// Placer une tour blanche en E4
		Case caseTour = plateau.getCase(10, 5); // E4
		Tour tour = new Tour(caseTour, joueur0);
		caseTour.setPiece(tour);

// Test des déplacements
		List<Case> deplacementsTour = tour.getDeplacementsPossibles(plateau);
		System.out.println("\n===== TEST DES DÉPLACEMENTS DE LA TOUR =====");
		System.out.println("Tour en " + caseTour + " (" + tour.getSymboleAvecJoueur() + ") peut aller sur :");
		for (Case c : deplacementsTour) {
			System.out.println(" - " + c);
		}

	}
/*
	public static void testDeplacementPion(Plateau plateau, int ligne, int colonne) {
		Case casePion = plateau.getCase(ligne, colonne);
		Piece piece = casePion.getPiece();

		if (piece == null || !(piece instanceof Pion)) {
			System.out.println("Aucun pion à tester en " + casePion);
			return;
		}

		List<Case> deplacements = piece.getDeplacementsPossibles(plateau);
		System.out.println("Déplacements possibles du pion " + piece.getSymboleAvecJoueur() + " en " + casePion + " :");
		if (deplacements.isEmpty()) {
			System.out.println(" - Aucun déplacement possible");
		} else {
			for (Case c : deplacements) {
				System.out.println(" - " + c);
			}
		}
		System.out.println();





	}*/


}

