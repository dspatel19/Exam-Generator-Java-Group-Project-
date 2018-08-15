//Dhaval Patel dpate236

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExamGrader {


	private static Scanner AnswerScan;
	private static Scanner ExamScan;
	private static String studentName = "";
	private static GraderExam Exam;
	private static String examfilename = "";
	private static String answerfilename = "";

  //1) Load up an Exam File and an Answer File confirming that they matched set
	public static void main(String[] args){

		try{
			System.out.println("Exam Grader: \n");

			System.out.println("Enter the Exam Filename: ");
			ExamScan = ScannerFactory.getKeyboardScanner();
			examfilename = ExamScan.nextLine();

			System.out.println("Enter the Answer Filename: ");
			AnswerScan = ScannerFactory.getKeyboardScanner();
			answerfilename = AnswerScan.nextLine();

			ExamScan = new Scanner((new File(examfilename)));
			AnswerScan  = new Scanner(new File(answerfilename));

			studentName = AnswerScan.nextLine();
			studentName = AnswerScan.nextLine();

			Exam = new GraderExam(ExamScan);

			if(Exam.CheckSum(Exam.GetCheckSum())){
				System.out.println("\n valid Exam Filename! \n");

				//ExamScan  = new Scanner(new File(examfilename));
				//Exam = new GraderExam(ExamScan);

				Exam.reportQuestionValues();
				Exam.restoreStudentAnswers(AnswerScan);
				AnswerScan.close();

				String scoreFile = studentName + "results.csv";
				FileWriter file = new FileWriter(scoreFile);
				PrintWriter printWriter = new PrintWriter(file);
				Exam.reportQuestionValuesToFile(printWriter, studentName);

				printWriter.flush();
				printWriter.close();
				}

			else {
				 System.out.println("\n Invalid Exam Filename! \n");
				 return;
			 }

		}

		catch(Exception Ex){
			Ex.printStackTrace();
		}
	}



}