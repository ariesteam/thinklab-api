package org.integratedmodelling.lang.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.integratedmodelling.thinklab.api.knowledge.IAxiom;
import org.integratedmodelling.thinklab.api.lang.IList;

/**
 * Beans to incarnate the model expressed in any of the Thinklab modeling languages. All languages should just
 * worry about generating these, and leave their operationalization to implementations.
 * 
 * @author Ferd
 *
 */
public class Namespace extends LanguageElement {

	public ArrayList<IAxiom> axioms = new ArrayList<IAxiom>();
	public HashSet<IAxiom> axiomCatalog = new HashSet<IAxiom>();
	
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
	
	public Collection<IAxiom> getAxioms() {
		return axioms;
	}
	
	/**
	 * Add an axiom. Tolerant to duplicated axioms.
	 * @param axiom
	 */
	public void addAxiom(IAxiom axiom) {
		if (!axiomCatalog.contains(axiom)) {
			axiomCatalog.add(axiom);
			axioms.add(axiom);
		}
	}
	@Override
	public void dump(PrintStream out) {
		
		// TODO dump all axioms
		for (IAxiom a : axioms) {
			out.println(a);
		}
		
		// TODO dump all model objects
		for (ModelObject m : modelObjects) {
			m.dump(out);
		}
	}
	
	/**
	 * This one is called for any concept that is named with the namespace where
	 * allowed. Concept is taken as is but validations should be made wherever
	 * possible.
	 * 
	 * @param id
	 */
	public void addExternalRequiredConcept(String id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
