package expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.*;

public class NumberExpression extends Expression {

	// double is made up of an optional negative sign, followed by a sequence
	// of one or more digits, optionally followed by a period, then possibly
	// another sequence of digits
	private static final Pattern DOUBLE_REGEX = Pattern
			.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");

	RGBColor myValue;

	public NumberExpression(RGBColor rgb) {
		myValue = rgb;
	}

	public RGBColor evaluate(double x, double y){
		return myValue;
	}


	public static class Factory extends ExpressionFactory {
		public boolean isThisTypeOfExpression() {
			myParser.skipWhiteSpace();
			String temp = myParser.getInput().substring(myParser.getCurrentPosition());
			Matcher doubleMatcher = DOUBLE_REGEX.matcher(temp);
			return doubleMatcher.lookingAt();
		}

		public Expression parseExpression() {
			myParser.skipWhiteSpace();
			Matcher doubleMatcher = DOUBLE_REGEX.matcher(myParser
					.getInput());
			doubleMatcher.find(myParser.getCurrentPosition());
			String numberMatch = myParser.getInput().substring(
					doubleMatcher.start(), doubleMatcher.end());
			myParser.setMyCurrentPosition(doubleMatcher.end());
			double value = Double.parseDouble(numberMatch);
			RGBColor gray = new RGBColor(value);
			return new NumberExpression(gray);			
		}
	}

	

}
