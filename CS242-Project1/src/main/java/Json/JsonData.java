package Json;

public class JsonData
{       
        
        private JsonObject data;                        

        public JsonData()
        {}
        
        public JsonData(JsonValue parsedJson)
        {
                data = (JsonObject)parsedJson;
        }
        
        public void SetData(JsonObject objectData)
        {
                data = objectData;
        }

        private void IsPropertyIndexInBounds(JsonObject object, int index)
                throws Exception
        {
                if (index >= object.size())
                {
                        throw new Exception("Index out of bounds of object - Index: " + index + " object size: " + object.size());
                }
        }

        private void IsArrayIndexInBounds(JsonArray array, int index)
                throws Exception
        {
                if (index >= array.size())
                {
                        throw new Exception("Index out of bounds of array - Index: " + index + " array size: " + array.size());
                }
        }

        public JsonObject BaseObject()
        {
                return data;
        }

        public JsonProperty GetProperty(JsonObject object, int index)
        {
                return object.get(index);

        }

        public String GetPropertyKey(JsonProperty property)
        {
                return property.key;
        }

        private String IsPropertyValueAString(JsonProperty property) 
                throws JsonValueException
        {
                if (property.value instanceof JsonString)
                {
                        return ((JsonString)property.value).value;
                }
                else
                {
                        throw new JsonValueException("Value of property: " + property.key + " is not a string.");
                }
        }

        public String GetPropertyValueAsAString(JsonProperty property)
        {
                String value = "";
                try
                {
                        value = IsPropertyValueAString(property);
                }
                catch (JsonValueException e)
                {
                        System.out.println(e.getMessage());
                        System.exit(1);
                }
                return value;
        }

        private int IsPropertyValueAnInteger(JsonProperty property)
                throws JsonValueException
        {
                if (property.value instanceof JsonNumber)
                {
                        return Integer.parseInt(((JsonNumber)property.value).value);
                }
                else 
                {
                        throw new JsonValueException("Value of property: " + property.key + " is not an integer.");
                }
        }

        public int GetPropertyValueAsInteger(JsonProperty property)
        {
                int value = 0;
                try
                {
                        value = IsPropertyValueAnInteger(property);
                }
                catch (JsonValueException e)
                {
                        System.out.println(e.getMessage());
                        System.exit(1);
                }
                return value;
        }

        private JsonArray IsPropertyValueAnArray(JsonProperty property)
                throws JsonValueException
        {
                if (property.value instanceof JsonArray)
                {
                        return (JsonArray) property.value;
                }
                else 
                {
                        throw new JsonValueException("Value of property: " + property.key + " is not an array.");
                }
        }

        public JsonArray GetPropertyValueAsAnArray(JsonProperty property)
        {
                JsonArray array = new JsonArray();
                try
                {
                        array = IsPropertyValueAnArray(property);
                }
                catch(JsonValueException e) {
                        System.out.println(e.getMessage());
                        System.exit(1);
                }
                return array;
        }

        private JsonObject IsPropertyValueAnObject(JsonProperty property)
                throws JsonValueException
        {
                if (property.value instanceof JsonObject)
                {
                        return (JsonObject) property.value;
                }
                else
                {
                        throw new JsonValueException("Value of property: " + property.key + " is not an object.");
                }
        }

        public JsonObject GetPropertyValueAsAnObject(JsonProperty property)
        {
                JsonObject object = new JsonObject();
                try
                {
                        object = IsPropertyValueAnObject(property);
                }
                catch (JsonValueException e)
                {
                        System.out.println(e.getMessage());
                        System.exit(1);
                }
                return object;
        }

        private boolean IsPropertyValueABoolean(JsonProperty property)
                throws JsonValueException
        {
                if (property.value instanceof JsonBoolean)
                {
                        return ((JsonBoolean)property.value).value;
                }
                else
                {
                        throw new JsonValueException("Value of property: " + property.key + " is not a boolean.");
                }
        }
        public boolean GetPropertyValueAsABoolean(JsonProperty property)
        {
                boolean value = false;
                try
                {
                        value = IsPropertyValueABoolean(property);
                }
                catch (JsonValueException e)
                {
                        System.out.println(e.getMessage());
                        System.exit(1);
                }
                return value;
        }

        private JsonObject IsValueAnObject(JsonValue value, int index)
                throws JsonValueException
        {
                if (value instanceof JsonObject)
                {
                        return (JsonObject) value;
                }
                else 
                {
                        throw new JsonValueException("Value is not an object at index " + index);
                }
        }

        public boolean IsPropertyValueNull(JsonProperty property)
        {
                if (property.value instanceof JsonNull)
                {
                        return true;
                }
                return false;
        }
        
        public JsonObject GetArrayValueAsAnObject(JsonArray array, int index)
        {
                JsonObject object = new JsonObject();
                try
                {
                        IsArrayIndexInBounds(array, index);
                        object = IsValueAnObject(array.get(index), index);
                }
                catch (JsonValueException e)
                {
                        System.out.println(e.getMessage());
                        System.exit(1);
                }
                catch(Exception e)
                {
                        System.out.println(e.getMessage());
                        System.exit(1);
                }
                return object;
        }

        private JsonArray IsValueAnArray(JsonValue value, int index)
                throws JsonValueException
        {
                if (value instanceof JsonArray)
                {
                        return (JsonArray)value;
                }
                else 
                {
                        throw new JsonValueException("Value is not an array at index " + index);
                }
        }

        public JsonArray GetArrayValueAsAnArray(JsonArray array, int index)
        {
                JsonArray arrayValue = new JsonArray();
                try
                {
                        IsArrayIndexInBounds(array, index);
                        arrayValue = IsValueAnArray(array.get(index), index);
                }
                catch (JsonValueException e)
                {
                        System.out.println(e.getMessage());
                        System.exit(1);
                }
                catch (Exception e)
                {
                        System.out.println(e.getMessage());
                        System.exit(1);
                }
                return arrayValue;
        }

        private int IsValueAnInteger(JsonValue value, int index)
                throws JsonValueException
        {
                if (value instanceof JsonNumber)
                {
                        return Integer.parseInt(((JsonNumber)value).value);
                }
                else 
                {
                        throw new JsonValueException("Value is not an integer at index " + index);
                }
        }

        public int GetArrayValueAsAnInteger(JsonArray array, int index)
        {
                int value = 0;
                try
                {       
                        IsArrayIndexInBounds(array, index);
                        value = IsValueAnInteger(array.get(index), index);
                }
                catch (JsonValueException e)
                {
                        System.out.println(e.getMessage());
                        System.exit(1);
                }
                catch (Exception e)
                {
                        System.out.println(e.getMessage());
                        System.exit(1);
                }
                return value;
        }

        private String IsValueAString(JsonValue value, int index)
                throws JsonValueException
        {
                if (value instanceof JsonString)
                {
                        return ((JsonString)value).value;
                }
                else 
                {
                        throw new JsonValueException("Value is not a string at index " + index);
                }
        }

        public String GetArrayValueAsString(JsonArray array, int index)
        {
                String value = "";
                try
                {
                        IsArrayIndexInBounds(array, index);
                        value = IsValueAString(array.get(index), index);
                }
                catch (JsonValueException valueException)
                {
                        System.out.println(valueException.getMessage());
                        System.exit(1);
                }
                catch (Exception exception)
                {
                        System.out.println(exception.getMessage());
                        System.exit(1);
                }
                return value;
        }

        private boolean IsValueABoolean(JsonValue value, int index)
                throws JsonValueException
        {
                if (value instanceof JsonBoolean)
                {
                        return ((JsonBoolean)value).value;
                }
                else
                {
                        throw new JsonValueException("Value is not a boolean at index " + index);
                }
        }

        public boolean GetArrayValueAsBoolean(JsonArray array, int index)
        {
                boolean value = false;
                try
                {
                        IsArrayIndexInBounds(array, index);
                        value = IsValueABoolean(array.get(index), index);
                }
                catch (JsonValueException valueException)
                {
                        System.out.println(valueException.getMessage());
                        System.exit(1);
                }
                catch (Exception exception)
                {
                        System.out.println(exception.getMessage());
                        System.exit(1);
                }
                return value;
        }

        public boolean IsArrayValueNull(JsonArray array, int index)
        {
                if (array.get(index) instanceof JsonNull)
                {
                        return true;
                }
                return false;
        }

        public JsonProperty GetObjectProperty(JsonObject object, int index)
        {
                JsonProperty property = new JsonProperty();
                try
                {
                        IsPropertyIndexInBounds(object, index);
                        property = object.get(index);
                }
                catch (Exception e)
                {
                        System.out.println(e.getMessage());
                        System.exit(1);
                }
                return property;
        }
}
