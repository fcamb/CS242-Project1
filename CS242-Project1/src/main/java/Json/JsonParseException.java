package Json;

/**
 *      class JsonParseException
 *      extends Exception
 *      represents an exception thrown during JSON parsing with position information
 *      @author Frank Cambria
 */
class JsonParseException extends Exception
{
        // position in the input where the error occurred
        private int pos;

        /**
         *       JsonParseException(String message, int pos)
         *       constructor for the exception with a message and position
         *       @param String message
         *       @param int pos
         */
        JsonParseException(String message, int pos)
        {
                super(message + " at position " + pos);
                this.pos = pos;
        }
}
