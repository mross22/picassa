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

		public String getLetVariable(){
			int next = 0;
			int nextParen = myParser.getInput().indexOf(')',
					myParser.getCurrentPosition());
			int nextSpace = myParser.getInput().indexOf(' ',
					myParser.getCurrentPosition());
			if (nextParen < 0 && nextSpace < 0)
				return null;
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
					return null;
				}
			}
			for (char c : variable.toCharArray()) {
				if (!Character.isLetter(c))
					return null;
			}
			return variable;
			
		}
		
		@Override
		public boolean isThisTypeOfExpression() {
			return (getLetVariable() != null);
		}

		@Override
		public Expression parseExpression(Map<String, Expression> argMap) {

			String variable = getLetVariable();
			myParser.advanceCurrentPosition(variable.length());
			return new LetVariableExpression(variable, argMap);
		}

	}
}
