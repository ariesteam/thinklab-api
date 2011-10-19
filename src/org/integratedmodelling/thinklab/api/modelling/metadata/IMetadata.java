package org.integratedmodelling.thinklab.api.modelling.metadata;

import org.integratedmodelling.thinklab.api.knowledge.IConceptualizable;
import org.integratedmodelling.thinklab.api.lang.IParseable;

public interface IMetadata extends IConceptualizable, IParseable {

	public static String DC_NAME = "dc:name";
	public static String DC_LABEL = "dc:name";
	public static String DC_COMMENT = "dc:name";
	public static String DC_DEFINITION = "dc:name";
	public static String DC_SEEALSO = "dc:name";
	
	/**
	 * @deprecated use subclasses
	 */
	public static String DC_COVERAGE = "dc:coverage";
	
	/**
	 * DCMI point http://dublincore.org/documents/dcmi-point/
	 * ISO 3166 http://www.din.de/gremien/nas/nabd/iso3166ma/codlstp1/index.html
	 * DCMI box http://dublincore.org/documents/dcmi-box/
	 * TGN http://shiva.pub.getty.edu/tgn_browser/
	 */
	public static String DC_COVERAGE_SPATIAL = "dc:coverage";
	
	/**
	 * DCMI period http://dublincore.org/documents/dcmi-period/
	 * W3C-DTF http://www.w3.org/TR/NOTE-datetime
	 */
	public static String DC_COVERAGE_TEMPORAL = "dc:coverage";
	
	/**
	 * free text
	 */
	public static String DC_DESCRIPTION = "dc:coverage";

	/**
	 * free text
	 */
	public static String DC_DESCRIPTION_TABLEOFCONTENTS = "dc:coverage";

	/**
	 * free text
	 */
	public static String DC_DESCRIPTION_ABSTRACT = "dc:coverage";

	/**
	 * DCMI type vocabulary http://dublincore.org/documents/dcmi-type-vocabulary/
	 */
	public static String DC_TYPE = "dc:coverage";

	/**
	 * @deprecated use subclasses
	 */
	public static String DC_RELATION = "dc:coverage";

	/**
	 * URI http://www.ietf.org/rfc/rfc2396.txt
	 */
	public static String DC_RELATION_ISVERSIONOF = "dc:coverage";

	/**
	 * URI
	 */
	public static String DC_RELATION_HASVERSION = "dc:coverage";

	/**
	 * URI
	 */
	public static String DC_RELATION_ISREPLACEDBY = "dc:coverage";

	/**
	 * URI
	 */
	public static String DC_RELATION_REPLACES = "dc:coverage";

	/**
	 * URI
	 */
	public static String DC_RELATION_ISREQUIREDBY = "dc:coverage";

	/**
	 * URI
	 */
	public static String DC_RELATION_REQUIRES = "dc:coverage";

	/**
	 * URI
	 */
	public static String DC_RELATION_ISPARTOF = "dc:coverage";

	/**
	 * URI
	 */
	public static String DC_RELATION_HASPART = "dc:coverage";

	/**
	 * URI
	 */
	public static String DC_RELATION_ISREFERENCEDBY = "dc:coverage";

	/**
	 * URI
	 */
	public static String DC_RELATION_REFERENCES = "dc:coverage";

	/**
	 * URI
	 */
	public static String DC_RELATION_ISFORMATOF = "dc:coverage";

	/**
	 * URI
	 */
	public static String DC_RELATION_HASFORMAT = "dc:coverage";

	/**
	 * URI
	 */
	public static String DC_SOURCE = "dc:coverage";

	/**
	 * Vocabularies: 
	 * 
	 * LCSH Library of Congress Subject Headings
	 * MeSH http://www.nlm.nih.gov/mesh/meshhome.html
	 * DDC http://www.oclc.org/dewey/index.htm
	 * LCC http://lcweb.loc.gov/catdir/cpso/lcco/lcco.html
	 * UDC http://www.udcc.org/
	 */
	public static String DC_SUBJECT = "dc:coverage";
	public static String DC_TITLE = "dc:coverage";
	public static String DC_TITLE_ALTERNATIVE = "dc:coverage";
	
	public static String DC_CONTRIBUTOR = "dc:coverage";
	public static String DC_CREATOR = "dc:coverage";
	public static String DC_PUBLISHER = "dc:coverage";
	public static String DC_RIGHTS = "dc:coverage";
	
	/**
	 * @deprecated use subclasses
	 */
	public static String DC_DATE = "dc:coverage";
	
	/**
	 * DCMI period http://dublincore.org/documents/dcmi-period/
	 * W3C-DTF http://www.w3.org/TR/NOTE-datetime
	 */
	public static String DC_DATE_CREATED = "dc:coverage";
	/**
	 * DCMI period
	 * W3C-DTF
	 */
	public static String DC_DATE_VALID = "dc:coverage";
	/**
	 * DCMI period
	 * W3C-DTF
	 */
	public static String DC_DATE_AVAILABLE = "dc:coverage";
	/**
	 * DCMI period
	 * W3C-DTF
	 */
	public static String DC_DATE_ISSUED = "dc:coverage";
	/**
	 * DCMI period
	 * W3C-DTF
	 */
	public static String DC_MODIFIED = "dc:coverage";
	
	/**
	 * @deprecated use subclasses
	 */
	public static String DC_FORMAT = "dc:coverage";
	public static String DC_FORMAT_EXTENT = "dc:coverage";

	/**
	 * IMT http://www.isi.edu/in-notes/iana/assignments/media-types/media-types
	 */
	public static String DC_FORMAT_MEDIUM = "dc:coverage";
	
	public static String DC_IDENTIFIER = "dc:coverage";

	/**
	 * ISO639-2 http://www.w3.org/TR/NOTE-datetime
	 * RFC1766 http://www.ietf.org/rfc/rfc1766.txt
	 */
	public static String DC_LANGUAGE = "dc:coverage";

	/**
	 * The object returned will be a String, IMetadata, Number or IList for textually defined
	 * metadata. May be anything else if defined through the API.
	 *
	 * TODO: only String or also IConcept as a key?
	 * @param string
	 * @return
	 */
	public Object get(String string);

}
