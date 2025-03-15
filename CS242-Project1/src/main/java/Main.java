import Json.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

class ThemeRule
{
        public String name;
        public String scope;
        public String foreground;
        public ThemeRule()
        {}
        public String toString()
        {
                return "Name : " + name + ", Scope : " + scope + ", Foreground : " + foreground;
        }
}

public class Main
{
        public static void ParseStudentsData(JsonData jsonData)
        {
                // create list of students
                ArrayList<Student> Students = new ArrayList<>();

                // get the base object in the json data
                JsonObject BaseObject = jsonData.BaseObject();

                // get first property in the json, only one property in students.json (the students list)
                JsonProperty StudentsArrayProperty = jsonData.GetProperty(BaseObject, 0);

                // in students.json, the first property is an array so get the property value as an array
                JsonArray StudentsArray = jsonData.GetPropertyValueAsAnArray(StudentsArrayProperty);
                
                // for each student in the array
                for (int i = 0; i < StudentsArray.size(); i++)
                {       
                        // create a new student
                        Student NewStudent = new Student();

                        // get the object that represents the student
                        JsonObject CurrentStudent = jsonData.GetArrayValueAsAnObject(StudentsArray, i);
                        
                        // get the name property and set the students name value                        
                        JsonProperty StudentName = jsonData.GetObjectProperty(CurrentStudent, 0);
                        NewStudent.SetName(jsonData.GetPropertyValueAsAString(StudentName));

                        // get the age proprety and set the students age value
                        JsonProperty StudentAge = jsonData.GetObjectProperty(CurrentStudent, 1);
                        NewStudent.SetAge(jsonData.GetPropertyValueAsInteger(StudentAge));

                        // get the year property and set the students year value
                        JsonProperty StudentYear = jsonData.GetObjectProperty(CurrentStudent, 2);
                        NewStudent.SetYear(jsonData.GetPropertyValueAsInteger(StudentYear));

                        // get the id property and set the students id property
                        JsonProperty StudentId = jsonData.GetObjectProperty(CurrentStudent, 3);
                        NewStudent.SetId(jsonData.GetPropertyValueAsInteger(StudentId));

                        // get the grades property, its an array, so get the array from the property value
                        JsonProperty StudentGradesProperty = jsonData.GetObjectProperty(CurrentStudent, 4);
                        JsonArray StudentGrades = jsonData.GetPropertyValueAsAnArray(StudentGradesProperty);

                        // create array for the student's grades
                        ArrayList<Integer> Grades = new ArrayList<>();

                        // get all the grades and add to Grades array
                        for (int h = 0; h < StudentGrades.size(); h++)
                        {
                                // add the array value as an integer to the Grades array
                                Grades.add(jsonData.GetArrayValueAsAnInteger(StudentGrades, h));
                        }
                        // set the students grades
                        NewStudent.SetGrades(Grades);

                        // add the new student to the students array
                        Students.add(NewStudent);
                }

                // display students info to confirm we got the data correctly
                for (Student student : Students)
                {
                        System.out.println(student.toString());
                }
        }

        public static void LoadJsonFromFileIntoStringExample()
        {
                // create the json objects we need in order to parse
                JsonFile jsonFile = new JsonFile();
                JsonTokenizer jsonTokenizer = new JsonTokenizer();
                JsonParser jsonParser = new JsonParser();

                // open the json file and read as a string
                jsonFile.OpenAndReadJsonFileAsString("json_files/students.json");

                // tokenize the json string
                jsonTokenizer.TokenizeJson(jsonFile.FileDataString());

                // display the tokens (optional)
                jsonTokenizer.DisplayTokens();

                // parse the json
                jsonParser.ParseJson(jsonTokenizer.Tokens());

                // grab the JsonData from the parse
                JsonData jsonData = jsonParser.ParsedData();
                
                // example function of how to parse the JsonData
                ParseStudentsData(jsonData);

                // // serialize the json data and write to file
                jsonFile.WriteSerializedJsonDataToFile(jsonData, "json_files/students_data.dat");

                // // load seriazized json data back into new json data object
                jsonFile.ReadSerializedJsonDataFromFile("json_files/students_data.dat");
                JsonData newJsonData = jsonFile.FileDataSerializedBytes();
                
                // // test by parsing the students data again
                ParseStudentsData(newJsonData);
        }

        public static void LoadSerializedJson()
        {
                JsonFile jsonFile = new JsonFile();
                
                // load seriazized json data back into new json data object
                jsonFile.ReadSerializedJsonDataFromFile("json_files/students_data.dat");
                JsonData newJsonData = jsonFile.FileDataSerializedBytes();
                
                // test by parsing the students data again
                ParseStudentsData(newJsonData);
        }

        public static void ParserInterfaceExample()
        {                
                JsonParserImpl parser = new JsonParserImpl();
                parser.OpenFileAndReadAsString("json_files/students.json");
                parser.Tokenize();
                parser.DisplayTokens();
                parser.Parse();
                JsonData data = parser.ParsedData();
                ParseStudentsData(data);
                parser.WriteSerializedDataToFile("json_files/students_data.dat");
        }

        public static void ParserInterfaceSerializedExample()
        {
                JsonParserImpl parser = new JsonParserImpl();
                parser.OpenFileAndReadSerializedBytes("json_files/students_data.dat");
                JsonData data = parser.SerializedData();
                ParseStudentsData(data);
        }

        public static void DisplayParsedJsonTheme(String ThemeName,
                HashMap<String, String> Variables, 
                HashMap<String, String> Globals, 
                ArrayList<ThemeRule> Rules)
        {
                System.out.println("Theme Name: " + ThemeName);

                System.out.println("\nVariables");
                for (String key : Variables.keySet())
                {
                        System.out.println(key + ", " + Variables.get(key));
                }

                System.out.println("\nGlobals");
                for (String key : Globals.keySet())
                {
                        System.out.println(key + ", " + Globals.get(key));
                }

                System.out.println("\nRules");
                for (ThemeRule rule : Rules)
                {
                        System.out.println(rule.toString());
                }
        }

        public static void ParseThemeJson(JsonData jsonData)
        {
                String ThemeName;
                HashMap<String, String> Variables = new HashMap<>();
                HashMap<String, String> Globals = new HashMap<>();
                ArrayList<ThemeRule> Rules = new ArrayList<>();

                JsonObject BaseObject = jsonData.BaseObject();
                JsonProperty ThemeProperty = jsonData.GetProperty(BaseObject, 0);
                ThemeName = jsonData.GetPropertyValueAsAString(ThemeProperty);

                JsonProperty VariablesProperty = jsonData.GetProperty(BaseObject, 1);
                JsonObject VariablesObject = jsonData.GetPropertyValueAsAnObject(VariablesProperty);
                for (int i = 0; i < VariablesObject.size(); i++)
                {
                        JsonProperty property = jsonData.GetProperty(VariablesObject, i);
                        Variables.put(jsonData.GetPropertyKey(property), jsonData.GetPropertyValueAsAString(property));
                }

                JsonProperty GlobalsProperty = jsonData.GetProperty(BaseObject, 2);
                JsonObject GlobalsObject = jsonData.GetPropertyValueAsAnObject(GlobalsProperty);
                for (int i = 0; i < GlobalsObject.size(); i++)
                {
                        JsonProperty property = jsonData.GetProperty(GlobalsObject, i);
                        Globals.put(jsonData.GetPropertyKey(property), jsonData.GetPropertyValueAsAString(property));
                }

                JsonProperty RulesProperty = jsonData.GetProperty(BaseObject, 3);
                JsonArray RulesArray = jsonData.GetPropertyValueAsAnArray(RulesProperty);
                for (int i = 0; i < RulesArray.size(); i++)
                {
                        JsonObject RulesObject = jsonData.GetArrayValueAsAnObject(RulesArray, i);                        
                        ThemeRule NewRule = new ThemeRule();

                        JsonProperty NameProperty = jsonData.GetProperty(RulesObject, 0);
                        JsonProperty ScopeProperty = jsonData.GetProperty(RulesObject, 1);
                        JsonProperty ForegroundProperty = jsonData.GetProperty(RulesObject, 2);

                        NewRule.name = jsonData.GetPropertyValueAsAString(NameProperty);
                        NewRule.scope = jsonData.GetPropertyValueAsAString(ScopeProperty);
                        NewRule.foreground = jsonData.GetPropertyValueAsAString(ForegroundProperty);

                        Rules.add(NewRule);
                }

                DisplayParsedJsonTheme(ThemeName, Variables, Globals, Rules);
        }

        public static void ParseInterfaceExample2()
        {
                JsonParserImpl parser = new JsonParserImpl();
                parser.OpenFileAndReadAsString("json_files/json_test.json");
                parser.Tokenize();
                parser.Parse();
                JsonData jsonData = parser.ParsedData();
                ParseThemeJson(jsonData);
                parser.WriteSerializedDataToFile("json_files/json_test_data.dat");
        }
        
        public static void ParseInterfaceExample3()
        {
                JsonParserImpl parser = new JsonParserImpl();
                parser.OpenFileAndReadSerializedBytes("json_files/json_test_data.dat");                
                JsonData jsonData = parser.SerializedData();
                ParseThemeJson(jsonData);
        }

        public static void main(String[] args)
        {                        
                // LoadJsonFromFileIntoStringExample();                
                // LoadSerializedJson();
                // ParserInterfaceExample();
                // ParserInterfaceSerializedExample();
                // ParseInterfaceExample2();
                ParseInterfaceExample3();
        }       
}
