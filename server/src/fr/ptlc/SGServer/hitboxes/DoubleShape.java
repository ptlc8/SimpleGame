package fr.ptlc.SGServer.hitboxes;

/**
 * Pour pouvoir gérer la mainShape des Hitbox indépendemment de la forme de collision
 * @author <a href="http://ptlc.000webhostapp.com">PTLC_</a>
 * @see Hitbox
 */
public class DoubleShape implements Hitbox, Cloneable {
	
	private final Rectangle mainShape; 
	
	private final Shape shape;
	
	public DoubleShape(Rectangle mainShape, Shape shape) {
		this.mainShape = mainShape;
		this.shape = shape;
	}
	
	protected DoubleShape() {
		this.mainShape = new Rectangle();
		this.shape = new Rectangle();
	}
	
	@Override
	public boolean intersects(int x, int y, Hitbox other, int x2, int y2) {
		if (other == null) return false;
		if (other instanceof ShapesGroup)
			return intersects(x, y, (ShapesGroup)other, x2, y2);
		else if (other instanceof Shape)
			return intersects(x, y, (Shape)other, x2, y2);
		else
			return intersects(x, y, ((DoubleShape)other).getShape(), x2, y2);
	};
	
	public boolean intersects(int x, int y, ShapesGroup other, int x2, int y2) {
		if (other == null) return false;
		if (!other.getMainShape().intersects(x2, y2, shape, x, y)) return false;
		for (Shape otherShape : other.getShapes())
			if (shape.intersects(x, y, otherShape, x2, y2)) return true;
		return false;
	}
	
	public boolean intersects(int x, int y, Shape aShape, int x2, int y2) {
		if (aShape == null) return false;
		if (shape.intersects(x, y, aShape, x2, y2))
			return true;
		return false;
	}
	
	@Override
	public Rectangle getMainShape() {
		return mainShape;
	}
	
	public Shape getShape() {
		return shape;
	}
	
	@Override
	public String toString() {
		return "{\"mainShape\":"+mainShape.toString()+", \"shape\":"+shape.toString()+"}";
	}
	
}
