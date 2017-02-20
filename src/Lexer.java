import java.io.*;

public class Lexer {

	static public char ch = ' ';
	static public char ident = ' ';
	static private Buffer buffer = new Buffer(new DataInputStream(System.in));
	static public int nextToken;
	static public char nextChar;
	static public int intValue;
	static public int temp_index;
	static public int count = 0;
	static public int idload_count = 0;
	static public char flag_change = ' ';
	static public char flag_load = ' ';
	static public char flag_for = ' ';
	static public char op_temp  = ' ';
	static public char[] idload = new char[10];
	static public char[] idload_temp = new char[10];

	public static int lex() {
		while (Character.isWhitespace(ch))
			ch = buffer.getChar();
		if (Character.isLetter(ch)) {
			ident = Character.toLowerCase(ch);
			ch = buffer.getChar();
			if (ident == 'i' && ch == 'f') {
				ch = buffer.getChar();
				nextToken = Token.KEY_IF;
			} else if (ident == 'i' && ch == 'n') {
				ch = buffer.getChar(); // 't'
				ch = buffer.getChar();
				nextToken = Token.KEY_INT;
			} else if (ident == 'e' && ch == 'l') {
				ch = buffer.getChar(); // 's'
				ch = buffer.getChar(); // 'e'
				ch = buffer.getChar();
				nextToken = Token.KEY_ELSE;
			} else if (ident == 'e' && ch == 'n') {
					ch = buffer.getChar(); // 'd'
					ch = buffer.getChar();
					nextToken = Token.KEY_END;
			} else if (ident == 'f' && ch == 'o') {
				ch = buffer.getChar(); // 'r'
				ch = buffer.getChar(); 
				nextToken = Token.KEY_FOR;
			} else
			nextToken = Token.ID;
		} else if (Character.isDigit(ch)) {
			nextToken = getNumToken(); // intValue would be set
		} else {
			nextChar = ch;
			switch (ch) {
			case ';':
				nextToken = Token.SEMICOLON;
				ch = buffer.getChar();
				break;
			case ',':
				nextToken = Token.COMMA;
				ch = buffer.getChar();
				break;
			case '+':
				nextToken = Token.ADD_OP;
				ch = buffer.getChar();
				break;
			case '-':
				nextToken = Token.SUB_OP;
				ch = buffer.getChar();
				break;
			case '*':
				nextToken = Token.MULT_OP;
				ch = buffer.getChar();
				break;
			case '/':
				nextToken = Token.DIV_OP;
				ch = buffer.getChar();
				break;
			case '=':
				ch = buffer.getChar();
				if (ch == '=') 
					  { nextToken = Token.EQ_OP; 
					    ch = buffer.getChar(); 
					  }
				else nextToken = Token.ASSIGN_OP;
				break;
			case '<':
				ch = buffer.getChar();
				if (ch=='=')
				{ nextToken=Token.LESSER_EQ;
				ch = buffer.getChar();
				}
				else nextToken = Token.LESSER_OP; 
				break;
			case '>':
				ch = buffer.getChar();
				if(ch=='=')
				{
					nextToken=Token.GREATER_EQ;
					ch = buffer.getChar();
					
				}
				else nextToken = Token.GREATER_OP;
				break;
			case '!':
				ch = buffer.getChar(); // '='
				nextToken = Token.NOT_EQ;
				ch = buffer.getChar();
				break;
			case '(':
				nextToken = Token.LEFT_PAREN;
				ch = buffer.getChar();
				break;
			case ')':
				nextToken = Token.RIGHT_PAREN;
				ch = buffer.getChar();
				break;
			case '{':
				nextToken = Token.LEFT_BRACE;
				ch = buffer.getChar();
				break;
			case '}':
				nextToken = Token.RIGHT_BRACE;
				ch = buffer.getChar();
				break;
			default:
				error("Illegal character " + ch);
				break;
			}
		}
		return nextToken;
	} // lex

	private static int getNumToken() {
		int num = 0;
		do {
			num = num * 10 + Character.digit(ch, 10);
			ch = buffer.getChar();
		} while (Character.isDigit(ch));
		intValue = num;
		return Token.INT_LIT;
	}
	
	public int number() {
		return intValue;
	} // number

	public Character identifier() {
		return ident;
	} // letter

	public static void error(String msg) {
		System.err.println(msg);
		System.exit(1);
	} // error


}
