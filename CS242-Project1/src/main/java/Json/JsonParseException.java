package Json;

class JsonParseException extends Exception
{
        private int pos;
        JsonParseException(String message, int pos)
        {
                super(message + " at position " + pos);
                this.pos = pos;
        }
}
