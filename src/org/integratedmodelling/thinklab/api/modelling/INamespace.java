package org.integratedmodelling.thinklab.api.modelling;

import java.util.List;

import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.project.IProject;

/**
 * @author  Ferd
 */
public interface INamespace {

	public abstract String getId();
		
	public abstract long getTimeStamp();
	
	public abstract IConcept getConcept(String s);
	
	public abstract IProperty getProperty(String s);
	
	public abstract List<IModelObject> getModelObjects();
	
	public IModelObject getModelObject(String mod);

	public IProject getProject();

	public abstract String getResourceUrl();

	public abstract String getStorageKbox();

	public abstract String getTrainingKbox();
	
	public abstract String getLookupKbox();

	public abstract String getExpressionLanguage();
}
