package org.integratedmodelling.thinklab.api.listeners;


public interface IListenable {

	/**
	 * The type of the listener will determine the actions taken.
	 * @param listeners
	 */
	public abstract void listen(IListener ... listeners);
}
