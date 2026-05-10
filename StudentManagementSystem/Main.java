import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /**
         *
         * @param args Command-line arguments containing file paths for:
         *             args[0] - persons file
         *             args[1] - departments file
         *             args[2] - programs file
         *             args[3] - courses file
         *             args[4] - assignments file
         *             args[5] - grades file
         *             args[6] - output file
         */
        String persons = args[0];
        String departments = args[1];
        String programs = args[2];
        String courses = args[3];
        String assignments = args[4];
        String grades = args[5];
        String output = args[6];

        // Clear the output file at the very beginning
        try (PrintWriter writer = new PrintWriter(output)) {
            writer.print("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Persons personData= readPersons(persons,output);
        Map<String, Student> allStudents = personData.students;
        Map<String, AcademicMember> allAcademics = personData.academics;
        Map<String,Departments> allDepartments = readDepartments(departments,allAcademics,output);
        Map<String,Programs> allPrograms = readPrograms(programs,output);
        Map<String,Courses> allCourses = readCourses(courses,allPrograms,output);

        StudentManagementSystem studentManagementSystem = new StudentManagementSystem(allStudents,allAcademics,allDepartments,allPrograms,allCourses,grades,assignments,output);
        studentManagementSystem.readAssignments();
        studentManagementSystem.readGrades();
        studentManagementSystem.writeInfo();
        studentManagementSystem.courseReports();
        studentManagementSystem.studentReports();
    }

    /**
     * Reads person data from the specified file and categorizes them into students and academic members.
     *
     * @param persons Path to the persons input file.
     * @param output  Path to the output file.
     * @return A Persons object containing maps of students and academic members.
     */
    public static Persons readPersons(String persons, String output){
        Map<String, Student> allStudents = new HashMap<>();  // student ID to student object
        Map<String, AcademicMember> allAcademics = new HashMap<>();  // academic member ID to academic member object
        try (BufferedReader br = new BufferedReader(new FileReader(persons));
            BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))){
            writer.write("Reading Person Information \n");
            String line;
            boolean checking=true;  // its for controlling if the first parts of the line is something other than "S" and "F".
            try{
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String ID = parts[1];
                if(parts[0].equals("S")){
                    Student person = new Student(parts[2], parts[3], parts[4]);
                    allStudents.put(ID,person);
                } else if (parts[0].equals("F")) {
                    AcademicMember person = new AcademicMember(parts[2], parts[3], parts[4]);
                    allAcademics.put(ID,person);
                }else{
                    checking = false;  // it means the first parts is something invalid
                }
            }if(!checking){
                throw new AllExceptions.InvalidPersonException("Invalid Person Type\n");
            }
            }catch (AllExceptions.InvalidPersonException e) {
                writer.write(e.getMessage());
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new Persons(allStudents, allAcademics);
    }

    /**
     * Reads department data and associates each department with a valid academic member as head.
     *
     * @param departments Path to the departments input file.
     * @param allPersons  Map of academic members to verify department heads.
     * @param output      Path to the output file.
     * @return Map of department codes to Departments objects.
     */
    public static Map<String,Departments> readDepartments(String departments, Map<String,AcademicMember> allPersons,String output ){
        Map<String,Departments> allDepartments = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(departments));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))){
            writer.write("Reading Departments \n");
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(",");
                String headID = parts[3];  // academic member ID who is the head of the department
                try{
                if (!allPersons.containsKey(headID)){
                    throw new AllExceptions.NonExistingPersonException("Academic Member Not Found with ID "+headID+"\n");
                }
                Departments department = new Departments(parts[1],parts[3]);
                allDepartments.put(parts[0],department);
            }catch(AllExceptions.NonExistingPersonException e){
                writer.write(e.getMessage());
                Departments department = new Departments(parts[1], null);  // if there is no head member for department make it null
                allDepartments.put(parts[0],department);
            }
        }}catch (IOException e) {
            e.printStackTrace();
        }return allDepartments;
    }

    /**
     * Reads program data from the file and builds the program structure.
     *
     * @param programs Path to the programs input file.
     * @param output   Path to the output file.
     * @return Map of program codes to Programs objects.
     */
    public static Map<String,Programs> readPrograms(String programs,String output){
        Map<String,Programs> allPrograms = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(programs));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))){
            writer.write("Reading Programs \n");
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(",");
                Programs program = new Programs(parts[1],parts[3],parts[4],Integer.parseInt(parts[5]));
                allPrograms.put(parts[0],program);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }return allPrograms;
    }

    /**
     * Reads course data from the file and associates them with their programs.
     *
     * @param courses     Path to the courses input file.
     * @param allPrograms Map of programs to validate course-program relationships.
     * @param output      Path to the output file.
     * @return Map of course codes to Courses objects.
     */
    public static Map<String,Courses> readCourses(String courses,Map<String,Programs> allPrograms,String output){
        Map<String,Courses> allCourses = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(courses));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))){
            writer.write("Reading Courses \n");
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(",");
                String programCode = parts[5];  // program that the course belongs to
                try{
                    if (!allPrograms.containsKey(programCode)){
                        throw new AllExceptions.InvalidProgramNameException("Program "+programCode+" Not Found\n");
                    }
                    Courses course = new Courses(parts[1],parts[2],Integer.parseInt(parts[3]),parts[4],parts[5]);
                    allCourses.put(parts[0],course);
                    Programs programs = allPrograms.get(programCode);
                    programs.addCourse(programCode,parts[0]);  // adding the course code to the belonging programs list
                }catch (AllExceptions.InvalidProgramNameException e){
                    writer.write(e.getMessage());
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }return allCourses;
    }
}
