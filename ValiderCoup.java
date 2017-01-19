import java.util.Optional;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

public class ValiderCoup {
	public static boolean validerCoup()
	{
		boule.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
		        //les boutons
		        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
		        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
		        
		        // show close dialog
		        Alert alert = new Alert(null, "Make this move?", yes, no);
		        alert.setTitle("Confirmation Moves");

		        Optional<ButtonType> result = alert.showAndWait();
		        if (result.get() == yes){
		        	System.out.println("New Game");
		        }
	        }
		});
	}
}
