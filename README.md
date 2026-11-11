# Custom Turkish Programming Language Compiler

*Note: This academic project was collaboratively developed as a group project demonstrating Compiler Design principles.*

A lexical and syntax analyzer (parser) built in Java for a custom programming language featuring Turkish keywords. The project validates structural grammar rules, tokenizes source code using regular expressions, and implements custom exception handling for syntax errors.

## Features

* **Lexical Analyzer (Lexer):** Tokenizes source code into predefined categories (Keywords, Operators, Identifiers, Numbers) using Regex, while ignoring whitespaces and flagging unrecognized characters as errors.
* **Syntax Analyzer (Parser):** Validates the sequence of tokens against the custom language's grammar rules. It ensures proper block structuring, hierarchical parsing, and keyword matching.
* **Custom Error Handling:** Implements a specific `MissingSonException` to detect unclosed code blocks, alongside detailed real-time syntax error reporting.

## Language Grammar & Keywords

The language uses specific Turkish keywords for its control flow and structure:
* **Program Structure:** Starts with `açılış` and must end with `mutlu son`.
* **Variable Assignment:** `tahsis x = 10`
* **If/Else Statement:** `şayet [condition] buysa ... yahut ... son`
* **While Loop:** `döndur [condition] yapadur ... son`
* **For Loop:** `dolaş i = 0 den 5 kadar ... son`

*Every conditional or loop block must be explicitly closed with the `son` keyword.*

## Project Structure

* `src/programminglanguagesproject/`: Contains the core Java source code.
  * `Lexical_Analyzer.java`: The regex-based tokenizer module.
  * `Syntax_Analyzer.java`: The parser enforcing language grammar rules.
  * `MissingSonException.java`: Custom exception class for unclosed block detection.
  * `ProgrammingLanguagesProject.java`: The main entry point integrating both analyzers.
* `example1.txt` - `example4.txt`: Sample source code files written in the custom language, designed to test specific grammar combinations and error cases.

## How to Run

1. Clone this repository to your local machine.
2. Ensure you have the Java Development Kit (JDK 8 or higher) installed.
3. Keep the `example.txt` files in the root directory.
4. Compile and run the `ProgrammingLanguagesProject` main class.
5. The interactive console will prompt you to select one of the provided `example.txt` files to process through the Lexer and Syntax Analyzer.
