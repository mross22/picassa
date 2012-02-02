package expressions;

import model.*;

public class VariableExpression extends Expression {

	private String myVariable;

	public VariableExpression(String command) {
		myVariable = command;
	}

	public RGBColor evaluate(double x, double y) {
		if (myVariable.equals("x"))
			return new RGBColor(x);
		else if (myVariable.equals("y"))
			return new RGBColor(y);
		else
			return null;
	}
	
	public static class Factory extends ExpressionFactory {
		@Override
		public boolean isThisTypeOfExpression() {
				myParser.skipWhiteSpace();
		    	return myParser.getInput().substring(myParser.getCurrentPosition(), myParser.getCurrentPosition()+1).equals("x") ||    
		    			myParser.getInput().substring(myParser.getCurrentPosition(), myParser.getCurrentPosition()+1).equals("y");
		    
		}

		@Override
		public Expression parseExpression() {
			
			if(myParser.getInput().substring(myParser.getCurrentPosition(), myParser.getCurrentPosition()+1).equals("x")){
				myParser.advanceCurrentPosition(1);
				return new VariableExpression("x");
			}
			else if (myParser.getInput().substring(myParser.getCurrentPosition(), myParser.getCurrentPosition()+1).equals("y")){
				myParser.advanceCurrentPosition(1);
				return new VariableExpression("y");
			}
	    	return null; 
		}
	}

}
