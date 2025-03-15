package Json;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

abstract class JsonValue implements Serializable
{
        // base class        
        
        private static final long SerialVersionUID = 4L;

        public abstract String toString();
}

class JsonString extends JsonValue implements Serializable
{
        private static final long SerialVersionUID = 5L;
        String value;
        JsonString(String value)
        {
                this.value = value;
        }

        public String toString()
        {
                return value;
        }
}

class JsonNumber extends JsonValue implements Serializable
{
        private static final long SerialVersionUID = 6L;
        String value;
        JsonNumber(String value)
        {
                this.value = value;
        }

        public String toString()
        {
                return value;
        }
}

class JsonBoolean extends JsonValue implements Serializable
{
        private static final long SerialVersionUID = 7L;
        boolean value;
        JsonBoolean(boolean value)
        {
                this.value = value;
        }

        public String toString()
        {
                return Boolean.toString(value);
        }
}

class JsonNull extends JsonValue implements Serializable
{
        private static final long SerialVersionUID = 8L;
        String value;
        JsonNull(String value)
        {
                this.value = "null";
        }

        public String toString()
        {
                return value;
        }
}

class JsonWhitespace extends JsonValue
{
        String value;
        JsonWhitespace(String value)
        {
                this.value = value;
        }

        public String toString()
        {
                return value;
        }
}

class JsonTab extends JsonValue
{
        String value;
        JsonTab(String value)
        {
                this.value = value;
        }

        public String toString()
        {
                return value;
        }
}

class JsonComment extends JsonValue 
{
        String value;
        JsonComment(String value)
        {
                  this.value = value;
        }

        public String toString()
        {
                return value;
        }
}

class JsonNewline extends JsonValue
{
        String value;
        JsonNewline(String value)
        {
                this.value = value;
        }

        public String toString()
        {
                return value;
        }
}
