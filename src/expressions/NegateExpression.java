package expressions;


import java.util.List;
import java.util.Map;

import model.*;
import model.util.ColorCombinations;

public class NegateExpression extends ParenExpression {

	public NegateExpression(List<Expression> subExpressions,Map<String, Expression> letVariableMap) {
		super(subExpressions, letVariableMap);
	}

	public RGBColor evaluate(double x, double y){
		List<RGBColor> results = evaluateSubexpressions(x,y);
		return ColorCombinations.negate(results.get(0));
	}
	
	public static class Factory extends ParenExpression.Factory {
		
		@Override
		public int getNumberOfOperands() {
			return 2;
		}

		@Override
		public ParenExpression makeNewParenExpression(List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
			return new NegateExpression(subExpressions, letVariableMap);
		}

		@Override
		public String commandName() {
			return "neg";
		}

	}

}
