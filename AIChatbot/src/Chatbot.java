import java.util.*;

public class Chatbot {

    private final List<FAQ> faqList;

    private static final double MINIMUM_SCORE = 0.25;

    public Chatbot() {

        faqList = new ArrayList<>();

        loadFAQs();
    }

    private void loadFAQs() {

        faqList.add(new FAQ(
                "greeting",

                new String[]{
                        "hello",
                        "hi",
                        "hey",
                        "hii",
                        "greetings"
                },

                new String[]{
                        "hello",
                        "hi",
                        "hey"
                },

                "Hello! 👋 How can I help you today?"
        ));

        faqList.add(new FAQ(
                "java",

                new String[]{
                        "java",
                        "programming",
                        "language"
                },

                new String[]{
                        "what is java",
                        "tell me about java",
                        "java language"
                },

                "Java is a popular object-oriented programming language used to build desktop, web and enterprise applications."
        ));

        faqList.add(new FAQ(
                "ai",

                new String[]{
                        "ai",
                        "artificial",
                        "intelligence",
                        "intelligent"
                },

                new String[]{
                        "what is ai",
                        "what is artificial intelligence"
                },

                "Artificial Intelligence (AI) is a field of computer science that enables machines to perform tasks that normally require human-like intelligence."
        ));

        faqList.add(new FAQ(
                "nlp",

                new String[]{
                        "nlp",
                        "natural",
                        "language",
                        "processing",
                        "text"
                },

                new String[]{
                        "what is nlp",
                        "what is natural language processing"
                },

                "Natural Language Processing (NLP) helps computers process, understand and analyze human language."
        ));

        faqList.add(new FAQ(
                "oop",

                new String[]{
                        "oop",
                        "object",
                        "oriented",
                        "class"
                },

                new String[]{
                        "what is oop",
                        "what is object oriented programming"
                },

                "OOP stands for Object-Oriented Programming. It organizes programs around objects and classes. Its common concepts include encapsulation, inheritance, polymorphism and abstraction."
        ));

        faqList.add(new FAQ(
                "college",

                new String[]{
                        "college",
                        "university",
                        "campus",
                        "student"
                },

                new String[]{
                        "college information",
                        "tell me about college"
                },

                "This chatbot can be customized with your college's name, departments, courses, timings and other frequently asked questions."
        ));

        faqList.add(new FAQ(
                "project",

                new String[]{
                        "project",
                        "chatbot",
                        "assignment"
                },

                new String[]{
                        "what is this project",
                        "tell me about this chatbot"
                },

                "This is a Java-based AI chatbot project using rule-based NLP, keyword matching, a FAQ knowledge base and a Swing GUI."
        ));

        faqList.add(new FAQ(
                "creator",

                new String[]{
                        "creator",
                        "created",
                        "made",
                        "developer"
                },

                new String[]{
                        "who created you",
                        "who made you"
                },

                "I am a student project chatbot created using Java."
        ));

        faqList.add(new FAQ(
                "thanks",

                new String[]{
                        "thanks",
                        "thank",
                        "thankyou"
                },

                new String[]{
                        "thank you",
                        "thanks"
                },

                "You're welcome! 😊"
        ));

        faqList.add(new FAQ(
                "bye",

                new String[]{
                        "bye",
                        "goodbye",
                        "exit",
                        "quit"
                },

                new String[]{
                        "bye",
                        "goodbye",
                        "see you"
                },

                "Goodbye! 👋 Have a great day."
        ));
    }

    public String getResponse(String userInput) {

        if (userInput == null ||
                userInput.trim().isEmpty()) {

            return "Please type a message so I can help you.";
        }

        String normalizedInput =
                NLPProcessor.normalize(userInput);

        List<String> userKeywords =
                NLPProcessor.extractKeywords(normalizedInput);

        FAQ bestFAQ = null;

        double bestScore = 0.0;

        for (FAQ faq : faqList) {

            double score =
                    calculateScore(
                            normalizedInput,
                            userKeywords,
                            faq
                    );

            if (score > bestScore) {

                bestScore = score;
                bestFAQ = faq;
            }
        }

        if (bestFAQ != null &&
                bestScore >= MINIMUM_SCORE) {

            return bestFAQ.getAnswer();
        }

        return "Sorry, I don't understand that yet. Try asking about Java, AI, NLP, OOP, this project, or college information.";
    }

    private double calculateScore(
            String normalizedInput,
            List<String> userKeywords,
            FAQ faq) {

        double keywordScore =
                NLPProcessor.similarity(
                        userKeywords,
                        faq.getKeywords()
                );

        double questionScore = 0.0;

        for (String question : faq.getQuestions()) {

            String normalizedQuestion =
                    NLPProcessor.normalize(question);

            if (normalizedInput.equals(normalizedQuestion)) {

                questionScore = 1.0;

                break;
            }

            if (normalizedInput.contains(normalizedQuestion)) {

                questionScore =
                        Math.max(questionScore, 0.8);
            }
        }

        return Math.max(
                keywordScore,
                questionScore
        );
    }
}