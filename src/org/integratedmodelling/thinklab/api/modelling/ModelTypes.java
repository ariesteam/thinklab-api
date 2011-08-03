package org.integratedmodelling.thinklab.api.modelling;

import org.integratedmodelling.lang.SemanticType;

public interface ModelTypes {

	// Concepts for model types
	public final SemanticType C_MODEL = new SemanticType("modelling:Model");
	public final SemanticType C_MEASUREMENT = new SemanticType(
			"modelling:Measurement");
	public final SemanticType C_CLASSIFICATION = new SemanticType(
			"modelling:Classification");
	public final SemanticType C_RANKING = new SemanticType("modelling:Ranking");
	public final SemanticType C_BOOLEANRANKING = new SemanticType(
			"modelling:BooleanRanking");
	public final SemanticType C_NUMERICCODING = new SemanticType(
			"modelling:NumericCoding");
	public final SemanticType C_PROBABILISTICMEASUREMENT = new SemanticType(
			"modelling:ProbabilisticMeasurement");
	public final SemanticType C_PROBABILISTICRANKING = new SemanticType(
			"modelling:ProbabilisticRanking");
	public final SemanticType C_PROBABILISTICCLASSIFICATION = new SemanticType(
			"modelling:ProbabilisticClassification");
	
	// properties
	public final SemanticType P_HAS_OBSERVABLE = new SemanticType("observation:hasObservable");
}
