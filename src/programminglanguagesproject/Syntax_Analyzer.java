

package programminglanguagesproject;
import java.util.List;

public class Syntax_Analyzer {
    private final List<String> tokens;
    private int currentTokenIndex = 0;
    private boolean foundSon = false;

    public Syntax_Analyzer(List<String> tokens) {
        this.tokens = tokens;
    }

    private String nextToken() {
        if (currentTokenIndex < tokens.size()) {
            return tokens.get(currentTokenIndex++);
        }
        return null;
    }

    private String peekToken() {
        if (currentTokenIndex < tokens.size()) {
            return tokens.get(currentTokenIndex);
        }
        return null;
    }

    private boolean match(String expectedTokenPrefix) {
        String token = peekToken();
        if (token != null && token.startsWith(expectedTokenPrefix)) {
            nextToken();
            return true;
        }
        return false;
    }

    public void parse() throws MissingSonException {
    if (!match("KEYWORD: açılış")) {
        System.err.println("Sözdizimi hatası: Program 'açılış' anahtar kelimesi ile başlamalı.");
        return;
    }

    while (currentTokenIndex < tokens.size()) {
        if (peekToken().startsWith("ERROR")) {
            System.err.println("Sözdizimi hatası: " + peekToken());
            nextToken();
            return;
        }
        if (peekToken().equals("KEYWORD: mutlu son")) {
            break;
        }
        statement();
    }

    if (!match("KEYWORD: mutlu son")) {
        System.err.println("Sözdizimi hatası: Program 'mutlu son' anahtar kelimesi ile bitmeli.");
    }

    

    reportUnprocessedTokens();
}

    private boolean statement() {
        if (match("KEYWORD: şayet")) {
            ifStatement();
        } else if (match("KEYWORD: döndur")) {
            whileStatement();
        } else if (match("KEYWORD: dolaş")) {
            forStatement();
        } else if (match("KEYWORD: son")) {
            foundSon = true;
            reportUnprocessedTokens();
            return true;
        } else if (match("KEYWORD: tahsis")) {
            assignment();
        } else {
            System.err.println("Sözdizimi hatası: Geçersiz ifade " + peekToken());
            nextToken();
        }
        return false;
    }

    private void ifStatement() {
        condition();
        if (!match("KEYWORD: buysa")) {
            System.err.println("Sözdizimi hatası: 'şayet' ifadesinde 'buysa' eksik.");
        }
        block();

        if (match("KEYWORD: yahut")) {
            block();
        }
    }

    private void whileStatement() {
        condition();
        if (!match("KEYWORD: yapadur")) {
            System.err.println("Sözdizimi hatası: 'döndur' ifadesinde 'yapadur' eksik.");
        }
        block();
    }

    private void forStatement() {
        if (!match("IDENTIFIER:")) {
            System.err.println("Sözdizimi hatası: 'dolaş' ifadesinde tanımlayıcı eksik.");
        }
        if (!match("OPERATOR: =")) {
            System.err.println("Sözdizimi hatası: 'dolaş' ifadesinde '=' eksik.");
        }
        expression();
        if (!match("KEYWORD: den")) {
            System.err.println("Sözdizimi hatası: 'dolaş' ifadesinde 'den' eksik.");
        }
        expression();
        if (!match("KEYWORD: kadar")) {
            System.err.println("Sözdizimi hatası: 'dolaş' ifadesinde 'kadar' eksik.");
        }
        block();
    }

    private void assignment() {
        if (!match("IDENTIFIER:")) {
            System.err.println("Sözdizimi hatası: 'tahsis'ten sonra tanımlayıcı bekleniyor.");
            return;
        }
        if (!match("OPERATOR: =")) {
            System.err.println("Sözdizimi hatası: 'tahsis' ifadesinde '=' eksik.");
            return;
        }
        expression();
    }

   private void block() {
    while (currentTokenIndex < tokens.size()) {
        if (peekToken().equals("KEYWORD: son")) {
            match("KEYWORD: son");
            return;
        }
        statement();
    }
    System.err.println("Sözdizimi hatası: bloklar 'son' ile kapanmalıdır.");
}

    private void expression() {
        term();
        while (match("OPERATOR: +") || match("OPERATOR: -")) {
            term();
        }
    }

    private void term() {
        factor();
        while (match("OPERATOR: *") || match("OPERATOR: /")) {
            factor();
        }
    }

    private void factor() {
        if (match("NUMBER:") || match("IDENTIFIER:")) {
            return;
        } else if (match("OPERATOR: (")) {
            expression();
            if (!match("OPERATOR: )")) {
                System.err.println("Sözdizimi hatası: ')' bekleniyor.");
            }
        } else {
            System.err.println("Sözdizimi hatası: Geçersiz ifade: " + peekToken());
            nextToken();
        }
    }

    private void condition() {
        expression();
        String token = peekToken();
        if (token == null || !token.startsWith("OPERATOR:")) {
            System.err.println("Sözdizimi hatası: Karşılaştırma operatörü bekleniyor, ancak '" + token + "' bulundu.");
        } else {
            nextToken(); // Operatör
        }
        expression();
    }

    private void reportUnprocessedTokens() {
        if (currentTokenIndex < tokens.size()) {
            System.err.println("Program 'son' ile sonlandı ancak işlenmemiş tokenlar var:");
            while (currentTokenIndex < tokens.size()) {
                System.err.println("  İşlenmemiş token: " + nextToken());
            }
        }
    }
}
