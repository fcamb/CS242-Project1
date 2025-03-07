import Json.*;
import java.util.List;
import java.util.ArrayList;

public class Main
{
        public static void main(String[] args)
        {                        
                FileOp fileOp = new FileOp();
                fileOp.OpenAndReadFileAsString("C:\\School\\DataStructures\\Project1\\test_json.json");
                JsonTokenizer jsonTokenizer = new JsonTokenizer();
                JsonParser jsonParser   = new JsonParser();
                jsonTokenizer.TokenizeJson(fileOp.FileDataString());
                // jsonTokenizer.DisplayTokens();
                jsonParser.ParseJson(jsonTokenizer.Tokens());
                
                // for (String json : jsonTestCases)
                // {
                //         jsonTokenizer.TokenizeJson(json);
                //         jsonTokenizer.DisplayTokens();
                //         jsonParser.ParseJson(jsonTokenizer.Tokens());
                // }

                // JsonTokenizer jsonTokenizer = new JsonTokenizer(json);
                // jsonTokenizer.TokenizeJson();                
                // jsonTokenizer.DisplayTokens();

                // JsonParser jsonParser = new JsonParser(jsonTokenizer.Tokens());
                // jsonParser.ParseJson();
                // jsonParser.VerifyJson();
        }
}
