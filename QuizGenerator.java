import java.util.Scanner;

class Quiz {
    String quizName;        				 // Quiz name
    Question[] questions;    				 // Array of questions
    int size;               				 // Number of questions

    Quiz(String quizName, int capacity) {		 //constructor
        this.quizName = quizName;
        this.questions = new Question[capacity];
        this.size = 0;
    }

    void addQuestion(Question question) {		//constructor
        if (size < questions.length) {
            questions[size] = question;
            size++;
        } else {
            System.out.println("Quiz is full.");
        }
    }

    String getQuizName() {				//getter method to get name of quiz
        return quizName;
    }

    Question[] getQuestions() {				//getter method to get number of questions
        return questions;
    }
}

//Question Class-----------------------------------------------

class Question {
    String questionText;
    String[] options;
    int correctAnswer;

    Question(String questionText, String[] options, int correctAnswer) {	//constructor
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    String getQuestionText() {						//getter method to get questions

        return questionText;
    }

    String[] getOptions() {					//getter method to get options

        return options;
    }

    int getCorrectAnswer() {					//getter method to get correct answers

        return correctAnswer;
    }
}

//Main Class------------------------------------

public class QuizGenerator { 

    static Quiz[] quizzes = new Quiz[10]; 			// Assuming maximum 10 quizzes
    static int quizCount = 0;
    static Scanner scanner = new Scanner(System.in);		//how many number of quiz input

    public static void main(String[] args) {
       
	 while (true) {
            System.out.println("Welcome to Command-Line Quiz Generator");
            System.out.println("1. Add Quiz");
            System.out.println("2. Take Quiz");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();			// choice variable 
            scanner.nextLine();					 

            switch (choice) {					// Switch case
                case 1:
                    addQuiz();
                    break;
                case 2:
                    takeQuiz();
                    break;
                case 3:
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void addQuiz() {				//method
        if (quizCount >= quizzes.length) {		//to check quiz array is full or empty
            System.out.println("quizz array full");
            return;
        }

        System.out.print("Enter quiz name: ");
        String quizName = scanner.nextLine();
        System.out.print("Enter how many number of questions in this quiz: ");
        int numQuestions = scanner.nextInt();
        scanner.nextLine(); 

        Quiz quiz = new Quiz(quizName, numQuestions);    //Quiz object

        for (int i = 0; i < numQuestions; i++) {	//iterate for num of questions user entered
            System.out.print("Enter question : " + (i + 1));
            String questionText = scanner.nextLine();

            String[] options = new String[4]; 		// string array of 4 options
            for (int j = 0; j < 4; j++) {		//iterate 4 times for options
                System.out.print("Enter option " + (j + 1));
                options[j] = scanner.nextLine();
            }

            System.out.print("Enter correct answer (1/2/3/4): ");
            int correctAnswer = scanner.nextInt() - 1; 
            scanner.nextLine(); 

            Question question = new Question(questionText, options, correctAnswer);
            quiz.addQuestion(question);     //Adding the Question object to the quiz object
        }

        quizzes[quizCount] = quiz;          //adding quiz in quizzes array
        quizCount++;
        System.out.println("Quiz added");
    }

    static void takeQuiz() {
        if (quizCount == 0) {
            System.out.println("empty quiz.");
            return;
        }

        System.out.println("Available quizzes:");
        for (int i = 0; i < quizCount; i++) {
            System.out.println((i + 1) + quizzes[i].getQuizName());  //get method to display quiz available
        }

        System.out.print("Choose a quiz (enter number): ");
        int quizNumber = scanner.nextInt() - 1;
        scanner.nextLine(); 

        if (quizNumber < 0 || quizNumber >= quizCount) {
            System.out.println("Invalid quiz number.");
            return;
        }

        Quiz selectedQuiz = quizzes[quizNumber];
        Question[] questions = selectedQuiz.getQuestions();             //get method to display questions in quiz

        int score = 0;
            
	    for (int i = 0; i < questions.length; i++) { 		//ierate array of questions
	    Question question = questions[i];

            System.out.println("\nQuestion: " + question.getQuestionText()); 
            String[] options = question.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ". " + options[j]);
            }

            System.out.print("Your answer (1/2/3/4): ");
            int userAnswer = scanner.nextInt() - 1;
            scanner.nextLine();

            if (userAnswer == question.getCorrectAnswer()) {		//compare answers
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. Correct answer is: " + (question.getCorrectAnswer() + 1));
            }
        }

       int totalQuestions = questions.length;		//total question for score 
       
        System.out.println("\nQuiz completed!");
        System.out.println("Your score: " + score + "/" + totalQuestions);
      

  }
}
