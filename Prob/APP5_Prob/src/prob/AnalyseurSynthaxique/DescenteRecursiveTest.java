package prob.AnalyseurSynthaxique;

import prob.Reader;
import prob.Writer;

class DescenteRecursiveTest {

    //Methode principale a lancer pour tester l'analyseur syntaxique
    public static void main(String[] args) {
        String toWriteLect = "";
        String toWriteEval = "";

        System.out.println("Debut d'analyse syntaxique");
        if (args.length == 0){
            args = new String [2];
            args[0] = "src/prob/file/ExpArith.txt";
            args[1] = "src/prob/file/ResultatSyntaxique.txt";
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