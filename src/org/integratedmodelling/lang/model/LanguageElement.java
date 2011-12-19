package org.integratedmodelling.lang.model;

public class LanguageElement {

	String id;
	int    lastLineNumber;
	int    firstLineNumber;
	
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
}
