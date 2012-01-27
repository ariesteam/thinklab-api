package org.integratedmodelling.lang.model;

import java.io.PrintStream;

public abstract class LanguageElement {

	String id;
	int    lastLineNumber;
	int    firstLineNumber;
	Namespace namespace;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getLastLineNumber() {
		return lastLineNumber;
	}
	public void setLastLineNumber(int lineNumber) {
		this.lastLineNumber = lineNumber;
	}
	public int getFirstLineNumber() {
		return firstLineNumber;
	}
	public void setFirstLineNumber(int firstLineNumber) {
		this.firstLineNumber = firstLineNumber;
	}
	
	public void setNamespace(Namespace ns) {
		this.namespace = ns;
	}
	
	public Namespace getNamespace() {
		return namespace;
	}
	
	
	public abstract void dump(PrintStream out);
}
