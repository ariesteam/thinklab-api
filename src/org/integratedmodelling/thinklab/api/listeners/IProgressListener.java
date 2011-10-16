package org.integratedmodelling.thinklab.api.listeners;

public interface IProgressListener extends IListener {
	
	public void stop(Object o);
	public void isStopped(Object o);
	public void addProgress(int steps, String description);

}
