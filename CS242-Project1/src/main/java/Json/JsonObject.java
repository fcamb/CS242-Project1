package Json;

import java.util.List;
import java.util.ArrayList;

import java.io.Serializable;

public class JsonObject extends JsonValue implements Serializable
{
        private static final long SerialVersionUID = 2L;
        
        List<JsonProperty> properties = new ArrayList<>();

        public void addProperty(JsonProperty property)
        {
                properties.add(property);
        }

        public int size()
        {
                return properties.size();
        }

        public String toString()
        {
                return "object properties.";
        }

        public JsonProperty get(int index)
        {
                return properties.get(index);
        }
}
