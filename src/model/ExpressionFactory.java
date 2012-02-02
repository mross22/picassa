package model;

import expressions.Expression;

public abstract class ExpressionFactory {
	public abstract boolean isThisTypeOfExpression();	
	public abstract Expression parseExpression();
}
