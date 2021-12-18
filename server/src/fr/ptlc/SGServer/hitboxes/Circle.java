package fr.ptlc.SGServer.hitboxes;

public class Circle implements Shape {
	
	public int x;
	public int y;
	public float r;
	
	public Circle(int x, int y, float r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	public Circle() {
		this(0,0,0);
	}
	
	@Override
	public boolean intersects(int x, int y, Rectangle o, int x2, int y2) {
		if (o == null) return false;
		// temporary variables to set edges for testing
		float testX = this.x+x;
		float testY = this.y+y;
		// which edge is closest?
		if (this.x+x < o.x+x2)               testX = o.x+x2;      // test left edge
		else if (this.x+x > o.x+x2+o.width)  testX = o.x+x2+o.width;   // right edge
		if (this.y+y < o.y+y2)               testY = o.y+y2;      // top edge
		else if (this.y+y > o.y+y2+o.height) testY = o.y+y2+o.height;   // bottom edge
		// get distance from closest edges
		float distX = this.x+x-testX;
		float distY = this.y+y-testY;
		float distance = (float) Math.sqrt( (distX*distX) + (distY*distY) );
		// if the distance is less than the radius, collision!
		if (distance <= this.r)
			return true;
		return false;
	}

	@Override
	public boolean intersects(int x, int y, Circle o, int x2, int y2) {
		if (o == null) return false;
		return Math.pow(this.x+x-o.x-x2, 2)+Math.pow(this.y+y-o.y-y2, 2) < Math.pow(this.r+o.r, 2);
	}
	
	@Override
	public Rectangle getMainShape() {
		return new Rectangle((int)(x-r), (int)(y-r), (int)(r*2), (int)(r*2));
	}
	
	/*// unused
	public boolean intersectsPoint(int x, int y, int xPoint, int yPoint) {
		return getMinX(x, y, yPoint) < xPoint && xPoint < getMaxX(x, y, yPoint) ;
	}
	
	// unused
	public double getMaxX(int x, int y, int yPoint) {
		return Math.cos(Math.asin((yPoint-(this.y+y))/r))*r+this.x+x;
	}
	
	// unused
	public double getMinX(int x, int y, int yPoint) {
		return -Math.cos(Math.asin((yPoint-(this.y+y))/r))*(r)+this.x+x;
	}*/

}
