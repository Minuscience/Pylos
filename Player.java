import java.util.ArrayList;

public class Player {
	private boolean isBlack;
	private ArrayList<Ball> myBall;
	private boolean turn;

	public Player(boolean color) {
		myBall = new ArrayList<Ball>();
		for (int i = 0; i < 15; i++) {
			myBall.add(new Ball(color));
		}
		turn = color;
	}

	public boolean isBlack() {
		return isBlack;
	}

	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	// un joueur peut jouer si c'est son tour et qu'il a des boules
	/**
	 * Check if it's the player turn and if he had free ball in his pool
	 * 
	 * @return
	 */
	public boolean canPlay() {
		if (!turn)
			return false;
		for (Ball ball : myBall) {
			if (!ball.isOnBoard())
				return true;
		}
		return false;
	}

	/**
	 * check if the player have a ball free in is pool
	 * 
	 * @return the first playable ball in the player pooll
	 */
	public Ball playableBall() {
		for (Ball ball : myBall) {
			if (!ball.isOnBoard())
				return ball;
		}
		return null;
	}

	/**
	 * This function checck if the player can play then if the case in witch he
	 * wants to place the ball is free; if so it puts the ball on the case and
	 * return true
	 * 
	 * @param board
	 *            the board on wich the player play
	 * @param lv
	 *            the level of the ball
	 * @param x
	 * @param y
	 * @return true if the ball is placed else false
	 */
	public boolean placeBallOn(Board board, int lv, int x, int y) {
		if (!canPlay() && !board.playableCase(lv, x, y))
			return false;
		Ball current = playableBall();
		board.setBall(current, lv, x, y);
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
