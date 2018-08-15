import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Collections;
import java.security.MessageDigest;
//Exam class, defines the highest level of organization for the test randomizer program
//As well, the Exam class provides the high-level API and access to the lower-level APIs
//of the various question and answer classes.

public class Exam {
    protected String text;
    protected String StudentIdentifier;
    protected ArrayList<Question> questions = new ArrayList<>();

    public Exam(String Text)
    {
        text = Text;
    }
    public Exam(Scanner Scan)
    {
        text = Scan.nextLine();
        while(Scan.hasNextLine())
        {
            String Line = Scan.nextLine();
            if(Line.length() > 0) {
                if (Line.equalsIgnoreCase("MCSAQuestion")) {
                    questions.add(new MCSAQuestion(Scan));
                } else if (Line.equalsIgnoreCase("MCMAQuestion")) {
                    questions.add(new MCMAQuestion(Scan));
                } else if (Line.equalsIgnoreCase("SAQuestion")) {
                    questions.add(new SAQuestion(Scan));
                } else if (Line.equalsIgnoreCase("NumQuestion")) {
                    questions.add(new NumQuestion(Scan));
                } else {
                    System.out.println("Invalid input, cannot resolve question of type: \n\t" + Line);
                }
            }
        }
    }

    private String GetHashable()
    {
        String toHash = text.split(" ")[0];
        for(Question Q: questions)
        {
            toHash += Q.getHashString();
        }
        return toHash;
    }

    public int GetCheckSum()
    {
        return GetHashable().hashCode();
    }

    public boolean CheckSum(int inHash)
    {
        return GetCheckSum() == inHash;
    }

    public void print()
    {
        System.out.println(text);
        System.out.println("");
        for(Question Q : questions)
        {
            System.out.print((questions.indexOf(Q)+1) + ". ");
            Q.print();
        }
    }

    public void reportQuestionValues()
    {
        System.out.println("Printing Graded Exam:\n");
        for(Question Q : questions)
        {
            System.out.print("Question " );
            System.out.println((questions.indexOf(Q)+1) + ": " + Q.getValue());
        }
        System.out.println("-------------------");
        System.out.println("Exam Total: " + getValue());
    }

    public void reorderQuestions()
    {
        Collections.shuffle(questions);
    }

    public void reorderMCAnswers(int position)
    {
        if(position == -1)
        {
            for(Question Q: questions)
            {
                if(Q instanceof MCQuestion)
                {
                    ((MCQuestion)Q).reorderAnswers();
                }
            }
        }
        else
        {
            Question Q = questions.get(position);
            if(Q instanceof MCQuestion)
            {
                ((MCQuestion)Q).reorderAnswers();
            }
        }
    }

    public void getAnswerFromStudent(int Pos)
    {
        System.out.println(Pos);
        if(Pos == 1)
        {
            System.out.println("Now taking " + text);
            System.out.println("Enter your name and netid on the same line");
            Scanner Sc = ScannerFactory.getKeyboardScanner();
            StudentIdentifier = Sc.nextLine();
        }

        if(Pos <= (questions.size()))
        {
            questions.get(Pos-1).print();
            System.out.println("Please input your answer(s) below");
            questions.get(Pos-1).getAnswerFromStudent();
        }
    }

    public float getValue() {
        float Sum = 0.0f;
        for (Question Q : questions) {
            Sum += Q.getValue();
        }
        return Sum;
    }

    public void save(PrintWriter Fileout)
    {
        Fileout.println(text + "\n");
        for(Question Q : questions)
        {
            Q.save(Fileout);
        }
        Fileout.close();
    }



    public void saveStudentAnswers(PrintWriter Fileout)
    {
        Fileout.println(StudentIdentifier + "\n");

        for(Question Q: questions)
        {
            Q.saveStudentAnswer(Fileout);
        }
        Fileout.close();
    }

    public void restoreStudentAnswers(Scanner Scan)
    {
        StudentIdentifier = Scan.nextLine();
        int currentQuestion = 0;
        while(Scan.hasNextLine())
        {
            String Line = Scan.nextLine();
            if(Line.length() > 0) {
                questions.get(currentQuestion).restoreStudentAnswer(Scan);
                currentQuestion++;
            }
        }
    }


}

