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
		System.out.println(board);
		j1 = new Player(true);
		j2 = new Player(false);
		gammers = new Player[2];
		gammers[0] = j1;
		gammers[1] = j2;
		scan = new Scanner(System.in);
		int lv, x, y;

		for (int i = 0; i < 30; i+=2) {
			for (int j = 0; j < gammers.length; j++) {
				System.out.println("joueur" + j + " a vous de jouer");
				System.out.println("veuilllez entrer le niveau et les coordonées");
				lv = scan.nextInt();
				x = scan.nextInt();
				y = scan.nextInt();
				gammers[j].placeBallOn(board, lv, x-1, y-1);
				System.out.println(board);
			}

		}
	}

}
