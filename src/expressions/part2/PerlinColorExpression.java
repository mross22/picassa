package expressions.part2;

import java.util.List;
import java.util.Map;

import expressions.Expression;
import expressions.ParenExpression;

import model.*;
import model.util.ColorCombinations;


public class PerlinColorExpression extends ParenExpression {


	public PerlinColorExpression(List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
		super(subExpressions, letVariableMap);
	}
	
	@Override
	public RGBColor evaluate(double x, double y) {
		List<RGBColor> results = evaluateSubexpressions(x,y);
		return ColorCombinations.perlinColor(results.get(0), results.get(1));
	}

	public static class Factory extends ParenExpression.Factory {
		
		@Override
		public int getNumberOfOperands() {
			return 2;
		}

		@Override
		public ParenExpression makeNewParenExpression(List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
			return new PerlinColorExpression(subExpressions, letVariableMap);
		}

		@Override
		public String commandName() {
			return "perlinColor";
		}

	}

}
