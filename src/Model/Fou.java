package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fou extends Piece {
	public Fou(Case position, Joueur joueur) {
		super(position, joueur);
	}

	@Override
	public List<Case> getDeplacementsPossibles(Plateau plateau) {
		List<Case> deplacements = new ArrayList<>();
		Direction[] directions = {
				Direction.NORD_EST, Direction.NORD_OUEST,
				Direction.SUD_EST, Direction.SUD_OUEST
		};

		Case caseDepart = this.getPosition();
		int zoneInitiale = getZone(caseDepart);

		for (Direction dirInitiale : directions) {
			Direction dir = dirInitiale;
			Case courant = caseDepart;
			boolean dejaInverse = false;
			Set<Case> dejaVues = new HashSet<>();

			while (true) {
				Case suivant = courant.getVoisin(dir);
				if (suivant == null || !suivant.estValide()) break;

				if (dejaVues.contains(suivant)) break;
				dejaVues.add(suivant);
				//////////:
				if (courant.toString().equals("D4") && dirInitiale == Direction.NORD_EST) {
					Case pont = plateau.getCase(4, 8);
					while (pont != null && pont.estValide()) {
						if (pont.getPiece() != null) {
							if (pont.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
								deplacements.add(pont);
								break;
							}

						}
						deplacements.add(pont);
						int ligneSuiv = pont.getLigne() + 1;
						int colSuiv = pont.getColonne() + 1;
						if (ligneSuiv >= 12 || colSuiv >= 12) break;
						pont = plateau.getCase(ligneSuiv, colSuiv);
					}
				}
				/////////////////////////
				if (courant.toString().equals("E4") && dirInitiale == Direction.NORD_OUEST) {
					Case pont = plateau.getCase(3, 4);
					while (pont != null && pont.estValide()) {
						if (pont.getPiece() != null) {
							if (pont.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
								deplacements.add(pont);
								break;
							}

						}
						deplacements.add(pont);
						int ligneSuiv = pont.getLigne() - 1;
						int colSuiv = pont.getColonne() + 1;
						if (ligneSuiv >= 12 || colSuiv >= 12) break;
						pont = plateau.getCase(ligneSuiv, colSuiv);
					}
				}
				/////////////////////////////////////////probleme doublon
				if (courant.toString().equals("D5") && dirInitiale == Direction.NORD_OUEST) {
					Case pont = plateau.getCase(8, 8);
					while (pont != null && pont.estValide()) {
						if (pont.getPiece() != null) {
							if (pont.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
								deplacements.add(pont);
								break;
							}

						}
						deplacements.add(pont);
						int ligneSuiv = pont.getLigne() + 1;
						int colSuiv = pont.getColonne() + 1;
						if (ligneSuiv >= 12 || colSuiv >= 12) break;
						pont = plateau.getCase(ligneSuiv, colSuiv);
					}
				}
				////////////////////////////////////////
				if (courant.toString().equals("I5") && dirInitiale == Direction.NORD_EST) {
					Case pont = plateau.getCase(3, 3);
					while (pont != null && pont.estValide()) {
						if (pont.getPiece() != null) {
							if (pont.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
								deplacements.add(pont);
								break;
							}

						}
						deplacements.add(pont);
						int ligneSuiv = pont.getLigne() - 1;
						int colSuiv = pont.getColonne() - 1;
						if (ligneSuiv >= 12 || colSuiv >= 12) break;
						pont = plateau.getCase(ligneSuiv, colSuiv);
					}
				}
				///////////////////////////////////////problem doublon
				if (courant.toString().equals("I9") && dirInitiale == Direction.NORD_OUEST) {
					Case pont = plateau.getCase(4, 3);
					while (pont != null && pont.estValide()) {
						if (pont.getPiece() != null) {
							if (pont.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
								deplacements.add(pont);
								break;
							}

						}
						deplacements.add(pont);
						int ligneSuiv = pont.getLigne() + 1;
						int colSuiv = pont.getColonne() - 1;
						if (ligneSuiv >= 12 || colSuiv >= 12) break;
						pont = plateau.getCase(ligneSuiv, colSuiv);
					}
				}
				////////////////////////////////problem doublon
				if (courant.toString().equals("E9") && dirInitiale == Direction.NORD_EST) {
					Case pont = plateau.getCase(8, 4);
					while (pont != null && pont.estValide()) {
						if (pont.getPiece() != null) {
							if (pont.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
								deplacements.add(pont);
								break;
							}

						}
						deplacements.add(pont);
						int ligneSuiv = pont.getLigne() + 1;
						int colSuiv = pont.getColonne() + 1;
						if (ligneSuiv >= 12 || colSuiv >= 12) break;
						pont = plateau.getCase(ligneSuiv, colSuiv);
					}
				}
				//////////////////////////
				int zoneSuivante = getZone(suivant);

				if (!dejaInverse && zoneSuivante != zoneInitiale && zoneSuivante != -1) {
					dir = directionOpposeeDiagonale(dir);
					dejaInverse = true;

					if (suivant.getPiece() == null) {
						deplacements.add(suivant);
					} else if (suivant.getPiece().getJoueur().getCouleur() != this.joueur.getCouleur()) {
						deplacements.add(suivant);
					}
					courant = suivant;
					continue;
				}

				if (suivant.getPiece() != null) {
					if (suivant.getPiece().getJoueur().getCouleur() == this.joueur.getCouleur()) break;
					else {
						deplacements.add(suivant);
						break;
					}
				}


				deplacements.add(suivant);
				courant = suivant;
			}


		}

		return deplacements;
	}

	private Direction directionOpposeeDiagonale(Direction dir) {
		return switch (dir) {
			case NORD_EST -> Direction.SUD_OUEST;
			case SUD_OUEST -> Direction.NORD_EST;
			case NORD_OUEST -> Direction.SUD_EST;
			case SUD_EST -> Direction.NORD_OUEST;
			default -> null;
		};
	}

	private int getZone(Case c) {
		int ligne = c.getLigne();
		int colonne = c.getColonne();

		if (colonne >= 0 && colonne <= 3 && ligne >= 0 && ligne <= 7) return 1;

		if (colonne >= 4 && colonne <= 7 && (
				(ligne >= 0 && ligne <= 3) || (ligne >= 8 && ligne <= 11))) return 2;

		if (colonne >= 8 && colonne <= 11 && (
				(ligne >= 4 && ligne <= 7) || (ligne >= 8 && ligne <= 11))) return 0;

		return -1;
	}

	@Override
	public char getSymbole() {
		return 'F';
	}
}
