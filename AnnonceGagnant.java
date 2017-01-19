import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

	public class AnnonceGagnant {
		public static void gagnant(String gagnant)
		{
	        ButtonType yes = new ButtonType("OK !", ButtonBar.ButtonData.OK_DONE);
	        
	        Alert alert = new Alert(null, "Le joueur " + gagnant + " a gagné !", yes);
	        alert.setTitle("Félicitation !");

	        Optional<ButtonType> result = alert.showAndWait();
	        if (result.get() == yes){
	        	System.exit(0);
	        }
	}

}
