package Json;

import java.util.ArrayList;

import java.io.Serializable;

public class JsonArray extends JsonValue implements Serializable
{
        private static final long SerialVersionUID = 3L;
        
        ArrayList<JsonValue> elements = new ArrayList<>();

        public void addElement(JsonValue element)
        {
                elements.add(element);
        }

        public int size()
        {
                return elements.size();
        }

        public String toString()
        {
                return "array elements.";
        }

        public JsonValue get(int index)
        {
                return elements.get(index);
        }
}
