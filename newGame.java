import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class newGame {
	public static void setNewGame(Label newGameLabel, Stage primaryStage)
	{
		newGameLabel.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
	            final Stage popupNewGame = new Stage();
	            popupNewGame.initModality(Modality.APPLICATION_MODAL);
	            popupNewGame.initOwner(primaryStage);
	            popupNewGame.setTitle("Start a new Game");
	            VBox dialogVbox = new VBox(20);     
	            Text textNewGame = new Text();
	            Text textContinue = new Text();
	            HBox hboxChoice = new HBox();
	            
	            Button yesButton = new Button("Yes");
	            Button noButton = new Button("No");
	            hboxChoice.getChildren().addAll(yesButton, noButton);
	            hboxChoice.setAlignment(Pos.CENTER);
	            hboxChoice.setSpacing(10.0);
	            
	            textNewGame.setText("WARNING! You will start a new game, the current game won't be saved.");
	            textContinue.setText("Continue ?");
	            dialogVbox.setSpacing(2.0);
	            
	            dialogVbox.getChildren().addAll(textNewGame, textContinue, hboxChoice);
	            Scene dialogScene = new Scene(dialogVbox, 500, 100);
	            popupNewGame.setScene(dialogScene);
	            popupNewGame.show();
	            
	            /*noButton.setOnAction(new EventHandler<ActionEvent>() {
	    			@Override
	    			Window stage = node.getScene().getWindow();
	    			popupRules.close();
	            }*/
	        }
		});
	}
}
