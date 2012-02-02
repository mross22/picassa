package expressions;


import model.*;
import model.util.ColorCombinations;

public class ColorExpression extends ParenExpression {

	private Expression myOperand1;
	private Expression myOperand2;
	private Expression myOperand3;

	public ColorExpression(Expression operand1, Expression operand2,
			Expression operand3) {
		myOperand1 = operand1;
		myOperand2 = operand2;
		myOperand3 = operand3;
	}
	
	public RGBColor evaluate(double x, double y){
		return ColorCombinations.color(myOperand1.evaluate(x, y), myOperand2.evaluate(x, y), myOperand3.evaluate(x, y));
	}

	public static class Factory extends ExpressionFactory {
		@Override
		public boolean isThisTypeOfExpression() {
			return isThisTypeParenExpression("color");
		}

		@Override
		public Expression parseExpression() {
			Expression left = parseNextArgumentExpression();
			Expression center = parseNextArgumentExpression();
			Expression right = parseNextArgumentExpression();

			myParser.skipWhiteSpace();
			if (myParser.currentCharacter() == ')') {
				myParser.advanceCurrentPosition(1);
				return new ColorExpression(left, center, right);
			} else {
				throw new ParserException(
						"Expected close paren, instead found "
								+ myParser.getInput().substring(
										myParser.getCurrentPosition()));
			}
		}
	}

}
