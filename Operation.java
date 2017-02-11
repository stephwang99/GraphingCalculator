
import java.util.regex.Pattern;


public class Operation {
	//instance variables, equation, y-value
	String equation;
	double y=0;
	
	//constructor takes a string which is the equation.
	public Operation(String e){
		this.equation = e;
	}
	
	//default contructor is y=x
	public Operation(){
		this.equation = "y=x";
	}

	public String toString(){
		return ("y: " + this.y);
	}
	
	//method that removes y= in the equation.
	public String shorten(){
		String temp = "";
		//goes through the string, as it hits =, it removes everything before it.
		for(int j = this.equation.indexOf('=')+1; j < this.equation.length(); j ++){
			temp += this.equation.charAt(j);
		}
				
		return temp;
	}
	
	//the actual calculation
	public double calculate(double x){
		String function = shorten();
		String temp = "";
		//BEDMAS, looks for bracket and does the operation inside.
		for(int i = 0; i < function.length(); i++){
			//as soon as it sees (
			if(function.charAt(i)=='('){
				int a = i+1;
				//register every char into a temp string until it hits )
				while(function.charAt(a)!=')'){
					temp += function.charAt(a);
					a++;
				}
				//returns the function after brackets done.
				function = function.replaceFirst(Pattern.quote(temp), Double.toString(doBracket(temp,x)));
				temp = "";
			}
		}
		this.y = doBedmas(function,x);
		return this.y;
	}
	
	//method that removes unnecessary brackets.
	public String removeBracket(String s){
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i)==')'){
				s = s.replaceAll("\\)", "");
				if(i==s.length()-1){
					return s;
				}
				
			}
			//under certain circumstances, replace ( with * or remove it
			else if(s.charAt(i)=='('){
				if(i==0){
					s = s.replaceFirst("\\(", "");
				}
				else if(s.charAt(i+1)=='-'&&!checkChar(s.charAt(i-1))){
					s = s.replaceFirst("\\(", "*");
				}
				else if(checkChar(s.charAt(i+1)) || checkChar(s.charAt(i-1))){
					s = s.replaceFirst("\\(", "");
				}
				else{
					s = s.replaceFirst("\\(", "*");
				}
				
			}
		}
		return s;
	}
	
	public double doBedmas(String s, double value){
		//the two values that are going through operation
		String first = "";
		String second = "";
		//the part of the string that is under going an operation
		String doing = "";
		//the operator, +,-,/,*
		char operator = ' ';
		double finale = 0;
		double result = 0;
		//removes unnecessary brackets or replaces it with *
		s = removeBracket(s);
		for(int i = 0; i < s.length(); i++){
			//if it's only the x value, return the x value
			if(i==0&&i==s.length()-1&&s.charAt(i)=='x'){
				s = s.replaceAll("x", Double.toString(value));
				finale = Double.parseDouble(s);
				return finale;
			}
			//if it's x replace it with x-value with brackets around it
			else if(s.charAt(i)=='x'){
				s = s.replaceAll("x", "(" + Double.toString(value) + ")");
				s = removeBracket(s);
			}
			//if the string is a double than return it.
			if(isDouble(s)){
				finale = Double.parseDouble(s);
				return finale;
			}
			//if it reaches the end of the string, do the last operation and return it.
			else if(i==s.length()-1){
				first += s.charAt(i);
				doing += s.charAt(i);
				result = bedmas(operator, Double.parseDouble(second), first);
				s = s.replaceFirst(Pattern.quote(doing), Double.toString(result));
				finale = Double.parseDouble(s);
				return finale;
			}
			//if the first char in the string is -, add to first 
			if(i==0 && s.charAt(0)=='-'){
				first+=s.charAt(i);
				doing+= s.charAt(i);
			}
			//if its a . add to first string
			else if(s.charAt(i)=='.'){
				first+=s.charAt(i);
				doing+=s.charAt(i);
			}
			//if the character is a digit, add to first string.
			else if(Character.isDigit(s.charAt(i))){
				doing += s.charAt(i);
				first+=s.charAt(i);
				if(isDouble(s)){
					finale = Double.parseDouble(s);
					i= s.length()+10;
				}
			}
			//if the character is an operator
			else if(checkChar(s.charAt(i))){				
				if(!(second.equals(""))){
					//check if the - is part of the number or just an operator
					if(s.charAt(i)=='-' && checkChar(s.charAt(i-1))){
						first+=s.charAt(i);
						doing+= s.charAt(i);
					}
					else{
						//if the second has a value, then do the operation and start the loop back.
						result = bedmas(operator, Double.parseDouble(second), first);
						s = s.replaceFirst(Pattern.quote(doing), Double.toString(result));
						second = "";
						first = "";
						doing = "";
						i = -1;	
					}
					
				}
				//if the operator is a multiplication or division, then remember the value and operator
				else if(s.charAt(i)== '*' || s.charAt(i)=='/'){
					operator = s.charAt(i);
					doing += s.charAt(i);
					second = first;
					first = "";
				}
				//if the operator is addition or subtraction, check if there is multiplcation or division
				else{
					if(checkOperator(s, doing)){
						first = "";
						doing = "";
						
					}
					//if not, do the operation
					else{
						operator = s.charAt(i);
						doing += s.charAt(i);
						second = first;
						first = "";
					}
					
				}
					
			}
			else{
				//System.out.prdoubleln ("what?");
			}
		
		}
		return finale;
	}
	
	//checking if the operator is multiplication or division
	public boolean checkOperator (String s, String t){
		s = s.replaceAll(t, "");
		for (int i =0; i < s.length(); i ++){
			if(s.charAt(i)== '*' || s.charAt(i)== '/'){
				return true;
			}
		}
		return false;
	}
	
	//method that checks if the string is a double or if it still has operators
	public boolean isDouble(String s){
		try{
  			Double.parseDouble(s);
  			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}
	
	//method to do the brackets in the equation
	public double doBracket(String s, double value){
		
		return doBedmas(s, value);
		
	}
	
	//method that does the operation
	public double bedmas(char operand, double a, String b){
		if(operand == '+'){
			a+= Double.parseDouble(b);
		}
		else if(operand == '-'){
			a-= Double.parseDouble(b);
		}
		else if(operand == '*'){
			a*= Double.parseDouble(b);
		}
		else if(operand == '/'){
			a /= Double.parseDouble(b);
		}
		else if(operand == '('){
			a *= Double.parseDouble(b);
		}
		else{
			return a;
			
		}
		return a;
	}
	
	//checks if the char is a digit or an operator
	public boolean checkChar(char c){
		if(c=='+' || c=='-' || c=='*' || c=='/' || c=='('){
			return true;
		}
		else{
			return false;
		}
	}
	
	//test method...
	//public static void main (String args []){
	//	Operation tryout = new Operation("y=2(x+5)");
	//	for(int i = -10; i <= 10; i ++){
	//		System.out.println("(" + i + ", " + tryout.calculate((double)i) + ")");
	//	}
	//}
	//flaws on no more than one operator in brackets, no exponents yet

}
