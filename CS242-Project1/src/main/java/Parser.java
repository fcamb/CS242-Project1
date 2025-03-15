interface ParserInterface
{

        void OpenFileAndReadAsString(String fileName);

        void OpenFileAndReadSerializedBytes(String fileName);

        void WriteSerializedDataToFile(String fileName);

        void Tokenize();

        void DisplayTokens();

        void Parse();

}
