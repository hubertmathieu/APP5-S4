package prob.AnalyseurSynthaxique;

/** Classe Abstraite dont heriteront les classes FeuilleAST et NoeudAST
 */
public abstract class ElemAST {

  
  /** Evaluation d'AST
   */
  public abstract int EvalAST();


  /** Lecture d'AST
   */
  public abstract String LectAST();

  public abstract String Postfix();


  /** ErreurEvalAST() envoie un message d'erreur lors de la construction d'AST
   */
  public void ErreurEvalAST(String err) throws Exception {
    throw new Exception("Erreur syntaxique: " + err);
  }
}
