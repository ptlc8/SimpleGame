package fr.ptlc.SGServer;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import fr.ptlc.SGServer.Access.Ref;

public class OnlyStrategy implements ExclusionStrategy {
	
	String[] fieldNames;
	
	public OnlyStrategy(String... fieldsName) {
		this.fieldNames = fieldsName;
	}
	
	public boolean shouldSkipField(FieldAttributes f) {
		// temporaire
		if (f.getAnnotation(Access.class) != null)
			if (f.getAnnotation(Access.class).refs().length == 1 && f.getAnnotation(Access.class).refs()[0] == Ref.SAVE)
				return true;
		
		for (String fieldName : fieldNames)
			if (f.getName().equals(fieldName))
				return false;
		return true;
	}
	
	public boolean shouldSkipClass(Class<?> c) {
		return false;
	}
	
}
