package Json;

/**
 *       class JsonData
 *       class that represents all of the parsed json data
 *       provides methods to the user to access their parsed json data
 *       @author Frank Cambria
 */ 
public class JsonData
{       
        // data that represents all the parsed json, 
        // is the base object of the json
        private JsonObject data;                        

        /**
         *       public JsonData() 
         *       base Constructor
         */ 
        public JsonData()
        {}
        
        /**
         *       public JsonData(JsonValue parsedJson)
         *       constructor with parsedJson parameter to set the data member to
         *       @param JsonValue parsedJson
         */ 
        public JsonData(JsonValue parsedJson)
        {
                data = (JsonObject)parsedJson;
        }
        
        /**
         *       public void setData(JsonObject objectData)
         *       sets the data member to objectData
         *       @param JsonObject objectData
         *       @return void
         */ 
        public void SetData(JsonObject objectData)
        {
                data = objectData;
        }

        /**
         *       private void IsPropertyIndexInBounds(JsonObject object, int index)
         *       throws Exception
         *       checks if the property index is in bounds, throws error if not
         *       @param JsonObject object
         *       @param int index
         *       @return void
         */ 
        private void IsPropertyIndexInBounds(JsonObject object, int index)
                throws Exception
        {
                if (index >= object.size())
                {
                        throw new Exception("Index out of bounds of object - Index: " + index + " object size: " + object.size());
                }
        }

        /**
         *       private void IsArrayIndexInBounds(JsonObject object, int index)
         *       throws Exception
         *       checks if the array index is in bounds, throws error if not
         *       @param JsonArray array
         *       @param int index
         *       @return void
         */ 
        private void IsArrayIndexInBounds(JsonArray array, int index)
                throws Exception
        {
                if (index >= array.size())
                {
                        throw new Exception("Index out of bounds of array - Index: " + index + " array size: " + array.size());
                }
        }

        /**
         *      public JsonObject BaseObject()
         *      returns the data member variable (the base object of the parsed json data)
         *      @return JsonObject
         */ 
        public JsonObject BaseObject()
        {
                return data;
        }

        /**
         *       public JsonProperty GetProperty(JsonObject object, int index)
         *       returns the property of the object at index
         *       @param JsonObject object
         *       @param int index
         *       @return JsonProperty 
         */ 
        public JsonProperty GetProperty(JsonObject object, int index)
        {
                return object.get(index);
        }

        /**
         *       public String GetPropertyKey(JsonProperty property)
         *       returns the properties key
         *       @param JsonProperty property
         *       @return String
         */  
        public String GetPropertyKey(JsonProperty property)
        {
                return property.key;
        }

        /**
         *       private String IsPropertyValueAString(JsonProperty property)
         *       throws JsonValueException
         *       checks if the property value is a string, returns the string if it is,
         *       throws an error if not
         *       @param JsonProperty property
         *       @return String 
         */ 
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

        /**
         *       public String GetPropertyValueAsAString(JsonProperty property)
         *       returns property value as a string
         *       @param JsonProperty property
         *       @return String
         */  
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

        /**
         *       private int IsPropertyValueAnInteger(JsonProperty property)
         *       throws JsonValueException
         *       checks if the property value is an integer, throws an exception if not,
         *       returns the integer value if it is
         *       @param JsonProperty property
         *       @return int
         */ 
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

        /**
         *       public int GetPropertyValueAsInteger(JsonProperty property)
         *       returns the property value as an integer if it is one
         *       @param JsonProperty property
         *       @return int
         */  
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

        /**
         *       private JsonArray IsPropertyValueAnArray(JsonProperty property)
         *       throws JsonValueException
         *       checks if property value is an array, throws exception if not, or returns the array value
         *       @param JsonProperty property
         *       @return JsonArray 
         */ 
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

        /**
         *       public JsonArray GetPropertyValueAsAnArray(JsonProperty property)
         *       returns the property value as an array if it is one
         *       @param JsonProperty property
         *       @return JsonArray
         */ 
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

        /**
         *       private JsonObject IsPropertyValueAnObject(JsonProperty property)
         *       throws JsonValueException
         *       checks if property value is an object, throws exception if not, returns the object if it is
         *       @param JsonProperty property
         *       @return JsonObject
         */ 
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

        /**
         *       public JsonObject GetPropertyValueAsAnObject(JsonProperty property)
         *       returns the property value as an object if it is one
         *       @param JsonProperty property
         *       @return JsonObject
         */ 
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

        /**
         *       private boolean IsPropertyValueABoolean(JsonProperty property)
         *       throws JsonValueException
         *       checks if property value is a boolean, throws exception if not, returns the boolean if it is
         *       @param JsonProperty property
         *       @return boolean
         */ 
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

        /**
         *       public boolean GetPropertyValueAsABoolean(JsonProperty property)
         *       returns the property value as a boolean if it is one
         *       @param JsonProperty property
         *       @return boolean
         */ 
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

        /**
         *       private JsonObject IsValueAnObject(JsonValue value, int index)
         *       throws JsonValueException
         *       checks if the value is an object, throws exception if not, returns the object if it is
         *       @param JsonValue value
         *       @param int index
         *       @return JsonObject
         */ 
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

        /**
         *       public boolean IsPropertyValueNull(JsonProperty property)
         *       checks if the property value is null
         *       @param JsonProperty property
         *       @return boolean
         */ 
        public boolean IsPropertyValueNull(JsonProperty property)
        {
                if (property.value instanceof JsonNull)
                {
                        return true;
                }
                return false;
        }
        
        /**
         *       public JsonObject GetArrayValueAsAnObject(JsonArray array, int index)
         *       returns the array value at index as an object if it is one
         *       @param JsonArray array
         *       @param int index
         *       @return JsonObject
         */ 
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

        /**
         *       private JsonArray IsValueAnArray(JsonValue value, int index)
         *       throws JsonValueException
         *       checks if the value is an array, throws exception if not, returns the array if it is
         *       @param JsonValue value
         *       @param int index
         *       @return JsonArray
         */ 
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

        /**
         *       public JsonArray GetArrayValueAsAnArray(JsonArray array, int index)
         *       returns the array value at index as an array if it is one
         *       @param JsonArray array
         *       @param int index
         *       @return JsonArray
         */ 
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

        /**
         *       private int IsValueAnInteger(JsonValue value, int index)
         *       throws JsonValueException
         *       checks if the value is an integer, throws exception if not, returns the integer if it is
         *       @param JsonValue value
         *       @param int index
         *       @return int
         */ 
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

        /**
         *       public int GetArrayValueAsAnInteger(JsonArray array, int index)
         *       returns the array value at index as an integer if it is one
         *       @param JsonArray array
         *       @param int index
         *       @return int
         */ 
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

        /**
         *       private String IsValueAString(JsonValue value, int index)
         *       throws JsonValueException
         *       checks if the value is a string, throws exception if not, returns the string if it is
         *       @param JsonValue value
         *       @param int index
         *       @return String
         */ 
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

        /**
         *       public String GetArrayValueAsString(JsonArray array, int index)
         *       returns the array value at index as a string if it is one
         *       @param JsonArray array
         *       @param int index
         *       @return String
         */ 
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

        /**
         *       private boolean IsValueABoolean(JsonValue value, int index)
         *       throws JsonValueException
         *       checks if the value is a boolean, throws exception if not, returns the boolean if it is
         *       @param JsonValue value
         *       @param int index
         *       @return boolean
         */ 
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

        /**
         *       public boolean GetArrayValueAsBoolean(JsonArray array, int index)
         *       returns the array value at index as a boolean if it is one
         *       @param JsonArray array
         *       @param int index
         *       @return boolean
         */ 
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

        /**
         *       public boolean IsArrayValueNull(JsonArray array, int index)
         *       checks if the array value at index is null
         *       @param JsonArray array
         *       @param int index
         *       @return boolean
         */ 
        public boolean IsArrayValueNull(JsonArray array, int index)
        {
                if (array.get(index) instanceof JsonNull)
                {
                        return true;
                }
                return false;
        }

        /**
         *       public JsonProperty GetObjectProperty(JsonObject object, int index)
         *       returns the property of the object at the specified index
         *       @param JsonObject object
         *       @param int index
         *       @return JsonProperty
         */ 
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
