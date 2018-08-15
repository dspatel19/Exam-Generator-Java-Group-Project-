import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Answer {
    protected Answer(){}

    public Answer(Scanner Scan){}

    public abstract void print();

    public abstract double getCredit(Answer RightAnswer);

    public abstract void save(PrintWriter PW);
}
