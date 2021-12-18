package fr.ptlc.SGServer.entities;

import java.util.HashSet;
import java.util.Set;

import fr.ptlc.SGServer.Image;
import fr.ptlc.SGServer.game.GameManager;
import fr.ptlc.SGServer.hitboxes.Hitbox;
import fr.ptlc.SGServer.hitboxes.Rectangle;

public abstract class Entity {
	
	private static int kId = 0;
	
	private final int id;
	
	private transient final GameManager gm;
	
	protected float x;
	
	protected float y;
	
	protected final Hitbox hitbox;
	
	protected boolean visible = true;
	
	protected Image image; // seulement pour le client
	
	public Entity(final GameManager gm, float x, float y, Hitbox hitbox, Image image) {
		id = kId++;
		this.gm = gm;
		this.x = x;
		this.y = y;
		this.hitbox = hitbox;
		this.image = image;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	public int getId() {
		return id;
	}
	
	public GameManager getGM() {
		return gm;
	}
	
	public int getIntX() {
		return (int)x;
	}
	
	public int getIntY() {
		return (int)y;
	}
	
	public abstract void move();
	
	/*
	 * Supprime l'entité de la partie
	 */
	public void destroy() {
		getGM().remove(this);
		onDestroy();
	}
	
	protected abstract void onDestroy();
	
	/*private int getXCorner() {
		return ((int)x)-hitbox.getWidth()/2;
	}
	
	private int getYCorner() {
		return ((int)y)-hitbox.getHeight()/2;
	}
	
	private int getXCorner(float decalX) {
		return ((int)(x+decalX))-hitbox.getWidth()/2;
	}
	
	private int getYCorner(float decalY) {
		return ((int)(y+decalY))-hitbox.getHeight()/2;
	}
	
	private Rectangle getRectangle1() {
		return new Rectangle(getXCorner(), getYCorner(), hitbox.getWidth(), hitbox.getHeight());
	}*/
	
	public Rectangle getRectangle() {
		Rectangle mainShape = hitbox.getMainShape();
		return new Rectangle(mainShape.x+getIntX(), mainShape.y+getIntY(), mainShape.width, mainShape.height);
	}
	
	/*private Rectangle getRectangle(float decalX, float decalY) {
		Rectangle mainShape = hitbox.getMainShape();
		return new Rectangle(mainShape.x+(int)(x+decalX), mainShape.y+(int)(y+decalY), mainShape.width, mainShape.height);
	}
	
	private int getArea() {
		return hitbox.getWidth()*hitbox.getHeight();
	}*/
	
	public Set<Entity> getCollidedEntities(Class<?>[] focus) {
		Set<Entity> collidedEntities = new HashSet<Entity>();
		for (Entity entity : getGM().getEntities()) {
			if (entity.equals(this)) continue;
			boolean isFocusable = false;
			for (Class<?> c : focus) {
				if (c.isInstance(entity))
					isFocusable = true;
			}
			if (isFocusable && testCollision(entity))
				collidedEntities.add(entity);
		}
		return collidedEntities;
	}
	
	@SuppressWarnings("unchecked")
	public <E extends Entity> Set<E> getCollidedEntities(Class<E> focus) {
		Set<E> collidedEntities = new HashSet<E>();
		for (Entity entity : getGM().getEntities()) {
			if (entity.equals(this)) continue;
			if (focus.isInstance(entity) && testCollision(entity))
				collidedEntities.add((E)entity);
		}
		return collidedEntities;
	}
	
	public boolean testCollision(Entity entity) {
		return entity.hitbox.intersects(entity.getIntX(), entity.getIntY(), hitbox, getIntX(), getIntY());
	}
	
	// decalX&decalY -> décalage de l'entité à tester, pour prédire des collisions
	
	public boolean testCollisionMapAndTangibles() {
		return testCollisionMapAndTangibles(0, 0);
	}
	public boolean testCollisionMapAndTangibles(float decalX, float decalY) {
		return testCollisionMap(decalX, decalY) || testCollisionTangibles(decalX, decalY);
	}
	
	public boolean testCollisionMap() {
		return testCollisionMap(0, 0);
	}
	public boolean testCollisionMap(float decalX, float decalY) {
		final int xOnGameMap = (int)(x + decalX) / 64;
		final int yOnGameMap = (int)(y + decalY) / 64;
		for (int i = xOnGameMap-1; i <= xOnGameMap+1; i++)
			for (int j = yOnGameMap-1; j <= yOnGameMap+1; j++)
				if (hitbox.intersects((int)(x+decalX), (int)(y+decalY), getGM().getMap().getHitbox(i, j), i*64, j*64))
					//System.out.println("collision : " +x+";"+y+" -x-> "+(x+decalX)+";"+(y+decalY)+"");
					return true;
		//System.out.println("pas collision : " +x+";"+y+" ---> "+(x+decalX)+";"+(y+decalY)+"");
		return false;
	}
	
	public boolean testCollisionTangibles() {
		return testCollisionTangibles(0, 0);
	}
	public boolean testCollisionTangibles(float decalX, float decalY) {
		for (Entity entity : getGM().getEntities())
			if (entity instanceof Tangible)
				if (hitbox.intersects((int)(x+decalX), (int)(y+decalY), entity.hitbox, (int)entity.x, (int)entity.y))
					return true;
		return false;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisibility(boolean visible) {
		this.visible = visible;
	}
	
	/*private Point pixelPerfect(Entity entity) {
		return pixelPerfect(hitbox, getXCorner(), getYCorner(), entity.hitbox, entity.getRectangle1(), entity.getXCorner(), entity.getYCorner());
	}
	
	@SuppressWarnings("unused")
	private Point pixelPerfect(BufferedImage hitbox2, Rectangle rect2, int x2Corner, int y2Corner) {
		return pixelPerfect(hitbox, getXCorner(), getYCorner(), hitbox2, rect2, x2Corner, y2Corner);
	}
	
	private Point pixelPerfect(BufferedImage hitbox2, Rectangle rect2, int x2Corner, int y2Corner, float decalX, float decalY) {
		return pixelPerfect(hitbox, getXCorner(decalX), getYCorner(decalY), hitbox2, rect2, x2Corner, y2Corner);
	}
	
	private static Point pixelPerfect(final BufferedImage hitbox1, int x1Corner, int y1Corner, BufferedImage hitbox2, Rectangle rect2, int x2Corner, int y2Corner) {
		final int hitbox1W = hitbox1.getWidth();
		final int hitbox1H = hitbox1.getHeight();
		for (int i = 0; i < hitbox1W; i++)
			for (int j = 0; j < hitbox1H; j++)
				//test alpha ; canal 3 = canal alpha
				if (hitbox1.getRaster().getPixel(i, j, new int[4])[3] != 0)
					//test existence du point sur l'hitbox2
					if (rect2.contains(new Point(i+x1Corner, j+y1Corner)))
						if (hitbox2.getRaster().getPixel(i+x1Corner-x2Corner, j+y1Corner-y2Corner, new int[4])[3] != 0)
							return new Point(x1Corner+i, y1Corner+j);
		return null;
	}
	
	private static BufferedImage defaultImage = null;
	
	protected static BufferedImage getBImage(String imageName) {
		try {
			return ImageIO.read(new Object().getClass().getResource(imageName));
		} catch (IllegalArgumentException | IOException e) {
			//e.printStackTrace();
			System.out.println("Ressource manquante : " + imageName);
			if (defaultImage == null) {
				defaultImage = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
				Graphics2D dGr = defaultImage.createGraphics();
				dGr.setColor(Color.BLACK);
				dGr.drawOval(8, 8, 48, 48);
				dGr.dispose();
			}
			return defaultImage;
		}
	}
	
	protected static BufferedImage rotate(BufferedImage image, float direction) {
		if (direction == 0f) return image;
		double centerX = image.getWidth() / 2;
		double centerY = image.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(-direction*Math.PI, centerX, centerY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(image, null);
	}*/
	
}
