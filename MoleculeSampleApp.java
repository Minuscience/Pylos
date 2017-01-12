import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
<<<<<<< HEAD
import javafx.scene.shape.Rectangle;
=======
>>>>>>> af3c5884acf774fec9abe91864ae79cb98eb20e3
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import moleculesampleapp.Xform;
/**
 * MoleculeSampleApp
 */
public class MoleculeSampleApp extends Application {

	final Group root = new Group();
	final Group axisGroup = new Group();
	final Xform world = new Xform();
	final Xform moleculeGroup = new Xform();
	final PerspectiveCamera camera = new PerspectiveCamera(true);
	final Xform cameraXform = new Xform();
	final Xform cameraXform2 = new Xform();
	final Xform cameraXform3 = new Xform();
<<<<<<< HEAD
	final double cameraDistance = 850;
=======
	final double cameraDistance = 450;
>>>>>>> af3c5884acf774fec9abe91864ae79cb98eb20e3
    private Timeline timeline;
    boolean timelinePlaying = false;
    double ONE_FRAME = 1.0/24.0;
    double DELTA_MULTIPLIER = 200.0;
    double CONTROL_MULTIPLIER = 0.1;
    double SHIFT_MULTIPLIER = 0.1;
    double ALT_MULTIPLIER = 0.5;
        
    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;
    
<<<<<<< HEAD
    private static final double HYDROGEN_ANGLE = 80.0;
=======
    private static final double HYDROGEN_ANGLE = 104.5;
>>>>>>> af3c5884acf774fec9abe91864ae79cb98eb20e3

	private void buildAxes() {
		System.out.println("buildAxes()");
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

		axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
		world.getChildren().addAll(axisGroup);
	}

	private void buildCamera() {
		root.getChildren().add(cameraXform);
		cameraXform.getChildren().add(cameraXform2);
		cameraXform2.getChildren().add(cameraXform3);
		cameraXform3.getChildren().add(camera);
		cameraXform3.setRotateZ(180.0);

		camera.setNearClip(0.1);
		camera.setFarClip(10000.0);
		camera.setTranslateZ(-cameraDistance);
		cameraXform.ry.setAngle(320.0);
		cameraXform.rx.setAngle(40);
	}

	private void buildScene() {
		System.out.println("buildScene");
		root.getChildren().add(world);
	}

	private void buildBoard() {
		 
	       final PhongMaterial redMaterial = new PhongMaterial();
	       redMaterial.setDiffuseColor(Color.DARKRED);
	       redMaterial.setSpecularColor(Color.RED);
	 
	       final PhongMaterial whiteMaterial = new PhongMaterial();
	       whiteMaterial.setDiffuseColor(Color.WHITE);
	       whiteMaterial.setSpecularColor(Color.LIGHTBLUE);
	 
	       final PhongMaterial greyMaterial = new PhongMaterial();
	       greyMaterial.setDiffuseColor(Color.DARKGREY);
	       greyMaterial.setSpecularColor(Color.GREY);
	 
<<<<<<< HEAD
//	       Xform moleculeXform = new Xform();
//	       Xform oxygenXform = new Xform();
//	       Xform hydrogen1SideXform = new Xform();
//	       Xform hydrogen1Xform = new Xform();
//	       Xform hydrogen2SideXform = new Xform();
//	       Xform hydrogen2Xform = new Xform();
//
	      Sphere sphere = new Sphere(30.0);
	      sphere.setMaterial(whiteMaterial);
	      
	      Xform sphereXform = new Xform();
	      sphereXform.getChildren().add(sphere);
	      
	      Sphere sphere2 = new Sphere(30.0);
	      sphere2.setMaterial(greyMaterial);
	      
	      Xform sphere2Xform = new Xform();
	      sphere2Xform.getChildren().add(sphere2);
	      sphere2Xform.setTx(60);
	      
	      Sphere sphere3 = new Sphere(30.0);
	      sphere.setMaterial(whiteMaterial);
	      
	      Xform sphere3Xform = new Xform();
	      sphere3Xform.getChildren().add(sphere3);
	      sphere3Xform.setTz(60);
	      
//	      Sphere hydrogen1Sphere = new Sphere(40.0);
//	      hydrogen1Sphere.setMaterial(whiteMaterial);
//
//	      Sphere hydrogen2Sphere = new Sphere(40.0);
//	      hydrogen2Sphere.setMaterial(whiteMaterial);
//
//	      moleculeXform.getChildren().add(oxygenXform);
//	      moleculeXform.getChildren().add(hydrogen1SideXform);
//	      moleculeXform.getChildren().add(hydrogen2SideXform);
//	      oxygenXform.getChildren().add(oxygenSphere);
//	      hydrogen1SideXform.getChildren().add(hydrogen1Xform);
//	      hydrogen2SideXform.getChildren().add(hydrogen2Xform);
//	      hydrogen1Xform.getChildren().add(hydrogen1Sphere);
//	      hydrogen2Xform.getChildren().add(hydrogen2Sphere);
//	 
//	      hydrogen1Xform.setTx(80.0);
//	      hydrogen2Xform.setTx(80.0);
//	      hydrogen2SideXform.setTranslateZ(HYDROGEN_ANGLE);
	       
	       Box box = new Box(400, 10, 400);
	       box.setMaterial(whiteMaterial);

	       Xform boxXform = new Xform();
	       boxXform.getChildren().add(box);
	       boxXform.setTranslateY(-35);
	       
	      moleculeGroup.getChildren().add(boxXform);
	      moleculeGroup.getChildren().add(sphereXform);
	      moleculeGroup.getChildren().add(sphere2Xform);
	      moleculeGroup.getChildren().add(sphere3Xform);
=======
	      
	 
	       Xform moleculeXform = new Xform();
	       Xform oxygenXform = new Xform();
	       Xform hydrogen1SideXform = new Xform();
	       Xform hydrogen1Xform = new Xform();
	       Xform hydrogen2SideXform = new Xform();
	       Xform hydrogen2Xform = new Xform();

	      Sphere oxygenSphere = new Sphere(40.0);
	      oxygenSphere.setMaterial(redMaterial);

	      Sphere hydrogen1Sphere = new Sphere(30.0);
	      hydrogen1Sphere.setMaterial(whiteMaterial);
	      hydrogen1Sphere.setTranslateX(0.0);

	      Sphere hydrogen2Sphere = new Sphere(30.0);
	      hydrogen2Sphere.setMaterial(whiteMaterial);
	      hydrogen2Sphere.setTranslateZ(0.0);

	

	      moleculeXform.getChildren().add(oxygenXform);
	      moleculeXform.getChildren().add(hydrogen1SideXform);
	      moleculeXform.getChildren().add(hydrogen2SideXform);
	      oxygenXform.getChildren().add(oxygenSphere);
	      hydrogen1SideXform.getChildren().add(hydrogen1Xform);
	      hydrogen2SideXform.getChildren().add(hydrogen2Xform);
	      hydrogen1Xform.getChildren().add(hydrogen1Sphere);
	      hydrogen2Xform.getChildren().add(hydrogen2Sphere);
	 
	      hydrogen1Xform.setTx(100.0);
	      hydrogen2Xform.setTx(100.0);
	      hydrogen2SideXform.setRotateY(HYDROGEN_ANGLE);

	      moleculeGroup.getChildren().add(moleculeXform);
>>>>>>> af3c5884acf774fec9abe91864ae79cb98eb20e3

	      world.getChildren().addAll(moleculeGroup);
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
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX*modifierFactor*modifier*2.0);  // +
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY*modifierFactor*modifier*2.0);  // -
                }
                else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaX*modifierFactor*modifier;
                    camera.setTranslateZ(newZ);
                }
                else if (me.isMiddleButtonDown()) {
                    cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX*modifierFactor*modifier*0.3);  // -
                    cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY*modifierFactor*modifier*0.3);  // -
                }
            }
        });
    }
    
    
	@Override
	public void start(Stage primaryStage) {
		System.out.println("start");
<<<<<<< HEAD
=======
	 
		Sphere mySphere = new Sphere(50);
>>>>>>> af3c5884acf774fec9abe91864ae79cb98eb20e3

		buildScene();
		buildBoard();
		buildCamera();
		buildAxes();
	
		Scene scene = new Scene(root, 1024, 768, true);
		scene.setFill(Color.GREY);
<<<<<<< HEAD
        handleMouse(scene, world);
=======
		  ;
	        handleMouse(scene, world);
>>>>>>> af3c5884acf774fec9abe91864ae79cb98eb20e3
	
		primaryStage.setTitle("Molecule Sample Application");
		primaryStage.setScene(scene);
		primaryStage.show();
		scene.setCamera(camera);
<<<<<<< HEAD
=======
	
>>>>>>> af3c5884acf774fec9abe91864ae79cb98eb20e3
	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support. NetBeans ignores main().
	 *
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		System.setProperty("prism.dirtyopts", "false");
		launch(args);
	}
}