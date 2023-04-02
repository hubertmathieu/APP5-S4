package prob.AnalyseurLexical; /** @author Ahmed Khoumsi */

/** Cette classe identifie les terminaux reconnus et retournes par
 *  l'analyseur lexical
 */
public class Terminal {

  private final String chaine;

  private boolean isOperande = false;
  private boolean isVariable = false;
  private int value = 0;

  private boolean isOperator = false;
  private OperatorType operator;

  public enum OperatorType {
      MULT, DIV, SUB, ADD
  }


/** Un ou deux constructeurs (ou plus, si vous voulez)
  *   pour l'initalisation d'attributs 
 */	
    public Terminal(String chaine) {

        this.chaine = chaine;

        switch (chaine) {
            case "+" -> {
                isOperator = true;
                operator = OperatorType.ADD;
            }
            case "-" -> {
                isOperator = true;
                operator = OperatorType.SUB;
            }
            case "*" -> {
                isOperator = true;
                operator = OperatorType.MULT;
            }
            case "/" -> {
                isOperator = true;
                operator = OperatorType.DIV;
            }
        }

        char firstchar = chaine.charAt(0);

        if (String.valueOf(firstchar).matches("[A-Z]")) {
            isOperande = true;
            isVariable = true;
        }
        if (String.valueOf(firstchar).matches("[0-9]")) {
            isOperande = true;
            value = Integer.parseInt(chaine);
        }
    }

    public String getChaine() {
      return chaine;
    }

}
