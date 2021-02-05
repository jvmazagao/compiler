package symbolTable;

import tag.Tag;
import token.Token;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Objects;

/** Instance of HashTable **/
public class SymbolTable {
  private final Hashtable<String, Token> symbolTable;

  public SymbolTable() {
    this.symbolTable = initializeReservedWords();
  }

  private Hashtable<String, Token> initializeReservedWords() {
    return new Hashtable<>() {{
      put("init",new Token(Tag.INIT, "init"));
      put("stop", new Token(Tag.STOP, "stop"));
      put( "is", new Token(Tag.IS, "is"));
      put("integer", new Token(Tag.INTEGER, "integer"));
      put("real", new Token(Tag.REAL, "real"));
      put("if",new Token(Tag.IF, "if"));
      put("else", new Token(Tag.ELSE, "else"));
      put("begin", new Token(Tag.BEGIN, "begin"));
      put("do", new Token(Tag.DO, "do"));
      put("while", new Token(Tag.WHILE, "while"));
      put("end", new Token(Tag.END, "end"));
      put("read", new Token(Tag.READ, "read"));
      put("write", new Token(Tag.WRITE, "write"));
      put("string", new Token(Tag.STRING, "string"));
      put("and", new Token(Tag.AND, "and"));
      put("or", new Token(Tag.OR, "or"));

    }};
  }

  public Hashtable<String, Token> getSymbolTable() {
    return symbolTable;
  }

  public void reserveWord(Token word, String identity) {
    this.symbolTable.put(identity, word);
  }

  public Token getToken(String lex) {
    return this.symbolTable.get(lex);
  }
}
