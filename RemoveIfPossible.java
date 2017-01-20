import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

	public class RemoveIfPossible {
		public static int remove()
		{
	        ButtonType button0 = new ButtonType("0 boule", ButtonBar.ButtonData.OK_DONE);
	        ButtonType button1 = new ButtonType("1 boule", ButtonBar.ButtonData.OK_DONE);
	        ButtonType button2 = new ButtonType("2 boules", ButtonBar.ButtonData.OK_DONE);
	        int resultat = 0;
	        Alert alert = new Alert(null, "Combien de boule(s) voulez-vous retirer?", button0, button1, button2);
	        alert.setTitle("Un carré = Plus de boules dans sa réserve !");

	        Optional<ButtonType> result = alert.showAndWait();
	        if (result.get() == button0)
	        	return resultat;
	        else if (result.get() == button1)
	        	return resultat = 1;
	        else
	        	return resultat = 2;
	}

}
