/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package baikiemtra;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Phạm Mạnh Linh
 */
interface KPIEvaluator{
        double calculateKPI();
    }
abstract class Person{
    public String name;
    public int age;
    public String gender;
    protected String bank_account;
    public Person(String name, int age, String gender, String bank_account){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.bank_account = bank_account;
    }
    abstract String getRole();
    
}
class TeachingAssistant extends Person implements KPIEvaluator{
    public String employeeID;
    private int numberOfCourses;

    public TeachingAssistant(String name, int age, String gender, String employeeID, int numberOfCourses) {
        super(name, age, gender, employeeID);
        this.employeeID = employeeID;
        this.numberOfCourses = numberOfCourses;
    }
    @Override
    public String getRole() {
        return "Teaching Assistant";
    }

    @Override
    public double calculateKPI() {
        return numberOfCourses * 5;
    }
}
class Lecturer extends Person implements KPIEvaluator {
    public String employeeID;
    private int numberOfPublications;

    public Lecturer(String name, int age, String gender, String employeeID, int numberOfPublications) {
        super(name, age, gender, employeeID);
        this.employeeID = employeeID;
        this.numberOfPublications = numberOfPublications;
    }

    @Override
    public String getRole() {
        return "Lecturer";
    }

    @Override
    public double calculateKPI() {
        return numberOfPublications * 7;
    }
}
final class Professor extends Lecturer implements KPIEvaluator {
    public static int countProfessors = 0;
    private int numberOfProjects;

    public Professor(String name, int age, String gender, String employeeID, int numberOfPublications, int numberOfProjects) {
        super(name, age, gender, employeeID , numberOfPublications);
        this.numberOfProjects = numberOfProjects;
        countProfessors++;
    }

    @Override
    public String getRole() {
        return "Professor";
    }

    @Override
    public double calculateKPI() {
        return super.calculateKPI() + (numberOfProjects * 10);
    }
}
public class Baikiemtra {

    /**
     * @param args the command line arguments
     */
    static List<Person> persons = new ArrayList<>();
    static void inputPerson() {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("Enter type of person (ta/lec/gs) or q to quit: ");
            String type = sc.nextLine().trim().toLowerCase();
            if (type.equals("q")) break;

            System.out.println("Enter name: ");
            String name = sc.nextLine().trim();
            int age;
            while (true){
                try{
                    System.out.println("Enter age: ");
                    age = Integer.parseInt(sc.nextLine().trim());
                    if (age>=18){
                       break ;
                    }
                    else if (age>=0){
                        System.out.println("Độ tuổi không hợp lệ!");
                    }
                    else{
                        System.out.println("Rồi tuổi này là người sống dữ chưa?");
                    }
                    
                }
                catch(Exception ex){
                    System.out.println("Nhaplai, loi: "+ ex);
                }
            }
            String gender = "";
            boolean bool = true;
            while (bool)
            {
                String g1 = "nam";
                String g2 = "nu";
                
                System.out.println("Enter gender: ");
                gender = sc.nextLine().trim();
                if (gender.toLowerCase().equals(g1)| gender.toLowerCase().equals(g2)){
                    
                    bool = false;
                }    
                else{
                    System.out.println("Không tồn tại giới tính'"+ gender +"'");
                }
            }
            

            System.out.println("Enter employee ID: ");
            String employeeID = sc.nextLine().trim();
            
            switch (type) {
                case "ta" -> {
                    int numberOfCourses;
                    while(true){
                        try{
                            System.out.println("Enter number of courses: ");
                            numberOfCourses = Integer.parseInt(sc.nextLine().trim());
                            break;
                        }
                        catch(Exception e){
                            System.out.println("Nhap lai. Lỗi:"+ e);
                        }
                    }
                    
                    persons.add(new TeachingAssistant(name, age, gender, employeeID, numberOfCourses));
                }
                case "lec" -> {
                    int numberOfPublications;
                    while (true){
                        try{
                            System.out.println("Enter number of publications: ");
                            numberOfPublications = Integer.parseInt(sc.nextLine().trim());
                            break;
                        }catch(Exception e){
                            System.out.println("Nhap lai. Lỗi: "+ e);
                        }
                    }
                    persons.add(new Lecturer(name, age, gender, employeeID, numberOfPublications));
                }
                case "gs" -> {
                    int numberOfPublications;
                    while (true){
                        try{
                            System.out.println("Enter number of publications: ");
                            numberOfPublications = Integer.parseInt(sc.nextLine().trim());
                            break;
                        }catch(Exception e){
                            System.out.println("Nhap lai. Lỗi: "+ e);
                        }
                    }
                    int numberOfProjects;
                    while(true){
                        try{
                            System.out.println("Enter number of projects: ");
                            numberOfProjects = Integer.parseInt(sc.nextLine().trim());
                            break;
                        }catch(Exception e){
                            System.out.println("Nhap lai. Lỗi: "+ e);
                        }
                    }
                    
                    persons.add(new Professor(name, age, gender, employeeID, numberOfPublications, numberOfProjects));
                }
                default -> System.out.println("Invalid type. Try again.");
            }
        }
    }
    static void displayPersons() {
        for (Person p : persons) {
            System.out.print(p.name + " - " + p.getRole() + " - KPI: ");
        }
    }
    static void displayProfessorsCount() {
        System.out.println("Total Professors: " + Professor.countProfessors);
    }
    static Object[] pperson(){
        Object[] aperson = {};
        return persons.toArray();
    } 
    public static void main(String[] args) {
        // TODO code application logic here
        /*TeachingAssistant ta1 = new TeachingAssistant("Alice", 25, "Female", "TA001", 3);
        System.out.println(ta1.getRole() + ": "+ ta1.name + "-" + ta1.calculateKPI());

        Lecturer lec1 = new Lecturer("Bob", 35, "Male", "LEC001", 5);
        System.out.println(lec1.getRole() + ": "+ lec1.name + "-" + lec1.calculateKPI());

        Professor prof1 = new Professor("Charlie", 45, "Male", "PROF001", 10, 2);
        System.out.println(prof1.getRole() + ": "+ prof1.name+ "-" + prof1.calculateKPI());
        
        System.out.println("Total Professors: " + Professor.countProfessors);*/
        inputPerson();
        displayPersons();
        displayProfessorsCount();
        System.out.println("Chào mừng bạn đến với chương trình!");
        Object[] person = pperson(); 
        String[] col ={"Mã nhân sự", "Họ tên", "Tuổi", "Giới tính", "Chức danh" };
        
        

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                Course_UI frame = new Course_UI();
                frame.setVisible(true);
            }
        });
    }
}

