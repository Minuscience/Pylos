import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Rules {
	
	public static Text getTitrePrincipal()
	{
        Text titreRules = new Text("Déroulement d'une partie");
        titreRules.setFont(Font.font("arial", FontWeight.BOLD, 22));
        return titreRules;
	}
	
	public static Text getTitre()
	{
		Text titre1 = new Text("Début de partie\n");
		titre1.setFont(Font.font("arial", FontPosture.ITALIC, 18));
		return titre1;
	}
	
	public static Text getTitre1()
	{
		Text titre1 = new Text("Empilement sur un carré.\n");
		titre1.setFont(Font.font("arial", FontPosture.ITALIC, 18));
		return titre1;
	}
	
	public static Text getTitre2()
	{
		Text titre2 = new Text("Carré à sa couleur.\n");
		titre2.setFont(Font.font("arial", FontPosture.ITALIC, 18));
		return titre2;
	}
	
	public static Text getTitre3()
	{
		Text titre3 = new Text("Empilement et carré\n");
		titre3.setFont(Font.font("arial", FontPosture.ITALIC, 18));
		return titre3;
	}
	
	public static VBox getRules()
	{
		Text textRules = new Text();
		textRules.setText("Chaque joueur à son tour place une bille de sa réserve sur une case de son choix.\n");
			
		Text textRules1 = new Text();
		textRules1.setText("Quand sur le plateau, ou aux niveaux supérieurs, un ou plusieurs carrés de billes sont formés, un joueur peut choisir d’y\n"
				+ "empiler une de ses billes ;\n"
				+"à son tour de jeu, il a alors le choix entre :\n"
				+"	- placer u bille de sa réserve sur le plateau.\n"
				+"	- placer une bille de sa réserve sur un des carrés de billes.\n"
				+"	- déplacer une de ses billes déjà présentes sur le plateau pour la poser sur un carré de billes, uniquement si ce\n"
				+"	déplacement monte sa bille d’un ou plusieurs niveaux. Ce coup permet d’économiser une bille de sa réserve.\n"
				+"Une bille présente sur le plateau ne peut pas être déplacée si elle supporte déjà une autre bille.\n");
		
		Text textRules2 = new Text();
		textRules2.setText("Un joueur qui réalise un carré de billes à sa couleur, reprend tout de suite soit une, soit deux de ses billes\n"
				+"présentes sur le plateau pour les remettre dans sa réserve; il peut reprendre toute bille lui appartenant, à\n"
				+"n’importe quel niveau - y compris celle qu’il vient de poser - mais à l’exclusion des billes supportant d’autres billes.\n"
				+"(Réaliser plusieurs carrés de billes à sa couleur en posant une bille n’autorise le retrait que d’une ou deux de ses billes)\n");
		
		Text textRules3 = new Text();
		textRules3.setText("Dans le même coup, un joueur a la possibilité de :\n"
					+"- monter une de ses billes se trouvant déja sur le plateau.\n"
					+"- réaliser ainsi un carré de billes de sa couleur.\n"
					+"- reprendre tout de suite soit une, soit deux de ses billes pour les remettre en réserve.");
		
		textRules.setFont(Font.font("arial", 18));
		textRules1.setFont(Font.font("arial", 18));
		textRules2.setFont(Font.font("arial", 18));
		textRules3.setFont(Font.font("arial", 18));
		
		
		VBox rules = new VBox();
		rules.getChildren().addAll(getTitrePrincipal(), getTitre(), textRules, getTitre1(), textRules1, getTitre2(), textRules2, getTitre3(), textRules3);
		rules.setSpacing(3.0);
		return rules;
	}
}
