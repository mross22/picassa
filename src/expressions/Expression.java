package expressions;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.RGBColor;

/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical functions and the
 * leaves represent constant values.
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 * @author Michael C. Ross
 */
public abstract class Expression {

	static Parser myParser = Parser.getInstance();
	public Map<String, Expression> letVariableMap;

	public Expression() {
		letVariableMap = new HashMap<String, Expression>();
	}

	public abstract RGBColor evaluate(double x, double y);

	public abstract static class Factory {
		public abstract boolean isThisTypeOfExpression();

		public abstract Expression parseExpression(Map<String, Expression> argMap);

		public static Expression parseNextArgumentExpression() {
			for (Factory expressionKind : myParser.kindsOfExpressions) {
				if (expressionKind.isThisTypeOfExpression())
					return expressionKind.parseExpression(null);
			}
			return null;
		}
		
		protected boolean regexMatches(Pattern regex, String stringToMatch) {
            Matcher expMatcher =
                regex.matcher(stringToMatch);
            return expMatcher.lookingAt();
        }
	}

}
