package fr.ptlc.SGServer;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import fr.ptlc.SGServer.Access.Ref;

public class AccessStrat implements ExclusionStrategy {
	
	private final Ref accessRef;
	
	public AccessStrat(Ref accessRef) {
		this.accessRef = accessRef;
	}
	
	@Override
	public boolean shouldSkipClass(Class<?> c) {
		return false;
	}
	
	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		if (f.getAnnotations().size() == 0) return true;
		Access a = f.getAnnotation(Access.class);
		if (a == null) return true;
		for (Ref ref : a.refs())
			if (ref == Ref.ALL || ref == accessRef)
				return false;
		return true;
	}
	
}
