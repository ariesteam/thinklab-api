package org.integratedmodelling.thinklab.api.runtime;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.integratedmodelling.thinklab.api.lang.IMetadataHolder;
import org.integratedmodelling.thinklab.api.lang.IPrototype;
import org.integratedmodelling.thinklab.api.project.IProject;

/**
 * Represents a Thinklab server that we can use. Remote or local
 * should not make any difference. Objects returned by the main
 * methods must implement the appropriate interfaces, all of which
 * are immutable.
 * 
 * Its methods should not throw exceptions; error retrieval methods
 * should be used to check status.
 * 
 * Should contain a session and represent both multi-user with authentication 
 * and single user, embedded servers.
 * 
 * @author Ferd
 *
 */
public interface IServer extends IMetadataHolder {

	/*
	 * status codes for results and task management
	 */
	public static final int OK = 0;
	public static final int ERROR = -1;
	public static final int SCHEDULED = -2;
	public static final int RUNNING = -3;
	
	/*
	 * metadata fields that you should fill out as applicable if you're nice,
	 * but not count on to exist.
	 */
	public static final String CURRENT_USERNAME = "IServer.CURRENT_USERNAME";
	public static final String BOOT_TIME_MS = "IServer.BOOT_TIME_MS";
	public static final String TOTAL_MEMORY_MB = "IServer.TOTAL_MEMORY_MB";
	public static final String FREE_MEMORY_MB = "IServer.FREE_MEMORY_MB";
	public static final String LOAD_FACTOR_PERCENT = "IServer.LOAD_FACTOR_PERCENT";
	public static final String VERSION_STRING = "IServer.VERSION_STRING";
	public static final String AVAILABLE_PROCESSORS = "IServer.AVAILABLE_PROCESSORS";
	public static final String EXCEPTION_CLASS = "IServer.EXCEPTION_CLASS";
	public static final String STACK_TRACE = "IServer.STACK_TRACE";

	/**
	 * This is used to communicate results of operations.
	 *
	 * @author Ferd
	 *
	 */
	public static interface Result {
		
		public int getStatus();
		public String getCommand();
		public String getOutput();
		public Throwable getException();
		public Object getResult();
	}
		
	/**
	 * Execute a statement in modeling language. Block until
	 * result is returned.
	 * 
	 * @param s
	 * @return
	 */
	public abstract Result executeStatement(String s);
	
	/**
	 * Execute a command line instruction. Block until result
	 * is returned.
	 * 
	 * @param command
	 * @return
	 */
	public abstract Result executeCommand(String command);

	/**
	 * Execute statement, return a handle that should be checked against
	 * ERROR and if not, used later in getStatus() and if warranted,
	 * getTaskResult().
	 * 
	 * @param s
	 * @return
	 */
	public abstract long executeStatementAsynchronous(String s);
	
	/**
	 * 
	 * @param command
	 * @return
	 */
	public abstract long executeCommandAsynchronous(String command);

	/**
	 * Return the status of a previously enqueued task. If the task
	 * doesn't exist, return ERROR.
	 * 
	 * @param handle
	 * @return
	 */
	public abstract int getStatus(long handle);
	
	/**
	 * Return the result of the task. If task does not exist, return
	 * null. If dispose is true, clean up results and remove task from
	 * memory.
	 * 
	 * @param handle
	 * @param dispose
	 * @return
	 */
	public abstract Result getTaskResult(long handle, boolean dispose);

	/**
	 * Start the thing, after which it must either report error status or be
	 * ready to accept commands.
	 */
	public abstract Result boot();
	
	/**
	 * Always call this when done.
	 * 
	 */
	public abstract Result shutdown();

	/**
	 * True if server is active, connected and authenticated, ready to do stuff.
	 * 
	 * @return
	 */
	public abstract boolean isActive();
	
	/**
	 * Deploy project (with all its prerequisites). If privileges are not enough or there are errors,
	 * just return false. If project is already deployed, undeploy first.
	 * 
	 * @param p
	 * @return
	 */
	public abstract Result deploy(IProject p);
	
	/**
	 * Undeploy project. Behavior as expected for deploy().
	 * 
	 * @param p
	 * @return
	 */
	public abstract Result undeploy(IProject p);

	/*
	 * Inquiry functions: we must be able to know what languages, commands, functions and projects
	 * are supported in the server. These are basic inquiries for which we need prototype API - 
	 * anything else can be done through commands.
	 */
	
	/**
	 * Return prototypes for all known functions.
	 * @return
	 */
	public List<IPrototype> getFunctionPrototypes();
	
	/**
	 * Return prototypes for all known commands.
	 * @return
	 */
	public List<IPrototype> getCommandPrototypes();
	
	/**
	 * Return the name of all supported languages for knowledge representation.
	 * 
	 * @return
	 */
	public List<String> getSupportedLanguages();
	
	/**
	 * Export any knowledge needed to use the server to the local directory given, in a form that
	 * can be loaded by a standard resolver. This should be used for core ontologies, not for
	 * projects.
	 * 
	 * @return
	 */
	public abstract Result exportCoreKnowledge(File file);

	/**
	 * Import and load all the Thinklab projects passed, handling their dependencies appropriately.
	 * 
	 * @param projectDirectory
	 */
	public abstract void loadAll(Collection<IProject> projects);
	
	


}
