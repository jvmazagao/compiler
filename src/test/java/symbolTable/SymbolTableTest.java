package symbolTable;


import org.junit.jupiter.api.Test;
import tag.Tag;
import token.Token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SymbolTableTest {

  @Test
  public void symbolTableReservedTokens() {
    SymbolTable table = new SymbolTable();
    assertTrue(table.getSymbolTable().containsKey("init"));
    assertTrue(table.getSymbolTable().containsKey("stop"));
    assertTrue(table.getSymbolTable().containsKey("is"));
    assertTrue(table.getSymbolTable().containsKey("integer"));
    assertTrue(table.getSymbolTable().containsKey("if"));
    assertTrue(table.getSymbolTable().containsKey("else"));
    assertTrue(table.getSymbolTable().containsKey("begin"));
    assertTrue(table.getSymbolTable().containsKey("do"));
    assertTrue(table.getSymbolTable().containsKey("while"));
    assertTrue(table.getSymbolTable().containsKey("end"));
    assertTrue(table.getSymbolTable().containsKey("read"));
    assertTrue(table.getSymbolTable().containsKey("string"));
    assertTrue(table.getSymbolTable().containsKey("real"));
    assertTrue(table.getSymbolTable().containsKey("write"));
    assertTrue(table.getSymbolTable().containsKey("and"));
    assertTrue(table.getSymbolTable().containsKey("or"));


    assertEquals(table.getSymbolTable().get("init"), new Token(Tag.INIT, "init"));
    assertEquals(table.getSymbolTable().get("stop"), new Token(Tag.STOP, "stop"));
    assertEquals(table.getSymbolTable().get("is"), new Token(Tag.IS, "is"));
    assertEquals(table.getSymbolTable().get("integer"), new Token(Tag.INTEGER, "integer"));
    assertEquals(table.getSymbolTable().get("if"), new Token(Tag.IF, "if"));
    assertEquals(table.getSymbolTable().get("else"), new Token(Tag.ELSE, "else"));
    assertEquals(table.getSymbolTable().get("begin"), new Token(Tag.BEGIN, "begin"));
    assertEquals(table.getSymbolTable().get("do"), new Token(Tag.DO, "do"));
    assertEquals(table.getSymbolTable().get("while"), new Token(Tag.WHILE, "while"));
    assertEquals(table.getSymbolTable().get("end"), new Token(Tag.END, "end"));
    assertEquals(table.getSymbolTable().get("read"), new Token(Tag.READ, "read"));
    assertEquals(table.getSymbolTable().get("string"), new Token(Tag.STRING, "string"));
    assertEquals(table.getSymbolTable().get("real"), new Token(Tag.REAL, "real"));
    assertEquals(table.getSymbolTable().get("write"), new Token(Tag.WRITE, "write"));
    assertEquals(table.getSymbolTable().get("and"), new Token(Tag.AND, "and"));
    assertEquals(table.getSymbolTable().get("or"), new Token(Tag.OR, "or"));
  }

  @Test
  public void reserveWordTest() {
    SymbolTable table = new SymbolTable();
    table.reserveWord(new Token(Tag.ID, "alexa"), "alexa");
    assertTrue(table.getSymbolTable().containsKey("alexa"));
    assertEquals(table.getSymbolTable().get("alexa"), new Token(Tag.ID, "alexa"));
  }

  @Test
  public void getTokeByLexTest() {
    SymbolTable table = new SymbolTable();
    assertEquals(table.getToken("init"), new Token(Tag.INIT, "init"));
  }
}
