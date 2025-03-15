package Json;

import java.util.List;
import java.util.ArrayList;

/**
 *      enum JsonTokenType
 *      defines the possible token types in a JSON structure
 *      @author Frank Cambria
 */
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

/**
 *       class JsonToken
 *       represents a single token in a JSON structure
 */
class JsonToken
{
        // type of the token
        private JsonTokenType type;
        // value of the token
        private String value;

        /**
         *       public JsonToken(JsonTokenType type, String value)
         *       constructor with type and value parameters
         *       @param JsonTokenType type
         *       @param String value
         */
        public JsonToken(JsonTokenType type, String value)
        {
                this.type = type;
                this.value = value;
        }

        /**
         *       public String TokenValue()
         *       returns the value of the token
         *       @return String
         */
        public String TokenValue()
        {
                return value;
        }

        /**
         *       public JsonTokenType TokenType()
         *       returns the type of the token
         *       @return JsonTokenType
         */
        public JsonTokenType TokenType()
        {
                return type;
        }

        /**
         *       public void Print()
         *       prints the token in a formatted string
         *       @return void
         */
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

        /**
         *       @Override
         *       public String toString()
         *       returns a string representation of the token
         *       @return String
         */
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

/**
 *       class JsonTokens
 *       manages a list of JSON tokens
 */
class JsonTokens
{
        // list of JSON tokens
        private List<JsonToken> tokens = new ArrayList<>();

        /**
         *       public JsonTokens()
         *       base constructor
         */
        public JsonTokens()
        {}

        /**
         *       public void setTokens(List<JsonToken> tokens)
         *       sets the token list to the provided list
         *       @param List<JsonToken> tokens
         *       @return void
         */
        public void setTokens(List<JsonToken> tokens)
        {
                this.tokens = tokens;
        }

        /**
         *       public JsonToken Token(int index)
         *       returns the token at the specified index
         *       @param int index
         *       @return JsonToken
         */
        public JsonToken Token(int index)
        {
                return tokens.get(index);
        }

        /**
         *       public void addToken(JsonToken token)
         *       adds a token to the token list
         *       @param JsonToken token
         *       @return void
         */
        public void addToken(JsonToken token)
        {
                tokens.add(token);
        }

        /**
         *       public List<JsonToken> Tokens()
         *       returns the list of tokens
         *       @return List<JsonToken>
         */
        public List<JsonToken> Tokens()
        {
                return tokens;
        }

        /**
         *       public void Clear()
         *       clears the token list
         *       @return void
         */
        public void Clear()
        {
                tokens.clear();
        }

        /**
         *       public boolean Empty()
         *       checks if the token list is empty
         *       @return boolean
         */
        public boolean Empty()
        {
                return tokens.isEmpty();
        }

        /**
         *       public int size()
         *       returns the number of tokens in the list
         *       @return int
         */
        public int size()
        {
                return tokens.size();
        }
}
