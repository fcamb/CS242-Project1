package Json;

class JsonTokenizeException extends Exception
{
        int pos;
        JsonTokenizeException(String message, int pos)
        {
                super(message + " at position " + pos);
                this.pos = pos;
        }
}
