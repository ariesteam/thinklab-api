package org.integratedmodelling.lang.model;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.integratedmodelling.thinklab.api.knowledge.IAxiom;
import org.integratedmodelling.thinklab.api.lang.IList;
import org.integratedmodelling.thinklab.api.project.IProject;

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
	ArrayList<ImportedNamespace> importedNamespaces = new ArrayList<Namespace.ImportedNamespace>();
	ArrayList<ModelObject> modelObjects = new ArrayList<ModelObject>();
	ArrayList<ModelObject> _knowledge = new ArrayList<ModelObject>();
	public HashSet<String> _names = new HashSet<String>();
	
	long timeStamp;
	IProject project;
	private File sourceFile;
	
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
	
	
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Collection<ImportedNamespace> getImportedNamespaces() {
		return importedNamespaces;
	}

	public Collection<ModelObject> getModelObjects() {
		return modelObjects;
	}
	
	public Collection<IAxiom> getAxioms() {
		return axioms;
	}
	
	public Collection<ModelObject> getKnowledge() {
		return _knowledge;
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
	
	/**
	 * Model objects get in the model object array unless they're concepts or properties defined
	 * by inference. In that case they end up in the knowledge array.
	 * 
	 * @param mo
	 */
	public void addModelObject(ModelObject mo) {
		
		if (mo.getId() != null) {
			_names.add(mo.getId());
		}
		
		if ((mo instanceof ConceptObject || mo instanceof PropertyObject) && 
				mo.firstLineNumber == 0 && mo.lastLineNumber == 0) {
			_knowledge.add(mo);
		} else {
			modelObjects.add(mo);			
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
	
	public void addImported(Namespace namespace) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Linear search should be OK, but we can certainly add a hashtable at some point.
	 * 
	 * @param object
	 * @return
	 */
	public ModelObject getModelObject(String object) {
		
		if (!_names.contains(object))
			return null;
		
		for (ModelObject mo : modelObjects) {
			if (mo.getId().equals(object))
				return mo;
		}
		return null;
	}
	
	
	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
	}

	public File getSourceFile() {
		return this.sourceFile;
	}
	
	public void setSourceFile(File file) {
		this.sourceFile = file;
	}
	
	/**
	 * Synchronize axioms with ConceptObjects and PropertyObjects. Only
	 * parses axioms -> object and not the other way around as we assume
	 * that parsers will generate axioms from model objects.
	 * 
	 * TODO very incomplete!
	 * 
	 */
	public void synchronizeKnowledge() {

		for (IAxiom axiom : axioms) {
			
			if (axiom.is(IAxiom.CLASS_ASSERTION)) {
				if (!_names.contains(axiom.getArgument(0))) {
					ConceptObject co = new ConceptObject();
					co.setId(axiom.getArgument(0).toString());
					co.setNamespace(this);
					addModelObject(co);
				}
			}
		}
	}
		
}
