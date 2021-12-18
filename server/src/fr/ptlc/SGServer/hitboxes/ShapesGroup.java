package fr.ptlc.SGServer.hitboxes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import flexjson.JSONDeserializer;

public class ShapesGroup implements Hitbox, Cloneable {
	
	private final Rectangle mainShape; 
	
	private final Shape[] shapes;
	
	public static ShapesGroup getFromJSON(String json) {
		return new JSONDeserializer<ShapesGroup>().deserialize(json);
	}
	
	/**
	 * Pour pouvoir importer des Hitbox au format JSON contenue dans un fichier de l'archive.
	 * @param fileName : nom du fichier contenant la Hitbox au format JSON
	 * et contenu dans le package hitboxes (i.e. : arrow.json pour /hitboxes/arrow.json)
	 * @return la Hitbox contenu dans le fichier
	 * ou une Hitbox nulle si le fichier n'a pas été trouvé
	 * @author <a href="http://ptlc.000webhostapp.com">PTLC_</a>
	 * @see ShapesGroup
	 */
	public static ShapesGroup getFromFile(String fileName) {
		try {
			System.out.println("Récupération de la hibox " + fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(new Circle().getClass().getClassLoader().getResourceAsStream("hitboxes/"+fileName)));
			String json = "";
			String line;
			while ((line = br.readLine()) != null)
				json += line + "\n";
			br.close();
			return getFromJSON(json);
		} catch (IllegalArgumentException | IOException | NullPointerException e) {
			// e.printStackTrace();
			System.err.println("Impossible de trouver le fichier hitboxes/" + fileName);
			return new ShapesGroup(new Rectangle(), new Shape[0]);
		}
	
	}
	
	public ShapesGroup(Rectangle mainShape, Shape... shapes) {
		this.mainShape = mainShape;
		this.shapes = shapes;
	}
	
	public ShapesGroup() {
		this.mainShape = new Rectangle(0, 0, 0, 0);
		this.shapes = new Shape[0];
	}
	
	public ShapesGroup newWithRotation(final float rotation) {
		ShapesGroup nouv;
		try {
			nouv = (ShapesGroup) this.clone();
		} catch (CloneNotSupportedException e) {
			System.err.println(this.getClass().getCanonicalName() + " ne supporte pas le clonage");
			e.printStackTrace();
			return this;
		}
		for (Shape shape : nouv.getShapes()) {
			if (shape instanceof Circle) {
				Circle c = (Circle)shape;
				if (c.x == 0 && c.y == 0) continue;
				double a = Math.atan2(c.x, c.y) + rotation;
				double r = Math.sqrt(Math.pow(c.x, 2) + Math.pow(c.y, 2));
				c.x = (int) (Math.cos(a) * r);
				c.y = (int) (Math.sin(a) * r);
			} else if (shape instanceof Rectangle) {
				Rectangle r = (Rectangle)shape;
				int midX = r.x+r.width/2;
				int midY = r.y+r.height/2;
				double a = Math.atan2(midX, midY) + rotation;
				double q = Math.sqrt(Math.pow(midX, 2) + Math.pow(midY, 2));
				double d = (rotation % Math.PI) / Math.PI; // 0.25 < d < 0.75 ==v  
				if (0.25 < d || d < 0.75) {
					int tmp = r.width;
					r.width = r.height;
					r.height = tmp;
				}
				r.x = (int) (Math.cos(a) * q - r.width/2);
				r.y = (int) (Math.sin(a) * q - r.height/2);
			}
		}
		return nouv;
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
		if (!other.mainShape.intersects(x2, y2, mainShape, x, y)) return false;
		for (Shape shape : shapes)
			for (Shape otherShape : other.shapes)
				if (shape.intersects(x, y, otherShape, x2, y2)) return true;
		return false;
	}
	
	public boolean intersects(int x, int y, Shape aShape, int x2, int y2) {
		if (aShape == null) return false;
		if (!mainShape.intersects(x, y, aShape, x2, y2))
			return false;
		for (Shape shape : shapes)
			if (shape.intersects(x, y, aShape, x2, y2))
				return true;
		return false;
	}
	
	@Override
	public Rectangle getMainShape() {
		return mainShape;
	}
	
	public Shape[] getShapes() {
		return shapes;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"mainShape\":"+mainShape.toString()+", \"shapes\":[");
		for (Shape s: shapes)
			sb.append(s.toString());
		sb.append("]}");
		return sb.toString();
	}
	
}
