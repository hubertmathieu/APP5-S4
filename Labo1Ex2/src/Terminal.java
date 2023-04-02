/** @author Ahmed Khoumsi */

/** Cette classe identifie les terminaux reconnus et retournes par
 *  l'analyseur lexical
 */
public class Terminal {


// Constantes et attributs
//  ....
  int operandeValue = 0;
  boolean isAddition = false;

  String chaine = "";


/** Un ou deux constructeurs (ou plus, si vous voulez)
  *   pour l'initalisation d'attributs 
 */

  public Terminal() {   // arguments possibles
    //
  }
  public Terminal(String chaine ) {   // arguments possibles
     //
    this.chaine = chaine;
  }

  public Terminal(boolean isAddition, String chaine ) {   // arguments possibles
    //
    this.isAddition = isAddition;
    this.chaine = chaine;
  }

}
