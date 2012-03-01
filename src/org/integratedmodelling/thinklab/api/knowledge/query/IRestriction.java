package org.integratedmodelling.thinklab.api.knowledge.query;

import java.util.Collection;

import org.integratedmodelling.exceptions.ThinklabException;
import org.integratedmodelling.lang.LogicalConnector;
import org.integratedmodelling.lang.Quantifier;
import org.integratedmodelling.lang.SemanticAnnotation;
import org.integratedmodelling.thinklab.api.knowledge.IConcept;
import org.integratedmodelling.thinklab.api.knowledge.IProperty;
import org.integratedmodelling.thinklab.api.lang.IList;
import org.integratedmodelling.thinklab.api.lang.IOperator;

public interface IRestriction {

	public abstract boolean isConnector();

	public abstract IProperty getProperty();

	public abstract Quantifier getQuantifier();

	public abstract boolean isLiteral();

	public abstract boolean isClassification();

	public abstract boolean isObject();

	public abstract IList asList();

	public abstract boolean match(SemanticAnnotation c) throws ThinklabException;

	public abstract IConcept getClassificationConcept();

	public abstract Collection<IRestriction> getChildren();

	public abstract LogicalConnector getConnector();

	public abstract IQuery getSubQuery();

	public abstract IOperator getOperator();

	public abstract Object[] getOperatorArguments();

}