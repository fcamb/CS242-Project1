package Json;

import java.util.ArrayList;
import java.util.List;

public abstract class JsonValue
{
        // base class    
}

class JsonProperty
{
        String key;
        JsonValue value;
        public JsonProperty(String key, JsonValue value)
        {
                this.key = key;
                this.value = value;
        }
}

class JsonObject extends JsonValue
{
        List<JsonProperty> properties = new ArrayList<>();

        public void addProperty(JsonProperty property)
        {
                properties.add(property);
        }

        public int size()
        {
                return properties.size();
        }
}

class JsonArray extends JsonValue
{
        ArrayList<JsonValue> elements = new ArrayList<>();

        public void addElement(JsonValue element)
        {
                elements.add(element);
        }

        public int size()
        {
                return elements.size();
        }
}

class JsonString extends JsonValue
{
        String value;
        JsonString(String value)
        {
                this.value = value;
        }
}

class JsonNumber extends JsonValue
{
        String value;
        JsonNumber(String value)
        {
                this.value = value;
        }
}

class JsonBoolean extends JsonValue
{
        boolean value;
        JsonBoolean(boolean value)
        {
                this.value = value;
        }
}

class JsonNull extends JsonValue
{
        String value;
        JsonNull(String value)
        {
                this.value = "null";
        }
}

class JsonWhitespace extends JsonValue
{
        String value;
        JsonWhitespace(String value)
        {
                this.value = value;
        }
}

class JsonTab extends JsonValue
{
        String value;
        JsonTab(String value)
        {
                this.value = value;
        }
}

class JsonComment extends JsonValue
{
        String value;
        JsonComment(String value)
        {
                  this.value = value;
        }
}

class JsonNewline extends JsonValue
{
        String value;
        JsonNewline(String value)
        {
                this.value = value;
        }
}
