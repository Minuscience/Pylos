import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Circle2D {
	private Circle circle;
	private int posX;
	private int posY;
	
	public Circle2D(float radius){
		circle = new Circle();
		circle.setRadius(radius);
	}
	
	public Circle getCircle(){
		return circle;
	}
	
	public void setColor(Color c){
		circle.setFill(c);
	}
	
	public void setPosX(int posX){
		this.posX = posX;
		circle.setCenterX(posX);
	}
	
	public void setPosY(int posY){
		this.posY = posY;
		circle.setCenterY(posY);
	}
	
	public void setStroke(Color c, int width){
		circle.setStroke(c);
		circle.setStrokeWidth(width);
	}
}
