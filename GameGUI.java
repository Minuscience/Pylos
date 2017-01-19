import java.io.Writer;
import java.util.Optional;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import moleculesampleapp.Xform;

public class GameGUI extends Application {
	static Group game3dBox = new Group();
	static Group subRoot3d = new Group();
	static Group subRoot2d = new Group();
	private BorderPane game2dBox = new BorderPane();
	public static Label player1 = new Label("PLAYER 1");
	public static Label player2 = new Label("PLAYER 2");
	public static Scene scene;
	final PerspectiveCamera camera = new PerspectiveCamera(true);
	final Xform cameraXform1 = new Xform();
	final Xform cameraXform2 = new Xform();
	final Xform cameraXform3 = new Xform();
	final double cameraDistance = 1000;
	private double mousePosX;
	private double mousePosY;
	private double mouseOldX;
	private double mouseOldY;
	private double mouseDeltaX;
	private double mouseDeltaY;

	public static final int LEVELS = 4;
	public static final int BALLS = 30;
	public static Board board = new Board();
	public static Player j1;
	public static Player j2;

	public static Player[] gammers;
	public static Label[] labelPlayers;
	public static Boule[] tabBoule;
	public static Scanner scan;
	public static Thread game;
	public static Writer writer;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		HBox menuBox = gameMenu(primaryStage);

		camera.setNearClip(0.1);
		camera.setFarClip(1500.0);
		camera.setTranslateZ(-cameraDistance);
		cameraXform3.getChildren().add(camera);
		cameraXform3.setRotateZ(180.0);
		cameraXform2.getChildren().add(cameraXform3);
		cameraXform1.getChildren().add(cameraXform2);
		// cameraXform1.ry.setAngle(320.0);
		cameraXform1.rx.setAngle(80);
		
		BorderPane root = new BorderPane();

		scene = new Scene(root, 900, 750, Color.WHITE);
		SubScene subScene3d = new SubScene(subRoot3d, 900, 500, true, SceneAntialiasing.BALANCED);
		SubScene subScene2d = new SubScene(subRoot2d, 900, 200, true, SceneAntialiasing.BALANCED);

		subScene3d.setCamera(camera);
		subRoot3d.getChildren().add(game3dBox);

		root.setTop(subScene3d);
		root.setCenter(subScene2d);
		root.setBottom(menuBox);
		setGame3d();

		// Menu start
		Image background = new Image("file:src/img/background.jpg");
		
		ImageView imgNg = new ImageView(new Image("file:src/img/newgame.png"));
		imgNg.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setScene(scene);
				j1 = new Player(true);
				j2 = new Player(false);
				gammers = new Player[2];
				gammers[0] = j1;
				gammers[1] = j2;
				labelPlayers = new Label[2];
				labelPlayers[0] = player1;
				labelPlayers[1] = player2;
				scan = new Scanner(System.in);
				game = new Thread(new GameThread(gammers, scan, board, game3dBox, labelPlayers));
				game.start();
				draw3D();
				
				game2dBox = setGame2d();
				game2dBox.setMinWidth(800);
				subRoot2d.getChildren().add(game2dBox);
			}
		});
		imgNg.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				imgNg.setImage(new Image("file:src/img/newgameselect.png"));
			}
		});
		imgNg.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				imgNg.setImage(new Image("file:src/img/newgame.png"));
			}
		});
		ImageView imgQ = new ImageView(new Image("file:src/img/quit.png"));
		imgQ.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent me) {
				System.exit(0);
			}
		});
		imgQ.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				imgQ.setImage(new Image("file:src/img/quitselect.png"));
			}
		});
		imgQ.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				imgQ.setImage(new Image("file:src/img/quit.png"));
			}
		});
		VBox imgGame = new VBox();
		imgGame.getChildren().addAll(imgNg, imgQ);
		imgGame.setLayoutX(200);
		imgGame.setLayoutY(500);

		Pane rootMenuStart = new Pane();
		rootMenuStart.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		rootMenuStart.getChildren().add(imgGame);

		Scene sceneMenuStart = new Scene(rootMenuStart, 900, 750);
		// Fin menu start

		handleMouse(subScene3d, game3dBox);
		primaryStage.setTitle("Pylos");
		primaryStage.setScene(sceneMenuStart);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	private void handleMouse(SubScene subScene3d, final Node root) {
		subScene3d.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				mousePosX = me.getSceneX();
				mousePosY = me.getSceneY();
				mouseOldX = me.getSceneX();
				mouseOldY = me.getSceneY();
			}
		});
		subScene3d.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				mouseOldX = mousePosX;
				mouseOldY = mousePosY;
				mousePosX = me.getSceneX();
				mousePosY = me.getSceneY();
				mouseDeltaX = (mousePosX - mouseOldX);
				mouseDeltaY = (mousePosY - mouseOldY);

				double modifier = 1.0;
				double modifierFactor = 0.1;

				if (me.isControlDown()) {
					modifier = 0.1;
				}
				if (me.isShiftDown()) {
					modifier = 10.0;
				}
				if (me.isPrimaryButtonDown()) {
					cameraXform1.ry
							.setAngle(cameraXform1.ry.getAngle() - mouseDeltaX * modifierFactor * modifier * 2.0); // +
					cameraXform1.rx
							.setAngle(cameraXform1.rx.getAngle() + mouseDeltaY * modifierFactor * modifier * 2.0); // -
				} else if (me.isSecondaryButtonDown()) {
					double z = camera.getTranslateZ();
					double newZ = z + mouseDeltaX * modifierFactor * modifier;
					camera.setTranslateZ(newZ);
				} else if (me.isMiddleButtonDown()) {
					cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX * modifierFactor * modifier * 0.3); // -
					cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY * modifierFactor * modifier * 0.3); // -
				}
			}
		});
	}

	private Ball3D createBall3D(double size, String color) {
		Ball3D ball = new Ball3D(40);
		ball.setBallColor(color);

		return ball;
	}

	void setGame3d() {
		final PhongMaterial greyMaterial = new PhongMaterial();
		greyMaterial.setDiffuseColor(Color.DARKGREY);
		greyMaterial.setSpecularColor(Color.GREY);

		// Game board
		Box box = new Box(340, 10, 350);
		box.setMaterial(greyMaterial);

		Xform boxXform = new Xform();
		boxXform.getChildren().add(box);
		boxXform.setTranslateY(-40);

		// Game balls


		game3dBox.getChildren().add(boxXform);
		game3dBox.getChildren().add(cameraXform1);

	}

	static void draw3D() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j <= i; j++) {
				for (int k = 0; k <= i; k++) {
					if (!board.isEmpty(i, j, k))
						if (!board.getBall(i, j, k).isOnBoard())
							board.getBall(i, j, k).removeOnGroup3D(game3dBox);
						else
							board.getBall(i, j, k).placeOnGroup3D(game3dBox);
				}

			}
		}

	}

	private BorderPane setGame2d() {
		BorderPane game2dPane = new BorderPane();

		// BorderPane LEFT content
		player1.setPadding(new Insets(5, 5, 5, 5));
		player1.setStyle("-fx-background-color: grey; -fx-border-width: 1; -fx-border-color: black");

		player2.setPadding(new Insets(5, 5, 5, 5));
		player2.setStyle("-fx-border-width: 1; -fx-border-color: black");
		VBox playersBox = new VBox();
		playersBox.setAlignment(Pos.BOTTOM_CENTER);
		playersBox.setSpacing(10);
		playersBox.setTranslateX(10);
		playersBox.getChildren().addAll(player1, player2);

		game2dPane.setLeft(playersBox);

		// BorderPane CENTER content
		Group circles = new Group();
		circles.setTranslateX(30);
		
		int i = 0;
		for (int lv = 0; lv < 4; lv++) {
			for (int x = 0; x <= lv; x++) {
				for (int y = 0; y <= lv; y++) {

					Boule b = new Boule(board, game3dBox, gammers, labelPlayers, lv, x, y);
					if (board.getBall(lv, x, y) == null){
						Circle2D circle = new Circle2D(20.0f);
						circle.setStroke(Color.GRAY, 2);
						circle.setColor(Color.WHITE);
						circle.setPosX(lv*170+x*41);
						circle.setPosY(y*41);
						circles.getChildren().add(circle.getCircle());
						b.setBoule2D(circle);
					}
					
//					if (board.getBall(lv, x, y) == null) {
//						Boule test = new Boule(true);
//						Circle2D circle = test.getBoule2D();
//						circle = new Circle2D(20.0f);
//						circle.setStroke(Color.GRAY, 2);
//						circle.setColor(Color.WHITE);
//						circle.setPosX(lv*170+x*41);
//						circle.setPosY(y*41);
//						circles.getChildren().add(circle.getCircle());
//					}else if(board.getBall(lv, x, y).isBlack()){
//						System.out.println("test testttttttttttttttttttttt");
//						Circle2D circle = board.getBall(lv, x, y).getBoule2D();
//						circle.setStroke(Color.WHITE, 2);
//						circle.setColor(Color.BLACK);
//						circle.setPosX(lv*170+x*41);
//						circle.setPosY(y*41);
//						circles.getChildren().add(circle.getCircle());
//					}else{
//						Circle2D circle = board.getBall(lv, x, y).getBoule2D();
//						circle.setStroke(Color.BEIGE, 2);
//						circle.setColor(Color.WHITE);
//						circle.setPosX(lv*170+x*41);
//						circle.setPosY(y*41);
//						circles.getChildren().add(circle.getCircle());
//					}	
				}
			}
		}

		game2dPane.setCenter(circles);

		return game2dPane;
	}

	private HBox gameMenu(Stage primaryStage) {
		Label newGameLabel = new Label("New game");
		newGameLabel.setAlignment(Pos.CENTER);
		newGameLabel.setPrefWidth(300);
		newGameLabel.setWrapText(true);

		NewGame newGame = new NewGame(game, gammers, board, game3dBox, labelPlayers);
		newGame.setNewGame(newGameLabel);

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
				popupRules.getIcons().add(new Image("file:src/img/rules.png"));
				VBox dialogVbox = new VBox(20);
				dialogVbox.getChildren().addAll(Rules.getRules());
				dialogVbox.setSpacing(2.0);
				Scene dialogScene = new Scene(dialogVbox, 1000, 630);
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
				popupHelp.setTitle("Besoin d'aide ?");
				popupHelp.getIcons().add(new Image("file:src/img/help.png"));
				VBox dialogVbox = new VBox(20);

				dialogVbox.getChildren().add(Help.finalVboxHelp());
				Scene dialogScene = new Scene(dialogVbox, 920, 150);
				popupHelp.setScene(dialogScene);
				popupHelp.show();
			}
		});

		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		menuBar.getMenus().addAll(newGameMenu, rulesMenu, helpMenu);

		HBox menuBox = new HBox();
		menuBox.getChildren().addAll(menuBar);

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {

				// changer le comportement du bouton
				event.consume();

				// les boutons
				ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
				ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

				// show close dialog
				Alert alert = new Alert(null, "Are you sure?", yes, no);
				alert.setTitle("='(");
				alert.initOwner(primaryStage);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == yes) {
					System.exit(0);
				}
			}
		});

		return menuBox;

	}

}
