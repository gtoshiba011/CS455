public class Question {
    private String text;

    private String getText() {
        return "from superclass";
    }
}

class MultiChoiceQuestion extends Question {

    private String getText() {
        return "from subclass";
    }
}
