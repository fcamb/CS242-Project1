package Json;
/**
 *      interface ParserInterface
 *      defines methods for parsing and handling JSON data
 *      @author Frank Cambria
 */
interface ParserInterface
{
        /**
         *       void OpenFileAndReadAsString(String fileName)
         *       opens and reads a JSON file as a string
         *       @param String fileName
         *       @return void
         */
        void OpenFileAndReadAsString(String fileName);

        /**
         *       void OpenFileAndReadSerializedBytes(String fileName)
         *       opens and reads a serialized JSON file as bytes
         *       @param String fileName
         *       @return void
         */
        void OpenFileAndReadSerializedBytes(String fileName);

        /**
         *       void WriteSerializedDataToFile(String fileName)
         *       writes serialized JSON data to a file
         *       @param String fileName
         *       @return void
         */
        void WriteSerializedDataToFile(String fileName);

        /**
         *       void Tokenize()
         *       tokenizes the JSON data
         *       @return void
         */
        void Tokenize();

        /**
         *       void DisplayTokens()
         *       displays the tokenized JSON data
         *       @return void
         */
        void DisplayTokens();

        /**
         *       void Parse()
         *       parses the tokenized JSON data
         *       @return void
         */
        void Parse();
}
