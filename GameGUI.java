import moleculesampleapp.Xform;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

public class GameGUI extends Application {
	final PerspectiveCamera camera = new PerspectiveCamera(true);
	final Xform cameraXform1 = new Xform();
	final Xform cameraXform2 = new Xform();
	final Xform cameraXform3 = new Xform();
	final double cameraDistance = 850;
	double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;
	
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
		cameraXform1.ry.setAngle(320.0);
		cameraXform1.rx.setAngle(40);
		
		Group gameAxis = gameAxis();
		
		Group field3D = game3d();
		field3D.getChildren().add(cameraXform1);
		field3D.getChildren().add(gameAxis);
		
		BorderPane root = new BorderPane();
		Group subRoot = new Group();
		
		Scene scene = new Scene(root, 900, 800, Color.WHITE);
		SubScene subScene = new SubScene(subRoot, 900, 500, true, SceneAntialiasing.BALANCED);
		
		subScene.setCamera(camera);
		subRoot.getChildren().add(field3D);
		root.setTop(subScene);
		root.setBottom(menuBox);
		
		handleMouse(scene, field3D);
		
		primaryStage.setTitle("Pylos");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	private void handleMouse(Scene scene, final Node root) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
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
                    cameraXform1.ry.setAngle(cameraXform1.ry.getAngle() - mouseDeltaX * modifierFactor * modifier * 2.0);  // +
                    cameraXform1.rx.setAngle(cameraXform1.rx.getAngle() + mouseDeltaY * modifierFactor * modifier * 2.0);  // -
                }
                else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaX * modifierFactor * modifier;
                    camera.setTranslateZ(newZ);
                }
                else if (me.isMiddleButtonDown()) {
                    cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX * modifierFactor * modifier * 0.3);  // -
                    cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY * modifierFactor * modifier * 0.3);  // -
                }
            }
        });
    }
	
	private Group gameAxis(){
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
	
	private Ball3D createBall3D(double size, String color){
		Ball3D ball = new Ball3D(40);
		ball.setBallColor(color);
		
		return ball;
	}
	
	private Group game3d(){
		final PhongMaterial greyMaterial = new PhongMaterial();
		greyMaterial.setDiffuseColor(Color.DARKGREY);
		greyMaterial.setSpecularColor(Color.GREY);
	       
		Box box = new Box(400, 10, 400);
		box.setMaterial(greyMaterial);
		
		Xform boxXform = new Xform();
		boxXform.getChildren().add(box);
		boxXform.setTranslateY(-40);
		
		Ball3D ball = createBall3D(30, "black");
		Ball3D ball2 = createBall3D(30, "white");
		ball2.setTranslate("x", 80);
		Ball3D ball3 = createBall3D(30, "black");
		ball3.setTranslate("z", 80);
		Ball3D ball4 = createBall3D(30, "white");
		ball4.setTranslate("x", 80);
		ball4.setTranslate("z", 80);	
		
		Group game3dBox = new Group();
		game3dBox.getChildren().add(boxXform);
		game3dBox.getChildren().add(ball.getXformBall());
		game3dBox.getChildren().add(ball2.getXformBall());
		game3dBox.getChildren().add(ball3.getXformBall());
		game3dBox.getChildren().add(ball4.getXformBall());
		
		return game3dBox;
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
		
		Label helpLabel = new Label("Help");
		helpLabel.setAlignment(Pos.CENTER);
		helpLabel.setPrefWidth(300);
		helpLabel.setWrapText(true);
		
		Menu helpMenu = new Menu();
		helpMenu.setGraphic(helpLabel);
		
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		menuBar.getMenus().addAll(newGameMenu, rulesMenu, helpMenu);
		
		HBox menuBox = new HBox();
		menuBox.getChildren().addAll(menuBar);
		
		return menuBox;
	}
}
