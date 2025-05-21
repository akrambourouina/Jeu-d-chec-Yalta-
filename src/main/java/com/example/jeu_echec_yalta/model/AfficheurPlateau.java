package com.example.jeu_echec_yalta.model;

import javafx.scene.layout.Pane;
import com.example.jeu_echec_yalta.model.Case;
import com.example.jeu_echec_yalta.model.Plateau;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
public class AfficheurPlateau {

	public static void afficherPieces(Plateau plateau, Pane root) {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				Case c = plateau.getCase(i, j);
				if (c != null && c.estValide()) {
					String id = "case" + i + "_" + j;
					Label label = (Label) root.lookup("#" + id);
					if (label != null) {
						if (c.getPiece() != null) {
							label.setText(c.getPiece().getSymboleUnicode());
						} else {
							label.setText(""); // vide si pas de pièce
						}
					}
				}
			}
		}
	}
}