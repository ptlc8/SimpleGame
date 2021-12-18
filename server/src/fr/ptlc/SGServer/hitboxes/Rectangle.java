package fr.ptlc.SGServer.hitboxes;

public class Rectangle implements Shape {
	
	public int x, y;
	public int width, height;
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Rectangle() {
		this(0,0,0,0);
	}
	
	@Override
	public boolean intersects(int x, int y, Rectangle o, int x2, int y2) {
		if (o == null) return false;
		return (this.x+x < o.x+x2+o.width && o.x+x2 < this.x+x+width) && (this.y+y < o.y+y2+o.height && o.y+y2 < this.y+y+height);
	}

	@Override
	public boolean intersects(int x, int y, Circle o, int x2, int y2) {
		if (o == null) return false;
		return o.intersects(x2, y2, this, x, y);
	}
	
	@Override
	public Rectangle getMainShape() {
		return this;
	}
	
}
