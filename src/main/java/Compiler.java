import lexer.Lexer;
import symbolTable.SymbolTable;
import token.Token;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Compiler {

  public static void main(String[] args) throws IOException {
    String file = formatFile(args[0]);
    String formattedFile = formatFile(file);
    SymbolTable table = new SymbolTable();
    Lexer lexer =  new Lexer(formattedFile, table);
    List<Token> list = new ArrayList<>(Collections.emptyList());
    while (lexer.isEnd()) {
      list.add(lexer.scan());
    }
    System.out.println("Symbol table");
    table.getSymbolTable().forEach((k, t) -> System.out.println("Key: " + k + ", Token: " + t.getTag().toString() +
                                                                    " " +
        "lex: " + t.getLex()));
    System.out.println("Lexical List");
    list.forEach(l -> System.out.println("Token: " + l.getTag().toString() + " lex: " + l.getLex()));
  }

  private static String formatFile(String file) {
    Path path = Paths.get(file);
    return path.toString();
  }

}
