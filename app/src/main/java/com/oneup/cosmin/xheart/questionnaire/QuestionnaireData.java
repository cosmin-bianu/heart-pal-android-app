package com.oneup.cosmin.xheart.questionnaire;

public class QuestionnaireData {
    private static final QuestionnaireData ourInstance = new QuestionnaireData();
    public static QuestionnaireData getInstance() {
        return ourInstance;
    }
    private QuestionnaireData() {
    }

    private enum QuestionnaireAnswer {
        A,B,C,D
    }
    private QuestionnaireAnswer Answer1;
    private QuestionnaireAnswer Answer2;
    private QuestionnaireAnswer Answer3;
    private QuestionnaireAnswer Answer4;

    public void setAnswer1(QuestionnaireAnswer answer1) {
        Answer1 = answer1;
    }
    public void setAnswer2(QuestionnaireAnswer answer2) {
        Answer2 = answer2;
    }
    public void setAnswer3(QuestionnaireAnswer answer3) {
        Answer3 = answer3;
    }
    public void setAnswer4(QuestionnaireAnswer answer4) {
        Answer4 = answer4;
    }
}