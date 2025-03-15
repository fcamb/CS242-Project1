package Json;

import java.util.ArrayList;
import java.io.Serializable;

/**
*       JsonArray class
*       extends JsonValue
*       implements Serializable
*       represents a json array
*       @author Frank Cambria
*/
public class JsonArray extends JsonValue implements Serializable
{
        // serialization id
        private static final long SerialVersionUID = 3L;
        
        // array list of the JsonValue elements in the array
        private ArrayList<JsonValue> elements = new ArrayList<>();

        /**
        *       addElement(JsonValue element)
        *       adds an element to the elements array list
        *       @param JsonValue element
        */
        public void addElement(JsonValue element)
        {
                elements.add(element);
        }

        /**
        *       int size()
        *       returns the size of the elements array list
        *       @return int
        */ 
        public int size()
        {
                return elements.size();
        }

        /**
        *       JsonValue get(int index)
        *       gets the element at index
        *       @param int index
        *       @return JsonValue
        */ 
        public JsonValue get(int index)
        {
                return elements.get(index);
        }

        /**
        *       public String toString()
        *       toString method
        *       @return String
        */ 
        public String toString()
        {
                return "JsonArray";
        }
}
