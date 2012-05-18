package org.integratedmodelling.thinklab.api.lang;

import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.metadata.IMetadata;

/**
 * An object capable of producing metadata, exposing some convenient 
 * functions to retrieve fields.
 * 
 * @author  Ferd
 */
public interface IMetadataHolder  {

	public abstract IMetadata getMetadata();
	
	public abstract String getMetadataFieldAsString(String field);

	public abstract Integer getMetadataFieldAsInt(String field);
	
	public abstract Long getMetadataFieldAsLong(String field);
	
	public abstract Double getMetadataFieldAsDouble(String field);
	
	public abstract Float getMetadataFieldAsFloat(String field);
	
	public abstract Boolean getMetadataFieldAsBoolean(String field);

	public abstract IConcept getMetadataFieldAsConcept(String field);

	public abstract String  getMetadataFieldAsString(String field, String def);

	public abstract int getMetadataFieldAsInt(String field, int def);
	
	public abstract long getMetadataFieldAsLong(String field, long def);
	
	public abstract double getMetadataFieldAsDouble(String field, double def);
	
	public abstract float getMetadataFieldAsFloat(String field, float def);
	
	public abstract boolean getMetadataFieldAsBoolean(String field, boolean def);

	public abstract IConcept getMetadataFieldAsConcept(String field, IConcept def);

}
