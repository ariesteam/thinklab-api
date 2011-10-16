package org.integratedmodelling.thinklab.api.listeners;

public interface IControlListener extends IListener {

	public void warn(Object o);	
	public void info(Object o);
	public void error(Object o);
	public void fatal(Object o);
}
