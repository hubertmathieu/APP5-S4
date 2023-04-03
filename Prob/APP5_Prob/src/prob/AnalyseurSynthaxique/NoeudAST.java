package prob.AnalyseurSynthaxique;

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
    else if (Objects.equals(op, "-"))
      return f1.EvalAST() - f2.EvalAST();
    else if (Objects.equals(op, "*"))
      return f1.EvalAST() * f2.EvalAST();
    else
      return f1.EvalAST() / f2.EvalAST();
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
     return f1.LectAST() + f2.LectAST() + op;
  }

  @Override
  public String toString() {
    return "\nNoeud: " + op + " -> L:(" + f1 + "), R:(" + f2 + ")";
  }


}


