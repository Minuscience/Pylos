import javafx.application.Platform;

public class Board {
	private Level[] board;

	public Board() {
		board = new Level[4];
		for (int i = 0; i < board.length; i++) {
			board[i] = new Level(i + 1);
		}
	}

	// affiche chacun des niveaux
	public String toString() {
		StringBuffer plate = new StringBuffer();
		for (int i = 0; i < board.length; i++) {
			plate.append(board[i].toString());
			plate.append("\n");
		}
		return plate.toString();
	}

	// retourne une balle sur le plateau
	public Boule getBall(int lv, int x, int y) {
		return board[lv].getBall(x, y);
	}

	// verrifie si on a une balle au sommet
	public boolean onTop() {
		return this.getBall(0, 0, 0) != null;
	}

	// verifie si une case est vide
	public boolean isEmpty(int lv, int x, int y) {
		if (getBall(lv, x, y) == null) {
			return true;
		} else
			return !getBall(lv, x, y).isOnBoard();
	}

	// verifie si on peut jouer sur une case
	public boolean playableCase(int lv, int x, int y) {
		if (lv < 0 || lv > 3)
			return false;
		if (isEmpty(lv, x, y)) {
			if (lv == 3)
				return isEmpty(lv, x, y);
			else
				return (!isEmpty(lv + 1, x, y) && !isEmpty(lv + 1, x + 1, y) && !isEmpty(lv + 1, x, y + 1)
						&& !isEmpty(lv + 1, x + 1, y + 1));

		}
		return false;
	}

	// place la boule ball sur le plateau
	public boolean setBall(Boule ball, int lv, int x, int y) {
		if (isEmpty(lv, x, y)) {

			board[lv].setBall(ball, x, y);
			ball.setOnBoard(true);
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
	 * @return true if there is a ball on top
	 */
	public boolean hasABallOnTop(int lv, int x, int y) {
		if (lv == 0)
			return false;
		else if (lv == 1)
			return onTop();
		else {
			if (x == 0) {
				if (y == lv)
					return !isEmpty(lv - 1, x, y - 1);
				else if (y == 0)
					return !isEmpty(lv - 1, 0, 0);
				else
					return !isEmpty(lv - 1, x, y) || !isEmpty(lv, x, y + 1);
			} else if (x == lv) {
				if (y == lv)
					return !isEmpty(lv - 1, x - 1, y - 1);
				else if (y == 0)
					return !isEmpty(lv - 1, x - 1, y);
				else
					return !isEmpty(lv - 1, x - 1, y) || !isEmpty(lv - 1, x - 1, y + 1);
			} else if (y == 0) {
				return !isEmpty(lv - 1, x, y) || !isEmpty(lv - 1, x + 1, y);
			} else if (y == lv) {
				return !isEmpty(lv - 1, x, y - 1) || !isEmpty(lv, x + 1, y - 1);
			} else {
				return (!isEmpty(lv - 1, x, y) || !isEmpty(lv - 1, x - 1, y) || !isEmpty(lv - 1, x, y - 1)
						|| !isEmpty(lv - 1, x - 1, y - 1));

			}
		}
	}

	public boolean removeBall(Player player, int lv, int x, int y) {
		System.out.println("player color = " + player.isBlack());
		System.out.println("ball color = " + getBall(lv, x, y).isBlack());
		if (hasABallOnTop(lv, x, y)) {
			System.out.println("il y a une balle au dessus");
			return false;
		} else if (player.isBlack() == getBall(lv, x, y).isBlack()) {
			System.out.println("on retire");
			Platform.runLater(new Remove3D(GameGUI.board.getBall(lv, x, y).getBoule3D(), GameGUI.game3dBox));
			getBall(lv, x, y).setOnBoard(false);
			return true;
		}
		return false;
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
					return square(lv, x, y - 1);
				else if (y == 0)
					return square(lv, x, y);
				else
					return square(lv, x, y - 1) || square(lv, x, y);
			} else if (x == lv) {
				if (y == lv)
					return square(lv, x - 1, y - 1);
				else if (y == 0)
					return square(lv, x - 1, y);
				else
					return square(lv, x - 1, y - 1) || square(lv, x - 1, y);
			} else if (y == 0) {
				return square(lv, x - 1, y) || square(lv, x, y);
			} else if (y == lv) {
				return square(lv, x - 1, y - 1) || square(lv, x, y - 1);
			} else {
				return square(lv, x - 1, y - 1) || square(lv, x - 1, y) || square(lv, x, y - 1) || square(lv, x, y);
			}
		}
	}

}
