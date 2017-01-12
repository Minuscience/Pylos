import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameGUI extends Application {
	public static void main(String[] args) {
        launch(args);
    }
	
	public void start(Stage primaryStage) throws Exception {
		HBox menuBox = gameMenu(primaryStage);
		
		HBox troisD = new HBox();
		troisD.setStyle("-fx-background-color: #336699");
		troisD.setPadding(new Insets(0,1000,450,0));
		
		HBox deuxD = new HBox();
		deuxD.setStyle("-fx-background-color: #445699");
		deuxD.setPadding(new Insets(0,0,0,0));
		
		BorderPane mainBox = new BorderPane();
		mainBox.setTop(troisD);
		mainBox.setCenter(deuxD);
		mainBox.setBottom(menuBox);
		
		Scene scene = new Scene(mainBox, 1000, 900, Color.WHITE);
		
		primaryStage.setTitle("Pylos");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	private HBox gameMenu(Stage primaryStage){
		Label newGameLabel = new Label("New game");
		newGameLabel.setAlignment(Pos.CENTER);
		newGameLabel.setPrefWidth(300);
		newGameLabel.setWrapText(true);
		
		Menu newGameMenu = new Menu();
		newGameMenu.setGraphic(newGameLabel);
		
		Label rulesLabel = new Label("Rules");
		rulesLabel.setAlignment(Pos.CENTER);
		rulesLabel.setPrefWidth(300);
		rulesLabel.setWrapText(true);
		
		Menu rulesMenu = new Menu();
		rulesMenu.setGraphic(rulesLabel);
		
		rulesLabel.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
                final Stage popupRules = new Stage();
                popupRules.initModality(Modality.APPLICATION_MODAL);
                popupRules.initOwner(primaryStage);
                popupRules.setTitle("Les règles du jeu");
                popupRules.getIcons().add(new Image("file:img/rules.png"));
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().addAll(Rules.getRules());
                dialogVbox.setSpacing(2.0);
                Scene dialogScene = new Scene(dialogVbox, 1000, 600);
                popupRules.setScene(dialogScene);
                popupRules.show();
            }
		});
		
		Label helpLabel = new Label("Help");
		helpLabel.setAlignment(Pos.CENTER);
		helpLabel.setPrefWidth(300);
		helpLabel.setWrapText(true);
		
		Menu helpMenu = new Menu();
		helpMenu.setGraphic(helpLabel);
		
		helpLabel.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
                final Stage popupHelp = new Stage();
                popupHelp.initModality(Modality.APPLICATION_MODAL);
                popupHelp.initOwner(primaryStage);
                popupHelp.setTitle("Besoin d'aide?");
                popupHelp.getIcons().add(new Image("file:img/help.png"));
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("Ceci va vous aider"));
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                popupHelp.setScene(dialogScene);
                popupHelp.show();
            }
		});
		
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		menuBar.getMenus().addAll(newGameMenu, rulesMenu, helpMenu);
		
		HBox menuBox = new HBox();
		menuBox.getChildren().addAll(menuBar);
		
		return menuBox;
	}
}
