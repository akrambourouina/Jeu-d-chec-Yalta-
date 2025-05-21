package com.example.jeu_echec_yalta.model;

import java.util.ArrayList;
import java.util.List;

public class Reine extends Piece {
	public Reine(Case position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public List<Case> getDeplacementsPossibles(Plateau plateau) {
		List<Case> deplacements = new ArrayList<>();

		Tour tourTemp = new Tour(this.position, this.joueur, false) {
			@Override
			public char getSymbole() {
				return 'Q'; // Reine
			}
			@Override
			public TypePiece getType() {
				return TypePiece.REINE; // pour l’affichage Unicode
			}
		};
		deplacements.addAll(tourTemp.getDeplacementsPossibles(plateau));

		// Créer un fou temporaire SANS modifier la position réelle
		Fou fouTemp = new Fou(this.position, this.joueur, false) {
			@Override
			public char getSymbole() {
				return 'Q'; // Reine
			}
			@Override
			public TypePiece getType() {
				return TypePiece.REINE; // pour l’affichage Unicode
			}

		};
		deplacements.addAll(fouTemp.getDeplacementsPossibles(plateau));

		// Créer une tour temporaire SANS modifier la position réelle


		return deplacements;
	}


	@Override
	public char getSymbole() {
		return 'Q';
	}
	public TypePiece getType() {
		return TypePiece.REINE;
	}
}
