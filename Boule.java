
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

}
