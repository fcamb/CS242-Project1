import Json.*;
import java.util.ArrayList;

public class Main
{
        public static void ParseStudentsData(JsonData jsonData)
        {
                String Title;

                // create list of students
                ArrayList<Student> Students = new ArrayList<>();

                // get the base object in the json data
                JsonObject BaseObject = jsonData.BaseObject();

                // get first property in the json, the title
                JsonProperty StudentsJsonTitle = jsonData.GetProperty(BaseObject, 0);
                Title = jsonData.GetPropertyValueAsAString(StudentsJsonTitle);

                // get the array proprety of the students
                JsonProperty StudentsArrayProperty = jsonData.GetProperty(BaseObject, 1);

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

                System.out.println(Title);
                // display students info to confirm we got the data correctly
                for (Student student : Students)
                {
                        System.out.println(student.toString());
                }
        }

        public static void LoadJsonAsStringExample()
        {
                System.out.println("Loading json from file into a string and parsing.");

                // create the pasrser                 
                JsonParserImpl parser = new JsonParserImpl();

                // open the JSON file and read as a string
                parser.OpenFileAndReadAsString("json_files/students.json");

                // tokenize the JSON
                parser.Tokenize();

                // display the tokens
                parser.DisplayTokens();

                // parser the JSON
                parser.Parse();

                // grab the parsed JsonData from the parser
                JsonData jsonData = parser.ParsedData();

                // pass the jsonData to the ParseStudentsData() function
                // to get view our data
                ParseStudentsData(jsonData);

                // serialize the jsonData and write it to file
                parser.WriteSerializedDataToFile("json_files/students_data.dat");
        }

        public static void LoadSerializedJsonExample()
        {
                System.out.println("\nLoading json from serialized data file.");

                // create the parser object
                JsonParserImpl parser = new JsonParserImpl();

                // open and read the serialized json data
                parser.OpenFileAndReadSerializedBytes("json_files/students_data.dat");
                
                // get the json data from the parser
                JsonData jsonData = parser.SerializedData();
                
                // test by parsing the students data again
                ParseStudentsData(jsonData);
        }

        public static void main(String[] args)
        {                       
                // loads a json file as a string and parses it
                // then serializes the data and saves it to file
                LoadJsonAsStringExample();

                // loads the previously saved serialized json data file
                // no need to parse now, since its just the serialized json data
                // we can just load and parse the data to get the students information                
                LoadSerializedJsonExample();
        }       
}
