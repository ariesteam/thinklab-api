package org.integratedmodelling.thinklab.api.lang;

import java.io.InputStream;
import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.thinklab.api.knowledge.IExpression;
import org.integratedmodelling.thinklab.api.modelling.IContext;
import org.integratedmodelling.thinklab.api.modelling.IModelObject;
import org.integratedmodelling.thinklab.api.modelling.INamespace;
import org.integratedmodelling.thinklab.api.modelling.parsing.IConceptDefinition;
import org.integratedmodelling.thinklab.api.modelling.parsing.IFunctionDefinition;
import org.integratedmodelling.thinklab.api.modelling.parsing.ILanguageDefinition;
import org.integratedmodelling.thinklab.api.modelling.parsing.IModelObjectDefinition;
import org.integratedmodelling.thinklab.api.modelling.parsing.IPropertyDefinition;

/**
 * A Resolver is used by anything that generates model objects to interface to the parser for
 * whatever language is implemented. Using a resolver is a way to make any language implementation
 * depend only on the API. 
 * 
 * @author Ferd
 *
 */
public interface IResolver {
	
	/**
	 * This one returns a new "definable" object for the model class passed. This way each 
	 * implementation can use their own objects and the API remains clean.
	 * 
	 * @param cls
	 * @return
	 */
	public abstract ILanguageDefinition newLanguageObject(Class<?> cls);
	
	/**
	 * Override to fine-tune error management. If this throws an exception, the parser also will. If not, 
	 * the return value determines whether parsing continue (true) or not (false).
	 * 
	 * @param e
	 * @return
	 */
	public abstract boolean onException(Throwable e, int lineNumber) throws ThinklabException;

	/**
	 * Override to fine-tune error management. Will stop parsing if false is returned.
	 * 
	 * @param warning
	 * @return
	 */
	public abstract boolean onWarning(String warning, int lineNumber);

	/**
	 * Override to fine-tune error management. Will stop parsing if false is returned.
	 * 
	 * @param info
	 * @return
	 */
	public abstract boolean onInfo(String info, int lineNumber);

	/**
	 * Resolve a namespace (optionally further specified by an import URI) to an input stream. Either the namespace id or
	 * the reference may be null, i.e. it must be prepared to produce an inputstream based on a namespace id only (e.g. 
	 * resolving across dependent plugin classpaths) and/or based on an external reference URI; if both are present, 
	 * it should ensure that they're compatible name-wise. 
	 *
	 * @param namespace. May be null if only the reference is passed.
	 * @param reference where to look for the namespace to load if not known. May be null.
	 * @return a valid input stream which will be read and closed by the parser.
	 * @throws ThinklabException
	 */
	public abstract InputStream resolveNamespace(String namespace, String reference) throws ThinklabException;
	
	/**
	 * Callback invoked as soon as a namespace declaration is parsed. Passed back the parameters that were
	 * used in namespace resolution (see resolveNamespace for docs) both of which may be null. Should ensure that 
	 * the namespace declaration is appropriate for the resources. Any imports will be parsed before this is called.
	 * 
	 * @param namespaceId
	 * @param resourceId
	 * @param namespace
	 */
	public abstract void onNamespaceDeclared(String namespaceId, INamespace namespace);
	
	/**
	 * Callback invoked as soon as parsing of a namespace has been completed. The namespace will contain all
	 * model objects declared in it and the axioms collected from them. 
	 * 
	 * @param namespace
	 * @throws ThinklabException 
	 */
	public abstract void onNamespaceDefined(INamespace namespace) throws ThinklabException;

	/**
	 * Callback invoked at every new model object at main level. Not all the namespace will be
	 * defined when this is called.
	 * 
	 * @param namespace
	 * @param ret
	 * @throws ThinklabException 
	 */
	public abstract void onModelObjectDefined(INamespace namespace, IModelObject ret) throws ThinklabException;

	/**
	 * Ensure that the namespace declaration conforms with the resource it comes from. Only called when the 
	 * resource is an actual model file.
	 * 
	 * @param resource
	 * @param namespace
	 * @throws ThinklabException 
	 */
	public abstract void validateNamespaceForResource(String resource,
			String namespace) throws ThinklabException;

	/**
	 * Resolve a function to the corresponding expression. Just return null if not found.
	 * 
	 * @param resource
	 * @param parameterNames
	 * @return
	 */
	public abstract IExpression resolveFunction(String functionId, Collection<String> parameterNames);
	
	/**
	 * Called when an external concept (with the :) is identified in a legal place in a model object. Should
	 * return a ConceptObject pointing to whatever definition of the import we need, which will be set in the
	 * model tree.
	 *  
	 * @param id
	 * @param namespace
	 * @param line 
	 * @return
	 */
	public abstract IConceptDefinition resolveExternalConcept(String id, INamespace namespace, int line) throws ThinklabException;
	
	/**
	 * Called when an external property (with the :) is identified in a legal place in a model object. Should
	 * return a ConceptObject pointing to whatever definition of the import we need, which will be set in the
	 * model tree.
	 *  
	 * @param id
	 * @param namespace
	 * @param line 
	 * @return
	 */
	public abstract IPropertyDefinition resolveExternalProperty(String id, INamespace namespace, int line) throws ThinklabException;

	/**
	 * Check if a passed model object ID has been generated by generateId().
	 * 
	 * @param id
	 * @return
	 */
	public abstract boolean isGeneratedId(String id);

	/**
	 * Generate an ID for the passed model object. Only the type of the model object should be
	 * checked, as the remaining attributes may not be initialized when this is called. The ID
	 * should be recognizable by isGeneratedId().
	 * 
	 * @param o
	 * @return
	 */
	public abstract String generateId(IModelObject o);

	/**
	 * Resolve the passed function from its definition and run it with the parameters set in the
	 * def. If not resolved just return null.
	 * 
	 * @param function
	 * @return
	 */
	public abstract Object runFunction(IFunctionDefinition function);

	/**
	 * Return the last model object notified to the resolver. Used by interactive
	 * applications only. Should not return any object more than once.
	 * 
	 * @return
	 */
	public abstract IModelObject getLastProcessedObject();

	/**
	 * Return whether the resolver is being used in an interactive session. The parser may
	 * allow or disallow some statements in that case. 
	 * 
	 * @return
	 */
	boolean isInteractive();

	/**
	 * In an interactive session, an 'observe' command should be supported which calls
	 * this method for handling. The command is expected to contribute observations to
	 * the current context in the session.
	 * 
	 * @param observable
	 * @param ctx 
	 * @param ctx
	 * @throws ThinklabException 
	 */
	public abstract void handleObserveStatement(Object observable, INamespace namespace, IContext ctx, boolean resetContext) 
		throws ThinklabException;

	/**
	 * Get another resolver to handle an import, whose lifetime is local to ours.
	 * 
	 * @return
	 */
	public abstract IResolver getImportResolver();

	/**
	 * Return whether the namespace was defined already, to prevent recursions in the 
	 * parser.
	 * 
	 * @param id
	 * @return
	 */
	public abstract boolean isNamespaceDefined(String id);

	/**
	 * Resolve the given object or return null.
	 * 
	 * @param ns
	 * @param object
	 * @return
	 */
	public abstract IModelObjectDefinition resolveModelObject(String ns, String object);
	
}