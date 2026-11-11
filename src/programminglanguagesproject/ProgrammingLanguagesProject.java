package programminglanguagesproject;
import java.io.*;
import java.util.*;

public class ProgrammingLanguagesProject {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            args = getFilename();
        }

        for (String inputFile : args) {
            try {
                // 1. LEXICAL ANALYSIS
                List<String> tokens = lexFile(inputFile);
                System.out.println("\nDosya: " + inputFile + " için LEXER çıktısı:");
                for (String token : tokens) {
                    System.out.println("  " + token);
                }

                // 2. SYNTAX ANALYSIS
                System.out.println("\nSyntax analizi başlatılıyor...");
                Syntax_Analyzer syntaxAnalyzer = new Syntax_Analyzer(tokens);
                syntaxAnalyzer.parse();
                System.out.println("Ayrıştırma tamamlandı: " + inputFile + "\n");

            } catch (MissingSonException e) {
                System.err.println("Sözdizimi hatası: 'son' ifadesi eksik: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Dosya okunurken hata oluştu: " + inputFile);
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Bilinmeyen hata oluştu: " + inputFile);
                e.printStackTrace();
            }
        }
    }

    // Kullanıcıdan dosya seçmesini isteyen yardımcı fonksiyon
    private static String[] getFilename() {
        String[] exampleFiles = { "example1.txt", "example2.txt", "example3.txt", "example4.txt" };
        Scanner scanner = new Scanner(System.in);

        System.out.println("Aşağıdaki örnek dosyalardan birini seçin:");
        for (int i = 0; i < exampleFiles.length; i++) {
            System.out.println((i + 1) + ". " + exampleFiles[i]);
        }
        System.out.println(exampleFiles.length + 1 + ". Çıkış");

        System.out.print("Seçiminiz: ");
        try {
            int choice = scanner.nextInt();
            if (choice < 1 || choice > exampleFiles.length + 1) {
                System.out.println("Geçersiz seçim.");
                return getFilename();
            }
            if (choice == exampleFiles.length + 1) {
                System.out.println("Program sonlandırıldı.");
                System.exit(0);
            }
            return new String[]{ exampleFiles[choice - 1] };
        } catch (InputMismatchException e) {
            System.out.println("Geçersiz giriş.");
            return getFilename();
        }
    }

    // Dosyayı satır satır okuyarak token listesi üretir
    private static List<String> lexFile(String filename) throws IOException {
        List<String> tokens = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
        String line;

        while ((line = reader.readLine()) != null) {
            tokens.addAll(Lexical_Analyzer.tokenize(line));
        }

        reader.close();
        return tokens;
    }
}
