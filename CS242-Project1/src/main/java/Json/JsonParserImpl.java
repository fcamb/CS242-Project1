// import Json.*;
package Json;

/**
 *      class JsonParserImpl
 *      implements ParserInterface
 *      provides an implementation for parsing and handling JSON data
 *      @author Frank Cambria
 */
public class JsonParserImpl implements ParserInterface
{
        // handles file operations
        private final JsonFile jsonFile = new JsonFile();
        // handles tokenization
        private final JsonTokenizer jsonTokenizer = new JsonTokenizer();
        // handles parsing
        private final JsonParser jsonParser = new JsonParser();

        /**
         *       public JsonParserImpl()
         *       base constructor
         */
        public JsonParserImpl()
        {}

        /**
         *       public JsonParserImpl(int flags)
         *       constructor with flags parameter for tokenization
         *       @param int flags
         */
        public JsonParserImpl(int flags)
        {
                jsonTokenizer.SetFlags(flags);
        }

        /**
         *       public void OpenFileAndReadAsString(String fileName)
         *       opens and reads a JSON file as a string
         *       @param String fileName
         *       @return void
         */
        public void OpenFileAndReadAsString(String fileName)
        {
                jsonFile.OpenAndReadJsonFileAsString(fileName);
        }

        /**
         *       public void OpenFileAndReadSerializedBytes(String fileName)
         *       opens and reads a serialized JSON file as bytes
         *       @param String fileName
         *       @return void
         */
        public void OpenFileAndReadSerializedBytes(String fileName)
        {
                jsonFile.ReadSerializedJsonDataFromFile(fileName);
        }

        /**
         *       public void WriteSerializedDataToFile(String fileName)
         *       writes parsed JSON data to a file in serialized form
         *       @param String fileName
         *       @return void
         */
        public void WriteSerializedDataToFile(String fileName)
        {
                jsonFile.WriteSerializedJsonDataToFile(ParsedData(), fileName);
        }

        /**
         *       public void Tokenize()
         *       tokenizes the JSON file data
         *       @return void
         */
        public void Tokenize()
        {
                jsonTokenizer.TokenizeJson(jsonFile.FileDataString());
        }

        /**
         *       public void DisplayTokens()
         *       displays the tokenized JSON data
         *       @return void
         */
        public void DisplayTokens()
        {
                jsonTokenizer.DisplayTokens();
        }

        /**
         *       public void Parse()
         *       parses the tokenized JSON data
         *       @return void
         */
        public void Parse()
        {
                jsonParser.ParseJson(jsonTokenizer.Tokens());
        }

        /**
         *       public JsonData ParsedData()
         *       returns the parsed JSON data
         *       @return JsonData
         */
        public JsonData ParsedData()
        {
                return jsonParser.ParsedData();
        }

        /**
         *       public JsonData SerializedData()
         *       returns the deserialized JSON data from serialized bytes
         *       @return JsonData
         */
        public JsonData SerializedData()
        {
                return jsonFile.FileDataSerializedBytes();
        }
}
