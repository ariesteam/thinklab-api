package org.integratedmodelling.thinklab.api.knowledge;

import org.integratedmodelling.lang.SemanticAnnotation;

/**
 * A specialized conceptualizable that can add semantics to a target when
 * it's found inside its context. Obviously used in extents - e.g. spatial
 * extent can add locational metadata to the observations to which 
 * it provides a spatial topology.
 * 
 * Using this interface properly is left to the implementing code.
 * 
 * @author Ferd
 *
 */
public interface IContextualConceptualizable extends IConceptualizable {

	/**
	 * Take the completed definition of a target and return it after adding any semantics
	 * that the implementing object is expected to provide the target with.
	 * 
	 * @param target
	 * @return
	 */
	public abstract SemanticAnnotation contextualizeTarget(SemanticAnnotation target);
}
