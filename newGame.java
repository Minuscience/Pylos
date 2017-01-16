import java.util.Optional;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class newGame {
	public static void setNewGame(Label newGameLabel)
	{
		newGameLabel.setOnMousePressed(new EventHandler<MouseEvent>() {
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
		        }
	        }
		});
	}
}
