package expressions;

import java.util.Map;
import model.ParserException;
import model.RGBColor;

public class LetVariableExpression extends Expression {

	public String myVariable;

	public LetVariableExpression(String variable, Map<String, Expression> argMap) {
		myVariable = variable;
		letVariableMap = argMap;
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		Expression exp = letVariableMap.get(myVariable);
		if(exp == null){
			throw new ParserException(
					"Found unrecognized variable " + myVariable);
		}
		return letVariableMap.get(myVariable).evaluate(x, y);
	}

	public String getVariableName() {
		return myVariable;
	}

	public static class Factory extends Expression.Factory {

		@Override
		public boolean isThisTypeOfExpression() {
			int next = 0;
			int nextParen = myParser.getInput().indexOf(')',
					myParser.getCurrentPosition());
			int nextSpace = myParser.getInput().indexOf(' ',
					myParser.getCurrentPosition());
			if (nextParen < 0 && nextSpace < 0)
				return false;
			else if (nextParen > 0 && nextSpace > 0) {
				next = nextParen < nextSpace ? nextParen : nextSpace;
			} else if (nextParen < 0)
				next = nextSpace;
			else if (nextSpace < 0)
				next = nextParen;

			String variable = myParser.getInput().substring(
					myParser.getCurrentPosition(), next);
			if(variable.length() == 1){
				if(variable.charAt(0) == 'x' || variable.charAt(0) == 'y'){
					return false;
				}
			}
			for (char c : variable.toCharArray()) {
				if (!Character.isLetter(c))
					return false;
			}
			return true;

		}

		@Override
		public Expression parseExpression(Map<String, Expression> argMap) {
			int next = 0;
			int nextParen = myParser.getInput().indexOf(')',
					myParser.getCurrentPosition());
			int nextSpace = myParser.getInput().indexOf(' ',
					myParser.getCurrentPosition());

			if (nextParen > 0 && nextSpace > 0) {
				next = nextParen < nextSpace ? nextParen : nextSpace;
			} else if (nextParen < 0)
				next = nextSpace;
			else if (nextSpace < 0){
				next = nextParen;
			}

			String variable = myParser.getInput().substring(
					myParser.getCurrentPosition(), next);
			myParser.advanceCurrentPosition(variable.length());
			return new LetVariableExpression(variable, argMap);
		}

	}
}
