import java.util.Scanner;

import javafx.scene.Group;
import javafx.scene.control.Label;

public class Reset implements Runnable {

	@Override
	public void run() {
		GameGUI.board = new Board();
    	GameGUI.j1 = new Player(false);
    	GameGUI.j2 = new Player(true);
    	GameGUI.gammers = new Player[2];
    	GameGUI.gammers[0] = GameGUI.j1;
    	GameGUI.gammers[1] = GameGUI.j2;
    	GameGUI.labelPlayers = new Label[2];
    	GameGUI.labelPlayers[0] = GameGUI.player1;
    	GameGUI.labelPlayers[1] = GameGUI.player2;
    	GameGUI.scan = new Scanner(System.in);
    	GameGUI.game3dBox=new Group();
    	GameGUI.game = new Thread(new GameThread(GameGUI.gammers, GameGUI.scan, GameGUI.board, GameGUI.game3dBox, GameGUI.labelPlayers));
		GameGUI.game.start();
	}

}
