package Model;

import java.util.EnumMap;
import java.util.Map;

public class Case {
	private int ligne;
	private int colonne;
	private boolean estValide;
	private Piece piece;

	private Map<Direction, Case> voisins = new EnumMap<>(Direction.class); // Nouveau !

	public Case(int ligne, int colonne, boolean estValide) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.estValide = estValide;
	}

	// === GETTERS/SETTERS ===
	public boolean estValide() { return estValide; }
	public int getLigne() { return ligne; }
	public int getColonne() { return colonne; }
	public Piece getPiece() { return piece; }
	public void setPiece(Piece piece) { this.piece = piece; }

	// === VOISINS ===
	public void setVoisin(Direction dir, Case voisin) {
		if (voisin != null) {
			voisins.put(dir, voisin);
		}
	}

	public Case getVoisin(Direction dir) {
		return voisins.get(dir); // peut retourner null
	}

	public Map<Direction, Case> getTousLesVoisins() {
		return voisins;
	}

	@Override
	public String toString() {
		return "" + (char)('A' + ligne) + (colonne + 1);
	}
}
