import java.util.ArrayList;

public class Player {
	private final boolean isBlack;
	private ArrayList<Ball> myBall;
	private boolean turn;

	public Player(boolean color) {
		myBall = new ArrayList<Ball>();
		for (int i = 0; i < 15; i++) {
			myBall.add(new Ball(color));
		}
		turn = color;
		isBlack = color;
	}
	
	public String toString(){
		return myBall.toString();
	}

	public boolean isBlack() {
		return isBlack;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	//un joueur peut jouer si c'est son tour et qu'il a des boules
	public boolean canPlay() {
		if (!turn)
			return false;
		for (Ball ball : myBall) {
			if (!ball.isOnBoard())
				return true;
		}
		return false;
	}

	public Ball playableBall() {
		for (Ball ball : myBall) {
			if (!ball.isOnBoard())
				return ball;
		}
		return null;
	}

	public boolean placeBallOn(Board b, int lv, int x, int y) {
		if (!canPlay() && !b.playableCase(lv, x, y))
			return false;
		Ball current = playableBall();
		b.setBall(current, lv, x, y);
		current.getPlace().setPosition(lv, x, y);
		current.setOnBoard(true);
		return true;
	}

	public enum Action {
		/** place a new ball or remove one to mount */
		PLACE,
		/** place the removed ball on a higher level */
		MOUNT,
		/** choose 1 or 2 balls to remove from board */
		REMOVE,
		/** Wait for other player */
		WAIT
	}

	public enum Type {
		UNDEFINED, LOCAL, AI, REMOTE
	}
}
