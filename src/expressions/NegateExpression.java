package expressions;


import model.*;
import model.util.ColorCombinations;

public class NegateExpression extends ParenExpression {

	private Expression myOperand;

	public NegateExpression(Expression operand) {
		myOperand = operand;
	}

	public RGBColor evaluate(double x, double y){
		return ColorCombinations.negate(myOperand.evaluate(x, y));
	}
	
	public static class Factory extends ExpressionFactory {
		
		public boolean isThisTypeOfExpression() {		
			return isThisTypeParenExpression("neg");
		}

		@Override
		public Expression parseExpression() {
			Expression exp = parseNextArgumentExpression();

			myParser.skipWhiteSpace();
			if (myParser.currentCharacter() == ')') {
				myParser.advanceCurrentPosition(1);
				return new NegateExpression(exp);
			} else {
				throw new ParserException(
						"Expected close paren, instead found "
								+ myParser.getInput().substring(
										myParser.getCurrentPosition()));
			}
		}
	}

}
