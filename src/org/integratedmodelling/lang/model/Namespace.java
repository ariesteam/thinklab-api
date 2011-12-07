package org.integratedmodelling.lang.model;

import org.integratedmodelling.thinklab.api.lang.IList;

/**
 * Beans to incarnate the model expressed in any of the Thinklab modeling languages. All languages should just
 * worry about generating these, and leave their operationalization to implementations.
 * 
 * @author Ferd
 *
 */
public class Namespace extends LanguageElement {

	public static class ImportedNamespace {
		String id;
		IList imported;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public IList getImported() {
			return imported;
		}
		public void setImported(IList imported) {
			this.imported = imported;
		}
	}
	
	ImportedNamespace[] importedNamespaces;
	ModelObject[] modelObjects;
	long timeStamp;
	
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public ImportedNamespace[] getImportedNamespaces() {
		return importedNamespaces;
	}
	public void setImportedNamespaces(ImportedNamespace[] importedNamespaces) {
		this.importedNamespaces = importedNamespaces;
	}
	public ModelObject[] getModelObjects() {
		return modelObjects;
	}
	public void setModelObjects(ModelObject[] modelObjects) {
		this.modelObjects = modelObjects;
	}
	
	
}
