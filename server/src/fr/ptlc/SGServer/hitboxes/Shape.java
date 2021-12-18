package fr.ptlc.SGServer.hitboxes;

public interface Shape extends Hitbox {
	
	public boolean intersects(int x, int y, Rectangle other, int x2, int y2);
	
	public boolean intersects(int x, int y, Circle other, int x2, int y2);
	
	public default boolean intersects(int x, int y, Shape other, int x2, int y2) {
		if (other == null) return false;
		if (other instanceof Rectangle)
			return intersects(x, y, (Rectangle)other, x2, y2);
		if (other instanceof Circle)
			return intersects(x, y, (Circle)other, x2, y2);
		System.err.println("Type de collision non pris en compte : "+other.getClass().getCanonicalName());
		return false;
	}
	
	public default boolean intersects(int x, int y, ShapesGroup shapesGroup, int x2, int y2) {
		if (shapesGroup == null) return false;
		if (!intersects(x, y, shapesGroup.getMainShape(), x2, y2))
			return false;
		for (Shape o : shapesGroup.getShapes())
			if (intersects(x, y, o, x2, y2))
				return true;
		
		return false;
	}
	
	@Override
	public default boolean intersects(int x, int y, Hitbox other, int x2, int y2) {
		if (other == null) return false;
		if (other instanceof ShapesGroup)
			return intersects(x, y, (ShapesGroup)other, x2, y2);
		else if (other instanceof Shape)
			return intersects(x, y, (Shape)other, x2, y2);
		else
			return intersects(x, y, ((DoubleShape)other).getShape(), x2, y2);
	}
	
}
