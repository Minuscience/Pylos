import java.util.Scanner;

public class Game {
	public static final int LEVELS = 4;
	public static final int BALLS = 30;
	public static Board board;
	public static Player j1;
	public static Player j2;
	public static Player[] gammers;
	public static Scanner scan;

	public static void main(String[] args) {
		board = new Board();
		j1 = new Player(false);
		j2 = new Player(true);
		gammers = new Player[2];
		gammers[0] = j1;
		gammers[1] = j2;
		scan = new Scanner(System.in);
		int lv, x, y;
		gammers[0].placeBallOn(board, 3, 0, 1);
		gammers[0].placeBallOn(board, 3, 1, 1);
		gammers[0].placeBallOn(board, 3, 1, 0);
		gammers[1].placeBallOn(board, 3, 3, 3);
		gammers[1].placeBallOn(board, 3, 2, 2);
		gammers[1].placeBallOn(board, 3, 2, 3);
		System.out.println(board);
		System.out.println(j1);
		System.out.println(j2);
		for (int i = 0; i < 30; i += 2) {
			for (int j = 0; j < gammers.length; j++) {
				System.out.println("joueur" + j + " a vous de jouer");
				System.out.println("veuilllez entrer le niveau et les coordonées");
				lv = scan.nextInt();
				x = scan.nextInt();
				y = scan.nextInt();
				if (gammers[j].playableBall() != null && !gammers[j].placeBallOn(board, lv, x, y))
					j--;
				else if (board.squareFull(lv, x, y)) {
					System.out.println("vous pouvez retiter 1 ou 2 balles");
					System.out.println("vous voulez en retirer combien?");
					int nbBallToRemove = scan.nextInt();
					while (nbBallToRemove != 0 && nbBallToRemove != 1 && nbBallToRemove != 2) {
						System.out.println("nombre saisi incorrect veillez entrer 0 , 1 ou 2");
						nbBallToRemove = scan.nextInt();
					}
					System.out.println(board);
					System.out.println(j1);
					System.out.println(j2);
					switch (nbBallToRemove) {
					case 0:
						System.out.println("vous ne retirez pas de balle");
						break;
					case 2:
						System.out.println("entrer les coordonées de la balle à retirer");
						lv = scan.nextInt();
						x = scan.nextInt();
						y = scan.nextInt();
						if (!board.removeBall(gammers[j], lv, x, y)) {
							do {
								System.out.println("vous ne pouvez pas retirer cette balle");
								System.out.println("entrer les coordonées de la balle à retirer");
								lv = scan.nextInt();
								x = scan.nextInt();
								y = scan.nextInt();
							} while (!board.removeBall(gammers[j], lv, x, y));
						}
						System.out.println(board);
						System.out.println(j1);
						System.out.println(j2);
						;
					case 1:
						System.out.println("entrer les coordonées de la balle à retirer");
						lv = scan.nextInt();
						x = scan.nextInt();
						y = scan.nextInt();
						if (!board.removeBall(gammers[j], lv, x, y)) {
							do {
								System.out.println("vous ne pouvez pas retirer cette balle");
								System.out.println("entrer les coordonées de la balle à retirer");
								lv = scan.nextInt();
								x = scan.nextInt();
								y = scan.nextInt();
							} while (!board.removeBall(gammers[j], lv, x, y));
						}
						System.out.println(board);
						System.out.println(j1);
						System.out.println(j2);
						;

					}
				}
				System.out.println(board);
				System.out.println(j1);
				System.out.println(j2);
				if (board.onTop()) {
					if (board.getBall(0, 0, 0).isBlack()) {
						System.out.println("le joueur 0 a gagner");
					} else
						System.out.println("le joueur 1 a gagner");
					break;
				}
				System.out.println(board);
			}
		}

	}
}
