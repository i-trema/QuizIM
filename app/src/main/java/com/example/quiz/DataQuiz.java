package com.example.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataQuiz {

    public static final QuestionBank generateQuestions(){
        //TODO
        Question question1 = new Question();
        question1.setQuestion("Quel est le premier président de la 4ème République ?");
        question1.setResponsesList(Arrays.asList("Vincent AURIOL", "René COTY", "Albert LEBRUN", "Paul DOUMER"));
        question1.setCorrectAnswerIndex(0);

        Question question2 = new Question();
        question2.setQuestion("Combien existe-t-il de pays dans l'Union Européenne ?");
        question2.setResponsesList(Arrays.asList("15", "24", "27", "32"));
        question2.setCorrectAnswerIndex(2);

        Question question3 = new Question();
        question3.setQuestion("Quelle est la langue la moins parlée du monde ?");
        question3.setResponsesList(Arrays.asList("L'artchi", "Le Silbo", "Rotokas", "Le piraha"));
        question3.setCorrectAnswerIndex(3);

        Question question4 = new Question();
        question4.setQuestion("Quel est le pays le plus peuplé du monde ?");
        question4.setResponsesList(Arrays.asList("USA", "Chine", "Inde", "Indonésie"));
        question4.setCorrectAnswerIndex(1);

        Question question5 = new Question();
        question5.setQuestion("Quel est le créateur du système d'exploitation Android ?");
        question5.setResponsesList(Arrays.asList("Jake Wharton", "Steve Wozmiak", "Paul Smith", "Andu Rubin"));
        question5.setCorrectAnswerIndex(3);

        Question question6 = new Question();
        question6.setQuestion("Quelle est la plus petite République du monde en nombre d'habitants ?");
        question6.setResponsesList(Arrays.asList("Les Tuvalu", "Nauru", "Monaco", "Les Palaos"));
        question6.setCorrectAnswerIndex(1);



        return new QuestionBank(Arrays.asList(question1,question2,question3,question4,question5,question6));
    }
}
