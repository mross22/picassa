package expressions;

import model.*;
import model.util.ColorCombinations;

public class ExponentExpression extends ParenExpression {

	private Expression myOperand1;
	private Expression myOperand2;

	public ExponentExpression(Expression operand1, Expression operand2) {
		myOperand1 = operand1;
		myOperand2 = operand2;
	}
	
	public RGBColor evaluate (double x, double y) {
		return ColorCombinations.exponent(myOperand1.evaluate(x, y), myOperand2.evaluate(x, y));
	}

	public static class Factory extends ExpressionFactory {
		public boolean isThisTypeOfExpression() {
			return isThisTypeParenExpression("exp");
		}

		@Override
		public Expression parseExpression() {
			Expression left = parseNextArgumentExpression();
			Expression right = parseNextArgumentExpression();

			myParser.skipWhiteSpace();
			if (myParser.currentCharacter() == ')') {
				myParser.advanceCurrentPosition(1);
				return new ExponentExpression(left, right);
			} else {
				throw new ParserException(
						"Expected close paren, instead found "
								+ myParser.getInput().substring(
										myParser.getCurrentPosition()));
			}

		}
	}



	

}
