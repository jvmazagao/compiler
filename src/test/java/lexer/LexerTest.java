package lexer;

import org.junit.jupiter.api.Test;
import symbolTable.SymbolTable;
import tag.Tag;
import token.Token;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexerTest {
  @Test
  public void reservedWords() throws IOException {
    SymbolTable table = new SymbolTable();
    Lexer lexer = new Lexer(formatFile("reservedWord"), table);
    Token list = lexer.scan();
    assertEquals(list,new Token(Tag.INIT, "init"));
    assertEquals(table.getSymbolTable().size(), 16);
  }

  @Test
  public void scanAllCharsAndReturnAllReservedTokens() throws IOException {
    SymbolTable table = new SymbolTable();
    Lexer lexer = new Lexer(formatFile("reservedWord"), table);
    List<Token > list = new ArrayList<>(Collections.emptyList());
    while (lexer.isEnd()) {
      list.add(lexer.scan());
    }

    assertEquals(list.get(0),new Token(Tag.INIT, "init"));
    assertEquals(list.get(1), new Token(Tag.STOP, "stop"));
    assertEquals(list.get(2), new Token(Tag.IS, "is"));
    assertEquals(list.get(3), new Token(Tag.INTEGER, "integer"));
    assertEquals(list.get(4), new Token(Tag.REAL, "real"));
    assertEquals(list.get(5),new Token(Tag.IF, "if"));
    assertEquals(list.get(6), new Token(Tag.ELSE, "else"));
    assertEquals(list.get(7), new Token(Tag.BEGIN, "begin"));
    assertEquals(list.get(8), new Token(Tag.DO, "do"));
    assertEquals(list.get(9), new Token(Tag.WHILE, "while"));
    assertEquals(list.get(10), new Token(Tag.END, "end"));
    assertEquals(list.get(11), new Token(Tag.READ, "read"));
    assertEquals(list.get(12), new Token(Tag.WRITE, "write"));
    assertEquals(list.get(13), new Token(Tag.STRING, "string"));
    assertEquals(list.size(), 15);
  }

  @Test
  public void scanStringAndReturnTokenIdAndInsertInTable() throws IOException {
    SymbolTable table = new SymbolTable();
    Lexer lexer = new Lexer(formatFile("stringWord"), table);
    List<Token > list = new ArrayList<>(Collections.emptyList());
    while (lexer.isEnd()) {
      list.add(lexer.scan());
    }
    assertEquals(list.get(0), new Token(Tag.STRING, "\"Hello, world\""));
    assertEquals(list.get(1), new Token(Tag.ID, ";"));
  }

  @Test
  public void scanVariablesAndReturnTokens() throws IOException {
    SymbolTable table = new SymbolTable();
    Lexer lexer = new Lexer(formatFile("variables"), table);
    List<Token > list = new ArrayList<>(Collections.emptyList());
    while (lexer.isEnd()) {
      list.add(lexer.scan());
    }
    assertEquals(list.get(0),new Token(Tag.INIT, "init"));
    assertEquals(list.get(1),new Token(Tag.ID, "a"));
    assertEquals(list.get(2),new Token(Tag.ID, ","));
    assertEquals(list.get(3),new Token(Tag.ID, "b"));
    assertEquals(list.get(4),new Token(Tag.ID, ","));
    assertEquals(list.get(5),new Token(Tag.ID, "$"));
    assertEquals(list.get(6),new Token(Tag.ID, "c"));
    assertEquals(list.get(7),new Token(Tag.ID, ","));
    assertEquals(list.get(8),new Token(Tag.IS, "is"));
    assertEquals(list.get(9),new Token(Tag.INTEGER, "integer"));
    assertEquals(list.get(10),new Token(Tag.ID, ";"));
    assertEquals(list.get(11),new Token(Tag.ID, "result"));
    assertEquals(list.get(12),new Token(Tag.IS, "is"));
    assertEquals(list.get(13),new Token(Tag.REAL, "real"));
    assertEquals(list.get(14),new Token(Tag.ID, ";"));
    assertEquals(list.get(15),new Token(Tag.STOP, "stop"));
  }

  @Test
  public void shouldProcessVariablesAttributions() throws IOException {
    SymbolTable table = new SymbolTable();
    Lexer lexer = new Lexer(formatFile("attribution"), table);
    List<Token > list = new ArrayList<>(Collections.emptyList());
    while (lexer.isEnd()) {
      list.add(lexer.scan());
    }

    assertEquals(list.get(0),new Token(Tag.ID, "a"));
    assertEquals(list.get(1),new Token(Tag.EQ, ":="));
    assertEquals(list.get(2),new Token(Tag.STRING, "\"a\""));
    assertEquals(list.get(3),new Token(Tag.ID, "b"));
    assertEquals(list.get(4),new Token(Tag.EQ, ":="));
    assertEquals(list.get(5),new Token(Tag.INTEGER, "100"));
    assertEquals(list.get(6),new Token(Tag.ID, "c"));
    assertEquals(list.get(7),new Token(Tag.EQ, ":="));
    assertEquals(list.get(8),new Token(Tag.REAL, "0.1"));
  }

  @Test
  public void shouldProcessVariablesAttributions2() throws IOException {
    SymbolTable table = new SymbolTable();
    Lexer lexer = new Lexer(formatFile("attribution2"), table);
    List<Token > list = new ArrayList<>(Collections.emptyList());
    while (lexer.isEnd()) {
      list.add(lexer.scan());
    }

    assertEquals(list.get(0),new Token(Tag.ID, "c"));
    assertEquals(list.get(1),new Token(Tag.EQ, ":="));
    assertEquals(list.get(2),new Token(Tag.REAL, "0.00000000001"));
  }

  @Test
  public void shouldProcessFunctions() throws IOException {
    SymbolTable table = new SymbolTable();
    Lexer lexer = new Lexer(formatFile("functions"), table);
    List<Token > list = new ArrayList<>(Collections.emptyList());
    while (lexer.isEnd()) {
      list.add(lexer.scan());
    }

    assertEquals(list.get(0),new Token(Tag.READ, "read"));
    assertEquals(list.get(1),new Token(Tag.ID, "("));
    assertEquals(list.get(2),new Token(Tag.ID, "a"));
    assertEquals(list.get(3),new Token(Tag.ID, ")"));
    assertEquals(list.get(4),new Token(Tag.READ, "read"));
    assertEquals(list.get(5),new Token(Tag.ID, "("));
    assertEquals(list.get(6),new Token(Tag.STRING, "\"a\""));
    assertEquals(list.get(7),new Token(Tag.ID, ")"));
  }

  @Test
  public void shouldProcessExpressions() throws IOException {
    SymbolTable table = new SymbolTable();
    Lexer lexer = new Lexer(formatFile("expressions"), table);
    List<Token > list = new ArrayList<>(Collections.emptyList());
    while (lexer.isEnd()) {
      list.add(lexer.scan());
    }

    assertEquals(list.get(0),new Token(Tag.ID, "a"));
    assertEquals(list.get(1),new Token(Tag.EQ, ":="));
    assertEquals(list.get(2),new Token(Tag.INTEGER, "1"));
    assertEquals(list.get(3),new Token(Tag.ID, "+"));
    assertEquals(list.get(4),new Token(Tag.INTEGER, "1"));
    assertEquals(list.get(5),new Token(Tag.ID, "b"));
    assertEquals(list.get(6),new Token(Tag.EQ, ":="));
    assertEquals(list.get(7),new Token(Tag.INTEGER, "1"));
    assertEquals(list.get(8),new Token(Tag.ID, "/"));
    assertEquals(list.get(9),new Token(Tag.INTEGER, "1"));
    assertEquals(list.get(10),new Token(Tag.ID, "c"));
    assertEquals(list.get(11),new Token(Tag.EQ, ":="));
    assertEquals(list.get(12),new Token(Tag.INTEGER, "100"));
    assertEquals(list.get(13),new Token(Tag.ID, "*"));
    assertEquals(list.get(14),new Token(Tag.INTEGER, "30"));
    assertEquals(list.get(15),new Token(Tag.ID, "d"));
    assertEquals(list.get(16),new Token(Tag.EQ, ":="));
    assertEquals(list.get(17),new Token(Tag.REAL, "0.01"));
    assertEquals(list.get(18),new Token(Tag.ID, "-"));
    assertEquals(list.get(19),new Token(Tag.INTEGER, "1"));
  }

  @Test
  public void shouldNotProcessComments() throws IOException {
    SymbolTable table = new SymbolTable();
    Lexer lexer = new Lexer(formatFile("comments"), table);
    List<Token> list = new ArrayList<>(Collections.emptyList());
    while (lexer.isEnd()) {
      list.add(lexer.scan());
    }
    assertEquals(list.get(0), new Token(Tag.ID, "EOF"));
  }

  @Test
  public void shouldProcessConditionals() throws IOException {
    SymbolTable table = new SymbolTable();
    Lexer lexer = new Lexer(formatFile("conditional"), table);
    List<Token> list = new ArrayList<>(Collections.emptyList());
    while (lexer.isEnd()) {
      list.add(lexer.scan());
    }

    assertEquals(list.get(0), new Token(Tag.IF, "if"));
    assertEquals(list.get(1), new Token(Tag.ID, "("));
    assertEquals(list.get(2), new Token(Tag.ID, "a"));
    assertEquals(list.get(3), new Token(Tag.GT, ">"));
    assertEquals(list.get(4), new Token(Tag.ID, "b"));
    assertEquals(list.get(5), new Token(Tag.AND, "and"));
    assertEquals(list.get(6), new Token(Tag.ID, "a"));
    assertEquals(list.get(7), new Token(Tag.GT, ">"));
    assertEquals(list.get(8), new Token(Tag.ID, "c"));
    assertEquals(list.get(9), new Token(Tag.ID, ")"));
    assertEquals(list.get(10), new Token(Tag.ID, "maior"));
    assertEquals(list.get(11), new Token(Tag.EQ, ":="));
    assertEquals(list.get(12), new Token(Tag.ID, "a"));
    assertEquals(list.get(13), new Token(Tag.ID, ";"));
    assertEquals(list.get(14), new Token(Tag.ELSE, "else"));
    assertEquals(list.get(15), new Token(Tag.IF, "if"));
    assertEquals(list.get(16), new Token(Tag.ID, "("));
    assertEquals(list.get(17), new Token(Tag.ID, "b"));
    assertEquals(list.get(18), new Token(Tag.GT, ">"));
    assertEquals(list.get(19), new Token(Tag.ID, "c"));
    assertEquals(list.get(20), new Token(Tag.ID, ")"));
    assertEquals(list.get(21), new Token(Tag.ID, "maior"));
    assertEquals(list.get(22), new Token(Tag.EQ, ":="));
    assertEquals(list.get(23), new Token(Tag.ID, "b"));
    assertEquals(list.get(24), new Token(Tag.ID, ";"));
    assertEquals(list.get(25), new Token(Tag.ELSE, "else"));
    assertEquals(list.get(26), new Token(Tag.ID, "maior"));
    assertEquals(list.get(27), new Token(Tag.EQ, ":="));
    assertEquals(list.get(28), new Token(Tag.ID, "c"));
    assertEquals(list.get(29), new Token(Tag.ID, ";"));
    assertEquals(list.get(30), new Token(Tag.ID, "EOF"));
  }

  @Test
  public void shouldProcessConditionals2() throws IOException {
    SymbolTable table = new SymbolTable();
    Lexer lexer = new Lexer(formatFile("conditional2"), table);
    List<Token> list = new ArrayList<>(Collections.emptyList());
    while (lexer.isEnd()) {
      list.add(lexer.scan());
    }

    assertEquals(list.get(0), new Token(Tag.IF, "if"));
    assertEquals(list.get(1), new Token(Tag.ID, "("));
    assertEquals(list.get(2), new Token(Tag.ID, "a"));
    assertEquals(list.get(3), new Token(Tag.GTE, ">="));
    assertEquals(list.get(4), new Token(Tag.ID, "b"));
    assertEquals(list.get(5), new Token(Tag.AND, "and"));
    assertEquals(list.get(6), new Token(Tag.ID, "a"));
    assertEquals(list.get(7), new Token(Tag.LT, "<"));
    assertEquals(list.get(8), new Token(Tag.ID, "c"));
    assertEquals(list.get(9), new Token(Tag.ID, ")"));
    assertEquals(list.get(10), new Token(Tag.ID, "maior"));
    assertEquals(list.get(11), new Token(Tag.EQ, ":="));
    assertEquals(list.get(12), new Token(Tag.ID, "a"));
    assertEquals(list.get(13), new Token(Tag.ID, ";"));
    assertEquals(list.get(14), new Token(Tag.ELSE, "else"));
    assertEquals(list.get(15), new Token(Tag.IF, "if"));
    assertEquals(list.get(16), new Token(Tag.ID, "("));
    assertEquals(list.get(17), new Token(Tag.ID, "b"));
    assertEquals(list.get(18), new Token(Tag.LTE, "<="));
    assertEquals(list.get(19), new Token(Tag.ID, "c"));
    assertEquals(list.get(20), new Token(Tag.OR, "or"));
    assertEquals(list.get(21), new Token(Tag.ID, "b"));
    assertEquals(list.get(22), new Token(Tag.NE, "<>"));
    assertEquals(list.get(23), new Token(Tag.ID, "c"));
    assertEquals(list.get(24), new Token(Tag.ID, ")"));
    assertEquals(list.get(25), new Token(Tag.ID, "maior"));
    assertEquals(list.get(26), new Token(Tag.EQ, ":="));
    assertEquals(list.get(27), new Token(Tag.ID, "b"));
    assertEquals(list.get(28), new Token(Tag.ID, ";"));
    assertEquals(list.get(29), new Token(Tag.ELSE, "else"));
    assertEquals(list.get(30), new Token(Tag.ID, "maior"));
    assertEquals(list.get(31), new Token(Tag.EQ, ":="));
    assertEquals(list.get(32), new Token(Tag.ID, "c"));
    assertEquals(list.get(33), new Token(Tag.ID, ";"));
    assertEquals(list.get(34), new Token(Tag.ID, "EOF"));
  }

  private String formatFile(String file) {
    Path path = Paths.get("src/test/java/" + file);
    return path.toString();
  }
}
