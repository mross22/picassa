package expressions;

import model.*;
import model.util.ColorCombinations;

public class DivideExpression extends ParenExpression {

	Expression myOperand1;
	Expression myOperand2;

	public DivideExpression(Expression operand1, Expression operand2) {
		myOperand1 = operand1;
		myOperand2 = operand2;
	}

	public RGBColor evaluate(double x, double y) {
		return ColorCombinations.divide(myOperand1.evaluate(x, y), myOperand2
				.evaluate(x, y));
	}

	public static class Factory extends ExpressionFactory {

		@Override
		public boolean isThisTypeOfExpression() {
			return isThisTypeParenExpression("div");
		}

		@Override
		public Expression parseExpression() {
			Expression left = parseNextArgumentExpression();
			Expression right = parseNextArgumentExpression();

			myParser.skipWhiteSpace();
			if (myParser.currentCharacter() == ')') {
				myParser.advanceCurrentPosition(1);
				return new DivideExpression(left, right);
			} else {
				throw new ParserException(
						"Expected close paren, instead found "
								+ myParser.getInput().substring(
										myParser.getCurrentPosition()));
			}
		}

	}

}