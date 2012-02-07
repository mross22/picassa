package expressions.part2;

import java.util.List;
import java.util.Map;

import expressions.Expression;
import expressions.ParenExpression;

import model.*;
import model.util.ColorCombinations;


public class TanExpression extends ParenExpression {


	public TanExpression(List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
		super(subExpressions, letVariableMap);
	}
	
	@Override
	public RGBColor evaluate(double x, double y) {
		List<RGBColor> results = evaluateSubexpressions(x,y);
		return ColorCombinations.tan(results.get(0));
	}

	public static class Factory extends ParenExpression.Factory {
		
		@Override
		public int getNumberOfOperands() {
			return 1;
		}

		@Override
		public ParenExpression makeNewParenExpression(List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
			return new TanExpression(subExpressions, letVariableMap);
		}

		@Override
		public String commandName() {
			return "tan";
		}

	}

}
