package expressions;


import java.util.List;
import java.util.Map;

import model.*;
import model.util.ColorCombinations;

public class ModulusExpression extends ParenExpression {

	public ModulusExpression(List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
		super(subExpressions, letVariableMap);
	}
	
	public RGBColor evaluate(double x, double y){
		List<RGBColor> results = evaluateSubexpressions(x,y);
		return ColorCombinations.modulus(results.get(0), results.get(1));
	}

	public static class Factory extends ParenExpression.Factory {

		@Override
		protected String commandName() {
			return "mod";
		}

		@Override
		protected int getNumberOfOperands() {
			return 2;
		}

		@Override
		protected ParenExpression makeNewParenExpression(
				List<Expression> subExpressions, Map<String, Expression> letVariableMap) {
			return new ModulusExpression(subExpressions, letVariableMap);
		}

	}

}
