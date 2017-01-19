import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Help {
	
	public static Text getTitre()
	{
		Text titre = new Text("Comment jouer?");
		titre.setFont(Font.font("arial", FontWeight.BOLD, 18));
		return titre;
	}
	
	public static Text getTitre2()
	{
		Text titre = new Text("A quoi servent les boutons?");
		titre.setFont(Font.font("arial", FontWeight.BOLD, 18));
		return titre;
	}
	
	public static Text getHelp()
	{
		Text help = new Text();
		help.setText("- Avec votre souris, choississez l'emplacement de votre coup (plateau 2D), un pop-up apparaitra et vous demandera de valider votre coup.\n"
				+ "- Valider et une boule sera automatiquement créée dans le plateau 3D.");
		help.setFont(Font.font("arial", 14));
		return help;
	}
	
	public static VBox getHelpButton()
	{
		Text helpButton = new Text("- Le bouton \"New Game\" vous permet de réinitialiser votre partie, à noter qu'aucune partie n'est sauvegardée.");
		Text helpButton2 = new Text("- Le bouton \"Rules\" vous permet de consulter les règles du jeu");
		helpButton.setFont(Font.font("arial", 14));
		helpButton2.setFont(Font.font("arial", 14));
		VBox helpVbox = new VBox();
		helpVbox.getChildren().addAll(getTitre2(), helpButton, helpButton2);
		helpVbox.setSpacing(5.0);
		
		return helpVbox;
	}
	
	public static VBox finalVboxHelp()
	{
		VBox finalVbox = new VBox();
		finalVbox.getChildren().addAll(getTitre(), getHelp(), getHelpButton());
		finalVbox.setSpacing(10.0);;
		return finalVbox;
	}
}
