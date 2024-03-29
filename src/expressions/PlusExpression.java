package expressions;

import java.util.List;
import java.util.Map;

import model.*;
import model.util.ColorCombinations;


public class PlusExpression extends ParenExpression {


	public PlusExpression(List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
		super(subExpressions, letVariableMap);
	}
	
	@Override
	public RGBColor evaluate(double x, double y) {
		List<RGBColor> results = evaluateSubexpressions(x,y);
		return ColorCombinations.add(results.get(0), results.get(1));
	}

	public static class Factory extends ParenExpression.Factory {
		
		@Override
		public int getNumberOfOperands() {
			return 2;
		}

		@Override
		public ParenExpression makeNewParenExpression(List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
			return new PlusExpression(subExpressions, letVariableMap);
		}

		@Override
		public String commandName() {
			return "plus";
		}

	}

}
