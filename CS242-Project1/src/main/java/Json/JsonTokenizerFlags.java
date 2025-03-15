package Json;

/**
 *      enum JsonTokenizerFlags
 *      defines flags for controlling JSON tokenization behavior
 *      @author Frank Cambria
 */
public enum JsonTokenizerFlags
{
        NONE(0),
        INCLUDE_WHITESPACE(1),
        INCLUDE_NEWLINES(2);

        // numeric value of the flag
        private final int value;
        
        /**
         *       JsonTokenizerFlags(int value)
         *       constructor with a numeric value for the flag
         *       @param int value
         */
        JsonTokenizerFlags(int value)
        {
                this.value = value;
        }

        /**
         *       public int Value()
         *       returns the numeric value of the flag
         *       @return int
         */
        public int Value()
        {
                return value;
        }
}
