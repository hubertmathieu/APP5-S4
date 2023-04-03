package prob.AnalyseurLexical;

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

  private final String _str;
  private int charIndex;
  private final int strLength;

  private enum State {
      INIT,
      FINAL1,
      FINAL2,
      UNDERSCORE,
  }

  /** Constructeur pour l'initialisation d'attribut(s)
   */
  public AnalLex(String s) {
    _str = s;
    charIndex = 1;
    strLength = _str.length();
  }


  /** resteTerminal() retourne :
        false  si tous les terminaux de l'expression arithmetique ont ete retournes
        true s'il reste encore au moins un terminal qui n'a pas ete retourne
   */
  public boolean resteTerminal() {
    return charIndex <= strLength;
  }


  /** prochainTerminal() retourne le prochain terminal
        Cette methode est une implementation d'un AEF
   */
  public Terminal prochainTerminal() throws Exception {
    State currState = State.INIT;
    State nextState = State.INIT;
    StringBuilder chaine = new StringBuilder();

    while(resteTerminal()) {
      String c = String.valueOf(_str.charAt(charIndex-1));  // Get the next char in the string
      switch (currState) {
        case INIT -> {
          if (c.matches("[A-Z]")) {
            nextState = State.FINAL2;
          } else if (c.matches("[0-9]")) {
            nextState = State.FINAL1;
          } else if (c.matches("[+\\-*/()]")) {
            charIndex++;
            return new Terminal(c);
          } else {
            ErreurLex(charIndex, "Le caractère n'est pas supporté par le compilateur (caractère attendu : lettre maj, chiffre, opérateur ou parenthèse). ", c.charAt(0));
          }
        }
        case FINAL1 -> {
          if (c.matches("[0-9]")) {} else {
            return new Terminal(chaine.toString());
          }
        }
        case FINAL2 -> {
          if (c.matches("[a-zA-Z]")) {}
          else if (c.matches("_")) {
            nextState = State.UNDERSCORE;
          } else {
            return new Terminal(chaine.toString());
          }
        }
        case UNDERSCORE -> {
          if (c.matches("[a-zA-Z]")) {
            nextState = State.FINAL2;
          } else {
            ErreurLex(charIndex, "Une lettre (maj ou min) est attendu comme prochain caractère",  c.charAt(0));
          }
        }
      }
      chaine.append(c);
      currState = nextState;
      charIndex++;
    }
    return new Terminal(chaine.toString());
  }

 
  /** ErreurLex() envoie un message d'erreur lexicale
  */
  public void ErreurLex(int index, String explication, char cInvalid) throws Exception {
     throw new Exception("\nErreur lexical à l'index " + index +
             ", caractère interdit : " + cInvalid +
              "\nChaine avant l'erreur " + _str.substring(0, index - 1) +
             "\n" + explication);
  }

  public int getCharIndex() {
    return charIndex;
  }

}
