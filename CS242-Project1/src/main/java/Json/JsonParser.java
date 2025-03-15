package Json;
import java.util.List;
import java.util.ArrayList;

/**
 *      class JsonParser
 *      implements a recursive descent parser for JSON data
 *      @author Frank Cambria
 */
class JsonParser
{
        // list of tokens to parse
        private JsonTokens tokens;
        // result of the parsing process
        private JsonValue parsedResult;
        // current position in the token list
        private int pos;

        /**
         *       public JsonParser()
         *       base constructor, initializes position to 0
         */
        public JsonParser()
        {
                this.pos = 0;
        }

        /**
         *       public JsonParser(JsonTokens tokens)
         *       constructor with tokens parameter
         *       @param JsonTokens tokens
         */
        public JsonParser(JsonTokens tokens)
        {
                this.tokens = tokens;
                this.pos = 0;
        }

        /**
         *       private void AdvancePos()
         *       increments the current position in the token list
         *       @return void
         */
        private void AdvancePos()
        {
                pos++;
        }

        /**
         *       private JsonToken CurrentToken()
         *       returns the token at the current position
         *       @return JsonToken
         */
        private JsonToken CurrentToken()
        {
                return tokens.Token(pos);
        }

        /**
         *       private JsonTokenType CurrentTokenType()
         *       returns the type of the current token
         *       @return JsonTokenType
         */
        private JsonTokenType CurrentTokenType()
        {
                return CurrentToken().TokenType();
        }

        /**
         *       private String CurrentTokenValue()
         *       returns the value of the current token
         *       @return String
         */
        private String CurrentTokenValue()
        {
                return CurrentToken().TokenValue();
        }

        /**
         *       private String TokenValue(int pos)
         *       returns the value of the token at the specified position
         *       @param int pos
         *       @return String
         */
        private String TokenValue(int pos)
        {
                return tokens.Token(pos).TokenValue();
        }

        /**
         *       private JsonTokenType TokenType(int pos)
         *       returns the type of the token at the specified position
         *       @param int pos
         *       @return JsonTokenType
         */
        private JsonTokenType TokenType(int pos)
        {
                return tokens.Token(pos).TokenType();
        }

        /**
         *       private boolean PosInBounds()
         *       checks if the current position is within bounds and not at file end
         *       @return boolean
         */
        private boolean PosInBounds()
        {
                return pos < tokens.size() && CurrentTokenType() != JsonTokenType.FILE_END;
        }

        /**
         *       private boolean IsClosingBracket()
         *       checks if the current token is a closing bracket
         *       @return boolean
         */
        private boolean IsClosingBracket()
        {
                boolean result = CurrentTokenType() == JsonTokenType.CLOSE_BRACKET;
                return result;
        }

        /**
         *       private boolean IsClosingCurly()
         *       checks if the current token is a closing curly brace
         *       @return boolean
         */
        private boolean IsClosingCurly()
        {
                boolean result = CurrentTokenType() == JsonTokenType.CLOSE_CURLY;
                return result;
        }

        /**
         *       private boolean IsComma()
         *       checks if the current token is a comma
         *       @return boolean
         */
        private boolean IsComma()
        {
                boolean result = CurrentTokenType() == JsonTokenType.COMMA;
                return result;
        }

        /**
         *       private boolean IsColon()
         *       checks if the current token is a colon
         *       @return boolean
         */
        private boolean IsColon()
        {
                boolean result = CurrentTokenType() == JsonTokenType.COLON;
                return result;
        }
        
        /**
         *       private boolean IsString()
         *       checks if the current token is a string
         *       @return boolean
         */
        private boolean IsString()
        {
                boolean result = CurrentTokenType() == JsonTokenType.STRING;
                return result;
        }

        /**
         *       private boolean IsComment()
         *       checks if the current token is a comment
         *       @return boolean
         */
        private boolean IsComment()
        {
                boolean result = CurrentTokenType() == JsonTokenType.COMMENT;
                return result;
        }

        /**
         *       private boolean IsWhitespace()
         *       checks if the current token is whitespace
         *       @return boolean
         */
        private boolean IsWhitespace()
        {
                boolean result = CurrentTokenType() == JsonTokenType.WHITESPACE;
                return result;                
        }

        /**
         *       private boolean IsTab()
         *       checks if the current token is a tab
         *       @return boolean
         */
        private boolean IsTab()
        {
                boolean result = CurrentTokenType() == JsonTokenType.TAB;
                return result;
        }

        /**
         *       private boolean IsNewline()
         *       checks if the current token is a newline
         *       @return boolean
         */
        private boolean IsNewline()
        {
                boolean result = CurrentTokenType() == JsonTokenType.LINE_END;
                return result;
        }

        /**
         *       private boolean EndOfFile()
         *       throws JsonParseException
         *       checks if the end of the file is reached, throws exception if unexpected tokens remain
         *       @return boolean
         */
        private boolean EndOfFile() throws JsonParseException
        {
                SkipWhitespace();                
                boolean isLineEnd = CurrentTokenType() == JsonTokenType.FILE_END;                
                if (!isLineEnd)
                {
                        throw new JsonParseException("Unexpected tokens after file end", pos);                        
                }        
                return true;        
        }

        /**
         *       private void SkipWhitespace()
         *       advances position past any whitespace, tabs, or newlines
         *       @return void
         */
        private void SkipWhitespace()
        {
                while (PosInBounds() && (IsWhitespace() || IsTab() || IsNewline()))
                {
                        AdvancePos();
                }
        }

        /**
         *       private String TokenValue()
         *       returns the value of the current token
         *       @return String
         */
        private String TokenValue()
        {
                String key = CurrentToken().TokenValue();
                return key;
        }

        /**
         *       private JsonObject ParseObject()
         *       throws JsonParseException
         *       parses a JSON object from the token stream
         *       @return JsonObject
         */
        private JsonObject ParseObject() throws JsonParseException
        {
                JsonObject object = new JsonObject();
                AdvancePos();
                int startPos = pos - 1;

                if (!PosInBounds())
                {
                        throw new JsonParseException("Unterminated object", startPos);
                }

                if (IsClosingCurly())
                {
                        AdvancePos();
                        return object;
                }

                boolean parsingObject = true;
                while (parsingObject)
                {       
                        if (!PosInBounds())
                        {
                                throw new JsonParseException("Unterminated object", startPos);
                        }

                        SkipWhitespace();

                        if (IsComment())
                        {
                                while (IsComment())
                                {
                                        AdvancePos();
                                        SkipWhitespace();
                                }
                        }

                        if (IsClosingCurly())
                        {
                                parsingObject = false;
                        }
                        else
                        {
                                if (!IsString())
                                {
                                        throw new JsonParseException("Expected string key, got '" + CurrentTokenValue() + "'", pos);
                                }

                                String key = CurrentTokenValue();
                                JsonTokenType type = CurrentTokenType();
                                
                                AdvancePos();
                                SkipWhitespace();

                                if (!PosInBounds() || !IsColon())
                                {
                                        throw new JsonParseException("Expected colon after key '" + key + "'" + " type " + type, pos);
                                }

                                AdvancePos();
                                SkipWhitespace();

                                JsonValue value = ParseValue();
                                object.addProperty(new JsonProperty(key, value));

                                if (!PosInBounds())
                                {
                                        throw new JsonParseException("Unterminated object", startPos);
                                }

                                SkipWhitespace();

                                if (IsClosingCurly())
                                {
                                        parsingObject = false;
                                }
                                else if (!IsComma())
                                {
                                        throw new JsonParseException("Expected ',' or '}' after value, got '" + CurrentTokenValue() + "'", pos);
                                }
                        }

                        AdvancePos();
                }

                return object;
        }

        /**
         *       private JsonArray ParseArray()
         *       throws JsonParseException
         *       parses a JSON array from the token stream
         *       @return JsonArray
         */
        private JsonArray ParseArray() throws JsonParseException
        {
                JsonArray array = new JsonArray();
                AdvancePos();
                int startPos = pos - 1;

                if (!PosInBounds())
                {
                        throw new JsonParseException("Unterminated array", startPos);
                }

                if (IsClosingBracket())
                {
                        AdvancePos();
                        return array;
                }

                boolean parsingArray = true;
                while (parsingArray)
                {
                        if (!PosInBounds())
                        {
                                throw new JsonParseException("Unterminated array", startPos);
                        }

                        SkipWhitespace();

                        if (IsComment())
                        {
                                while (IsComment())
                                {
                                        AdvancePos();
                                        SkipWhitespace();
                                }
                        }

                        if (IsClosingBracket())
                        {
                                parsingArray = false;
                        }
                        else
                        {
                                array.addElement(ParseValue());

                                if (!PosInBounds())
                                {
                                        throw new JsonParseException("Unterminated array", startPos);
                                }

                                if (IsClosingBracket())
                                {                                
                                        parsingArray = false;
                                }
                                else if (!IsComma())
                                {
                                        throw new JsonParseException("Expected ',' or ']' after element, got '" + TokenValue(pos - 1) + "'", pos - 1);
                                }
                        }

                        AdvancePos();
                        SkipWhitespace();
                }

                return array;
        }

        /**
         *       private JsonValue ParseValue()
         *       throws JsonParseException
         *       parses a single JSON value from the token stream
         *       @return JsonValue
         */
        private JsonValue ParseValue() throws JsonParseException
        {                
                if (!PosInBounds())
                {
                        throw new JsonParseException("Unexpected end of tokens", pos);
                }

                int currPos = pos;

                switch (CurrentTokenType())
                {
                        case OPEN_CURLY ->
                        {
                                return ParseObject();
                        }

                        case OPEN_BRACKET ->
                        {
                                return ParseArray();
                        }

                        case STRING ->
                        {
                                AdvancePos();
                                return new JsonString(TokenValue(currPos));
                        }

                        case NUMBER ->
                        {
                                AdvancePos();
                                return new JsonNumber(TokenValue(currPos));
                        }

                        case TRUE ->
                        {
                                AdvancePos();
                                return new JsonBoolean(true);
                        }

                        case FALSE ->
                        {
                                AdvancePos();
                                return new JsonBoolean(false);
                        }

                        case NULL ->
                        {
                                AdvancePos();
                                return new JsonNull("null");
                        }

                        case WHITESPACE ->
                        {
                                AdvancePos();
                                return new JsonWhitespace(" ");
                        }

                        case TAB ->
                        {
                                AdvancePos();
                                return new JsonTab("\t");
                        }

                        case COMMENT ->
                        {
                                AdvancePos();
                                return new JsonComment(TokenValue(currPos));
                        }
                        
                        case LINE_END ->
                        {
                                AdvancePos();
                                return new JsonNewline(TokenValue(currPos));
                        }

                        default ->
                        {
                                throw new JsonParseException("Unexpected token '" + TokenValue(currPos) + "'", currPos);
                        }
                }                
        }

        /**
         *       private void VerifyJson()
         *       prints verification information about the parsed JSON result
         *       @return void
         */
        private void VerifyJson()
        {
                if (parsedResult instanceof JsonObject)
                {
                        JsonObject object = (JsonObject)parsedResult;
                        System.out.println("Parsed object with " + object.size() + " properties");
                }
                else if (parsedResult instanceof JsonArray)
                {
                        JsonArray array = (JsonArray)parsedResult;
                        System.out.println("Parsed object with " + array.size() + " elements");
                }
                else if (parsedResult instanceof JsonComment)
                {
                        JsonComment comment = (JsonComment)parsedResult;
                        System.out.println("Comment: " + comment.value);
                }
                System.out.println();
        }

        /**
         *       private void Parse()
         *       throws JsonParseException
         *       parses the token stream into a JSON structure
         *       @return void
         */
        private void Parse() throws JsonParseException
        {
                if (tokens == null || tokens.Empty())
                {
                        throw new JsonParseException("No json tokens provided", 0);
                }
                parsedResult = ParseObject();
        }

        /**
         *       public JsonData ParsedData()
         *       returns the parsed result as a JsonData object
         *       @return JsonData
         */
        public JsonData ParsedData()
        {
                if (parsedResult == null)
                {
                        return null;
                }                
                return new JsonData(parsedResult);
        }

        /**
         *       public void ParseJson(JsonTokens tokens)
         *       parses the provided tokens into a JSON structure
         *       @param JsonTokens tokens
         *       @return void
         */
        public void ParseJson(JsonTokens tokens)
        {
                this.tokens = tokens;
                this.pos = 0;
                try
                {
                        Parse();
                        EndOfFile();
                        VerifyJson();
                }
                catch (JsonParseException e)
                {
                        System.out.println("JsonParseException: " + e.getMessage());
                }
        }

        /**
         *       public void ParseJson()
         *       parses the current token stream into a JSON structure
         *       @return void
         */
        public void ParseJson() 
        {
                try
                {
                        Parse();
                        EndOfFile();
                        VerifyJson();
                }
                catch (JsonParseException e)
                {
                        System.out.println("JsonParseException: " + e.getMessage());
                }
        }
}
