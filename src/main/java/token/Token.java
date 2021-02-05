package token;

import tag.Tag;

import java.util.Objects;

public class Token {

  private Tag tag;
  private String lex;
  private long line;

  public Token(Tag tag, String lex) {
    this.tag = tag;
    this.lex = lex != null ? lex : "";
    this.line = 0;
  }

  public Tag getTag() {
    return tag;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
  }

  public String getLex() {
    return lex;
  }

  public void setLex(String lex) {
    this.lex = lex;
  }

  public long getLine() {
    return line;
  }

  public void setLine(long line) {
    this.line = line;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Token))
      return false;
    Token token = (Token) o;
    return getLine() == token.getLine() && getTag() == token.getTag() && getLex().equals(token.getLex());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTag(), getLex(), getLine());
  }
}
