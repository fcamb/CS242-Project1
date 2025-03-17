package Json;

import java.util.List;
import java.util.ArrayList;

/**
 *      class JsonTokens
 *      manages a list of JSON tokens
 *      @author Frank Cambria
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
