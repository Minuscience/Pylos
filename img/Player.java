package img;

import java.util.ArrayList;

import javafx.scene.Group;

public class Player {
	private final boolean isBlack;
	private ArrayList<Boule> myBall;
	private boolean turn;

	public Player(boolean color) {
		myBall = new ArrayList<Boule>();
		for (int i = 0; i < 15; i++) {
			myBall.add(new Boule(color));
		}
		turn = color;
		isBlack = color;
	}

	public String toString() {
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

	// un joueur peut jouer si c'est son tour et qu'il a des boules
	public boolean canPlay() {
		if (!turn)
			return false;
		for (Ball ball : myBall) {
			if (!ball.isOnBoard())
				return true;
		}
		return false;
	}

	public Boule playableBall() {
		for (Boule ball : myBall) {
			if (!ball.isOnBoard())
				return ball;
		}
		return null;
	}

	public boolean placeBallOn(Board board, int lv, int x, int y, Group group) {
		if (canPlay() && !board.playableCase(lv, x, y))
			return false;
		Boule current = playableBall();
		board.setBall(current, lv, x, y);
		current.getPlace().setPosition(lv, x, y);
		current.place3D(lv, x, y);
		if (group != null)
			current.placeOnGroup3D(group);
		current.setOnBoard(true);
		return true;
	}

	public boolean placeBallOn(Board board, int lv, int x, int y) {
		return placeBallOn(board, lv, x, y, null);
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
