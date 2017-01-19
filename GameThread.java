import java.util.Scanner;

import javafx.scene.Group;

public class GameThread implements Runnable {
	public Player[] gammers;
	Scanner scan;
	Board board;
	int lv;
	int x;
	int y;
	Group scene3d;
	GameGUI panel;

	public GameThread(Player[] gammers, Scanner scan, Board board, Group game3dBox, GameGUI panel) {
		this.gammers = gammers;
		this.scan = scan;
		this.board = board;
		this.scene3d= game3dBox;
	}

	@Override
	public void run() {
		gammers[0].placeBallOn(board, 3, 0, 1);
		gammers[0].placeBallOn(board, 3, 1, 1);
		gammers[0].placeBallOn(board, 3, 1, 0);
		gammers[1].placeBallOn(board, 3, 3, 3);
		gammers[1].placeBallOn(board, 3, 2, 2);
		gammers[1].placeBallOn(board, 3, 2, 3);
		System.out.println(board);
		System.out.println(gammers[0]);
		System.out.println(gammers[1]);
		for (int i = 0; i < 30; i += 2) {
			for (int j = 0; j < gammers.length; j++) {
				System.out.println("joueur" + j + " a vous de jouer");
				System.out.println("veuillez entrer le niveau et les coordon�es");
				lv = scan.nextInt();
				x = scan.nextInt();
				y = scan.nextInt();
				if (gammers[j].playableBall() != null && !gammers[j].placeBallOn(board, lv, x, y))
					j--;
				System.out.println(board);
			}

		}

	}

}
