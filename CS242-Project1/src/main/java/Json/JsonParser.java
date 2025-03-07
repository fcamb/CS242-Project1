package Json;
import java.util.List;
import java.util.ArrayList;

public class JsonParser
{
        private JsonTokens tokens;
        private JsonValue parsedResult;
        private int pos;

        public JsonParser()
        {
                this.pos = 0;
        }

        public JsonParser(JsonTokens tokens)
        {
                this.tokens = tokens;
                this.pos = 0;
        }

        private void AdvancePos()
        {
                pos++;
        }

        private JsonToken CurrentToken()
        {
                return tokens.Token(pos);
        }

        private JsonTokenType CurrentTokenType()
        {
                return CurrentToken().TokenType();
        }

        private String CurrentTokenValue()
        {
                return CurrentToken().TokenValue();
        }

        private String TokenValue(int pos)
        {
                return tokens.Token(pos).TokenValue();
        }

        private JsonTokenType TokenType(int pos)
        {
                return tokens.Token(pos).TokenType();
        }

        private boolean PosInBounds()
        {
                return pos < tokens.size() && CurrentTokenType() != JsonTokenType.FILE_END;
        }

        private boolean IsClosingBracket()
        {
                boolean result = CurrentTokenType() == JsonTokenType.CLOSE_BRACKET;
                return result;
        }

        private boolean IsClosingCurly()
        {
                boolean result = CurrentTokenType() == JsonTokenType.CLOSE_CURLY;
                return result;
        }

        private boolean IsComma()
        {
                boolean result = CurrentTokenType() == JsonTokenType.COMMA;
                return result;
        }

        private boolean IsColon()
        {
                boolean result = CurrentTokenType() == JsonTokenType.COLON;
                return result;
        }
        
        private boolean IsString()
        {
                boolean result = CurrentTokenType() == JsonTokenType.STRING;
                return result;
        }

        private boolean IsComment()
        {
                boolean result = CurrentTokenType() == JsonTokenType.COMMENT;
                return result;
        }

        private boolean IsWhitespace()
        {
                boolean result = CurrentTokenType() == JsonTokenType.WHITESPACE;
                return result;                
        }

        private boolean IsTab()
        {
                boolean result = CurrentTokenType() == JsonTokenType.TAB;
                return result;
        }

        private boolean IsNewline()
        {
                boolean result = CurrentTokenType() == JsonTokenType.LINE_END;
                return result;
        }

        private boolean EndOfFile() throws JsonParseException
        {
                // could be whitespace at the end of the file, sometimes text editors new lines at the end 
                SkipWhitespace();
                boolean isLineEnd = CurrentTokenType() == JsonTokenType.FILE_END;                
                if (!isLineEnd)
                {
                        throw new JsonParseException("Unexpected tokens after file end", pos);                        
                }        
                return true;        
        }

        private void SkipWhitespace()
        {
                while (PosInBounds() && (IsWhitespace() || IsTab() || IsNewline()))
                {
                        AdvancePos();
                }
        }

        private String TokenValue()
        {
                String key = CurrentToken().TokenValue();
                return key;
        }

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
                        // make sure were still in bounds
                        if (!PosInBounds())
                        {
                                throw new JsonParseException("Unterminated object", startPos);
                        }

                        // skip to first key/value pair
                        SkipWhitespace();

                        // make sure where not a comment, advance pos until were not
                        if (IsComment())
                        {
                                while (IsComment())
                                {
                                        AdvancePos();
                                        SkipWhitespace();
                                }
                        }

                        // check if were at the end of a nested object
                        if (IsClosingCurly())
                        {
                                parsingObject = false;
                        }
                        else
                        {
                                // if not closing an object, next token must be a string value for the key so 
                                // make sure the current token is a string key
                                if (!IsString())
                                {
                                        throw new JsonParseException("Expected string key, got '" + CurrentTokenValue() + "'", pos);
                                }

                                // get key string and type
                                String key = CurrentTokenValue();
                                JsonTokenType type = CurrentTokenType();
                                
                                // advance pos to what should be colon if not closing curly
                                AdvancePos();
                                SkipWhitespace();

                                // if it isnt colon, error
                                if (!PosInBounds() || !IsColon())
                                {
                                        throw new JsonParseException("Expected colon after key '" + key + "'" + " type " + type, pos);
                                }

                                // skip to value 
                                AdvancePos();
                                SkipWhitespace();

                                // parse the value
                                JsonValue value = ParseValue();
                                object.addProperty(new JsonProperty(key, value));

                                // make sure were still in bounds
                                if (!PosInBounds())
                                {
                                        throw new JsonParseException("Unterminated object", startPos);
                                }

                                // skip any whitespace
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

        private JsonArray ParseArray() throws JsonParseException
        {
                JsonArray array = new JsonArray();
                AdvancePos();
                int startPos = pos - 1;

                if (!PosInBounds())
                {
                        throw new JsonParseException("Unterminated array", startPos);
                }

                // empty json array []
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

                        // make sure where not a comment, advance pos until were not
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

        private void Parse() throws JsonParseException
        {
                if (tokens == null || tokens.Empty())
                {
                        throw new JsonParseException("No json tokens provided", 0);
                }
                parsedResult = ParseValue();
        }

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
