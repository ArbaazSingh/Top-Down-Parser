/* 		OBJECT-ORIENTED RECOGNIZER FOR SIMPLE EXPRESSIONS
expr    -> term   (+ | -) expr | term
term    -> factor (* | /) term | factor
factor  -> int_lit | '(' expr ')'     
*/

public class Parser {
	public static void main(String[] args) {
		System.out.println("Enter an expression\n");
		Lexer.lex();
		new Program();
		Code.output();
	}
}
//////////////////////////////////////////////////////////
class Program{ // progrM-> decls stmts end
	Decls d;
	Stmts s;

	
	public Program(){
		d = new Decls();
		s = new Stmts();
		
		Code.gen(Code.end());
		
		/////////////////
		
	}
}
class Decls{
	Idlist i;
	
	public Decls(){
		
		Lexer.lex();
		i=new Idlist();
		////////////
	}
}

class Idlist{
	Idlist i;
	public Idlist(){
		
		if (Lexer.nextToken==Token.ID)
		{
			Storage.array[Storage.index]=Lexer.ident;
			Storage.index=Storage.index+1;
			Storage.counter=Storage.counter+1;
			Lexer.lex();
		}
		switch (Lexer.nextToken) {
		case Token.COMMA: // ','
			Lexer.lex();
			i = new Idlist();
			break;
		case Token.SEMICOLON: // ','
			Lexer.lex();
			break;	
		default:
			break;
		}
	}
}

		

class Stmts{
	Stmt s,s2;
	Stmts s1;
	
	public Stmts(){
		
		
		s= new Stmt();
		if(Lexer.nextToken==Token.KEY_END)
			return;
		Lexer.lex();
		if(Lexer.nextToken==Token.RIGHT_BRACE)
			return;
		if(Lexer.nextToken!=Token.KEY_END)
		{	
			//if(Lexer.nextToken==Token.KEY_ELSE)
			//s2= new Stmt();
			//else
			s1= new Stmts();
			
			//if(Lexer.nextToken==Token.KEY_ELSE)
				//{
				//break;
				//}
				
		}
	}
}


class Stmt{
	Assign a;
	Cmpd cm;
	Cond co;
	Loop l;

 	public Stmt(){
		
		switch (Lexer.nextToken) {
		case Token.ID: // literal
			a=new Assign();
			break;
		case Token.LEFT_BRACE: // ','
			cm = new Cmpd();
			break;
		case Token.KEY_IF: // ','
			co = new Cond();
			break;
		case Token.KEY_FOR:
			Storage.for_count++;
			Storage.loc1++;
			l=new Loop();// ','
				break;
		default:
			break;
		}
	}
	
}

class Assign{
	Expr e;
	int i;
		public Assign(){
		if(Lexer.nextToken==Token.ID){
			int k=0;
			while(Storage.array[k]!=Lexer.ident){
				k=k+1;
			}
			Lexer.lex();
			Lexer.lex();
			e=new Expr();
			
			if(Storage.flag=='X')	{
				Loop.gen(Code.istore(k));}
				else
					Code.gen(Code.istore(k));
}
			

			}
	}


class Cmpd{
	Stmts s;
	
	public Cmpd(){
		Lexer.lex();
		s=new Stmts();
		if(Lexer.nextToken==Token.KEY_END)
			return;
		Lexer.lex();

	}
}

class Cond{
	Stmt s1,s2;
	Rexp r;
	Cond c;
	Stmt s;
	public Cond(){
		Lexer.lex();
		Lexer.lex();
		r= new Rexp();
		Code.gen(Storage.ifcase);
		//Code.codeptr_count = Code.codeptr_count + 3;
		Lexer.lex();
		s1 = new Stmt();
		if(Lexer.nextToken==Token.SEMICOLON)
			Lexer.lex();
		if(Lexer.nextToken==Token.KEY_ELSE){
			if(Storage.flag=='X'){	
				Loop.gen("goto");
			}
				else
					Code.gen("goto");
			Storage.c=Code.codeptr_count;
			Code.codeptr_count = Code.codeptr_count + 3;
//			Code.gen("goto");
			Lexer.lex();
			
			s2 = new Stmt();
			//break;
			}
		//Lexer.lex();
		//}	
		
	}
}

class Loop { //cmpd -> '{' stmts '}'
	//stmt s;
	Rexp r;
	Assign a1,a2;
	Expr e;
	Stmt s;
	public static int[] i=new int [10];
	public static int[] j=new int [10];
	char op;
	char c;
	public static String[][] temp=new String[10][10];
	public static int[][] loc=new int[10][10];
	public Loop() {
			Lexer.lex();
		    Lexer.lex();
		    	if (Lexer.nextToken == Token.ID)
		    	{	a1 = new Assign();
		    	Lexer.lex();
		    		
		    	}
		    	if (Lexer.nextToken == Token.SEMICOLON)
		    		Lexer.lex();
		    		
		    	if (Lexer.nextToken == Token.ID) 
		    		{
		    		r = new Rexp();
		    		Storage.flag_2 = 'X';
		    		Code.gen(Storage.ifcase);
		    		 Lexer.lex();	
					}
		    	if(Lexer.nextToken == Token.SEMICOLON)
		    		Lexer.lex();
		    				    		
		    
		    	if (Lexer.nextToken == Token.ID) {
		    		Storage.flag = 'X';
		    		a2 = new Assign();
		    		
				} 	
		    	if (Storage.flag_2 == 'X' )
		    		{Loop.gen("goto");
		    		 Storage.flag_2 = 'Y';
		    		}
		    	
				Lexer.lex();
				Storage.flag = 'Y';
				Storage.loc2 = 0;
				s = new Stmt();
				
				
				for(int p=0;temp[Storage.for_count][p]!=null;p++)
				{Code.gen(temp[Storage.for_count][p]);
				 //temp[Storage.for_count][p] = null; 
				}
					
				for (int p=0;p<10;p++)
					temp[Storage.for_count][p]=null;
				i[Storage.for_count] = 0;
				
				Storage.for_count--;
	}
	
	public static void gen(String s) {
			temp[Storage.for_count][i[Storage.for_count]] = s;
			i[Storage.for_count]=i[Storage.for_count]+1;

	}
	public static void loc(String s) {
		//loc[Storage.for_count][j[Storage.for_count]] = s;
		//j[Storage.for_count]=j[Storage.for_count]+1;

}
	public static void dif(String s) {
		//loc[Storage.for_count][j[Storage.for_count]] = s;
		//j[Storage.for_count]=j[Storage.for_count]+1;

}

}

class Rexp{
	Expr s,t;
	char op;
	
	public Rexp(){
		s= new Expr();
		
		switch (Lexer.nextToken){
		case Token.GREATER_OP:
			Storage.ifcase="if_icmple ";
			Lexer.lex();
			break;
		case Token.GREATER_EQ:
			Storage.ifcase="if_icmplt ";
			Lexer.lex();
			break;
		case Token.EQ_OP: 
			Storage.ifcase="if_icmpne ";
			Lexer.lex();
			break;
		case Token.NOT_EQ:
			Storage.ifcase="if_icmpeq ";
			Lexer.lex();
			break;
		case Token.LESSER_EQ:
			Storage.ifcase="if_icmpgt ";
			Lexer.lex();
			break;
		case Token.LESSER_OP:
			Storage.ifcase="if_icmpge";
			Lexer.lex();
			break;
		
		}
		t=new Expr();
	}
}


//////////////////////////////////////////////////////////

class Expr   { // expr -> term (+ | -) expr | term
	Term t;
	Expr e;
	char op;

	public Expr() {
		t = new Term();
		if (Lexer.nextToken == Token.ADD_OP || Lexer.nextToken == Token.SUB_OP) {
			op = Lexer.nextChar;
			Lexer.lex();
			e = new Expr();
			if(Storage.flag=='X'){	
			Loop.gen(Code.opcode(op));
			}
			else
				Code.gen(Code.opcode(op));
			Code.codeptr_count = Code.codeptr_count + 1;

			}
	}
}

class Term    { // term -> factor (* | /) term | factor
	Factor f;
	Term t;
	char op;

	public Term() {
		f = new Factor();
		if (Lexer.nextToken == Token.MULT_OP || Lexer.nextToken == Token.DIV_OP) {
			op = Lexer.nextChar;
			Lexer.lex();
			t = new Term();
			if(Storage.flag=='X'){	
				Loop.gen(Code.opcode(op));
				}
				else
					Code.gen(Code.opcode(op));
			Code.codeptr_count = Code.codeptr_count + 1;
}
	}
}
class Factor { // factor -> number | '(' expr ')'
	Expr e;
	int i;

	public Factor() {
		switch (Lexer.nextToken) {
		case Token.INT_LIT: // number
			i = Lexer.intValue;
			if(Storage.flag=='X'){	
				Loop.gen(Code.intcode(i));
			}
				else
					Code.gen(Code.intcode(i));
			Lexer.lex();
			break;
		//case:Token.
			
			
		case Token.LEFT_PAREN: // '('
			Lexer.lex();
			e = new Expr();
			Lexer.lex(); // skip over ')'
			break;
		case Token.ID:
			int k=0;
			while(Storage.array[k]!=Lexer.ident){
				k=k+1;
			}
			if(Storage.flag=='X'){	
				Loop.gen(Code.iload(k));
			}
				else
					Code.gen(Code.iload(k));
			Lexer.lex();
			
			break;
		
		default:
			break;
			
		}
	}
}

class Storage{
	public static char[] array=new char[20];
	public static int for_count =0;
	public static char flag= ' ';
	public static char flag_2= ' ';
	public static int index=0;
	public static int counter=0;
	public static int a = 0;
	public static int count=0;
	public static int ifcounter=0;
	public static String ifcase="";
	public static int c=0;
	public static int loc1=0;
	public static int loc2=0;
	
	
}



class Code {
	static String[] code = new String[100];
	static int[] temp = new int[100];
	static int[] index = new int[100];
	static int codeptr = 0;
	static int index1 = 0;
	static int codeptr_count = 0;
	static int v1 = 0;
	static int v2 = 0;
	static int v = 0;
	
	public static void gen(String s) {
		//if (Storage.flag == 'X') {
			//Storage.for_array[Storage.count] = s;
			//Storage.count++;
		//} else {
		if (Code.v2 == 0) 
			Code.v1 = 0;
		else
			Code.v1 = Code.v2;
		if (s.contains("if") || s.contains("goto")) {
			Code.v2 = Code.v1 + 3;
				Code.index[Code.index1] = Code.v1;
				Code.index1++;
			code[codeptr] = ": " +s;
			
		} else {
			code[codeptr] = s;
		}
			//code[codeptr] = s;
			codeptr=codeptr+1;
	//	}
	}
	
	public static String end(){
		if (Code.v2 == 0) 
			Code.v1 = 0;
		else
			Code.v1 = Code.v2;
		
			Code.index[Code.index1] = Code.v1;
			Code.index1++;
		Code.v2 = Code.v1 + 1;
		return ": "+"return";
	}
	
	public static String intcode(int i) {
		if (i > 127)
			{
			if (Code.v2 == 0) {
				Code.v1 = 0;
			}
			
			//codeptr_count = codeptr_count + 3;
				Code.index[Code.index1] = Code.v1;
				Code.index1++;
			Code.v2 = Code.v1 + 3;
			return ": "+"sipush " + i;
			}
		if (i > 5)
			{
			codeptr_count = codeptr_count + 2;
			if (Code.v2 == 0) 
				Code.v1 = 0;
			else
				Code.v1 = Code.v2;
				Code.index[Code.index1] = Code.v1;
				Code.index1++;
			Code.v2 = Code.v1 + 2;
			return ": "+"bipush " + i;
			}
			if (Code.v2 == 0) 
				Code.v1 = 0;
			else
				Code.v1 = Code.v2;
				Code.index[Code.index1] = Code.v1;
				Code.index1++;
			Code.v2 = Code.v1 + 1;
			//codeptr_count = codeptr_count + 1;
			
			return ": "+"iconst_" + i;
	}
	
	public static String istore(int i){
		if (i<=2){
			codeptr_count = codeptr_count + 1;
			i=i+1;
			if (Code.v2 == 0) 
				Code.v1 = 0;
			else
				Code.v1 = Code.v2;
			
				Code.index[Code.index1] = Code.v1;
				Code.index1++;
			
			Code.v2 = Code.v1 + 1;
			return ": "+"istore_" +i;
		}
		else{
			i=i+1;
			codeptr_count = codeptr_count + 2;
			if (Code.v2 == 0) 
				Code.v1 = 0;
			else
				Code.v1 = Code.v2;
			
				Code.index[Code.index1] = Code.v1;
				Code.index1++;
			Code.v2 = Code.v1 + 2;
			
			return ": "+"istore " +i;
		}
	}
	
	public static String iload(int i){
		if (i<=2){
			codeptr_count = codeptr_count + 1;
			i=i+1;
			if (Code.v2 == 0) 
				Code.v1 = 0;
			else
				Code.v1 = Code.v2;
			Code.index[Code.index1] = Code.v1;
			Code.index1++;
			Code.v2 = Code.v1 + 1;
			
			return ": "+"iload_" +i;
		}
		else{
			codeptr_count = codeptr_count + 2;
			i=i+1;
			if (Code.v2 == 0) 
				Code.v1 = 0;
			else
				Code.v1 = Code.v2;
			
				Code.index[Code.index1] = Code.v1;
				Code.index1++;}
			Code.v2 = Code.v1 + 2;
			
			return ": "+"iload " +i;
	}
	
	public static String opcode(char op) {
		if (Code.v2 == 0) 
			Code.v1 = 0;
		else
			Code.v1 = Code.v2;
		
			Code.index[Code.index1] = Code.v1;
			Code.index1++;
		Code.v2 = Code.v1 + 1;
		
		switch(op) {
		case '+' :{
			return ": "+"iadd";
		}
		case '-':{
			return ": "+"isub";
		}
		case '*':{
			return ": "+"imul";
		}
		case '/':{
			return ": "+"idiv";
		}
		default: return "";
		}		
	}
	
	public static void output() {
		for (int i=0; i<codeptr; i++)
			if (code[i]!=null){
				if (code[i].contains("if_icmp")) {
					for (int j=i; j<codeptr; j++)
						if (code[j].contains("goto"))
							{Code.v = 1;
							code[i] = code[i]+' '+String.valueOf(Code.index[j]);
							break;}
						else if(code[j].contains("return"))
							if (Code.v != 1) {
								code[i] = code[i]+' '+String.valueOf(Code.index[j]);	
								Code.v = 0;
								break;
							}
					Code.v = 0;
				}
				Code.v = 0;
				if (code[i].contains("goto" )) {
					for (int j=i; j<codeptr; j++)
						if (code[j].contains("return"))
						{
							code[i] = code[i]+' '+String.valueOf(Code.index[j]);
							break;
						}
				}
			System.out.println(String.valueOf(Code.index[i])+code[i]);
			}
			}		
}