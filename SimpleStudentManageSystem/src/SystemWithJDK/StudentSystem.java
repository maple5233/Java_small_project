package SystemWithJDK;

import java.util.*;

/**
 * Created by hongjiyao_2014150120 on 17-4-4.
 * 学生类
 */
class Student {
    String getName() {
        return name;
    }

    // 姓名、年龄、性别、成绩、年级
    private String name;
    private int age;
    private boolean isBoy;
    private double score;
    private String grade;

    Student(String name, int age, boolean isBoy, double score, String grade) {
        this.name = name;
        this.age = age;
        this.isBoy = isBoy;
        this.score = score;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "姓名:" + this.name + "\t年龄:" + this.age
                + "\t性别:" + (isBoy ? "男" : "女") + "\t成绩:" + this.score
                + "\t年级:" + this.grade;
    }
}

/**
 * Created by hongjiyao_2014150120 on 17-4-4.
 * 使用JDK提供的数据结构的学生管理系统
 */
public class StudentSystem {
    private static List<Student> students = new LinkedList<>();

    private static void showMenu() {
        System.out.println("---------------------");
        System.out.println("------1.增加学生------");
        System.out.println("------2.删除学生------");
        System.out.println("------3.修改学生------");
        System.out.println("------4.展示学生------");
        System.out.println("------5.排序学生------");
        System.out.println("------0.退出系统------");
        System.out.println("---------------------");
        Scanner in = new Scanner(System.in);
        int choose = in.nextInt();
        switch (choose) {
            case 0:
                return;
            case 1:
                addStudent();
                break;
            case 2:
                deleteStudent();
                break;
            case 3:
                modifyStudent();
                break;
            case 4:
                showStudent();
                break;
            case 5:
                sortStudent();
        }
    }

    private static void showStudent() {
        for (Student student : students) {
            System.out.println(student.toString());
        }
        showMenu();
    }

    private static void sortStudent() {
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("按姓名排序结果:");
        showStudent();
    }

    private static void modifyStudent() {
        Scanner in = new Scanner(System.in);

        System.out.println("请输入想修改的学生的位置(1 ~ " + students.size() + ")");
        int index = in.nextInt() - 1;

        System.out.println("请输入学生的姓名、年龄、性别、成绩、年级，用空格隔开，其中性别用0和1表示：");
        String name = in.next();
        int age = in.nextInt();
        int isBoyNum = in.nextInt();
        boolean isBoy = isBoyNum > 0;
        double score = in.nextDouble();
        String grade = in.next();
        students.set(index, new Student(name, age, isBoy, score, grade));
        System.out.println("修改结果:");
        showStudent();
    }

    private static void deleteStudent() {
        System.out.println("请输入想删除的学生的位置(1 ~ " + students.size() + ")");
        Scanner in = new Scanner(System.in);
        int choose = in.nextInt() - 1;
        students.remove(choose);
        System.out.println("删除结果:");
        showStudent();
    }

    private static void addStudent() {
        Scanner in = new Scanner(System.in);
        int continueIn;
        do {
            System.out.println("请输入学生的姓名、年龄、性别、成绩、年级，用空格隔开，其中性别用0和1表示：");
            String name = in.next();
            int age = in.nextInt();
            int isBoyNum = in.nextInt();
            boolean isBoy = isBoyNum > 0;
            double score = in.nextDouble();
            String grade = in.next();
            students.add(new Student(name, age, isBoy, score, grade));
            System.out.println("继续输入? 1继续 0停止");
            continueIn = in.nextInt();
        } while (continueIn > 0);
        showStudent();
    }

    public static void main(String[] args) {
        showMenu();
    }
}
