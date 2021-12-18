package fr.ptlc.SGServer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Access {
	
	public enum Ref {SAVE,SELF,ALL,TEAMMATES,OPPONENTS}
	
	Ref[] refs();
	
}
