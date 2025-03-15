package Json;

public enum JsonTokenizerFlags
{
        NONE(0),
        INCLUDE_WHITESPACE(1),
        INCLUDE_NEWLINES(2);

        private final int value;
        
        JsonTokenizerFlags(int value)
        {
                this.value = value;
        }

        public int Value()
        {
                return value;
        }
}
