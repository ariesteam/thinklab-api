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

	public static class Ontology {
		
		String id;
		String conceptSpace;
		IList definition;

		public void setId(String id) {
			this.id = id;
		}
		public IList getDefinition() {
			return definition;
		}
		public void setDefinition(IList definition) {
			this.definition = definition;
		}
		public String getConceptSpace() {
			return conceptSpace;
		}
		public void setConceptSpace(String conceptSpace) {
			this.conceptSpace = conceptSpace;
		}
		public String getId() {
			return id;
		}
	}
	
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
	
	Ontology ontology;
	ImportedNamespace[] importedNamespaces;
	ModelObject[] modelObjects;
	
	public Ontology getOntology() {
		return ontology;
	}
	public void setOntology(Ontology ontology) {
		this.ontology = ontology;
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
