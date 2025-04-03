package Model;

import java.util.List;

class Main {
	public static void main(String[] args) {
		Plateau plateau = new Plateau();
		plateau.afficherPlateau();

		Case cible = plateau.getCase(4, 4); // par exemple E5
		System.out.println("Case cible : " + cible);

		List<Case> voisins = plateau.getVoisins(cible);
		System.out.println("Voisins valides :");
		for (Case v : voisins) {
			System.out.println(" - " + v);
		}
	}
}

