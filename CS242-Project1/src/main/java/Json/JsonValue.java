package Json;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

/**
 *      abstract class JsonValue
 *      implements Serializable
 *      base class for all JSON value types
 *      @author Frank Cambria
 */
abstract class JsonValue implements Serializable
{
        // serialization id
        private static final long SerialVersionUID = 4L;

        /**
         *       public abstract String toString()
         *       returns a string representation of the JSON value
         *       @return String
         */
        public abstract String toString();
}

/**
 *       class JsonString
 *       extends JsonValue
 *       implements Serializable
 *       represents a JSON string value
 */
class JsonString extends JsonValue implements Serializable
{
        // serialization id
        private static final long SerialVersionUID = 5L;
        // string value
        String value;

        /**
         *       JsonString(String value)
         *       constructor with string value parameter
         *       @param String value
         */
        JsonString(String value)
        {
                this.value = value;
        }

        /**
         *       public String toString()
         *       returns the string value
         *       @return String
         */
        public String toString()
        {
                return value;
        }
}

/**
 *       class JsonNumber
 *       extends JsonValue
 *       implements Serializable
 *       represents a JSON numeric value
 */
class JsonNumber extends JsonValue implements Serializable
{
        // serialization id
        private static final long SerialVersionUID = 6L;
        // numeric value as a string
        String value;

        /**
         *       JsonNumber(String value)
         *       constructor with numeric value parameter as a string
         *       @param String value
         */
        JsonNumber(String value)
        {
                this.value = value;
        }

        /**
         *       public String toString()
         *       returns the numeric value as a string
         *       @return String
         */
        public String toString()
        {
                return value;
        }
}

/**
 *       class JsonBoolean
 *       extends JsonValue
 *       implements Serializable
 *       represents a JSON boolean value
 */
class JsonBoolean extends JsonValue implements Serializable
{
        // serialization id
        private static final long SerialVersionUID = 7L;
        // boolean value
        boolean value;

        /**
         *       JsonBoolean(boolean value)
         *       constructor with boolean value parameter
         *       @param boolean value
         */
        JsonBoolean(boolean value)
        {
                this.value = value;
        }

        /**
         *       public String toString()
         *       returns the boolean value as a string
         *       @return String
         */
        public String toString()
        {
                return Boolean.toString(value);
        }
}

/**
 *       class JsonNull
 *       extends JsonValue
 *       implements Serializable
 *       represents a JSON null value
 */
class JsonNull extends JsonValue implements Serializable
{
        // serialization id
        private static final long SerialVersionUID = 8L;
        // null value as a string
        String value;

        /**
         *       JsonNull(String value)
         *       constructor with null value parameter
         *       @param String value
         */
        JsonNull(String value)
        {
                this.value = "null";
        }

        /**
         *       public String toString()
         *       returns the null value as a string
         *       @return String
         */
        public String toString()
        {
                return value;
        }
}

/**
 *       class JsonWhitespace
 *       extends JsonValue
 *       represents a JSON whitespace value
 */
class JsonWhitespace extends JsonValue
{
        // whitespace value
        String value;

        /**
         *       JsonWhitespace(String value)
         *       constructor with whitespace value parameter
         *       @param String value
         */
        JsonWhitespace(String value)
        {
                this.value = value;
        }

        /**
         *       public String toString()
         *       returns the whitespace value
         *       @return String
         */
        public String toString()
        {
                return value;
        }
}

/**
 *       class JsonTab
 *       extends JsonValue
 *       represents a JSON tab value
 */
class JsonTab extends JsonValue
{
        // tab value
        String value;

        /**
         *       JsonTab(String value)
         *       constructor with tab value parameter
         *       @param String value
         */
        JsonTab(String value)
        {
                this.value = value;
        }

        /**
         *       public String toString()
         *       returns the tab value
         *       @return String
         */
        public String toString()
        {
                return value;
        }
}

/**
 *       class JsonComment
 *       extends JsonValue
 *       represents a JSON comment value
 */
class JsonComment extends JsonValue 
{
        // comment value
        String value;

        /**
         *       JsonComment(String value)
         *       constructor with comment value parameter
         *       @param String value
         */
        JsonComment(String value)
        {
                this.value = value;
        }

        /**
         *       public String toString()
         *       returns the comment value
         *       @return String
         */
        public String toString()
        {
                return value;
        }
}

/**
 *       class JsonNewline
 *       extends JsonValue
 *       represents a JSON newline value
 */
class JsonNewline extends JsonValue
{
        // newline value
        String value;

        /**
         *       JsonNewline(String value)
         *       constructor with newline value parameter
         *       @param String value
         */
        JsonNewline(String value)
        {
                this.value = value;
        }

        /**
         *       public String toString()
         *       returns the newline value
         *       @return String
         */
        public String toString()
        {
                return value;
        }
}
