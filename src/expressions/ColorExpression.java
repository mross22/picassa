package expressions;


import java.util.List;
import java.util.Map;

import model.RGBColor;
import model.util.ColorCombinations;

public class ColorExpression extends ParenExpression {

	
	public ColorExpression(List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
		super(subExpressions, letVariableMap);
	}
	
	public RGBColor evaluate(double x, double y){
		List<RGBColor> results = evaluateSubexpressions(x,y);
		return ColorCombinations.color(results.get(0), results.get(1), results.get(2));
	}

	public static class Factory extends ParenExpression.Factory {
		@Override
		public int getNumberOfOperands() {
			return 3;
		}

		@Override
		public ParenExpression makeNewParenExpression(List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
			return new ColorExpression(subExpressions, letVariableMap);
		}

		@Override
		public String commandName() {
			return "color";
		}

	}

}
