package Json;

import java.util.List;
import java.util.ArrayList;

/**
 *      class JsonTokenizer
 *      tokenizes a JSON string into a list of tokens
 *      @author Frank Cambria
 */
class JsonTokenizer
{
        // input JSON string to tokenize
        private String jsonInput;
        // current position in the input string
        private int pos;
        // resulting tokens from tokenization
        private JsonTokens tokens; 
        // flags controlling tokenization behavior
        private int flags;

        /**
         *       public JsonTokenizer()
         *       base constructor, initializes position and sets default flags to include newlines
         */
        public JsonTokenizer()
        {
                this.pos = 0;
                this.flags = JsonTokenizerFlags.INCLUDE_NEWLINES.Value();                
        }

        /**
         *       public JsonTokenizer(int flags)
         *       constructor with flags parameter
         *       @param int flags
         */
        public JsonTokenizer(int flags)
        {
                this.pos = 0;
                this.flags = flags;
        }

        /**
         *       public JsonTokenizer(String jsonInput, int flags)
         *       constructor with JSON input and flags parameters
         *       @param String jsonInput
         *       @param int flags
         */
        public JsonTokenizer(String jsonInput, int flags)
        {
                this.pos = 0;
                this.flags = flags;
        }

        /**
         *       public void SetFlags(int flags)
         *       sets the tokenization flags
         *       @param int flags
         *       @return void
         */
        public void SetFlags(int flags)
        {
                this.flags = flags;
        }

        /**
         *       public JsonTokens Tokens()
         *       returns the list of tokens
         *       @return JsonTokens
         */
        public JsonTokens Tokens()
        {
                return tokens;
        }

        /**
         *       private boolean VerifyJsonInput()
         *       checks if the JSON input is valid (not null or empty)
         *       @return boolean
         */
        private boolean VerifyJsonInput()
        {
                if (jsonInput == null || jsonInput.isEmpty())
                {
                        return false;
                }
                return true;
        }

        /**
         *       private void ResetTokens()
         *       resets or initializes the token list and position
         *       @return void
         */
        private void ResetTokens()
        {
                if (tokens == null)
                {
                        tokens = new JsonTokens();
                }
                else
                {
                        this.tokens.Clear();                        
                }
                this.pos = 0;
        }

        /**
         *       private boolean FlagsIncludeWhitespace()
         *       checks if the flags include whitespace
         *       @return boolean
         */
        private boolean FlagsIncludeWhitespace()
        {
                return (flags & JsonTokenizerFlags.INCLUDE_WHITESPACE.Value()) == JsonTokenizerFlags.INCLUDE_WHITESPACE.Value();
        }

        /**
         *       private boolean FlagsIncludeNewlines()
         *       checks if the flags include newlines
         *       @return boolean
         */
        private boolean FlagsIncludeNewlines()
        {
                return (flags & JsonTokenizerFlags.INCLUDE_NEWLINES.Value()) == JsonTokenizerFlags.INCLUDE_NEWLINES.Value();
        }

        /**
         *       private void AdvancePos()
         *       increments the current position by one
         *       @return void
         */
        private void AdvancePos()
        {
                pos++;
        }

        /**
         *       private void AdvancePos(int count)
         *       increments the current position by the specified count
         *       @param int count
         *       @return void
         */
        private void AdvancePos(int count)
        {
                pos += count;
        }

        /**
         *       private boolean PosInBounds()
         *       checks if the current position is within the input string bounds
         *       @return boolean
         */
        private boolean PosInBounds()
        {
                boolean result = pos < jsonInput.length();
                return result;
        }

        /**
         *       private boolean PosInBounds(int pos)
         *       checks if the specified position is within the input string bounds
         *       @param int pos
         *       @return boolean
         */
        private boolean PosInBounds(int pos)
        {
                boolean result = pos < jsonInput.length();
                return result;
        }

        /**
         *       private char CurrentChar()
         *       returns the character at the current position
         *       @return char
         */
        private char CurrentChar()
        {
                return jsonInput.charAt(pos);
        }

        /**
         *       private char CharAt(int pos)
         *       returns the character at the specified position
         *       @param int pos
         *       @return char
         */
        private char CharAt(int pos)
        {
                return jsonInput.charAt(pos);
        }

        /**
         *       private char CurrentChar(int pos)
         *       returns the character at the specified position
         *       @param int pos
         *       @return char
         */
        private char CurrentChar(int pos)
        {
                return jsonInput.charAt(pos);
        }

        /**
         *       private boolean AtLineEnd()
         *       checks if the current character is a line end (\n or \r)
         *       @return boolean
         */
        private boolean AtLineEnd()
        {
                boolean result = (CurrentChar() == '\n' || CurrentChar() == '\r');
                return result;
        }

        /**
         *       private boolean AtStringEnd()
         *       checks if the current character is a string terminator (")
         *       @return boolean
         */
        private boolean AtStringEnd()
        {
                boolean result = CurrentChar() == '"';
                return result;
        }

        /**
         *       private boolean StrZeroLength(String str)
         *       checks if the string has zero length
         *       @param String str
         *       @return boolean
         */
        private boolean StrZeroLength(String str)
        {
                boolean result = str.length() == 0;
                return result;
        }

        /**
         *       private boolean StrEquals(String str, String cmp)
         *       checks if two strings are equal
         *       @param String str
         *       @param String cmp
         *       @return boolean
         */
        private boolean StrEquals(String str, String cmp)
        {
                boolean result = str.equals(cmp);
                return result;
        }

        /**
         *       private boolean StrEndsWith(String str, String cmp)
         *       checks if the string ends with the specified substring
         *       @param String str
         *       @param String cmp
         *       @return boolean
         */
        private boolean StrEndsWith(String str, String cmp)
        {
                boolean result = str.endsWith(cmp);
                return result;
        }

        /**
         *       private boolean IsEscapedChar()
         *       checks if the current character is an escape character (\)
         *       @return boolean
         */
        private boolean IsEscapedChar()
        {
                boolean result = CurrentChar() == '\\';
                return result;
        }

        /**
         *       private boolean IsControlChar()
         *       checks if the current character is a control character (ASCII < 32)
         *       @return boolean
         */
        private boolean IsControlChar()
        {
                boolean result = CurrentChar() < 32;
                return result;
        }

        /**
         *       private boolean IsDigit()
         *       checks if the current character is a digit
         *       @return boolean
         */
        private boolean IsDigit()
        {
                boolean result = Character.isDigit(CurrentChar());
                return result;
        }

        /**
         *       private boolean IsLetter()
         *       checks if the current character is a letter
         *       @return boolean
         */
        private boolean IsLetter()
        {
                boolean result = Character.isLetter(CurrentChar());
                return result;
        }

        /**
         *       private boolean IsPeriod()
         *       checks if the current character is a period (.)
         *       @return boolean
         */
        private boolean IsPeriod()
        {
                boolean result = CurrentChar() == '.';
                return result;
        }

        /**
         *       private boolean IsMinus()
         *       checks if the current character is a minus sign (-)
         *       @return boolean
         */
        private boolean IsMinus()
        {
                boolean result = CurrentChar() == '-';
                return result;
        }

        /**
         *       private boolean IsMinus(int pos)
         *       checks if the character at the specified position is a minus sign (-)
         *       @param int pos
         *       @return boolean
         */
        private boolean IsMinus(int pos)
        {
                boolean result = jsonInput.charAt(pos) == '-';
                return result;
        }

        /**
         *       private boolean IsPlus()
         *       checks if the current character is a plus sign (+)
         *       @return boolean
         */
        private boolean IsPlus()
        {
                boolean result = CurrentChar() == '+';
                return result;
        }

        /**
         *       private boolean IsPlus(int pos)
         *       checks if the character at the specified position is a plus sign (+)
         *       @param int pos
         *       @return boolean
         */
        private boolean IsPlus(int pos)
        {
                boolean result = jsonInput.charAt(pos) == '+';
                return result;
        }

        /**
         *       private boolean IsBackslash()
         *       checks if the current character is a forward slash (/)
         *       @return boolean
         */
        private boolean IsBackslash()
        {
                boolean result = CurrentChar() == '/';
                return result;
        }

        /**
         *       private boolean IsScientificNotation()
         *       checks if the current character is part of scientific notation (e or E)
         *       @return boolean
         */
        private boolean IsScientificNotation()
        {
                boolean result = jsonInput.charAt(pos) == 'e' || jsonInput.charAt(pos) == 'E';
                return result;
        }

        /**
         *       private boolean IsClosingCurly()
         *       checks if the current character is a closing curly brace (})
         *       @return boolean
         */
        private boolean IsClosingCurly()
        {
                boolean result = jsonInput.charAt(pos) == '}';
                return result;
        }

        /**
         *       private boolean IsClosingBracket()
         *       checks if the current character is a closing bracket (])
         *       @return boolean
         */
        private boolean IsClosingBracket()
        {
                boolean result = jsonInput.charAt(pos) == ']';
                return result;
        }

        /**
         *       private boolean IsComma()
         *       checks if the current character is a comma (,)
         *       @return boolean
         */
        private boolean IsComma()
        {
                boolean result = jsonInput.charAt(pos) == ',';
                return result;
        }

        /**
         *       private boolean IsColon()
         *       checks if the current character is a colon (:)
         *       @return boolean
         */
        private boolean IsColon()
        {
                boolean result = jsonInput.charAt(pos) == ':';
                return result;
        }

        /**
         *       private boolean IsWhitespace()
         *       checks if the current character is whitespace
         *       @return boolean
         */
        private boolean IsWhitespace()
        {
                boolean result = Character.isWhitespace(CurrentChar());
                return result;
        }

        /**
         *       private JsonToken ParseString()
         *       throws JsonTokenizeException
         *       parses a string literal from the input
         *       @return JsonToken
         */
        private JsonToken ParseString() throws JsonTokenizeException
        {
                StringBuilder sb = new StringBuilder();
                AdvancePos();
                int startPos = pos;

                while (PosInBounds())
                {
                        if (AtStringEnd())
                        {
                                AdvancePos();
                                return new JsonToken(JsonTokenType.STRING, sb.toString());
                        }

                        if (IsEscapedChar())
                        {
                                AdvancePos();
                                if (!PosInBounds())
                                {
                                        throw new JsonTokenizeException("Invalid string literal", startPos);
                                }

                                char escapedChar = CurrentChar();
                                switch (escapedChar)
                                {
                                        case '"', '\\', '/' ->
                                        {
                                                sb.append(escapedChar);
                                        }

                                        case 'n' ->
                                        {
                                                sb.append('\n');
                                        }

                                        case 'r' ->
                                        {
                                                sb.append('\r');
                                        }

                                        case 't' ->
                                        {
                                                sb.append('\t');
                                        }

                                        default ->
                                        {
                                                sb.append(escapedChar);
                                        }
                                }
                        }
                        else if (IsControlChar())
                        {
                                throw new JsonTokenizeException("Illegal control character in string", startPos);
                        }
                        else
                        {
                                sb.append(CurrentChar());
                        }
                        
                        AdvancePos();
                }

                throw new JsonTokenizeException("Invalid string literal", startPos);                
        }

        /**
         *       private JsonToken ParseNumber()
         *       throws JsonTokenizeException
         *       parses a numeric value from the input
         *       @return JsonToken
         */
        private JsonToken ParseNumber() throws JsonTokenizeException
        {
                StringBuilder sb = new StringBuilder();
                int startPos = pos;
                boolean hasDecimal  = false;
                boolean hasExponent = false;

                if (IsMinus())
                {
                        sb.append(CurrentChar());
                        AdvancePos();
                }
                
                if (!PosInBounds())
                {
                        throw new JsonTokenizeException("Incomplete number", startPos);
                }

                while (PosInBounds())                
                {
                        if (IsDigit())
                        {
                                sb.append(CurrentChar());
                        }
                        else if (IsPeriod() && !hasDecimal && !hasExponent)
                        {
                                hasDecimal = true;
                                sb.append(CurrentChar());
                        }
                        else if (IsScientificNotation() && !hasExponent)
                        {
                                hasExponent = true;
                                sb.append(CurrentChar());

                                if (PosInBounds(pos + 1) && (IsPlus(pos + 1) || IsMinus(pos + 1)))
                                {
                                        AdvancePos();
                                        sb.append(CurrentChar());
                                }
                        }
                        else
                        {
                                break;
                        }

                        AdvancePos();
                }

                String str = sb.toString();
                if (StrZeroLength(str) || StrEquals(str, "-") || StrEndsWith(str, "."))
                {
                        throw new JsonTokenizeException("Invalid number format", startPos);
                }

                return new JsonToken(JsonTokenType.NUMBER, str);
        }

        /**
         *       private JsonToken ParseComment()
         *       throws JsonTokenizeException
         *       parses a comment from the input
         *       @return JsonToken
         */
        private JsonToken ParseComment() throws JsonTokenizeException
        {
                StringBuilder sb = new StringBuilder();
                AdvancePos();

                if (!IsBackslash())
                {
                        throw new JsonTokenizeException("Invalid backslash character outside of comment of string", pos - 1);
                }

                sb.append(CharAt(pos - 1));
                
                while (PosInBounds() && !AtLineEnd())
                {
                        sb.append(CurrentChar());
                        AdvancePos();
                }

                return new JsonToken(JsonTokenType.COMMENT, sb.toString());
        }

        /**
         *       private JsonToken ParseKeyword()
         *       parses a keyword (true, false, null) or string from the input
         *       @return JsonToken
         */
        private JsonToken ParseKeyword()
        {
                StringBuilder sb = new StringBuilder();

                while (PosInBounds() && IsLetter())
                {
                        sb.append(CurrentChar());
                        AdvancePos();
                }

                String str = sb.toString();
                switch (str)
                {
                        case "true" ->
                        {
                                return new JsonToken(JsonTokenType.TRUE, str);
                        }
                        case "false" ->
                        {
                                return new JsonToken(JsonTokenType.FALSE, str);
                        }
                        case "null" ->
                        {
                                return new JsonToken(JsonTokenType.NULL, str);
                        }
                        default ->
                        {
                                return new JsonToken(JsonTokenType.STRING, str);                                
                        }
                }
        }

        /**
         *       private JsonToken ParseValue()
         *       throws JsonTokenizeException
         *       parses a value (number or keyword) from the input
         *       @return JsonToken
         */
        private JsonToken ParseValue() throws JsonTokenizeException
        {
                if (IsDigit() || IsMinus())
                {
                        return ParseNumber();
                }
                else if (IsLetter())
                {                        
                        return ParseKeyword();
                }
                else
                {
                        throw new JsonTokenizeException("Unexpected character '" + CurrentChar() + "'", pos);
                }
        }

        /**
         *       public void DisplayTokens()
         *       displays the tokenized JSON tokens
         *       @return void
         */
        public void DisplayTokens()
        {
                System.out.println("Tokens(" + tokens.size() + "):");
                for (JsonToken token : tokens.Tokens())
                {
                        token.Print();                        
                        if (token.TokenValue().equals("\n") || token.TokenValue().equals("\r") || token.TokenValue().equals("\r\n"))
                        {
                                System.out.println();
                        }                        
                }
                System.out.println();
        }

        /**
         *       private void ValidateStringValue()
         *       throws JsonTokenizeException
         *       validates that a string token is properly quoted if not a keyword
         *       @return void
         */
        private void ValidateStringValue() throws JsonTokenizeException
        {
                JsonToken token = tokens.Token(tokens.size() - 1);
                if (token.TokenType() == JsonTokenType.STRING)
                {
                        String str = token.TokenValue();
                        int len = str.length();
                        int tokenStartPos = jsonInput.indexOf(str, pos - len);
                        if (tokenStartPos != -1)
                        {
                                if (jsonInput.charAt(tokenStartPos) != '"')
                                {
                                        throw new JsonTokenizeException("Invalid keyword or string value '" + str + "' only keywords (true, false, null) allowed", tokenStartPos + 1);
                                }
                        }
                }
        }

        /**
         *       private JsonTokens Tokenize()
         *       throws JsonTokenizeException
         *       tokenizes the JSON input into a list of tokens
         *       @return JsonTokens
         */
        private JsonTokens Tokenize() throws JsonTokenizeException
        {
                if (!VerifyJsonInput())
                {
                        throw new JsonTokenizeException("No json provided", 0);
                }
                
                ResetTokens();

                while (PosInBounds())
                {
                        switch (CurrentChar())
                        {
                                case '\t' ->
                                {
                                        tokens.addToken(new JsonToken(JsonTokenType.TAB, "\t"));                                        
                                        AdvancePos();
                                }

                                case ' ' ->
                                {        
                                        if (FlagsIncludeWhitespace())
                                        {
                                                tokens.addToken(new JsonToken(JsonTokenType.WHITESPACE, " "));
                                        }                                
                                        AdvancePos();
                                }

                                case '\n' ->
                                {
                                        if (FlagsIncludeNewlines())
                                        {
                                                tokens.addToken(new JsonToken(JsonTokenType.LINE_END, "\n"));
                                        }                                        
                                        AdvancePos();
                                }

                                case '\r' ->
                                {
                                        if (CharAt(pos + 1) == '\n')
                                        {
                                                if (FlagsIncludeNewlines())
                                                {
                                                        tokens.addToken(new JsonToken(JsonTokenType.LINE_END, "\r\n"));
                                                }
                                                AdvancePos(2);
                                        }
                                        else
                                        {
                                                if (FlagsIncludeNewlines())
                                                {
                                                        tokens.addToken(new JsonToken(JsonTokenType.LINE_END, "\r"));
                                                }                                                
                                                AdvancePos();
                                        }
                                }

                                case '{' ->     
                                {
                                        tokens.addToken(new JsonToken(JsonTokenType.OPEN_CURLY, "{"));
                                        AdvancePos();
                                }

                                case '}' ->
                                {
                                        tokens.addToken(new JsonToken(JsonTokenType.CLOSE_CURLY, "}"));
                                        AdvancePos();
                                }

                                case '[' ->
                                {
                                        tokens.addToken(new JsonToken(JsonTokenType.OPEN_BRACKET, "["));
                                        AdvancePos();
                                }

                                case ']' ->
                                {
                                        tokens.addToken(new JsonToken(JsonTokenType.CLOSE_BRACKET, "]"));
                                        AdvancePos();
                                }

                                case ':' ->
                                {
                                        tokens.addToken(new JsonToken(JsonTokenType.COLON, ":"));
                                        AdvancePos();
                                }

                                case ',' ->
                                {
                                        tokens.addToken(new JsonToken(JsonTokenType.COMMA, ","));
                                        AdvancePos();
                                }

                                case '/' ->
                                {                                        
                                        tokens.addToken(ParseComment());
                                }

                                case '"' ->
                                {
                                        tokens.addToken(ParseString());
                                }

                                default ->
                                {
                                        tokens.addToken(ParseValue());
                                        ValidateStringValue();
                                }
                        }
                }

                tokens.addToken(new JsonToken(JsonTokenType.FILE_END, ""));
                return tokens;
        }

        /**
         *       public void TokenizeJson(String json)
         *       tokenizes the provided JSON string
         *       @param String json
         *       @return void
         */
        public void TokenizeJson(String json)
        {                                       
                this.jsonInput = json;
                try 
                {
                        Tokenize();
                }
                catch (JsonTokenizeException e)
                {
                        System.out.println("Tokenization Error: " + e.getMessage());
                }
        }

        /**
         *       public void TokenizeJson()
         *       tokenizes the current JSON input
         *       @return void
         */
        public void TokenizeJson()
        {                       
                try 
                {
                        Tokenize();
                }
                catch (JsonTokenizeException e)
                {
                        System.out.println("Tokenization Error: " + e.getMessage());
                }
        }
}
