package prob.AnalyseurSynthaxique;

import prob.AnalyseurLexical.AnalLex;
import prob.AnalyseurLexical.Terminal;
import prob.AnalyseurLexical.Terminal.TerminalType;

/** Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {
    private final AnalLex analLex;
    private Terminal nextTerminal;
    private final String _str;

    /** Constructeur de DescenteRecursive :
          - recoit en argument le nom du fichier contenant l'expression a analyser
          - pour l'initalisation d'attribut(s)
     */
    public DescenteRecursive(String s) {
        analLex = new AnalLex(s);
        _str = s;
    }

    /** AnalSynt() effectue l'analyse syntaxique et construit l'AST.
     *    Elle retourne une reference sur la racine de l'AST construit
     */
    public ElemAST AnalSynt() throws Exception {
      nextTerminal = analLex.prochainTerminal();
      return E();
    }


    public ElemAST T() throws Exception {
        ElemAST n1 = F();

        switch (nextTerminal.getType()) {
            case MULT -> {
                nextTerminal = analLex.prochainTerminal();
                ElemAST n2 = T();
                return new NoeudAST("*", n1, n2);
            }
            case DIV -> {
                nextTerminal = analLex.prochainTerminal();
                ElemAST n2 = T();
                return new NoeudAST("/", n1, n2);
            }
        }

        return n1;
    }

    public ElemAST F() throws Exception {

        ElemAST n1 = null;

        switch (nextTerminal.getType()) {
            case ID, NB -> {
                n1 =  new FeuilleAST(nextTerminal.getChaine());
            }
            case O_PA -> {
                nextTerminal = analLex.prochainTerminal();
                n1 =  E();
                if (nextTerminal.getType() != TerminalType.C_PA) {
                    ErreurSynt(nextTerminal, "Paranthèse fermante");
                }
            }
            default -> {
                 ErreurSynt(nextTerminal, "ID ou un NB ou une parenthèse ouvrante");
            }
        }

        nextTerminal = analLex.prochainTerminal();
        return n1;
    }

    public ElemAST E() throws Exception {
        ElemAST n1 = T();

        switch (nextTerminal.getType()) {
            case ADD -> {
                nextTerminal = analLex.prochainTerminal();
                ElemAST n2 = E();
                return new NoeudAST("+", n1, n2);
            }
            case SUB -> {
                nextTerminal = analLex.prochainTerminal();
                ElemAST n2 = E();
                return new NoeudAST("-", n1, n2);
            }
        }

        return n1;
    }

    /** ErreurSynt() envoie un message d'erreur syntaxique
     */
    public void ErreurSynt(Terminal ulInvalid, String explication) throws Exception {

        throw new Exception("\nErreur synthaxique à l'index " + (analLex.getCharIndex() - 1 - ulInvalid.getChaine().length()) +
                "\nUnité lexical en erreur : " + ulInvalid.getChaine() +
                "\nChaine avant l'erreur : " + _str.substring(0, analLex.getCharIndex() - 1 - ulInvalid.getChaine().length()) +
                "\n" + "Unité lexical permise : " + explication);
    }
}

