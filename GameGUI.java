import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import moleculesampleapp.Xform;

public class GameGUI extends Application {
	private Group game3dBox = new Group();
	private BorderPane game2dBox = new BorderPane();
	Label player1;
	Label player2;
	final PerspectiveCamera camera = new PerspectiveCamera(true);
	final Xform cameraXform1 = new Xform();
	final Xform cameraXform2 = new Xform();
	final Xform cameraXform3 = new Xform();
	final double cameraDistance = 800;
	private double mousePosX;
	private double mousePosY;
	private double mouseOldX;
	private double mouseOldY;
	private double mouseDeltaX;
	private double mouseDeltaY;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		HBox menuBox = gameMenu(primaryStage);

		camera.setNearClip(0.1);
		camera.setFarClip(1000.0);
		camera.setTranslateZ(-cameraDistance);
		cameraXform3.getChildren().add(camera);
		cameraXform3.setRotateZ(180.0);
		cameraXform2.getChildren().add(cameraXform3);
		cameraXform1.getChildren().add(cameraXform2);
		// cameraXform1.ry.setAngle(320.0);
		cameraXform1.rx.setAngle(80);

		Group gameAxis = gameAxis();

		setGame3d();
		game3dBox.getChildren().add(cameraXform1);
		game3dBox.getChildren().add(gameAxis);

		BorderPane root = new BorderPane();
		Group subRoot3d = new Group();
		Group subRoot2d = new Group();

		Scene scene = new Scene(root, 900, 750, Color.WHITE);
		SubScene subScene3d = new SubScene(subRoot3d, 900, 500, true, SceneAntialiasing.BALANCED);
		SubScene subScene2d = new SubScene(subRoot2d, 900, 200, true, SceneAntialiasing.BALANCED);

		subScene3d.setCamera(camera);
		subRoot3d.getChildren().add(game3dBox);

		game2dBox = setGame2d();
		game2dBox.setMinWidth(800);
		subRoot2d.getChildren().add(game2dBox);

		root.setTop(subScene3d);
		root.setCenter(subScene2d);
		root.setBottom(menuBox);

		handleMouse(scene, game3dBox);

		primaryStage.setTitle("Pylos");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private void handleMouse(Scene scene, final Node root) {
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				mousePosX = me.getSceneX();
				mousePosY = me.getSceneY();
				mouseOldX = me.getSceneX();
				mouseOldY = me.getSceneY();
			}
		});
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
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

	private Group gameAxis() {
		final PhongMaterial redMaterial = new PhongMaterial();
		redMaterial.setDiffuseColor(Color.DARKRED);
		redMaterial.setSpecularColor(Color.RED);

		final PhongMaterial greenMaterial = new PhongMaterial();
		greenMaterial.setDiffuseColor(Color.DARKGREEN);
		greenMaterial.setSpecularColor(Color.GREEN);

		final PhongMaterial blueMaterial = new PhongMaterial();
		blueMaterial.setDiffuseColor(Color.DARKBLUE);
		blueMaterial.setSpecularColor(Color.BLUE);

		final Box xAxis = new Box(240.0, 1, 1);
		final Box yAxis = new Box(1, 240.0, 1);
		final Box zAxis = new Box(1, 1, 240.0);

		xAxis.setMaterial(redMaterial);
		yAxis.setMaterial(greenMaterial);
		zAxis.setMaterial(blueMaterial);

		Group axisGroup = new Group();
		axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);

		return axisGroup;
	}

	private Ball3D createBall3D(double size, String color) {
		Ball3D ball = new Ball3D(40);
		ball.setBallColor(color);

		return ball;
	}

	private void setGame3d() {
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
		Ball3D ball = createBall3D(30, "white");
		ball.setTranslate("x", 40);
		ball.setTranslate("z", 40);
		Ball3D ball2 = createBall3D(30, "white");
		ball2.setTranslate("x", -40);
		ball2.setTranslate("z", 40);
		Ball3D ball3 = createBall3D(30, "white");
		ball3.setTranslate("x", 40);
		ball3.setTranslate("z", -40);
		Ball3D ball4 = createBall3D(30, "white");
		ball4.setTranslate("x", -40);
		ball4.setTranslate("z", -40);
		Ball3D ball5 = createBall3D(30, "white");
		ball5.setTranslate("x", 40);
		ball5.setTranslate("z", 120);
		Ball3D ball6 = createBall3D(30, "white");
		ball6.setTranslate("x", -40);
		ball6.setTranslate("z", 120);
		Ball3D ball7 = createBall3D(30, "black");
		ball7.setTranslate("x", 40);
		ball7.setTranslate("z", -120);
		Ball3D ball8 = createBall3D(30, "black");
		ball8.setTranslate("x", -40);
		ball8.setTranslate("z", -120);
		Ball3D ball9 = createBall3D(30, "black");
		ball9.setTranslate("x", 120);
		ball9.setTranslate("z", 40);

		game3dBox.getChildren().add(boxXform);
		game3dBox.getChildren().add(ball.getXformBall());
		game3dBox.getChildren().add(ball2.getXformBall());
		game3dBox.getChildren().add(ball3.getXformBall());
		game3dBox.getChildren().add(ball4.getXformBall());
		game3dBox.getChildren().add(ball5.getXformBall());
		game3dBox.getChildren().add(ball6.getXformBall());
		game3dBox.getChildren().add(ball7.getXformBall());
		game3dBox.getChildren().add(ball8.getXformBall());
		game3dBox.getChildren().add(ball9.getXformBall());
	}

	private BorderPane setGame2d() {
		BorderPane game2dPane = new BorderPane();

		// BorderPane LEFT content
		player1 = new Label("PLAYER 1");
		player1.setPadding(new Insets(5, 5, 5, 5));
		player1.setStyle("-fx-background-color: grey; -fx-border-width: 1; -fx-border-color: black");
		player2 = new Label("PLAYER 2");
		player2.setPadding(new Insets(5, 5, 5, 5));
		player2.setStyle("-fx-border-width: 1; -fx-border-color: black");
		VBox playersBox = new VBox();
		playersBox.setAlignment(Pos.CENTER);
		playersBox.setSpacing(10);
		playersBox.getChildren().addAll(player1, player2);

		game2dPane.setLeft(playersBox);

		// BorderPane CENTER content
		Group circles = new Group();

		int posX = 0;
		int posY = 0;
		for (int i = 1; i <= 16; i++) {
			if ((i - 1) % 4 == 0) {
				posX = 0;
				posY += 41;
			}

			Circle2D circle = new Circle2D(20.0f);
			circle.setPosX(posX);
			circle.setPosY(posY);
			circle.setStroke(Color.BLACK, 2);
			circle.setColor(Color.WHITE);

			circles.getChildren().add(circle.getCircle());

			posX += 41;
		}

		game2dPane.setCenter(circles);

		return game2dPane;
	}

	private HBox gameMenu(Stage primaryStage) {
		Label newGameLabel = new Label("New game");
		newGameLabel.setAlignment(Pos.CENTER);
		newGameLabel.setPrefWidth(300);
		newGameLabel.setWrapText(true);

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
				popupRules.getIcons().add(new Image("file:img/rules.png"));
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

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {

				// changer le comportement du bouton
				event.consume();

				// les boutons
				ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
				ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

				// show close dialog
				Alert alert = new Alert(null, "Are you sure?", yes, no);
				alert.setTitle("='(");
				alert.initOwner(primaryStage);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == yes) {
					Platform.exit();
				}
			}
		});

		return menuBox;

	}
}
