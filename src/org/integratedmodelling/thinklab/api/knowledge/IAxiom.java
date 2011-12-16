package org.integratedmodelling.thinklab.api.knowledge;


/**
 * Used to programmatically create knowledge when it's more convenient to accumulate
 * axioms than it is to explicity create and restrict concepts and properties. Axioms
 * must be comparable and hashable (so they can be put in sets with no duplications) 
 * and ontologies must be able to define themselves completely from and as a set of
 * axioms.
 * 
 * @author Ferd
 *
 */
public interface IAxiom {

	/*
	 * class axioms
	 */
    public static final String EQUIVALENT_CLASSES = "EquivalentClasses";
    public static final String SUBCLASS_OF = "SubClassOf";
    public static final String DISJOINT_CLASSES = "DisjointClasses";
    public static final String DISJOINT_UNION = "DisjointUnion";

    /*
     * individual axioms
     */
    public static final String CLASS_ASSERTION = "ClassAssertion";
    public static final String SAME_INDIVIDUAL = "SameIndividual";
    public static final String DIFFERENT_INDIVIDUALS = "DifferentIndividuals";
    public static final String OBJECT_PROPERTY_ASSERTION = "ObjectPropertyAssertion";
    public static final String NEGATIVE_OBJECT_PROPERTY_ASSERTION = "NegativeObjectPropertyAssertion";
    public static final String DATA_PROPERTY_ASSERTION = "DataPropertyAssertion";
    public static final String NEGATIVE_DATA_PROPERTY_ASSERTION = "NegativeDataPropertyAssertion";

    /*
     * object property axioms
     */
    public static final String EQUIVALENT_OBJECT_PROPERTIES = "EquivalentObjectProperties";
    public static final String SUB_OBJECT_PROPERTY = "SubObjectPropertyOf";
    public static final String INVERSE_OBJECT_PROPERTIES = "InverseObjectProperties";
    public static final String FUNCTIONAL_OBJECT_PROPERTY = "FunctionalObjectProperty";
    public static final String INVERSE_FUNCTIONAL_OBJECT_PROPERTY = "InverseFunctionalObjectProperty";
    public static final String SYMMETRIC_OBJECT_PROPERTY = "SymmetricObjectProperty";
    public static final String ASYMMETRIC_OBJECT_PROPERTY = "AsymmetricObjectProperty";
    public static final String TRANSITIVE_OBJECT_PROPERTY = "TransitiveObjectProperty";
    public static final String REFLEXIVE_OBJECT_PROPERTY = "ReflexiveObjectProperty";
    public static final String IRREFLEXIVE_OBJECT_PROPERTY = "IrrefexiveObjectProperty";
    public static final String OBJECT_PROPERTY_DOMAIN = "ObjectPropertyDomain";
    public static final String OBJECT_PROPERTY_RANGE = "ObjectPropertyRange";
    public static final String DISJOINT_OBJECT_PROPERTIES = "DisjointObjectProperties";
    public static final String SUB_PROPERTY_CHAIN_OF = "SubPropertyChainOf";

    /*
     * data property axioms
     */
    public static final String EQUIVALENT_DATA_PROPERTIES = "EquivalentDataProperties";
    public static final String SUB_DATA_PROPERTY = "SubDataPropertyOf";
    public static final String FUNCTIONAL_DATA_PROPERTY = "FunctionalDataProperty";  
    public static final String DATA_PROPERTY_DOMAIN = "DataPropertyDomain";
    public static final String DATA_PROPERTY_RANGE = "DataPropertyRange";
    public static final String DISJOINT_DATA_PROPERTIES = "DisjointDataProperties";
    public static final String HAS_KEY = "HasKey";
    public static final String SWRL_RULE = "Rule";

    /*
     * annotation property axioms
     */
    public static final String ANNOTATION_ASSERTION = "AnnotationAssertion";
    public static final String SUB_ANNOTATION_PROPERTY_OF = "SubAnnotationPropertyOf";
    public static final String ANNOTATION_PROPERTY_RANGE = "AnnotationPropertyRangeOf";
    public static final String ANNOTATION_PROPERTY_DOMAIN = "AnnotationPropertyDomain";
    public static final String DATATYPE_DEFINITION = "DatatypeDefinition";
	
}
