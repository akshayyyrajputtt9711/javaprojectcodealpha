import java.util.Arrays;
import java.util.List;

public class FAQ {

    private final String intent;
    private final List<String> keywords;
    private final List<String> questions;
    private final String answer;

    public FAQ(String intent, String[] keywords,
               String[] questions, String answer) {

        this.intent = intent;
        this.keywords = Arrays.asList(keywords);
        this.questions = Arrays.asList(questions);
        this.answer = answer;
    }

    public String getIntent() {
        return intent;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public String getAnswer() {
        return answer;
    }
}