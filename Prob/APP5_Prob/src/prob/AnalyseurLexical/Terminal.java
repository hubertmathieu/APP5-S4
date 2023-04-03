package prob.AnalyseurLexical; /** @author Ahmed Khoumsi */

/** Cette classe identifie les terminaux reconnus et retournes par
 *  l'analyseur lexical
 */
public class Terminal {

    private final String chaine;

    private TerminalType type;

    public enum TerminalType {
      MULT, DIV, SUB, ADD, NB, ID, O_PA, C_PA, END
    }

    /** Un ou deux constructeurs (ou plus, si vous voulez)
      *   pour l'initalisation d'attributs
     */
    public Terminal(String chaine) {
        this.chaine = chaine;

        if (chaine.length() > 0) {
            switch (chaine) {
                case "+" -> {
                    type = TerminalType.ADD;
                }
                case "-" -> {
                    type = TerminalType.SUB;
                }
                case "*" -> {
                    type = TerminalType.MULT;
                }
                case "/" -> {
                    type = TerminalType.DIV;
                }
                case "(" -> {
                    type = TerminalType.O_PA;
                }
                case ")" -> {
                    type = TerminalType.C_PA;
                }
            }

            char firstChar = chaine.charAt(0);

            if (String.valueOf(firstChar).matches("[A-Z]")) {
                type = TerminalType.ID;
            }
            if (String.valueOf(firstChar).matches("[0-9]")) {
                type = TerminalType.NB;
            }
        } else {
            type = TerminalType.END;
        }
    }

    public String getChaine() {
      return chaine;
    }

    public TerminalType getType() {
        return type;
    }

    @Override
    public String toString() {
        switch (type) {
            case ADD -> {
                return "Chaine: " + getChaine() + "    Type: ADDITION";
            }
            case SUB -> {
                return "Chaine: " + getChaine() + "    Type: SOUSTRACTION";
            }
            case MULT -> {
                return "Chaine: " + getChaine() + "    Type: MULTIPLICATION";
            }
            case DIV -> {
                return "Chaine: " + getChaine() + "    Type: DIVISION";
            }
            case O_PA -> {
                return "Chaine: " + getChaine() + "    Type: PARENTHÈSE OUVRANTE";
            }
            case C_PA -> {
                return "Chaine: " + getChaine() + "    Type: PARENTHÈSE FERMANTE";
            }
            case ID -> {
                return "Chaine: " + getChaine() + "    Type: IDENTIFICATEUR";
            }
            case NB -> {
                return "Chaine: " + getChaine() + "    Type: NOMBRE";
            }
            default -> {return "";}
        }
    }
}
