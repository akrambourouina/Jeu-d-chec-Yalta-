package Model;



public class Case {
	private int ligne;   // 0 = A, 1 = B, ..., 11 = L
	private int colonne; // 0 = 1, ..., 11 = 12
	private boolean estValide;

	public Case(int ligne, int colonne, boolean estValide) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.estValide = estValide;
	}

	public boolean estValide() {
		return estValide;
	}

	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}

	@Override
	public String toString() {
		return "" + (char)('A' + ligne) + (colonne + 1);
	}
}

