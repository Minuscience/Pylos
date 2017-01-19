import java.util.Scanner;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Label;

public class GameThread implements Runnable {
	public Player[] gammers;
	Scanner scan;
	Board board;
	int lv;
	int x;
	int y;
	Group game3dBox;
	GameGUI panel;
	public Label[] labelPlayers;

	public GameThread(Player[] gammers, Scanner scan, Board board, Group game3dBox, Label[] labelPlayers) {
		this.gammers = gammers;
		this.scan = scan;
		this.board = board;
		this.game3dBox = game3dBox;
		this.labelPlayers = labelPlayers;
	}

	@Override
	public void run() {
		// try {
		// wait(1000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
//		gammers[0].placeBallOn(board, 3, 0, 1, game3dBox);
//		gammers[0].placeBallOn(board, 3, 1, 1, game3dBox);
//		gammers[0].placeBallOn(board, 3, 1, 0, game3dBox);
//		gammers[1].placeBallOn(board, 3, 3, 3, game3dBox);
//		gammers[1].placeBallOn(board, 3, 2, 2, game3dBox);
//		gammers[1].placeBallOn(board, 3, 2, 3, game3dBox);
//		System.out.println(board);
//		System.out.println(gammers[0]);
//		System.out.println(gammers[1]);
		
		while(!board.onTop()) {
			for (int j = 0; j < gammers.length; j++) {
				labelPlayers[j].setStyle("-fx-background-color: grey; -fx-border-width: 1; -fx-border-color: black");
				labelPlayers[(j + 1) % 2].setStyle("-fx-border-width: 1; -fx-border-color: black");

				System.out.println("joueur " + j + " à vous de jouer");
				System.out.println("veuillez entrer le niveau et les coordon�es");
				lv = scan.nextInt();
				x = scan.nextInt();
				y = scan.nextInt();
				if (gammers[j].playableBall() != null && !gammers[j].placeBallOn(board, lv, x, y, game3dBox)) {
					j--;
					System.out.println(board);
				} else if (board.squareFull(lv, x, y)) {
					System.out.println("vous pouvez retirer 1 ou 2 balles");
					System.out.println("vous voulez en retirer combien?");
					int nbBallToRemove = scan.nextInt();
					while (nbBallToRemove != 0 && nbBallToRemove != 1 && nbBallToRemove != 2) {
						System.out.println("nombre saisi incorrect veillez entrer 0 , 1 ou 2");
						nbBallToRemove = scan.nextInt();
					}
					System.out.println(board);
					System.out.println(GameGUI.j1);
					System.out.println(GameGUI.j1);
					switch (nbBallToRemove) {
					case 0:
						System.out.println("vous ne retirez pas de balle");
						break;
					case 2:
						System.out.println("entrer les coordonnées de la balle à retirer");
						lv = scan.nextInt();
						x = scan.nextInt();
						y = scan.nextInt();
						if (!board.removeBall(gammers[j], lv, x, y)) {
							do {
								System.out.println("vous ne pouvez pas retirer cette balle");
								System.out.println("entrer les coordonnées de la balle à retirer");
								lv = scan.nextInt();
								x = scan.nextInt();
								y = scan.nextInt();
							} while (!board.removeBall(gammers[j], lv, x, y));
						}
						System.out.println(board);
						System.out.println(GameGUI.j1);
						System.out.println(GameGUI.j2);
						;
					case 1:
						System.out.println("entrer les coordonnées de la balle à retirer");
						lv = scan.nextInt();
						x = scan.nextInt();
						y = scan.nextInt();
						if (!board.removeBall(gammers[j], lv, x, y)) {
							do {
								System.out.println("vous ne pouvez pas retirer cette balle");
								System.out.println("entrer les coordonnées de la balle à retirer");
								lv = scan.nextInt();
								x = scan.nextInt();
								y = scan.nextInt();
							} while (!board.removeBall(gammers[j], lv, x, y));
						}
						System.out.println(board);
						System.out.println(GameGUI.j1);
						System.out.println(GameGUI.j2);
					}
				}
			}
		}
	}
}
