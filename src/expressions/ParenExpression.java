package expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import model.Parser;
import model.ParserException;
import model.RGBColor;

public abstract class ParenExpression extends Expression {

	private List<Expression> subExpressions;

	protected ParenExpression(List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
		this.subExpressions = subExpressions;
		this.letVariableMap = letVariableMap;
	}

	protected List<RGBColor> evaluateSubexpressions(double x, double y) {
		List<RGBColor> result = new ArrayList<RGBColor>(subExpressions.size());
		for (Expression exp : subExpressions) {
			result.add(exp.evaluate(x, y));
		}
		return result;
	}

	public abstract static class Factory extends Expression.Factory {
		protected abstract String commandName();
		protected abstract int getNumberOfOperands();
		protected abstract ParenExpression makeNewParenExpression(
				List<Expression> subExpressions, Map<String, Expression> letVariableMap);

		public boolean isThisTypeOfExpression() {
			myParser.skipWhiteSpace();
			Matcher expMatcher = myParser.EXPRESSION_BEGIN_REGEX
					.matcher(myParser.getInput().substring(
							myParser.getCurrentPosition()));
			if (!expMatcher.lookingAt())
				return false;

			expMatcher = myParser.EXPRESSION_BEGIN_REGEX.matcher(myParser
					.getInput());
			expMatcher.find(myParser.getCurrentPosition());
			String parserCommandName = expMatcher.group(1);

			if (!parserCommandName.equals(commandName()))
				return false;

			return true;

		}

		public Expression parseExpression(Map<String, Expression> argMap) {
			Parser parser = Parser.getInstance();
			if (!isThisTypeOfExpression())
				throw new ParserException("Attempt to parse invalid string as "
						+ commandName() + " paren expression");

			parser.advanceCurrentPosition(commandName().length() + 1);

			List<Expression> subexpressions = new ArrayList<Expression>();
			for (int i = 0; i < getNumberOfOperands(); i++) {
				subexpressions.add(parser.parseExpression(argMap));
			}

			parser.skipWhiteSpace();
			if (parser.currentCharacter() == ')') {
				parser.advanceCurrentPosition(1);
				return makeNewParenExpression(subexpressions, argMap);
			} else {
				throw new ParserException(
						"Expected close paren, instead found "
								+ parser.getInput().substring(
										parser.getCurrentPosition()));
			}
		}
	}
}
