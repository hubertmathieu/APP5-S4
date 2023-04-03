package prob.AnalyseurLexical;

import prob.Reader;
import prob.Writer;

class AnalLexTest {

    //Methode principale a lancer pour tester l'analyseur lexical
    public static void main(String[] args) throws Exception {
        StringBuilder toWrite = new StringBuilder();
        System.out.println("Debut d'analyse lexicale");

        // Create file to write information about results and errors
        if (args.length == 0){
            args = new String [2];
            args[0] = "src/prob/file/ExpArith.txt";
            args[1] = "src/prob/file/ResultatLexical.txt";
        }

        Reader r = new Reader(args[0]);  // Read the file passed in as an argument
        AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

        while(lexical.resteTerminal()){
            Terminal t = lexical.prochainTerminal();
            toWrite.append(t.toString()).append("\n");  // toWrite contient le resultat
        }

        System.out.println(toWrite); 	// Ecriture de toWrite sur la console
        new Writer(args[1], toWrite.toString());    // Ecriture de toWrite dans fichier args[1]
        System.out.println("Fin d'analyse lexicale");
    }
}