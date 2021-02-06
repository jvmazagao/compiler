package token;

import tag.Tag;

import java.util.Objects;

public class Token {

  private final Tag tag;
  private final String lex;
  private final long line;

  public Token(Tag tag, String lex) {
    this.tag = tag;
    this.lex = lex != null ? lex : "";
    this.line = 0;
  }

  public Tag getTag() {
    return tag;
  }

  public String getLex() {
    return lex;
  }

  public long getLine() {
    return line;
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
