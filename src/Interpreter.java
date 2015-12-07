/**
 * Created by li_qiang on 2015/11/19.
 * Interpreter class
 */
public class Interpreter {
    private String text;
    private int pos;
    private Token currentToken;
    private char currentChar;

    public Interpreter(String text){
        this.text = text;
        this.pos = 0;
        this.currentToken = null;
        this.currentChar = text.charAt(pos);
    }

    private Exception err(){
        return new Exception("Token type not found!");
    }

    private void advance(){
        pos++;
        if(pos>text.length()-1){
            currentChar = (char)0;
        }else{
            currentChar = text.charAt(pos);
        }
    }

    private void skipWriteSapce(){
        while(currentChar!=(char)0 && Character.isWhitespace(currentChar)){
            advance();
        }
    }

    private int integer() throws Exception {
        String result = "";
        while(currentChar!=(char)0 && Character.isDigit(currentChar)) {
            result+=currentChar;
            advance();
        }
        try {
            return Integer.parseInt(result);
        }catch (Exception ex){
            throw new Exception("Integer parse exception!");
        }
    }

    private Token getNextToken() throws Exception {
        while(currentChar!=(char)0){
            if(Character.isWhitespace(currentChar)){
                skipWriteSapce();
                continue;
            }
            if(Character.isDigit(currentChar)){
                return new Token(TokenType.INTEGER, String.valueOf(integer()));
            }
            if(currentChar=='+'){
                advance();
                return new Token(TokenType.PLUSE, String.valueOf(currentChar));
            }
            if(currentChar=='-'){
                advance();
                return new Token(TokenType.MINUS, String.valueOf(currentChar));
            }
            throw err();
        }

        return new Token(TokenType.EOF, null);
    }

    private void eat(TokenType tokenType) throws Exception {
        if(currentToken.getType() == tokenType){
            currentToken = getNextToken();
        }else{
            throw err();
        }
    }

    private int term() throws Exception {
        Token token = this.currentToken;
        this.eat(TokenType.INTEGER);
        return Integer.parseInt(token.getValue());
    }

    public String expr() throws Exception {
        currentToken = getNextToken();

        int result = this.term();

        while(this.currentToken.getType()==TokenType.PLUSE || this.currentToken.getType()==TokenType.MINUS){
            Token token = this.currentToken;
            if(token.getType()==TokenType.PLUSE){
                this.eat(TokenType.PLUSE);
                result += this.term();
            }
            else if(token.getType()==TokenType.MINUS){
                this.eat(TokenType.MINUS);
                result -= this.term();
            }
        }

        return Integer.toString(result);
    }
}
