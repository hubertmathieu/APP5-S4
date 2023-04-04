package prob.AnalyseurSynthaxique;

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

    private final String s;
    private int value = 0;

    /**Constructeur pour l'initialisation d'attribut(s)
    */
    public FeuilleAST(String s) {
      this.s = s;

      if (String.valueOf(s.charAt(0)).matches("[0-9]")) {
          value = Integer.parseInt(s);
      }
    }


    /**
     * Evaluation de feuille d'AST
    */
    public int EvalAST() {
        return value;
    }


    /**
     * Lecture de chaine de caracteres correspondant a la feuille d'AST
    */
    public String LectAST( ) {
        return s;
    }

    /**
     * Expression postfix
     */
    @Override
    public String Postfix() {
        return s;
    }

    @Override
    public String toString() {
        return LectAST();
    }
}
