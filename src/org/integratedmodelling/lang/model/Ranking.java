package org.integratedmodelling.lang.model;

public class Ranking extends Observer {
	
	public final static int BINARY_CODING = 0;
	public final static int NUMERIC_ENCODING = 1;
	public final static int RANKING = 2;
	
	int type;
	double from = -1.0, to = -1.0;
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}

	public void setScale(double from, double to) {
		this.from = from;
		this.to = to;
	}

}
