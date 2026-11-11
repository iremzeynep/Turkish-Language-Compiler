

package programminglanguagesproject;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lexical_Analyzer {
    private static final String KEYWORDS =
            "\\b(açılış|mutlu son|şayet|buysa|yahut|döndur|yapadur|dolaş|den|kadar|tahsis|son)\\b";

    private static final String OPERATORS =
             "==|!=|<=|>=|=|\\+|\\-|\\*|\\/|\\(|\\)|<|>";

    private static final String IDENTIFIERS =
            "[a-zA-Z_ğüşöçİĞÜŞÖÇ][a-zA-Z0-9_ğüşöçİĞÜŞÖÇ]*";

    private static final String NUMBERS = "\\d+";

    private static final String WHITESPACE = "\\s+";

    private static final String ERROR = ".";

    public static List<String> tokenize(String line) {
        List<String> tokens = new ArrayList<>();
        String regex = String.format(
                "(?<KEYWORD>%s)|(?<OPERATOR>%s)|(?<IDENTIFIER>%s)|(?<NUMBER>%s)|(?<WHITESPACE>%s)|(?<ERROR>%s)",
                KEYWORDS, OPERATORS, IDENTIFIERS, NUMBERS, WHITESPACE, ERROR
        );

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            if (matcher.group("KEYWORD") != null) {
                tokens.add("KEYWORD: " + matcher.group("KEYWORD"));
            } else if (matcher.group("OPERATOR") != null) {
                tokens.add("OPERATOR: " + matcher.group("OPERATOR"));
            } else if (matcher.group("IDENTIFIER") != null) {
                tokens.add("IDENTIFIER: " + matcher.group("IDENTIFIER"));
            } else if (matcher.group("NUMBER") != null) {
                tokens.add("NUMBER: " + matcher.group("NUMBER"));
            } else if (matcher.group("WHITESPACE") != null) {
                // ignore
            } else if (matcher.group("ERROR") != null) {
                tokens.add("ERROR: " + matcher.group("ERROR"));
            }
        }

        return tokens;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Kod satırı girin (çıkmak için boş bırakın):");
            String line;

            while (!(line = scanner.nextLine()).isEmpty()) {
                List<String> tokens = tokenize(line);
                for (String token : tokens) {
                    System.out.println(token);
                }
                System.out.println("Yeni satır girin:");
            }
        }
    }

}
