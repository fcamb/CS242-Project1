import Json.*;

public class JsonParserImpl implements ParserInterface
{
        private final JsonFile jsonFile = new JsonFile();
        private final JsonTokenizer jsonTokenizer = new JsonTokenizer();
        private final JsonParser jsonParser = new JsonParser();

        public JsonParserImpl()
        {}

        public JsonParserImpl(int flags)
        {
                jsonTokenizer.SetFlags(flags);
        }

        public void OpenFileAndReadAsString(String fileName)
        {
                jsonFile.OpenAndReadJsonFileAsString(fileName);
        }

        public void OpenFileAndReadSerializedBytes(String fileName)
        {
                jsonFile.ReadSerializedJsonDataFromFile(fileName);
        }

        public void WriteSerializedDataToFile(String fileName)
        {
                jsonFile.WriteSerializedJsonDataToFile(ParsedData(), fileName);
        }

        public void Tokenize()
        {
                jsonTokenizer.TokenizeJson(jsonFile.FileDataString());
        }

        public void DisplayTokens()
        {
                jsonTokenizer.DisplayTokens();
        }

        public void Parse()
        {
                jsonParser.ParseJson(jsonTokenizer.Tokens());
        }

        public JsonData ParsedData()
        {
                return jsonParser.ParsedData();
        }

        public JsonData SerializedData()
        {
                return jsonFile.FileDataSerializedBytes();
        }
}
