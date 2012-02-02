package expressions;

import model.*;
import model.util.*;

public class MinusExpression extends ParenExpression {
	
	Expression myOperand1;
	Expression myOperand2;
	
	public MinusExpression(Expression operand1, Expression operand2){
		myOperand1 = operand1;
		myOperand2 = operand2;
	}
	
	public RGBColor evaluate(double x, double y){
		return ColorCombinations.subtract(myOperand1.evaluate(x, y), myOperand2.evaluate(x, y));
	}

	public static class Factory extends ExpressionFactory {

		@Override
		public boolean isThisTypeOfExpression() {
			return isThisTypeParenExpression("minus");
		}

		@Override
		public Expression parseExpression() {
			Expression left = parseNextArgumentExpression();
			Expression right = parseNextArgumentExpression();

			myParser.skipWhiteSpace();
			if (myParser.currentCharacter() == ')') {
				myParser.advanceCurrentPosition(1);
				return new MinusExpression(left, right);
			} else {
				throw new ParserException(
						"Expected close paren, instead found "
								+ myParser.getInput().substring(
										myParser.getCurrentPosition()));
			}

		}

	}
}
