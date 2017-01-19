import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Boule extends Ball {
	private Circle2D boule2D;
	private Ball3D boule3D;

	public Boule(boolean isBlack) {
		super(isBlack);
		
		boule2D = new Circle2D(20.0f);
		boule3D = new Ball3D(40);
		
		if (isBlack)
			boule3D.setBallColor("black");
		else
			boule3D.setBallColor("white");
		
		boule2D.getCircle().setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				boule2D.setColor(Color.RED);
				
			}
		});
	}

	public Circle2D getBoule2D() {
		return boule2D;
	}

	public void setBoule2D(Circle2D boule2d) {
		boule2D = boule2d;
	}

	public Ball3D getBoule3D() {
		return boule3D;
	}

	public void setBoule3D(Ball3D boule3d) {
		boule3D = boule3d;
	}
	
	public void placeOnGroup3D(Group group){
		group.getChildren().add(boule3D.getXformBall());
	}
	public void removeOnGroup3D(Group group){
		group.getChildren().remove(boule3D.getXformBall());
	}
	public void place3D(int lv, int x, int y){
		if (lv == 3)
			setTranslate3D(120-(80*x), 0, 120-(80*y));
		if (lv == 2)
			setTranslate3D(80-(80*x), 40, 80-(80*y));
		if (lv == 1)
			setTranslate3D(120-(80*x), 120, 120-(80*y));
		if (lv == 0)
			setTranslate3D(0, 160, 0);
	}
	public void setTranslate3D(int x,int y,int z){
		boule3D.setTranslate("x", x);
		boule3D.setTranslate("y", y);
		boule3D.setTranslate("z", z);
	}
}