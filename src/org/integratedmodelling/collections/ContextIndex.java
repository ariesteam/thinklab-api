package org.integratedmodelling.collections;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.integratedmodelling.thinklab.api.knowledge.ISemanticObject;

/**
 * Convenience bean to store the index of a visualized context. Its serialized
 * representation can be stored with a dataset to provide easy access to data,
 * images and thumbnails for display.
 * 
 * @author Ferd
 *
 */
public class ContextIndex {

	public static final String SPACE_ID = "space";
	public static final String TIME_ID  = "time";
	
	/*
	 * total number of states in context
	 */
	int multiplicity;
	
	/*
	 * The DOT representation of the model graph that created the context.
	 */
	String modelDOTGraph = null;
	
	/*
	 * a file containing the actual data - normally a NetCDF file. Should have
	 * data for all states, indexed with the official ID of each state used
	 * as key in the index.
	 */
	String datasetFile = null;
	
	/*
	 * each state is listed with its internal ID, a string for its display label, and the 
	 * string representation of its observable. The internal ID is also its key in the
	 * associated dataset if any.
	 */
	HashMap<String, Pair<String, String>> stateIndex = 
			new HashMap<String, Pair<String,String>>();
	
	/*
	 * each extent is listed with its internal ID, a string for its
	 * display label, an integer for its total multiplicity and a tag 
	 * for its topology type.
	 */
	HashMap<String, Pair<Integer, String>> extentIndex = 
			new HashMap<String, Pair<Integer,String>>();
	
	/*
	 * a thumbnail (relative to the path of the file that hosts the
	 * index) for each state or extent. The thumbnail is always an image.
	 */
	HashMap<String, String> thumbnailIndex = new HashMap<String, String>();
	
	/*
	 * media file for each state, relative to the index location. May be
	 * an image, a movie or something else - for now we leave it to the
	 * implementation to distinguish them.
	 */
	HashMap<String, String> imageIndex = new HashMap<String, String>();
	
	/*
	 * IDs of all extents in order of insertion.
	 */
	List<String> extentIDs = new ArrayList<String>();

	/*
	 * IDs of all states in order of insertion.
	 */
	List<String> stateIDs = new ArrayList<String>();
	
	/*
	 * this is set by the user to ease retrieval of absolute file paths
	 */
	String filePrefix = "";
		
	public ContextIndex() {}
	
	/*
	 * relatively obvious setters
	 */
	
	public void setMultiplicity(int m) {
		multiplicity = m;
	}
	
	public void setDataFile(String datafile) {
		datasetFile = datafile;
	}
	
	public void setModelGraph(String dotSource) {
		modelDOTGraph = dotSource;
	}
	
	public void addState(String id, String thumbnailFile, String mediaFile, String displayLabel, ISemanticObject<?> observable) {
		
		stateIDs.add(id);
		
		if (thumbnailFile != null)
			thumbnailIndex.put(id, thumbnailFile);
		if (mediaFile != null)
			imageIndex.put(id, mediaFile);
	
		stateIndex.put(id, new Pair<String, String>(displayLabel, observable.getDirectType().toString()));
	}
	
	public void addExtent(String id, int multiplicity, String thumbnailFile, String mediaFile, String typeTag) {
		
		extentIDs.add(id);
		
		if (thumbnailFile != null)
			thumbnailIndex.put(id, thumbnailFile);
		if (mediaFile != null)
			imageIndex.put(id, mediaFile);
		
		extentIndex.put(id, new Pair<Integer, String>(multiplicity, typeTag));
	}
	
	public void setFilePrefix(String fp) {
		filePrefix = fp;
	}

	/*
	 * getters
	 */
	public File getThumbnailFile(String id) {
		String th = thumbnailIndex.get(id);
		if (th == null)
			return null;
		return new File(filePrefix + File.separator + th);
	}
	
	public File getMediaFile(String id) {
		String th = imageIndex.get(id);
		if (th == null)
			return null;
		return new File(filePrefix + File.separator + th);
	}
	
	public List<String> getExtentIDs() {
		return extentIDs;
	}

	public List<String> getStateIDs() {
		return stateIDs;
	}

	public String getExtentLabel(String eid) {
		return extentIndex.get(eid).getSecond();
	}

	public String getStateLabel(String eid) {
		return stateIndex.get(eid).getFirst();
	}

}
