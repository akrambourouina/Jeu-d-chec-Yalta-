package Model;

import java.util.List;

class Main {
	public static void main(String[] args) {
		Plateau plateau = new Plateau();

		Case cible = plateau.getCase(0, 0); // E5
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

		Joueur joueur0 = new Joueur("Blanc", 0);   // En bas
		Joueur joueur1 = new Joueur("Rouge", 1);   // À gauche
		Joueur joueur2 = new Joueur("Noir", 2);    // En haut

		plateau.initialiserJoueurBasYalta(joueur0);
		plateau.initialiserJoueurGaucheYalta(joueur1);
		plateau.initialiserJoueurHautYalta(joueur2);

		plateau.afficherPlateauAvecPieces();
	}
}

