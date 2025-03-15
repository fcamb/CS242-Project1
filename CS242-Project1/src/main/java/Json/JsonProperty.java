package Json;

import java.io.Serializable;

public class JsonProperty implements Serializable
{
        private static final long SerialVersionUID = 9L;
        
        String key;
        JsonValue value;
        
        public JsonProperty()
        {}

        public JsonProperty(String key, JsonValue value)
        {
                this.key = key;
                this.value = value;
        }

        public String toString()
        {
                return value.toString();
        }
}
