import java.io.*;
import java.util.*;

/**
 * The StudentManagementSystem class manages students, academics, departments,
 * programs, and courses. It reads assignment and grade files, generates reports,
 * and writes information to an output file.
 */

public class StudentManagementSystem {
    private Map<String,Student> allStudents;
    private Map<String, AcademicMember> allAcademics;
    private Map<String,Departments> allDepartments;
    private Map<String,Programs> allPrograms;
    private Map<String,Courses> allCourses;
    private String grades;
    private String assignments;
    private String output;

    /**
     * Constructs a new StudentManagementSystem with provided data maps and file paths.
     *
     * @param allStudents    map of student IDs to Student objects
     * @param allAcademics   map of faculty IDs to AcademicMember objects
     * @param allDepartments map of department codes to Departments
     * @param allPrograms    map of program codes to Programs
     * @param allCourses     map of course codes to Courses
     * @param grades         path to the grades input file
     * @param assignments    path to the course assignments input file
     * @param output         path to the output file
     */
    public StudentManagementSystem(Map<String, Student> allStudents,Map<String, AcademicMember> allAcademics,Map<String,Departments> allDepartments, Map<String,Programs> allPrograms, Map<String,Courses> allCourses, String grades, String assignments, String output){
        this.allStudents=allStudents;
        this.allAcademics=allAcademics;
        this.allDepartments=allDepartments;
        this.allPrograms=allPrograms;
        this.allCourses=allCourses;
        this.grades=grades;
        this.assignments=assignments;
        this.output=output;
    }

    /**
     * Reads student and faculty course assignments from a file and updates internal data structures accordingly.
     * Outputs error messages if data is missing or invalid.
     */
    public void readAssignments(){
        try (BufferedReader br = new BufferedReader(new FileReader(assignments));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
            writer.write("Reading Course Assignments \n");
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts[0].equals("F")){
                    try{
                        if (!allAcademics.containsKey(parts[1])){
                            throw new AllExceptions.NonExistingPersonException("Academic Member Not Found with ID "+parts[1]+"\n");
                        }
                        if(!allCourses.containsKey(parts[2])){
                            throw new AllExceptions.NonExistingCourseException("Course "+parts[2]+" Not Found\n");
                        }
                        String instructorName = allAcademics.get(parts[1]).getName();  // getting the name of the instructor for that course
                        allCourses.get(parts[2]).setInstructor(instructorName);  // setting the instructors name for that course

                    }catch (AllExceptions.NonExistingPersonException |  AllExceptions.NonExistingCourseException e){
                        writer.write(e.getMessage());
                        continue;
                    }
                }
                if(parts[0].equals("S")){
                    try{
                        if (!allStudents.containsKey(parts[1])){
                            throw new AllExceptions.NonExistingPersonException("Student Not Found with ID "+parts[1]+"\n");
                        }
                        if(!allCourses.containsKey(parts[2])){
                            throw new AllExceptions.NonExistingCourseException("Course "+parts[2]+" Not Found\n");
                        }
                        Student student = allStudents.get(parts[1]);
                        student.enroll(parts[1],parts[2]);   // enrolling the course for that student
                        Courses courses = allCourses.get(parts[2]);
                        courses.enroll(parts[2],parts[1]);   // enrolling the student for that course
                    }catch (AllExceptions.NonExistingPersonException |  AllExceptions.NonExistingCourseException e){
                        writer.write(e.getMessage());
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads student grades from a file, updates student records and
     * course grade distributions. Validates grades and references.
     */
    public void readGrades(){
        List<String> gradesList = Arrays.asList("A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2", "F3");
        try (BufferedReader br = new BufferedReader(new FileReader(grades));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
            writer.write("Reading Grades \n");
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                try{
                    if(!allStudents.containsKey(parts[1])){
                        throw new AllExceptions.NonExistingPersonException("Student Not Found with ID "+parts[1]+"\n");
                    }
                    if(!allCourses.containsKey(parts[2])){
                        throw new AllExceptions.NonExistingCourseException("Course "+parts[2]+" Not Found\n");
                    }
                    if(!gradesList.contains(parts[0])){
                        throw new AllExceptions.InvalidLetterGrade("The grade "+parts[0]+" is not valid\n");
                    }
                    Student student = allStudents.get(parts[1]);
                    student.complete(parts[1],parts[2],parts[0]);   // studentID , courseCode , grade .  adding the course to the completed courses list for that student
                    Courses courses = allCourses.get(parts[2]);   // getting the belonging grade for the course
                    courses.count(parts[2],parts[0]);    // increasing the count for that grade for the belonging course
                }catch (AllExceptions.NonExistingPersonException |  AllExceptions.NonExistingCourseException | AllExceptions.InvalidLetterGrade e){
                    writer.write(e.getMessage());
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates course reports including enrolled students, instructor name,
     * grade distribution, and average grade, and writes them to the output file.
     */
    public void courseReports(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))){
            writer.write("\n----------------------------------------\n             COURSE REPORTS\n----------------------------------------\n");
            List<String> sortedIDs = new ArrayList<>(allCourses.keySet());
            Collections.sort(sortedIDs);
            for(String ID : sortedIDs){
                Courses courses = allCourses.get(ID);
                String semester = courses.getSemester();
                AcademicEntity course =allCourses.get(ID);
                writer.write("Course Code: "+ID+"\n");
                writer.write("Name: "+course.getName()+"\n");
                writer.write("Department: "+course.getDepartment()+"\n");
                writer.write("Credits: "+course.getCredit()+"\n");
                writer.write("Semester: "+semester+"\n");
                writer.newLine();
                String instructorName = courses.getInstructorName();
                if (instructorName != null) {  // checking if there is any instructor assigned
                    writer.write("Instructor: " + instructorName + "\n");
                } else {
                    writer.write("Instructor: Not assigned\n");
                }
                writer.newLine();
                writer.write("Enrolled Students:\n");
                List<String> enrolledStudents = courses.getEnrolledStudents();
                if(!enrolledStudents.isEmpty()){
                    for (String studentID : enrolledStudents){
                    writer.write("- "+allStudents.get(studentID).getName()+" (ID: "+studentID+")\n");
                }}
                writer.newLine();
                writer.write("Grade Distribution:\n");
                if(!courses.getCountOfGrades().isEmpty()){  // if the map for count and grades is not empty wrtiting to the file
                    List<String> sortedGrades = new ArrayList<>(courses.getCountOfGrades().keySet());
                    Collections.sort(sortedGrades);    // sorting the grades
                    for (String grade : sortedGrades){
                        writer.write(grade+": "+courses.getCountOfGrades().get(grade)+"\n");
                    }
                }writer.newLine();
                if(!enrolledStudents.isEmpty() & !courses.getCountOfGrades().isEmpty()){  // if there are grades and enrolled student for that course calculating the average grade
                    double averageGrade = courses.calculatingGrade(courses.getCountOfGrades());
                    writer.write("Average Grade: "+String.format(Locale.US,"%.2f", averageGrade) + "\n");
                }else {
                    writer.write("Average Grade: 0.00\n");
                }writer.write("\n----------------------------------------\n");
                writer.newLine();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates student reports including enrolled and completed courses,
     * grades, and GPA. Writes the reports to the output file.
     */
    public void studentReports(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))){
            writer.write("----------------------------------------\n            STUDENT REPORTS\n----------------------------------------\n");
            List<String> sortedIDs = new ArrayList<>(allStudents.keySet()); // getting the student IDs
            Collections.sort(sortedIDs); //  sorting in ascending order
            for(int i = 0; i < sortedIDs.size(); i++){
                String ID = sortedIDs.get(i);
                Student student = allStudents.get(ID);  // student object
                Persons persons = allStudents.get(ID);  // persons object
                writer.write("Student ID: "+ID+"\n");
                writer.write("Name: "+persons.getName()+"\n");
                writer.write("Email: "+persons.getEmail()+"\n");
                writer.write("Major: "+persons.getDepartment()+"\n");
                writer.write("Status: Active\n\n\n");
                List<String> enrolledCourses = student.getEnrolledCourses();
                Map<String,String> completedCourses = student.getCompletedCourses();
                writer.write("Enrolled Courses:\n");
                for(String courseCode : enrolledCourses ){  // only writing the courses that isn't completed
                    if(!completedCourses.containsKey(courseCode)){
                        writer.write("- "+allCourses.get(courseCode).getName()+" ("+courseCode+")\n");
                    }
                }
                writer.newLine();
                writer.write("Completed Courses:\n");
                for(Map.Entry<String, String> entry : completedCourses.entrySet()){
                    writer.write("- "+allCourses.get(entry.getKey()).getName()+" ("+entry.getKey()+"): "+entry.getValue()+"\n");
                }
                writer.newLine();
                if(!enrolledCourses.isEmpty()){  // if there are enrolled courses calculating the gpa
                    double gpa = student.calculatingGPA(completedCourses,allCourses);
                    writer.write("GPA: "+String.format(Locale.US,"%.2f", gpa) + "\n");
                }else {
                    writer.write("GPA: 0.00\n");
                }if (i < sortedIDs.size() - 1) {
                    writer.write("----------------------------------------\n\n");
                }else{   // not putting any newline at the end of the file
                    writer.write("----------------------------------------");
                }
            }


        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Writes full information about academic members, students, departments,
     * programs, and courses to the output file.
     */
    public void writeInfo(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))){
            writer.write("----------------------------------------\n            Academic Members\n----------------------------------------\n");
            List<String> sortedIDs = new ArrayList<>(allAcademics.keySet());
            Collections.sort(sortedIDs);  // sorting the academic member IDs in ascending order
            for(String ID : sortedIDs){
                String message = allAcademics.get(ID).printInfo(ID);
                writer.write(message+"\n");
            }
            writer.write("----------------------------------------\n");
            writer.write("\n----------------------------------------\n                STUDENTS\n----------------------------------------\n");
            List<String> sortedStudentIDs = new ArrayList<>(allStudents.keySet());
            Collections.sort(sortedStudentIDs);   // sorting the student IDs in ascending order
            for(String ID : sortedStudentIDs){
                String message = allStudents.get(ID).printInfo(ID);
                writer.write(message+"\n");
            }
            writer.write("----------------------------------------\n");
            writer.write("\n---------------------------------------\n              DEPARTMENTS\n---------------------------------------\n");
            List<String> sortedDepartmentCodes = new ArrayList<>(allDepartments.keySet());
            Collections.sort(sortedDepartmentCodes);  // sorting the department codes in ascending order
            for(String ID : sortedDepartmentCodes){
                String message = allDepartments.get(ID).printInfo(ID);
                String headID = allDepartments.get(ID).getHeadID();
                if(allAcademics.containsKey(headID)){
                    writer.write(message+allAcademics.get(headID).getName()+"\n\n");
                }else {
                    writer.write(message+"Not assigned"+"\n\n");  // if there is no head member
                }
            }
            writer.write("----------------------------------------\n");
            writer.write("\n--------------------------------------\n                PROGRAMS\n---------------------------------------\n");
            List<String> sortedProgramCodes = new ArrayList<>(allPrograms.keySet());
            Collections.sort(sortedProgramCodes); // sorting the program codes in ascending order
            for(String ID : sortedProgramCodes){
                String message = allPrograms.get(ID).printInfo(ID);
                writer.write(message+"\n");
            }
            writer.write("----------------------------------------\n");
            writer.write("\n---------------------------------------\n                COURSES\n---------------------------------------\n");
            List<String> sortedCourseCodes = new ArrayList<>(allCourses.keySet());
            Collections.sort(sortedCourseCodes);  // sorting the course codes in ascending order
            for(String ID : sortedCourseCodes){
                String message = allCourses.get(ID).printInfo(ID);
                writer.write(message+"\n");
            }
            writer.write("----------------------------------------\n");
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}










