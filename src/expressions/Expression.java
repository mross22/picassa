package expressions;

import model.ExpressionFactory;
import model.Parser;
import model.RGBColor;

/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical 
 * functions and the leaves represent constant values.
 *
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 * @author Michael C. Ross
 */
public abstract class Expression
{

    static Parser myParser = Parser.getInstance();
	
	public Expression(){}
        
    public abstract RGBColor evaluate (double x, double y);
     
    public static Expression parseNextArgumentExpression(){
    	for (ExpressionFactory expressionKind : myParser.kindsOfExpressions) {
			if (expressionKind.isThisTypeOfExpression())
				return expressionKind.parseExpression();
		} 
    	return null;
    }
      
}
