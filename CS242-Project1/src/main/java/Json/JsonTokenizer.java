package Json;

import java.util.List;
import java.util.ArrayList;

public class JsonTokenizer
{
        private String jsonInput;
        private int pos;
        private JsonTokens tokens; 
        private JsonTokenizerFlags flags;

        public JsonTokenizer()
        {
                this.pos = 0;
                this.flags = JsonTokenizerFlags.RELAXED_MODE;
        }

        public JsonTokenizer(JsonTokenizerFlags flags)
        {
                this.pos = 0;
                this.flags = flags;
        }

        public JsonTokenizer(String jsonInput, JsonTokenizerFlags flags)
        {
                this.jsonInput = jsonInput;
                this.pos = 0;
                this.flags = flags;
        }

        public void SetFlags(JsonTokenizerFlags flags)
        {
                this.flags = flags;
        }

        public JsonTokens Tokens()
        {
                return tokens;
        }

        private boolean VerifyJsonInput()
        {
                if (jsonInput == null || jsonInput.isEmpty())
                {
                        return false;
                }
                return true;
        }

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

        private boolean RelaxedMode()
        {
                boolean result = (flags.ordinal() & JsonTokenizerFlags.RELAXED_MODE.ordinal()) == JsonTokenizerFlags.RELAXED_MODE.ordinal();
                return result;
        }

        private void AdvancePos()
        {
                pos++;
        }

        private void AdvancePos(int count)
        {
                pos += count;
        }

        private boolean PosInBounds()
        {
                boolean result = pos < jsonInput.length();
                return result;
        }

        private boolean PosInBounds(int pos)
        {
                boolean result = pos < jsonInput.length();
                return result;
        }

        private char CurrentChar()
        {
                return jsonInput.charAt(pos);
        }

        private char CharAt(int pos)
        {
                return jsonInput.charAt(pos);
        }

        private char CurrentChar(int pos)
        {
                return jsonInput.charAt(pos);
        }

        private boolean AtLineEnd()
        {
                boolean result = (CurrentChar() == '\n' || CurrentChar() == '\r');
                return result;
        }

        private boolean AtStringEnd()
        {
                boolean result = CurrentChar() == '"';
                return result;
        }

        private boolean StrZeroLength(String str)
        {
                boolean result = str.length() == 0;
                return result;
        }

        private boolean StrEquals(String str, String cmp)
        {
                boolean result = str.equals(cmp);
                return result;
        }

        private boolean StrEndsWith(String str, String cmp)
        {
                boolean result = str.endsWith(cmp);
                return result;
        }

        private boolean IsEscapedChar()
        {
                boolean result = CurrentChar() == '\\';
                return result;
        }

        private boolean IsControlChar()
        {
                boolean result = CurrentChar() < 32;
                return result;
        }

        private boolean IsDigit()
        {
                boolean result = Character.isDigit(CurrentChar());
                return result;
        }

        private boolean IsLetter()
        {
                boolean result = Character.isLetter(CurrentChar());
                return result;
        }

        private boolean IsPeriod()
        {
                boolean result = CurrentChar() == '.';
                return result;
        }

        private boolean IsMinus()
        {
                boolean result = CurrentChar() == '-';
                return result;
        }

        private boolean IsMinus(int pos)
        {
                boolean result = jsonInput.charAt(pos) == '-';
                return result;
        }

        private boolean IsPlus()
        {
                boolean result = CurrentChar() == '+';
                return result;
        }

        private boolean IsPlus(int pos)
        {
                boolean result = jsonInput.charAt(pos) == '+';
                return result;
        }

        private boolean IsBackslash()
        {
                boolean result = CurrentChar() == '/';
                return result;
        }

        private boolean IsScientificNotation()
        {
                boolean result = jsonInput.charAt(pos) == 'e' || jsonInput.charAt(pos) == 'E';
                return result;
        }

        private boolean IsClosingCurly()
        {
                boolean result = jsonInput.charAt(pos) == '}';
                return result;
        }

        private boolean IsClosingBracket()
        {
                boolean result = jsonInput.charAt(pos) == ']';
                return result;
        }

        private boolean IsComma()
        {
                boolean result = jsonInput.charAt(pos) == ',';
                return result;
        }

        private boolean IsColon()
        {
                boolean result = jsonInput.charAt(pos) == ':';
                return result;
        }

        private boolean IsWhitespace()
        {
                boolean result = Character.isWhitespace(CurrentChar());
                return result;
        }

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

        private JsonToken ParseValue() throws JsonTokenizeException
        {
                if (IsDigit() || IsMinus())
                {
                        return ParseNumber();
                }
                else if (IsLetter())
                {                        
                        return ParseKeyword();
                        // if (RelaxedMode())
                        // {
                        //         return ParseRelaxedUnquotedString();
                        // }
                        // else
                        // {
                        //         return ParseKeyword();
                        // }
                }
                else
                {
                        throw new JsonTokenizeException("Unexpected character '" + CurrentChar() + "'", pos);
                }
        }

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
        }

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
                                        tokens.addToken(new JsonToken(JsonTokenType.WHITESPACE, " "));
                                        AdvancePos();
                                }

                                case '\n' ->
                                {
                                        tokens.addToken(new JsonToken(JsonTokenType.LINE_END, "\n"));
                                        AdvancePos();
                                }

                                case '\r' ->
                                {
                                        if (CharAt(pos + 1) == '\n')
                                        {
                                                tokens.addToken(new JsonToken(JsonTokenType.LINE_END, "\r\n"));        
                                                AdvancePos(2);
                                        }
                                        else
                                        {
                                                tokens.addToken(new JsonToken(JsonTokenType.LINE_END, "\r"));
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

        public void TokenizeJson(String json)
        {                       
                this.jsonInput = json;
                // System.out.println("Testing: " + this.jsonInput);
                try 
                {
                        Tokenize();
                }
                catch (JsonTokenizeException e)
                {
                        System.out.println("Tokenization Error: " + e.getMessage());
                }
        }

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
