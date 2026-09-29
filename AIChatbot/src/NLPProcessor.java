import java.util.*;
import java.util.stream.Collectors;

public class NLPProcessor {

    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "a", "an", "the", "is", "am", "are", "was", "were",
            "what", "who", "where", "when", "why", "how",
            "can", "could", "would", "please", "tell", "me",
            "about", "to", "of", "in", "on", "for", "do", "you",
            "i", "my", "your", "and", "or", "it"
    ));

    public static String normalize(String text) {

        if (text == null) {
            return "";
        }

        return text.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    public static List<String> extractKeywords(String text) {

        String normalized = normalize(text);

        if (normalized.isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.stream(normalized.split("\\s+"))
                .filter(word -> !STOP_WORDS.contains(word))
                .collect(Collectors.toList());
    }

    public static double similarity(
            List<String> userWords,
            List<String> intentKeywords) {

        if (userWords.isEmpty() || intentKeywords.isEmpty()) {
            return 0.0;
        }

        int matches = 0;

        for (String word : userWords) {

            if (intentKeywords.contains(word)) {
                matches++;
            }
        }

        return (double) matches / intentKeywords.size();
    }
}