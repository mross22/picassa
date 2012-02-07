package expressions.part2;

import java.util.List;
import java.util.Map;

import expressions.Expression;
import expressions.ParenExpression;

import model.*;
import model.util.ColorCombinations;


public class YCrCbToRGBExpression extends ParenExpression {


	public YCrCbToRGBExpression(List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
		super(subExpressions, letVariableMap);
	}
	
	@Override
	public RGBColor evaluate(double x, double y) {
		List<RGBColor> results = evaluateSubexpressions(x,y);
		return ColorCombinations.yCrCbtoRGB(results.get(0));
	}

	public static class Factory extends ParenExpression.Factory {
		
		@Override
		public int getNumberOfOperands() {
			return 1;
		}

		@Override
		public ParenExpression makeNewParenExpression(List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
			return new YCrCbToRGBExpression(subExpressions, letVariableMap);
		}

		@Override
		public String commandName() {
			return "yCrCbToRGB";			
		}

	}

}
