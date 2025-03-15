package Json;

import java.util.List;
import java.util.ArrayList;

import java.io.Serializable;

/**
 *       JsonObject class
 *       extends JsonValue
 *       implements Serializable
 *       represents a JSON object with a list of properties
 *       @author Frank Cambria
 */
public class JsonObject extends JsonValue implements Serializable
{
        // serialization id
        private static final long SerialVersionUID = 2L;
        
        // list of JsonProperty elements in the object
        List<JsonProperty> properties = new ArrayList<>();

        /**
         *       public void addProperty(JsonProperty property)
         *       adds a property to the properties list
         *       @param JsonProperty property
         *       @return void
         */
        public void addProperty(JsonProperty property)
        {
                properties.add(property);
        }

        /**
         *       public int size()
         *       returns the number of properties in the object
         *       @return int
         */
        public int size()
        {
                return properties.size();
        }

        /**
         *       public String toString()
         *       returns a string representation of the object
         *       @return String
         */
        public String toString()
        {
                return "object properties.";
        }

        /**
         *       public JsonProperty get(int index)
         *       retrieves the property at the specified index
         *       @param int index
         *       @return JsonProperty
         */
        public JsonProperty get(int index)
        {
                return properties.get(index);
        }
}
