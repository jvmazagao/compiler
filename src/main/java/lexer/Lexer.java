package lexer;

import symbolTable.SymbolTable;
import tag.Tag;
import token.Token;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lexer {
  private char ch;
  private boolean end;
  private final BufferedReader archive;
  private final SymbolTable table;
  private Integer line;

  public Lexer(String archive, SymbolTable table) throws FileNotFoundException {
    this.ch = ' ';
    this.line = 0;
    this.end = false;
    this.table = table;
    this.archive = new BufferedReader(new FileReader(archive));
  }

  private void readChar() throws IOException {
    this.ch = (char) this.archive.read();
  }

  private boolean readChar(char c) throws IOException {
    readChar();
    if (this.ch != c) {
      return false;
    }
    this.ch = ' ';
    return true;
  }

  public Token scan() throws IOException {
    for (;; readChar()) {
      if (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\b') continue;
      if (ch == '\"') {
        StringBuilder sb = new StringBuilder();
        do {
          sb.append(this.ch);
          readChar();
        } while (this.ch != 59 && this.ch != 41 && this.ch != '\n');
        return new Token(Tag.STRING, sb.toString());
      }
      else if (ch == '\n') line++;
      else if (ch == '%') {
        StringBuilder sb = new StringBuilder();
        do {
          sb.append(this.ch);
          readChar();
          if(this.ch == '\n') {
            line++;
          }
        } while (this.ch != '%' && this.ch != '\uFFFF');
        sb.append(this.ch);
        readChar();
        if(!sb.toString().endsWith("%")) {
          throw new Error("Syntax Error: Comments not closed");
        }
      }
      else break;
    }

    if (this.ch == ':') {
      if (readChar('=')) {
        return new Token(Tag.EQ, ":=");
      }
      return new Token(Tag.ID, ":");
    }

    if(this.ch == '>') {
      if (readChar('=')) {
        return new Token(Tag.GTE, ">=");
      }
      return new Token(Tag.GT, ">");
    }

    if(this.ch == '<') {
      readChar();
      if (this.ch == '=') {
        readChar();
        return new Token(Tag.LTE, "<=");
      }
      if (this.ch == '>') {
        readChar();
        return new Token(Tag.NE, "<>");
      }
      return new Token(Tag.LT, "<");
    }

    if(this.ch == '=') {
      readChar();
      if (this.ch == '=') {
        readChar();
        return new Token(Tag.EQS, "==");
      }
      return new Token(Tag.ID, "=");
    }

    if (Character.isDigit(ch)){
      String result;
      int value= 0;
      do{
        value = 10*value + Character.digit(ch,10);
        readChar();
      } while(Character.isDigit(ch));
      result = String.valueOf(value);
      if (this.ch == 46) {
        StringBuilder sb = new StringBuilder();
        readChar();
        do{
          sb.append(this.ch);
          readChar();
        } while(Character.isDigit(ch));
        result = result + "." + sb.toString();
      }

      if(result.contains(".")) {
        return new Token(Tag.REAL, result);
      }
      return new Token(Tag.INTEGER, result);
    }

    if (Character.isLetter(this.ch)) {
      StringBuilder sb = new StringBuilder();
      do{
        sb.append(ch);
        readChar();
        if(this.ch == '_') {
          sb.append(ch);
          readChar();
        }
      } while(Character.isLetterOrDigit(ch));
      String lex = sb.toString();
      if (isLexemeExists(lex)) {
        return this.table.getToken(lex);
      }
      Token token = createToken(lex);
      this.table.reserveWord(token, lex);
      return token;
    }

    if (this.ch == '\uFFFF') {
      this.end = true;
      return new Token(Tag.ID, "EOF");
    }
    Token token = createToken(String.valueOf(this.ch));
    this.ch = ' ';
    return token;
  }

  private Token createToken(String lex) {
   return new Token(Tag.ID, lex);
  }

  private boolean isLexemeExists(String processedLex) {
    return table.getSymbolTable().containsKey(processedLex);
  }

  public boolean isEnd() {
    return !end;
  }
}
