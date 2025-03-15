package Json;

/**
 *      class JsonValueException
 *      extends Exception
 *      represents an exception thrown when a JSON value type is invalid
 *      @author Frank Cambria
 */
class JsonValueException extends Exception
{
        /**
         *       JsonValueException(String message)
         *       constructor with error message
         *       @param String message
         */
        JsonValueException(String message)
        {
                super(message);
        }
}
