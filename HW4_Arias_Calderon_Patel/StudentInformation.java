import java.io.PrintWriter;
import java.util.Scanner;

public class StudentInformation
{
    private String firstName, lastName;

    public StudentInformation(Scanner sc)
    {
        System.out.println("Please enter your First Name");
        firstName = sc.nextLine();
        System.out.println("Please enter your Last Name");
        lastName = sc.nextLine();
        return;
    }

    public void printInfo(PrintWriter pw)
    {
        pw.print(firstName + " " + lastName + "\n" + "\n");
    }

}
