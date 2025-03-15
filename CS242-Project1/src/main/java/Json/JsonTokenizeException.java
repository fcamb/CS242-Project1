package Json;

/**
 *      class JsonTokenizeException
 *      extends Exception
 *      represents an exception thrown during JSON tokenization with position information
 *      @author Frank Cambrias
 */
class JsonTokenizeException extends Exception
{
        // position in the input where the error occurred
        int pos;

        /**
         *       JsonTokenizeException(String message, int pos)
         *       constructor for the exception with a message and position
         *       @param String message
         *       @param int pos
         */
        JsonTokenizeException(String message, int pos)
        {
                super(message + " at position " + pos);
                this.pos = pos;
        }
}
