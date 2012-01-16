package org.integratedmodelling.lang.model;

import java.util.HashMap;

public class Metadata extends ModelObject {

	HashMap<String, Object> data = new HashMap<String, Object>();
	
	public void put(String id, Object value) {
		data.put(id,value);
	}

}
