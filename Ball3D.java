import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import moleculesampleapp.Xform;

public class Ball3D {
	private Sphere sphere;
	private Xform ball;
	final PhongMaterial whiteMaterial;
	final PhongMaterial blackMaterial;
	
	public Ball3D(double size){
		whiteMaterial = new PhongMaterial();
		whiteMaterial.setDiffuseColor(Color.WHITE);
		whiteMaterial.setSpecularColor(Color.LIGHTBLUE);
		
		blackMaterial = new PhongMaterial();
		blackMaterial.setDiffuseColor(Color.BROWN);
		blackMaterial.setSpecularColor(Color.LIGHTGRAY);
		
		sphere = new Sphere(size);
		sphere.setMaterial(whiteMaterial);
		ball = new Xform();
		ball.getChildren().add(sphere);
	}
	
	public Xform getXformBall(){
		return ball;
	}
	
	public void setBallColor(String color){
		if (color.equals("white"))	
			sphere.setMaterial(whiteMaterial);
		else
			sphere.setMaterial(blackMaterial);
	}
	
	public void setTranslate(String axe, int value){
		if (axe.equals("x"))	
			ball.setTx(value);
		else if (axe.equals("y"))
			ball.setTy(value);
		else
			ball.setTz(value);
	}
}
