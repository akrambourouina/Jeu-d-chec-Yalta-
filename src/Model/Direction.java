package Model;
public enum Direction {
	NORD(-1, 0), SUD(1, 0), EST(0, 1), OUEST(0, -1),
	NORD_EST(-1, 1), NORD_OUEST(-1, -1),
	SUD_EST(1, 1), SUD_OUEST(1, -1);

	public final int dLigne;
	public final int dColonne;

	Direction(int dLigne, int dColonne) {
		this.dLigne = dLigne;
		this.dColonne = dColonne;
	}
}