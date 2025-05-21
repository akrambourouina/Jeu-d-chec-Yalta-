package com.example.jeu_echec_yalta.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Roi extends Piece {
	public Roi(Case position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public List<Case> getDeplacementsPossibles(Plateau plateau) {
		List<Case> deplacements = new ArrayList<>();
		Case caseDepart = this.getPosition();
		int zoneInitiale = getZone(caseDepart);


		for (Direction dirInitiale : Direction.values()) {
			Direction dir = dirInitiale;

			if (zoneInitiale != getZoneForJoueur(joueur)) {
				dir = directionOpposee(dir);
			}

			Case voisin = caseDepart.getVoisin(dir);
			if (voisin == null || !voisin.estValide()) {
				continue;
			}


			if (voisin.getPiece() != null) {
				if (voisin.getPiece().getJoueur().getCouleur() == this.joueur.getCouleur()) {
					continue;
				} else {
					deplacements.add(voisin);
				}
			} else {
				deplacements.add(voisin);
			}
		}
		// Cas spécial : D4 doit accéder à E9 (comme un 2ème NORD_EST)
		if (caseDepart.toString().equals("D4")) {
			Case caseE9 = plateau.getCase(4, 8); // E9
			if (caseE9 != null && caseE9.estValide()) {
				if (caseE9.getPiece() == null || caseE9.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
					deplacements.add(caseE9);
				}
			}
		}

		if (caseDepart.toString().equals("E4")) {
			Case caseE9 = plateau.getCase(3, 4);
			if (caseE9 != null && caseE9.estValide()) {
				if (caseE9.getPiece() == null || caseE9.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
					deplacements.add(caseE9);
				}
			}
		}

		if (caseDepart.toString().equals("E9")) {
			Case caseE9 = plateau.getCase(8, 4);
			if (caseE9 != null && caseE9.estValide()) {
				if (caseE9.getPiece() == null || caseE9.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
					deplacements.add(caseE9);
				}
			}
		}

		if (caseDepart.toString().equals("I9")) {
			Case caseE9 = plateau.getCase(4, 3);
			if (caseE9 != null && caseE9.estValide()) {
				if (caseE9.getPiece() == null || caseE9.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
					deplacements.add(caseE9);
				}
			}
		}

		if (caseDepart.toString().equals("I5")) {
			Case caseE9 = plateau.getCase(3, 3);
			if (caseE9 != null && caseE9.estValide()) {
				if (caseE9.getPiece() == null || caseE9.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
					deplacements.add(caseE9);
				}
			}
		}

		if (caseDepart.toString().equals("D5")) {
			Case caseE9 = plateau.getCase(8, 8);
			if (caseE9 != null && caseE9.estValide()) {
				if (caseE9.getPiece() == null || caseE9.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
					deplacements.add(caseE9);
				}
			}
		}


		return deplacements;
	}



	private int getZone(Case c) {
		int ligne = c.getLigne();
		int colonne = c.getColonne();


		if (colonne >= 0 && colonne <= 3 && ligne >= 0 && ligne <= 7) {
			return 1;
		}


		if (colonne >= 4 && colonne <= 7 && (
				(ligne >= 0 && ligne <= 3) ||
						(ligne >= 8 && ligne <= 11)
		)) {
			return 2;
		}


		if (colonne >= 8 && colonne <= 11 && (
				(ligne >= 4 && ligne <= 7) ||
						(ligne >= 8 && ligne <= 11)
		)) {
			return 0;
		}

		return -1;
	}

	private Direction directionOpposee(Direction dir) {
		return switch (dir) {
			case NORD -> Direction.SUD;
			case SUD -> Direction.NORD;
			case EST -> Direction.OUEST;
			case OUEST -> Direction.EST;
			case NORD_EST -> Direction.SUD_OUEST;
			case NORD_OUEST -> Direction.SUD_EST;
			case SUD_EST -> Direction.NORD_OUEST;
			case SUD_OUEST -> Direction.NORD_EST;
		};
	}

	@Override
	public char getSymbole() {
		return 'R';
	}
	@Override
	public TypePiece getType() {
		return TypePiece.ROI;
	}

	private int getZoneForJoueur(Joueur j) {
		return switch (j.getCouleur()) {
			case 0 -> 0;
			case 1 -> 1;
			case 2 -> 2;
			default -> -1;
		};
	}

}
