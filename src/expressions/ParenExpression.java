package expressions;

import java.util.regex.Matcher;

import model.RGBColor;

public abstract class ParenExpression extends Expression{

	@Override
	public abstract RGBColor evaluate(double x, double y) ;

	protected static boolean isThisTypeParenExpression(String type){
	    	myParser.skipWhiteSpace();
			Matcher expMatcher = myParser.EXPRESSION_BEGIN_REGEX
					.matcher(myParser.getInput().substring(
							myParser.getCurrentPosition()));
			if (!expMatcher.lookingAt())
				return false;

			expMatcher = myParser.EXPRESSION_BEGIN_REGEX.matcher(myParser
					.getInput());
			expMatcher.find(myParser.getCurrentPosition());
			String commandName = expMatcher.group(1);

			if (!commandName.equals(type))
				return false;

			myParser.setMyCurrentPosition(expMatcher.end());

			return true;
	    	
	    }
}
