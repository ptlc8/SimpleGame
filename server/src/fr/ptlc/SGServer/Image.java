package fr.ptlc.SGServer;

public class Image {
	
	public String name;
	
	public String suffix;
	
	public float rotation; // en PI.rad
	
	public boolean flip;
	
	public boolean onGround;
	
	public Image(String name) {
		this(name, "", 0f, false);
	}
	
	public Image(String name, String suffix) {
		this(name, suffix, 0f, false);
	}
	
	/*
	 * rotation en PI.rad
	 */
	public Image(String name, float rotation) {
		this(name, "", rotation , false);
	}
	
	/*
	 * rotation en Pi.rad
	 */
	public Image(String name, String suffix, float rotation, boolean flip) {
		this.name = name;
		this.suffix = suffix;
		this.rotation = rotation;
		this.flip = flip;
	}
	
	public Image setOnGround(boolean onGround) {
		this.onGround = onGround;
		return this;
	}
	
	public String toString() {
		return name + suffix + " " + rotation + " " + flip;
	}
	
}
