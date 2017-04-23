package SystemWithoutStruct;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 不适用记录结构的学生系统
 * Created by hongjiyao_2014150120 on 17-4-23.
 */
public class StudentSystemWithoutStruct {
    private static ArrayList<String> studentsName = new ArrayList<>();
    private static ArrayList<Integer> studentsAge = new ArrayList<>();
    private static ArrayList<Boolean> studentsSex = new ArrayList<>();
    private static ArrayList<Double> studentsScore = new ArrayList<>();
    private static ArrayList<String> studentsGrade = new ArrayList<>();

    private static void showMenu() {
        System.out.println("-------------------");
        System.out.println("-----1.增加学生-----");
        System.out.println("-----2.删除学生-----");
        System.out.println("-----3.修改学生-----");
        System.out.println("-----4.展示学生-----");
        System.out.println("-----5.排序学生-----");
        System.out.println("-----0.退出系统-----");
        System.out.println("-------------------");
        Scanner in = new Scanner(System.in);
        int choose = in.nextInt();
        switch (choose) {
            case 0:
                System.out.println("bye");
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
        for (int i = 0; i < studentsName.size(); i++) {
            System.out.println("姓名:" + studentsName.get(i) + "\t年龄:" + studentsAge.get(i)
                    + "\t性别:" + (studentsSex.get(i) ? "男" : "女") + "\t成绩:" + studentsScore.get(i)
                    + "\t年级:" + studentsGrade.get(i));
        }
        showMenu();
    }

    private static void sortStudent() {
        for (int i = 0; i < studentsName.size(); i++) {
            for (int j = 0; j < studentsName.size() - 1; j++) {
                if (studentsName.get(i).compareTo(studentsName.get(j)) > 0) {

                    String name = studentsName.get(i);
                    studentsName.set(i, studentsName.get(j));
                    studentsName.set(j, name);

                    boolean sex = studentsSex.get(i);
                    studentsSex.set(i, studentsSex.get(j));
                    studentsSex.set(j, sex);

                    int age = studentsAge.get(i);
                    studentsAge.set(i, studentsAge.get(j));
                    studentsAge.set(j, age);

                    double score = studentsScore.get(i);
                    studentsScore.set(i, studentsScore.get(j));
                    studentsScore.set(j, score);

                    String grade = studentsGrade.get(i);
                    studentsGrade.set(i, studentsGrade.get(j));
                    studentsGrade.set(j, grade);
                }
            }
        }
        showStudent();
    }

    private static void modifyStudent() {
        Scanner in = new Scanner(System.in);

        System.out.println("请输入想修改的学生的位置(1 ~ " + studentsName.size() + ")");
        int i = in.nextInt() - 1;

        System.out.println("请输入学生的姓名、年龄、性别、成绩、年级，用空格隔开，其中性别用0和1表示：");
        String name = in.next();
        int age = in.nextInt();
        int isBoyNum = in.nextInt();
        boolean isBoy = isBoyNum > 0;
        double score = in.nextDouble();
        String grade = in.next();
        studentsName.set(i, name);
        studentsAge.set(i, age);
        studentsSex.set(i, isBoy);
        studentsScore.set(i, score);
        studentsGrade.set(i, grade);
        System.out.println("修改结果:");
        showStudent();
    }

    private static void deleteStudent() {
        System.out.println("请输入想删除的学生的位置(1 ~ " + studentsName.size() + ")");
        Scanner in = new Scanner(System.in);
        int choose = in.nextInt() - 1;
        studentsName.remove(choose);
        studentsGrade.remove(choose);
        studentsAge.remove(choose);
        studentsSex.remove(choose);
        studentsScore.remove(choose);
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
            studentsName.add(name);
            studentsAge.add(age);
            studentsSex.add(isBoy);
            studentsScore.add(score);
            studentsGrade.add(grade);
            System.out.println("继续输入? 1继续 0停止");
            continueIn = in.nextInt();
        } while (continueIn > 0);
        showStudent();
    }

    public static void main(String[] args) {
        showMenu();
    }
}
