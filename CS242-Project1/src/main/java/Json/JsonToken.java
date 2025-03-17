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
