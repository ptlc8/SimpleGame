package fr.ptlc.SGServer.hitboxes;

public interface Hitbox {

	public boolean intersects(int x, int y, Hitbox other, int x2, int y2);
	
	public Rectangle getMainShape();
	
}
