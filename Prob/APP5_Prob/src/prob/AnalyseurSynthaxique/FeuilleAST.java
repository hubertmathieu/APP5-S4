package prob.AnalyseurSynthaxique;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

    private final String s;

/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST(String s) {
    this.s = s;
  }


  /** Evaluation de feuille d'AST
   */
  public int EvalAST() {
    return Integer.parseInt(s);
  }


 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    return s;
  }
}
