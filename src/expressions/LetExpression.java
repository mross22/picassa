package expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Parser;
import model.ParserException;
import model.RGBColor;

public class LetExpression extends ParenExpression{
	
	protected List<Expression> subExpressions;
	
	public LetExpression(List<Expression> subExpressions, Map<String, Expression> letVariableMap){
		super(subExpressions, letVariableMap);
		this.subExpressions = subExpressions;
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		Expression exp = subExpressions.get(2);
		return exp.evaluate(x,y);
	}
	
	
	public static class Factory extends ParenExpression.Factory {

		@Override
		protected String commandName() {
			return "let";
		}

		@Override
		protected int getNumberOfOperands() {
			return 3;
		}

		@Override
		protected ParenExpression makeNewParenExpression(
				List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
				return new LetExpression(subExpressions, letVariableMap);
		}
		
		@Override
		public ParenExpression parseExpression(Map<String, Expression> argMap){
			Parser parser = Parser.getInstance();
			if (!isThisTypeOfExpression())
				throw new ParserException("Attempt to parse invalid string as "
						+ commandName() + " paren expression");

			parser.advanceCurrentPosition(commandName().length() + 1);

			List<Expression> subexpressions = new ArrayList<Expression>();
			
			LetVariableExpression letVariable = (LetVariableExpression) parser.parseExpression(argMap);
			Expression letValue  = parser.parseExpression(argMap);
			argMap.put(letVariable.myVariable, letValue);
			Expression letEvaluatedExpression = parser.parseExpression(argMap); 
			
			subexpressions.add(letVariable);
			subexpressions.add(letValue);
			subexpressions.add(letEvaluatedExpression);
			
			subexpressions.get(2).letVariableMap.put(letVariable.getVariableName(), subexpressions.get(1));
			
			parser.skipWhiteSpace();			
			if (parser.currentCharacter() == ')') {
				parser.advanceCurrentPosition(1);
				return new LetExpression(subexpressions, argMap);
			} else {
				throw new ParserException(
						"Expected close paren, instead found "
								+ parser.getInput().substring(
										parser.getCurrentPosition()));
			}
		}
		
	}
}
