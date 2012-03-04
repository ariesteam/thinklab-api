package org.integratedmodelling.thinklab.api.modelling;

import java.util.Set;

public interface ICategorizingObserver extends IObserver {

	/**
	 * If this categorizer uses a dictionary for validation, return it; otherwise
	 * return null.
	 * 
	 * @return
	 */
	public abstract Set<String> getDictionary();
}
