CS242 - Project 1

- My project was too large to do UML diagrams and flow charts. I made the mistake of not creating them along the way so I
        ran out of time to go back and create them all. Because of this, I opted for JavaDocs. In the project directory, 
        I made a directory call JavaDocs, which contains "index.html" which is the main page for the documentation. 
        Also, because its as big as it is, putting it into a single printed document would be a few thousands of lines so opted 
        not to. I'm Sorry about this. I fully understand that this breaks parts of the project's objectives and
        the project requirements #1, and accept any penalties.

Compilation: 
        javac -d bin Main.java

        My project is a recursive descent parser for JSON. It opens a JSON file specified by the user and then tokenizes
and parses the JSON. The user can create a JsonParserImpl object in order to tokenize and parse the json.
They can then retrieve the parsed JSON data for use in their own program. This is done through the class "JsonData" which holds the all the data
that the parser obtained. With the JsonData object, the user has access to functions to pull the actual values of the parsed JSON so that
they can use it how they need to in their program. 

        The parser can load the JSON file as a string which can then be tokenized/parsed or it can load a serialized binary JsonData object 
which the user can use right away in their program. The parser can also write the parsed JSON data to file as a serialized JsonData object.

NOTE: 
        No tokenizing/parsing is required when the user loads a serialized JsonData object as it is loading a JsonData object directly,
which is the resulting data of tokenizing/parsing. The user must load the JSON as a string and tokenize/parse it and then save
it as a serialized JsonData object first of course though. So once the serialized json object exists and is saved to disk, 
its just like loading any other static data into a program.


General WorkFlow:
- create JsonParserImpl object
- open a JSON file with the parser object
- tokenize the JSON with the parser object
- optionally, the user can display the tokens with the parser object
- parse the JSON tokens with the parser object
- retrieve the JSON data from parser object in the form of a JsonData object
- with the JsonData object the user can use functions to traverse and grab
        all the data values of the parsed nJSON from the JsonData object in whichever
        way is necessary to fill their data structures of choice.

- The methods that can be used to parse JSON are declared in ParserInterface.java which is the main interface for the parser.
        These methods are defined in JsonParserImpl.java which implements the ParserInterface.
        - the methods include
                // constructors
                public JsonParserImpl()
                public JsonParserImpl(int flags)

                // file operations
                public void OpenFileAndReadAsString(String fileName)
                public void OpenFileAndReadSerializedBytes(String fileName)
                public void WriteSerializedDataToFile(String fileName)

                // tokenization/parsing
                public void Tokenize()
                public void DisplayTokens()
                public void Parse()

                // retrieving the parsed data
                public JsonData ParsedData()
                public JsonData SerializedData()

- once the JSON has been tokenized/parsed or if the user loads a serialized JsonData object, the user can use that data in their program.
- The JsonData class with all its methods are detailed in the JavaDocs, as it is too large to detail all its methods here.

- This is the general structure of the JsonData though.
- {} - curly brackets are a JsonObject, theyre the base of JSON, they contain n JsonProperty objects
- [] - braces are arrays, they can hold any kind of JsonValue.
- key : value - JSON data is represented as key value pairs. I call them JsonProperty
        - each JsonProperty has a key and a value which can both be retrieved by the user
        - the key is always a String
        - the value can either be a nested JsonObject, a JsonArray or JsonNumber or JsonString or JsonBoolean or JsonNull
        - Ex. { "name" : "frank" } - The curly brackets are a JsonObject which contains one JsonProperty with 
                the key "name" and the value "frank" which is a JsonString

                the code to retrive this data is ->
                JsonObject object = jsonData.BaseObject();                  // get the first object in the data
                JsonProperty property = jsonData.GetProperty(object, 0);    // get the first JsonProperty in the object
                String key = jsonData.GetPropertyKey(property);             // get the property key, which is "name" in this example
                String name = jsonData.GetPropertyValueAsAString(property); // the value of the property, which is "frank" in this example

- in Main.java, there is an example of using/showcasing the functions and overall workflow of the parser. 
