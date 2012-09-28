package org.integratedmodelling.thinklab.api.factories;

import java.io.File;
import java.net.URL;
import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IOntology;

public interface IKnowledgeFactory extends IKnowledgeManager {

//	/**
//	 * import an ontology from URL with the passed short name. Name
//	 * may be null; if so, assign a name from URL base name. 
//	 * @param url the ontology URL, either local or web based.
//	 * @param name the name by which we want the ontology to be known, or null if we don't care.
//	 * @param saveToRepository pass true if you want the ontology to be automatically loaded at the next
//	 *        instantiation of the repository.
//	 * @return the ontology short name. If null has been passed for the name parameter, it's calculated from the URL
//	 * basename. Otherwise it's the same passed. Names may not be duplicated - if generated, a unique one is forced.
//	 * @exception ThinklabException if anything goes wrong
//	 */
//	public abstract String importOntology(URL url, String name, boolean saveToRepository) throws ThinklabException;
//	
	/**
	 * Supposed to check if the ontology we're trying to import is already in the repository, and
	 * only load it if it's not there or if there is an older version. Versioning is not formally
	 * defined at this stage, so it's likely to only translate to "load if not present" for the 
	 * time being.
	 * 
	 * @param url the ontology URL, either local or web based.
	 * @param name the name by which we want the ontology to be known, or null if we don't care.
	 * @param saveToRepository pass true if you want the ontology to be automatically loaded at the next
	 *        instantiation of the repository.
	 * @return the ID of the ontology if it has been loaded or refreshed, null otherwise
	 * @throws ThinklabException if anything goes wrong
	 */
	public abstract String refreshOntology(URL url, String name) throws ThinklabException;
	
	/**
	 * Release the passed ontology and delete from repository. Nothing should happen if name is not found.
	 * @param s name of ontology to release.
	 */
	public abstract void releaseOntology(String s);

	/**
	 * Empty the repository.
	 */
	public abstract void releaseAllOntologies();
	
	/**
	 * retrieve the named ontology as an Ontology object.
	 * @param ontName the name of the ontology
	 * @return the Ontology object.
	 */
	public abstract IOntology getOntology(String ontName);
    
    /**
     * retrieve a Collection of all ontologies in repository
     */
    public abstract Collection<IOntology> getOntologies();

	/**
	 * Create a new ontology with given ID, using defaults for the rest of the URI. Complain if
	 * the ontology exists.
	 * 
	 * @param id
	 * @return
	 */
	public abstract IOntology createOntology(String id, String ontologyPrefix) throws ThinklabException;
	
	/**
	 * Return all concepts whose direct and only parent is the root concept (e.g. owl:Thing).
	 * 
	 * @return
	 */
	public abstract Collection<IConcept> getRootConcepts();

	/**
	 * Get a collection of all the known concepts. Not sure it should stay.
	 */
	public abstract Collection<IConcept> getConcepts();

	/**
	 * Export given ontology as an OWL file. Should return the original file if the
	 * ontology was read from a local file, or create a temporary file if the ontology
	 * was created through the API.
	 * 
	 * @param ontologyId
	 * @return
	 */
	public abstract File exportOntology(String ontologyId);
}