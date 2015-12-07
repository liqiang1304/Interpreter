/**
 * Created by li_qiang on 2015/11/19.
 * Token
 */
public class Token {
    private TokenType type;
    private String value;

    public Token(TokenType type, String value){
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public TokenType getType() {
        return type;
    }
}
