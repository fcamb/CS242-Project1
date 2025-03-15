import java.util.*;

public class Student
{
        private String name;
        private int age;
        private int year;
        private int id;
        private ArrayList<Integer> grades = new ArrayList<>();
        
        public Student()
        {}

        public Student(String name, int age, int year, int id, ArrayList<Integer> grades)
        {
                this.name = name;
                this.age = age;
                this.year = year;
                this.id = id;
                this.grades = grades;
        }

        public void SetName(String name)
        {
                this.name = name;
        }

        public void SetAge(int age)
        {
                this.age = age;
        }

        public void SetYear(int year)
        {
                this.year = year;
        }
        
        public void SetId(int id)
        {
                this.id = id;
        }

        public void SetGrades(ArrayList<Integer> grades)
        {
                this.grades = grades;
        }

        public String toString()
        {
                String gradesStr = "";
                for (int grade : grades)
                {
                        gradesStr += String.valueOf(grade) + ", ";
                }

                return "Name: " + name + "\n" +
                        "Age: " + String.valueOf(age) + "\n" +
                        "Year: " + String.valueOf(year) + "\n" +
                        "ID: " + String.valueOf(id) + "\n" +
                        "Grades: " + gradesStr;
        }
}
