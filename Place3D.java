import javafx.scene.Group;

public class Place3D implements Runnable {
	Group group;
	Ball3D boule3D;

	public Place3D(Ball3D boule3D, Group group) {
		this.group = group;
		this.boule3D = boule3D;
	}

	@Override
	public void run() {
		group.getChildren().add(this.boule3D.getXformBall());
		
	}

}
