
public class Board {
	private Level[] board;

	public Board() {
		board = new Level[4];
		for (int i = 0; i < board.length; i++) {
			board[i] = new Level(i + 1);
		}
	}

	// affiche chacun des niveau
	public String toString() {
		StringBuffer plate = new StringBuffer();
		for (int i = 0; i < board.length; i++) {
			plate.append(board[i].toString());
			plate.append("\n");
		}
		return plate.toString();
	}

	// retourne une balle sur le plateau
	public Ball getBall(int lv, int x, int y) {
		return board[lv].getBall(x, y);
	}

	/**
	 * Check if a ball is on the top of the board
	 * 
	 * @return true if there is a ball on the top
	 */
	public boolean onTop() {
		return this.getBall(0, 0, 0) != null;
	}

	/**
	 * Check if a case is empty or not
	 * 
	 * @param lv
	 * @param x
	 * @param y
	 * @return true if the case is empty
	 */
	public boolean isEmpty(int lv, int x, int y) {
		return getBall(lv, x, y) == null;
	}

	/**
	 * Check if a case is playable: -check if the case is free -check if on the
	 * lower level there are balls to support the choosen case (if you choose an
	 * upper level)
	 * 
	 * @param lv
	 * @param x
	 * @param y
	 * @return true if the case is playable else false
	 */
	public boolean playableCase(int lv, int x, int y) {
		if (lv < 0 || lv > 3)
			return false;
		if (isEmpty(lv, x, y)) {
			if (lv == 3)
				return true;
			else if (!isEmpty(lv + 1, x, y) && !isEmpty(lv + 1, x + 1, y) && !isEmpty(lv + 1, x, y + 1)
					&& !isEmpty(lv + 1, x + 1, y + 1))
				return true;
			else
				return false;
		}
		return false;
	}

	/**
	 * Put the ball in parameter on the board
	 * 
	 * @param ball
	 * @param lv
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean setBall(Ball ball, int lv, int x, int y) {
		if (playableCase(lv, x, y)) {
			board[lv].setBall(ball, x, y);
			return true;
		}
		return false;
	}

	/**
	 * Check if a ball has another on top of it
	 * 
	 * @param lv
	 * @param x
	 * @param y
	 * @return true if there is a ball
	 */
	public boolean hasABallOnTop(int lv, int x, int y) {
		if (lv == 0)
			return false;
		else if (lv == 1)
			return onTop();
		else {
			if (x == 0) {
				if (y != lv)
					return !isEmpty(lv - 1, 0, y);
				else if (y == lv)
					return !isEmpty(lv - 1, 0, y - 1);
			}
			if (x == lv) {
				if (y != 0)
					return !isEmpty(lv - 1, x - 1, y);
				else if (y == lv)
					return !isEmpty(lv - 1, x - 1, y - 1);
			}

			if (y == lv) {
				if (x != lv)
					return !isEmpty(lv - 1, x, y - 1);
			}
			if (y == 0) {
				if (x != lv)
					return !isEmpty(lv - 1, x, 0);
				else if (y == lv)
					return !isEmpty(lv - 1, x - 1, 0);
			}
			return !isEmpty(lv - 1, x - 1, y - 1) && !isEmpty(lv - 1, x - 1, y) && !isEmpty(lv - 1, x, y - 1)
					&& !isEmpty(lv - 1, x, y);

		}

	}

	public boolean square(int lv, int x, int y) {
		if (lv == 0)
			return false;
		else {
			if (isEmpty(lv, x, y) || isEmpty(lv, x + 1, y) || isEmpty(lv, x, y + 1) || isEmpty(lv, x + 1, y + 1))
				return false;
			else {
				boolean color = getBall(lv, x, y).isBlack();
				return (color == getBall(lv, x + 1, y).isBlack() && color == getBall(lv, x, y + 1).isBlack()
						&& color == getBall(lv, x + 1, y + 1).isBlack());
			}
		}

	}

	public boolean squareFull(int lv, int x, int y) {
		if (lv == 0)
			return false;
		else if (lv == 1)
			return square(lv, 0, 0);
		else {
			if (x == 0) {
				if (y == lv)
					return square(lv, 0, y - 1);
				else if (y > 0)
					return square(lv, 0, y - 1) && square(lv, 0, y);
				else
					return square(lv, x, y);
			} else if (x == lv) {
				if (y == lv)
					return square(lv, x - 1, y - 1);
				else if (y > 0)
					return square(lv, 0, y - 1) && square(lv, 0, y);
				else
					return square(lv, x - 1, y);
			} else if (y == 0) {
				return square(lv, x - 1, y) && square(lv, x, y);
			} else if (y == lv) {
				return square(lv, x - 1, y - 1) && square(lv, x, y - 1);
			} else {
				return square(lv, x - 1, y - 1) && square(lv, x - 1, y) && square(lv, x, y - 1) && square(lv, x, y);
			}
		}
	}

	public boolean removeBall(int lv, int x, int y) {
		if (hasABallOnTop(lv, x, y))
			return false;
		else {
			getBall(lv, x, y).setOnBoard(false);
			return true;
		}
	}
}
