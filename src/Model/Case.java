package Model;

public class Case {
	private int ligne;
	private int colonne;
	private boolean estValide;
	private Piece piece;

	public Case(int ligne, int colonne, boolean estValide) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.estValide = estValide;
	}

	public boolean estValide() { return estValide; }
	public int getLigne() { return ligne; }
	public int getColonne() { return colonne; }
	public Piece getPiece() { return piece; }
	public void setPiece(Piece piece) { this.piece = piece; }

	@Override
	public String toString() {
		return "" + (char)('A' + ligne) + (colonne + 1);
	}
}