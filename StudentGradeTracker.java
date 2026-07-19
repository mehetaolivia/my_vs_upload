import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("      STUDENT GRADE TRACKER SYSTEM");
        System.out.println("========================================");

        System.out.print("Enter Number of Students: ");
        int students = sc.nextInt();

        System.out.print("Enter Number of Subjects: ");
        int subjects = sc.nextInt();
        sc.nextLine();

        String[] names = new String[students];
        int[] totals = new int[students];
        double[] averages = new double[students];
        double[] percentages = new double[students];
        String[] grades = new String[students];
        String[] results = new String[students];

        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;
        String highestStudent = "";
        String lowestStudent = "";

        int passCount = 0;
        int failCount = 0;
        double classAverage = 0;

        // Input Student Details
        for (int i = 0; i < students; i++) {

            System.out.println("\n----------------------------------------");
            System.out.println("Enter Details of Student " + (i + 1));

            System.out.print("Student Name: ");
            names[i] = sc.nextLine();

            int total = 0;

            for (int j = 0; j < subjects; j++) {

                int mark;

                while (true) {
                    System.out.print("Enter Marks of Subject " + (j + 1) + " (0-100): ");
                    mark = sc.nextInt();

                    if (mark >= 0 && mark <= 100)
                        break;

                    System.out.println("Invalid Marks! Enter between 0 and 100.");
                }

                total += mark;
            }

            sc.nextLine();

            totals[i] = total;
            averages[i] = (double) total / subjects;
            percentages[i] = ((double) total / (subjects * 100)) * 100;

            if (averages[i] >= 90)
                grades[i] = "A+";
            else if (averages[i] >= 80)
                grades[i] = "A";
            else if (averages[i] >= 70)
                grades[i] = "B";
            else if (averages[i] >= 60)
                grades[i] = "C";
            else if (averages[i] >= 50)
                grades[i] = "D";
            else
                grades[i] = "F";

            if (averages[i] >= 50) {
                results[i] = "PASS";
                passCount++;
            } else {
                results[i] = "FAIL";
                failCount++;
            }

            if (total > highest) {
                highest = total;
                highestStudent = names[i];
            }

            if (total < lowest) {
                lowest = total;
                lowestStudent = names[i];
            }

            classAverage += averages[i];
        }

        classAverage = classAverage / students;

        // Display Results
        System.out.println("\n==============================================================");
        System.out.printf("%-15s %-8s %-10s %-12s %-8s %-8s%n",
                "Name", "Total", "Average", "Percentage", "Grade", "Result");
        System.out.println("--------------------------------------------------------------");

        for (int i = 0; i < students; i++) {
            System.out.printf("%-15s %-8d %-10.2f %-12.2f %-8s %-8s%n",
                    names[i],
                    totals[i],
                    averages[i],
                    percentages[i],
                    grades[i],
                    results[i]);
        }

        System.out.println("==============================================================");

        System.out.println("\n----------- CLASS SUMMARY -----------");
        System.out.println("Total Students    : " + students);
        System.out.println("Passed Students   : " + passCount);
        System.out.println("Failed Students   : " + failCount);
        System.out.printf("Class Average     : %.2f%n", classAverage);

        System.out.println("\nHighest Scorer    : " + highestStudent + " (" + highest + ")");
        System.out.println("Lowest Scorer     : " + lowestStudent + " (" + lowest + ")");

        System.out.println("-------------------------------------");
        System.out.println("Project Completed Successfully");
        System.out.println("-------------------------------------");

        sc.close();
    }
}