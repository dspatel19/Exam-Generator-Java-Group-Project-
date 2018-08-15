 import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.text.DecimalFormat;
 import java.util.Scanner;
public class GraderExam extends Exam{

	public GraderExam(Scanner Scan){
		super(Scan);
	}


	public void restoreStudentAnswers(Scanner Scan)
	{
		//get student name from file and save it


		//restore student answers.
		int currentQuestion = 0;
		while (Scan.hasNextLine()) {
			String Line = Scan.nextLine();
			if (Line.length() > 0) {
				questions.get(currentQuestion).restoreStudentAnswer(Scan);
				currentQuestion++;
			}
		}
	}

	public void reportQuestionValuesToFile(PrintWriter prt, String studentName){

		for(Question Q : questions)
		{

			prt.print( studentName + ", " + "\n" +
					"Total Scores: " + Q.getValue() + "," + "\n" +
					" Question (" + (questions.indexOf(Q)+1)+ ")" + ", " + Q.getValue() + "\n");
		}

	}

	
}