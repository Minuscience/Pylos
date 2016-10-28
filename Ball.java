
public class Ball {
	private boolean isBlack;
	private boolean onBoard;
	private Position place;

	public Ball(boolean isBlack) {
		this.isBlack = isBlack;
		onBoard = false;

		place = new Position(-1,-1,-1);
	}

	public boolean isBlack() {
		return isBlack;
	}

	public void setColor(boolean isBlack) {
		this.isBlack = isBlack;
	}

	public String toString() {
		if (isBlack)
			return "O";
		else
			return "X";
	}

	public boolean isOnBoard() {
		return onBoard;
	}

	public void setOnBoard(boolean onBoard) {
		this.onBoard = onBoard;
	}

	public Position getPlace() {
		return place;
	}

	public void setPlace(Position place) {
		this.place = place;
	}
}
