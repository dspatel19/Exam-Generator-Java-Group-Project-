ReadMe for TestRandomizer release 0.4
Alfonso Arias | aarias9
Ruy Calderon  | rcalde6
Dhaval Patel  | dpate236

Responsibilities:        APPLICATION       FILES CONTRIBUTED
	Alfonso Arias:   ExamTaker         Exam, Question, MCQuestion
	Ruy Calderon:    ExamBuilder       Answer, SAQuestion, SAAnswer, NumQuestion, NumAnswer
	Dhaval Patel:    ExamGrader        MCAnswer, MCSAQuestion, MCSAAnswer, MCMAQuestion, MCMAAnswer, ScannerFactory
Making the Project:
	ExamBuilder: type "make ExamBuilder.java"
	ExamTaker:   type "make ExamTaker.java"
	ExamGrader:  type "make ExamGrader.java"
		**Ensure that your make is calling javac *filename* with the proper path specified

Running The Project:
	ExamBuilder: type "java ExamBuilder"
	ExamTaker:   type "java ExamTaker"
	ExamGrader:  type "java ExamGrader"
		
Mandatory Features:
	The programs are implemented as intended in the documentation. There were some difficulties with the ExamGrader,
but the scope was reduced so this shouldn't be a problem. The programs are self-explanatory when run, although many of
the options require that the spelling be exact, so please be careful with your spelling.