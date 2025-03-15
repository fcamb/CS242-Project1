package Json;

import java.io.Serializable;

/**
 *      class JsonProperty
 *      implements Serializable
 *      represents a key-value pair in a JSON object
 *      @author Frank Cambria
 */
public class JsonProperty implements Serializable
{
        // serialization id
        private static final long SerialVersionUID = 9L;
        
        // key of the property
        String key;
        // value of the property
        JsonValue value;
        
        /**
         *       public JsonProperty()
         *       base constructor
         */
        public JsonProperty()
        {}

        /**
         *       public JsonProperty(String key, JsonValue value)
         *       constructor with key and value parameters
         *       @param String key
         *       @param JsonValue value
         */
        public JsonProperty(String key, JsonValue value)
        {
                this.key = key;
                this.value = value;
        }

        /**
         *       public String toString()
         *       returns a string representation of the property's value
         *       @return String
         */
        public String toString()
        {
                return value.toString();
        }
}
