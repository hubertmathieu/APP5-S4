/** @author Ahmed Khoumsi */

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

    // Attributs
//  ...
    private int indexChaine = 0;
    private String chaineToRead = "";
    private State actualSate = State.Initial;
    private enum State {
        Initial,
        Final
    }


    /** Constructeur pour l'initialisation d'attribut(s)
     */
    public AnalLex(String chaineToRead) {  // arguments possibles
        //
        this.chaineToRead = chaineToRead;
    }


    /** resteTerminal() retourne :
     false  si tous les terminaux de l'expression arithmetique ont ete retournes
     true s'il reste encore au moins un terminal qui n'a pas ete retourne
     */
    public boolean resteTerminal( ) {
        return indexChaine < chaineToRead.length();
    }


    /** prochainTerminal() retourne le prochain terminal
     Cette methode est une implementation d'un AEF
     */
    public Terminal prochainTerminal( ) throws Error {
        State nextState = State.Initial;
        actualSate = nextState;

        String chaineTerminal = "";

        while(resteTerminal()) {
            char cToRead = chaineToRead.charAt(indexChaine);
            switch (actualSate) {
                case Initial:
                    if (cToRead == '1' || cToRead == '0') {
                        chaineTerminal += cToRead;
                        nextState = State.Final;
                    } else if (cToRead == '+') {
                        chaineTerminal += cToRead;
                        indexChaine++;
                        return new Terminal(true, chaineTerminal);
                    } else {
                        ErreurLex("Erreur (autre caractères)");
                    }
                    break;
                case Final:
                    if (cToRead == '1' || cToRead == '0') {
                        chaineTerminal += cToRead;
                        nextState = State.Final;
                    } else {
                        return new Terminal(chaineTerminal);
                    }
                    break;
            }
            actualSate = nextState;
            indexChaine++;
        }
        return new Terminal(chaineTerminal);
    }


    /** ErreurLex() envoie un message d'erreur lexicale
     */
    public void ErreurLex(String s) throws Error {
        System.out.println(s);
        throw new Error();
    }


    //Methode principale a lancer pour tester l'analyseur lexical
    public static void main(String[] args) {
        String toWrite = "";
        System.out.println("Debut d'analyse lexicale");
        if (args.length == 0){
            args = new String [2];
            args[0] = "src/ExpArith.txt";
            args[1] = "src/ResultatLexical.txt";
        }
        Reader r = new Reader(args[0]);

        AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

        // Execution de l'analyseur lexical
        Terminal t = null;
        while(lexical.resteTerminal()){
            t = lexical.prochainTerminal();
            toWrite +=t.chaine + "\n" ;  // toWrite contient le resultat
        }                   //    d'analyse lexicale
        System.out.println(toWrite);     // Ecriture de toWrite sur la console
        Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
        System.out.println("Fin d'analyse lexicale");
    }
}
