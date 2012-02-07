package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import expressions.*;

/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * Due to the nature of the language being parsed, a recursive descent parser is
 * used http://en.wikipedia.org/wiki/Recursive_descent_parser
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions, some functions)
 */
public class Parser {
	
	private static final Parser instance = new Parser();
	
	public static Parser getInstance(){
		return instance;
	}
	// double is made up of an optional negative sign, followed by a sequence
	// of one or more digits, optionally followed by a period, then possibly
	// another sequence of digits
	@SuppressWarnings("unused")
	private final Pattern DOUBLE_REGEX = Pattern
			.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");
	// expression begins with a left paren followed by the command name,
	// which is a sequence of alphabetic characters
	public final Pattern EXPRESSION_BEGIN_REGEX = Pattern
			.compile("\\(([a-z]+)");
	
	public final Pattern LET_VARIABLE_REGEX = Pattern.compile("([a-z]+)");

	public List<Expression.Factory> kindsOfExpressions = new ArrayList<Expression.Factory>();

	// state of the parser
	private int myCurrentPosition;
	private String myInput;

	 private Parser() {
		kindsOfExpressions.clear();
		kindsOfExpressions.add(new ColorExpression.Factory());
		kindsOfExpressions.add(new DivideExpression.Factory());
		kindsOfExpressions.add(new ExponentExpression.Factory());
		kindsOfExpressions.add(new MinusExpression.Factory());
		kindsOfExpressions.add(new ModulusExpression.Factory());
		kindsOfExpressions.add(new MultiplyExpression.Factory());
		kindsOfExpressions.add(new NegateExpression.Factory());
		kindsOfExpressions.add(new NumberExpression.Factory());
		kindsOfExpressions.add(new PlusExpression.Factory());
		kindsOfExpressions.add(new LetExpression.Factory());
		kindsOfExpressions.add(new LetVariableExpression.Factory());
		kindsOfExpressions.add(new VariableExpression.Factory());
	}	

	/**
	 * Converts given string into expression tree.
	 * 
	 * @param input
	 *            expression given in the language to be parsed
	 * @return expression tree representing the given formula
	 */
	public Expression makeExpression(String input) {
		myInput = input;
		myCurrentPosition = 0; // NEW LINE
		Expression result = parseExpression(new HashMap<String, Expression>());
		skipWhiteSpace();
		if (notAtEndOfString()) {
			throw new ParserException(
					"Unexpected characters at end of the string: "
							+ myInput.substring(myCurrentPosition),
					ParserException.Type.EXTRA_CHARACTERS);
		}
		return result;
	}

	public Expression parseExpression(Map<String, Expression> argMap) {
		for (Expression.Factory expressionKind : kindsOfExpressions) {
			if (expressionKind.isThisTypeOfExpression())
				return expressionKind.parseExpression(argMap);
		}
		throw new ParserException("Unexpected expression type", ParserException.Type.UNKNOWN_COMMAND);
	}

	public void skipWhiteSpace() {
		while (notAtEndOfString() && Character.isWhitespace(currentCharacter())) {
			myCurrentPosition++;
		}
	}

	public char currentCharacter() {
		return myInput.charAt(myCurrentPosition);
	}

	public boolean notAtEndOfString() {
		return myCurrentPosition < myInput.length();
	}
	
	public String getInput() {
		return myInput;
	}

	public int getCurrentPosition() {
		return myCurrentPosition;
	}

	public void setMyCurrentPosition(int position) {
		myCurrentPosition = position;
	}

	public void advanceCurrentPosition(int numChars) {
		myCurrentPosition += numChars;
	}
}
