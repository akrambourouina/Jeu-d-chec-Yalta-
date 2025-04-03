package Model;

import java.util.List;

class Main {
	public static void main(String[] args) {
		Plateau plateau = new Plateau();
		plateau.afficherPlateau();

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
		Joueur joueur = new Joueur("Alice", 0);

		// Place les pièces du joueur sur les lignes A et B
		plateau.initialiserPiecesPourJoueur(joueur, 0, 1);

		// Affiche le plateau avec les pièces
		plateau.afficherPlateauAvecPieces();
	}
}

