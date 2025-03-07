package Json;

import java.util.List;
import java.util.ArrayList;

enum JsonTokenType
{
        NO_TOKEN,
        WHITESPACE,
        OPEN_CURLY,
        CLOSE_CURLY,
        OPEN_BRACKET,
        CLOSE_BRACKET,
        TAB,
        COLON,
        COMMA,
        STRING,
        TRUE,
        FALSE,
        NUMBER,
        COMMENT,
        NULL,
        LINE_END,
        FILE_END,
}

public class JsonToken
{
        private JsonTokenType type;
        private String value;

        public JsonToken(JsonTokenType type, String value)
        {
                this.type = type;
                this.value = value;
        }

        public String TokenValue()
        {
                return value;
        }

        public JsonTokenType TokenType()
        {
                return type;
        }

        public void Print()
        {
                if (value.equals("\r\n"))
                {                        
                        System.out.print("Token {" + type + ", '\\r\\n'}");
                }
                else if (value.equals("\r"))
                {
                        System.out.print("Token {" + type + ", '\\r'}");
                }
                else if (value.equals("\n"))
                {
                        System.out.print("Token {" + type + ", '\\n'}");       
                }
                else
                {
                        System.out.print("Token {" + type + ", '" + value + "'}");
                }
        }

        @Override
        public String toString()
        {        

                if (value.equals("\r\n") && type != JsonTokenType.COMMENT)
                {                        
                        return "Token {" + type + ", '\\r\\n'}";
                }
                else if (value.equals("\r") && type != JsonTokenType.COMMENT)
                {
                        return "Token {" + type + ", '\\r'}";
                }
                else if (value.equals("\n") && type != JsonTokenType.COMMENT)
                {
                        return "Token {" + type + ", '\\n'}";       
                }
                else
                {
                        return "Token {" + type + ", '" + value + "'}";
                }
        }
}

class JsonTokens
{
        private List<JsonToken> tokens = new ArrayList<>();
        public JsonTokens()
        {}

        public void setTokens(List<JsonToken> tokens)
        {
                this.tokens = tokens;
        }

        public JsonToken Token(int index)
        {
                return tokens.get(index);
        }

        public void addToken(JsonToken token)
        {
                tokens.add(token);
        }

        public List<JsonToken> Tokens()
        {
                return tokens;
        }

        public void Clear()
        {
                tokens.clear();
        }

        public boolean Empty()
        {
                return tokens.isEmpty();
        }

        public int size()
        {
                return tokens.size();
        }
}
