package prob.AnalyseurSynthaxique;

/** @author Ahmed Khoumsi */

import java.util.Objects;

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  private final String op;
  private final ElemAST f1;
  private final ElemAST f2;

  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(String op, ElemAST f1, ElemAST f2) {
    this.op = op;
    this.f1 = f1;
    this.f2 = f2;
  }
 
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {
    if (Objects.equals(op, "+"))
      return f1.EvalAST() + f2.EvalAST();
    else
      return 0;
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
     return f1.LectAST() + op + f2.LectAST();
  }

}


