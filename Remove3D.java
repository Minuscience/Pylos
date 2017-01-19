import javafx.scene.Group;

public class Remove3D implements Runnable {
	Group group;
	Ball3D boule3D;

	public Remove3D(Ball3D boule3D, Group group) {
		this.group = group;
		this.boule3D = boule3D;
	}

	@Override
	public void run() {
		group.getChildren().remove(this.boule3D.getXformBall());
		
	}

}
