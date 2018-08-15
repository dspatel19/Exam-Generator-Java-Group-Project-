//Ruy Calderon rcalde6

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExamBuilder {

    public enum Choice
    {
        None,
        Create_Exam,
        Load_Exam,
        Add_Question,
        Remove_Question,
        Reorder_Question,
        Print_Exam,
        Save_Exam,
        Quit;

        @Override
        public String toString() {
            String s = super.toString();
            return s.replace('_', ' ');
        }
    }

    public static void PrintChoices()
    {
        System.out.println("Available Choices:");
        for(Choice C : Choice.values())
        {
            System.out.println(C.toString());
        }
    }

    public static Choice GetChoice(String S)
    {
        Choice Result = Choice.None;
        for(Choice C: Choice.values())
        {
            String ChoiceString = C.toString();
            if(ChoiceString.equalsIgnoreCase(S))
            {
                return C;
            }
        }
        return Result;
    }

    public static void main(String[] args)
    {
        System.out.println("Ruy Calderon | rcalde6");
        System.out.println("Alfonso Arias, Ruy Calderon, Dhaval Patel\n\n");
        BuilderExam LoadedExam = null;
        Choice CurrentChoice = Choice.None;
        while(CurrentChoice != Choice.Quit)
        {
            PrintChoices();
            CurrentChoice = GetChoice(ScannerFactory.getKeyboardScanner().nextLine());

            switch(CurrentChoice)
            {
                case Create_Exam:
                {
                    System.out.println("Please enter exam title");
                    String ExamName = ScannerFactory.getKeyboardScanner().nextLine();
                    LoadedExam = new BuilderExam(ExamName);
                    System.out.println("Exam created");
                }break;
                case Load_Exam:
                {
                    System.out.println("Please enter file name");
                    String fileName = ScannerFactory.getKeyboardScanner().nextLine();
                    try
                    {
                        Scanner FileScanner = new Scanner(new File(fileName));
                        LoadedExam = new BuilderExam(FileScanner);
                        FileScanner.close();
                    }
                    catch(Exception E)
                    {
                        System.out.println("Error opening file: " + fileName);
                        System.out.println("Your current path: " + System.getProperty("user.dir"));
                        break;
                    }
                    System.out.println("Exam Loaded");
                }break;
                case Add_Question:
                {
                    System.out.println("Add Question Selected");
                    System.out.println("Your Question Requires: ");
                    QuestionConstructor QC = new QuestionConstructor();
                    QC.BuildQuestion();
                    assert(QC.Finished());
                    Question result = QC.Deliver();
                    try
                    {
                        LoadedExam.addQuestion(result);
                    }
                    catch(Exception e)
                    {
                        System.out.println("Error, failed to create your question");
                    }
                    System.out.println("Question Added");
                }break;
                case Remove_Question:
                {
                    System.out.println("Remove Questions");
                    QuestionViewer QV = new QuestionViewer(LoadedExam);
                    System.out.println("type Next or Previous to scroll to the Next and Previous Questions respectively");
                    System.out.println("type Remove to select question for removal");
                    System.out.println("type Quit when finished");
                    QV.ViewQuestionAt(0);
                    QV.Refresh();
                    Scanner Scan = ScannerFactory.getKeyboardScanner();
                    boolean Continue = true;
                    while(Continue)
                    {
                        String PlayerIn = Scan.nextLine().toLowerCase().trim();
                        switch(PlayerIn)
                        {
                            case "previous":
                            {
                                QV.PreviousQuestion();
                                QV.Refresh();
                            }break;
                            case "next":
                            {
                                QV.NextQuestion();
                                QV.Refresh();
                            }break;
                            case "remove":
                            {
                                QV.Mark();
                                System.out.println("Question marked for removal");
                                System.out.flush();
                            }break;
                            case "quit":
                            {
                                Continue = false;
                            }break;
                            default:
                            {
                                System.out.println("Invalid input");
                            }break;
                        }
                    }

                    for(int QuestionIndex : QV.GetMarked())
                    {
                        LoadedExam.removeQuestionAt(QuestionIndex);
                    }
                    System.out.println("Question(s) Removed");
                }break;
                case Reorder_Question:
                {
                    System.out.println("Reorder Answers");
                    QuestionViewer QV = new QuestionViewer(LoadedExam);
                    System.out.println("type Next or Previous to scroll to the Next and Previous Questions respectively");
                    System.out.println("type Reorder to select question for reordering");
                    System.out.println("type Quit when finished");
                    QV.ViewQuestionAt(0);
                    QV.Refresh();
                    Scanner Scan = ScannerFactory.getKeyboardScanner();
                    String PlayerIn = Scan.nextLine().toLowerCase().trim();
                    boolean Continue = true;
                    while(Continue)
                    {
                        switch(PlayerIn)
                        {
                            case "previous": //Up Arrow
                            {
                                QV.PreviousQuestion();
                                QV.Refresh();
                            }break;
                            case "next": //Down Arrow
                            {
                                QV.NextQuestion();
                                QV.Refresh();
                            }break;
                            case "reorder": //Enter Key
                            {
                                LoadedExam.reorderMCAnswers(QV.IndexOfCurrent());
                                System.out.println("Question marked for reordering");
                                System.out.flush();
                            }break;
                            case "quit":
                            {
                                Continue = false;
                            }break;
                            default:
                            {
                                System.out.println("Invalid input detected");
                            }break;
                        }
                        PlayerIn = Scan.nextLine().toLowerCase().trim();
                    }
                    LoadedExam.reorderQuestions();
                    System.out.println("Exam Reordered");

                }break;
                case Print_Exam:
                {
                    System.out.println("Printing Exam");
                    System.out.println("Would you like to print to file?");
                    System.out.println("Press Y if so, N if you would like to print to console");
                    if(ScannerFactory.getKeyboardScanner().nextLine().equalsIgnoreCase("Y"))
                    {
                        while(true)
                        {
                            try
                            {
                                System.out.println("Please enter valid file name");
                                String saveFile = ScannerFactory.getKeyboardScanner().nextLine();
                                LoadedExam.saveHardCopy(saveFile);
                            }
                            catch(Exception E)
                            {
                                System.out.println("File not found");
                                continue;
                            }
                            break;
                        }
                        System.out.println("Exam Printed to file");
                    }
                    else
                    {
                        LoadedExam.print();
                    }

                }break;
                case Save_Exam:
                {
                    System.out.println("Saving Exam to file");
                    while(true)
                    {
                        try
                        {
                            System.out.println("Please enter valid file name");
                            String saveFile = ScannerFactory.getKeyboardScanner().nextLine();
                            LoadedExam.save(new PrintWriter(new File(saveFile)));
                        }
                        catch(Exception E)
                        {
                            System.out.println("File not found");
                            continue;
                        }
                        break;
                    }
                    System.out.println("Exam Saved");
                }break;
                case Quit:
                {
                    System.out.println("Exiting");
                }break;
                case None:
                default:
                {
                    System.out.println("Invalid command, please try again");
                }break;
            }
        }

        return;
    }

}
