package prob.AnalyseurSynthaxique;

import prob.AnalyseurLexical.AnalLex;
import prob.Reader;
import prob.AnalyseurLexical.Terminal;
import prob.Writer;

import java.util.Objects;

/** Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

  private final AnalLex analLex;
  private Terminal nextTerminal;

/** Constructeur de DescenteRecursive :
      - recoit en argument le nom du fichier contenant l'expression a analyser
      - pour l'initalisation d'attribut(s)
 */
public DescenteRecursive(String s) {
    analLex = new AnalLex(s);
}


/** AnalSynt() effectue l'analyse syntaxique et construit l'AST.
 *    Elle retourne une reference sur la racine de l'AST construit
 */
public ElemAST AnalSynt() throws Exception {
  nextTerminal = analLex.prochainTerminal();
  return E();
}


public FeuilleAST T() throws Exception {
  if (!Objects.equals(nextTerminal.getChaine(), "+")) {
    FeuilleAST n1 = new FeuilleAST(nextTerminal.getChaine());
    nextTerminal = analLex.prochainTerminal();
    return n1;
  } else {
    ErreurSynt(nextTerminal.getChaine());
    return null;
  }
}

public ElemAST E() throws Exception {
  FeuilleAST n1 = T();

  if (Objects.equals(nextTerminal.getChaine(), "+")) {
    nextTerminal = analLex.prochainTerminal();
    ElemAST n2 = E();
    return new NoeudAST("+", n1, n2);
  }
  return n1;
}

/** ErreurSynt() envoie un message d'erreur syntaxique
 */
public void ErreurSynt(String err) throws Exception {
  throw new Exception("Erreur syntaxique: " + err);
}


//Methode principale a lancer pour tester l'analyseur syntaxique
public static void main(String[] args) {
  String toWriteLect = "";
  String toWriteEval = "";

  System.out.println("Debut d'analyse syntaxique");
  if (args.length == 0){
    args = new String [2];
    args[0] = "src/file/ExpArith.txt";
    args[1] = "src/file/ResultatSyntaxique.txt";
  }

  Reader r = new Reader(args[0]);  // Read the file passed in as an argument
  DescenteRecursive dr = new DescenteRecursive(r.toString());

  try {
    ElemAST RacineAST = dr.AnalSynt();
    toWriteLect += "Lecture de l'AST trouve : " + RacineAST.LectAST() + "\n";
    System.out.println(toWriteLect);
    toWriteEval += "Evaluation de l'AST trouve : " + RacineAST.EvalAST() + "\n";
    System.out.println(toWriteEval);
    new Writer(args[1],toWriteLect+toWriteEval);

  } catch (Exception e) {
    System.out.println(e);
    e.printStackTrace();
    System.exit(51);
  }

  System.out.println("Analyse syntaxique terminee");
}
}

