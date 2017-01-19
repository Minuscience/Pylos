import java.util.Optional;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class NewGame {
	static Thread game;
	static Player[] gammers;
	static Scanner scan;
	static Board board;
	static Group game3dBox;
	static Label[] labelPlayers;
	
	public NewGame(Thread game, Player[] gammers, Board board, Group game3dBox, Label[] labelPlayers){
		NewGame.game=game;
		NewGame.gammers=gammers;
		NewGame.scan= GameGUI.scan;
		NewGame.board=board;
		NewGame.game3dBox=game3dBox;
		NewGame.labelPlayers=labelPlayers;
	}
	
	public void setNewGame(Label newGameLabel)
	{
		newGameLabel.setOnMousePressed(new EventHandler<MouseEvent>() {
			

			@SuppressWarnings("deprecation")
			@Override
			public void handle(MouseEvent e) {
		        //les boutons
		        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
		        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
		        
		        // show close dialog
		        Alert alert = new Alert(null, "WARNING! You will start a new game, the current game won't be saved. Continue?", yes, no);
		        alert.setTitle("New Game");

		        Optional<ButtonType> result = alert.showAndWait();
		        if (result.get() == yes){
		        	System.out.println("New Game");
		        	Platform.runLater(new Reset());
		        	
		        }
	        }
		});
	}
}
