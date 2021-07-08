package fr.eni.ihm;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class CouleurIhm extends JFrame {
	private static final int CHANGEMENT_ECRITURE_ROUGE = 220;// passage de la couleur de caractères en autre chose que rouge quand cette valeur est atteinte
	private static final int NOMBRE_SLIDERS = 3;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panneauPrincipal;
	
	private JLabel labelRGB;	
	private monSlider sliders[];
	
	public CouleurIhm() {
		super("couleurs");
	
		gestionComposants();//On initialise les différents paramètres
		
		this.setSize(280,300);//Autre manière de faire une fenêtre rectangulaire en interface graphique
		this.setLocationRelativeTo(null);//position la fenêtre dans l'écran mettre null la positionne au centre
		this.setResizable(false);//Interdire le redimensionnement de la fenêtre par l'utilisateur
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//On supprime l'instance de la fenêtre 	
	}
	
	private void gestionComposants() {
		//Création des différents éléments dont le panneau dans lequel tout les autres éléments seront intégré
		panneauPrincipal = new JPanel();
		sliders= new monSlider[NOMBRE_SLIDERS];
		for(int indiceSlider = 0; indiceSlider < NOMBRE_SLIDERS;indiceSlider++) {
			sliders[indiceSlider]= new monSlider();
		}
		
		/*slRouge = new JSlider(JSlider.HORIZONTAL,0, 255, 255);
		slRouge.setMinorTickSpacing(10);
		slRouge.setMajorTickSpacing(100);
		slRouge.setPaintTicks(true);
		slRouge.setPaintLabels(true);
		slRouge.setOpaque(false);
		slVert = new JSlider(JSlider.HORIZONTAL,0, 255, 0);
		slVert.setMinorTickSpacing(10);
		slVert.setMajorTickSpacing(100);
		slVert.setPaintTicks(true);
		slVert.setPaintLabels(true);
		slVert.setOpaque(false);
		slBleu = new JSlider(JSlider.HORIZONTAL,0, 255, 0);
		slBleu.setMinorTickSpacing(10);
		slBleu.setMajorTickSpacing(100);
		slBleu.setPaintTicks(true);
		slBleu.setPaintLabels(true);
		slBleu.setOpaque(false);*/
		
		
		labelRGB = new JLabel();
		panneauPrincipal.setBackground(new Color(sliders[0].getValue(),sliders[1].getValue(),sliders[2].getValue()));

		if(sliders[0].getValue()>=CHANGEMENT_ECRITURE_ROUGE ||sliders[1].getValue()>=CHANGEMENT_ECRITURE_ROUGE ||sliders[2].getValue()>=CHANGEMENT_ECRITURE_ROUGE ) {
			labelRGB.setForeground(Color.BLACK);	
		}else{
			labelRGB.setForeground(Color.RED);			
		}
		labelRGB.setText("("+sliders[0].getValue()+","+sliders[1].getValue()+","+sliders[2].getValue()+")");
		labelRGB.setFont(new Font("Comic Sans MS", Font.BOLD,20));

		this.getContentPane().add(panneauPrincipal);//Renvoyer le panneau courant pour ensuite rajouter dessus le panneau créé
		
		//ajout des différents éléments au panneau
		for(int indiceSlider = 0; indiceSlider < NOMBRE_SLIDERS;indiceSlider++) {
			panneauPrincipal.add(sliders[indiceSlider]);
			sliders[indiceSlider].addChangeListener(new SliderGeneralChangeListener());
		}
		/*panneauPrincipal.add(slRouge);
		panneauPrincipal.add(slVert);
		panneauPrincipal.add(slBleu);
		//detection d'un changement de valeur pour chacun des sliders tous renvoyant vers la même fonction de détection 
		slRouge.addChangeListener(new SliderGeneralChangeListener());
		slVert.addChangeListener(new SliderGeneralChangeListener());
		slBleu.addChangeListener(new SliderGeneralChangeListener());*/

		panneauPrincipal.add(labelRGB);
		


	}

	class SliderGeneralChangeListener implements ChangeListener{
		//Action à réaliser lorsque l'un des slider bouge
		
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			
			for(int indiceChangeListener = 0; indiceChangeListener < NOMBRE_SLIDERS;indiceChangeListener++) {	
				//On change la couleur des labels et des graduation lorsque l'on passe  près de la couleur noire en arrière-plan et on les remet en noir dès qu'on s'en éloigne
				if(sliders[indiceChangeListener].getValue() <= 40 ) {

					sliders[indiceChangeListener].setForeground(Color.WHITE);
				}else {
					sliders[indiceChangeListener].setForeground(Color.BLACK);
				}
			}
			if(sliders[0].getValue() >= CHANGEMENT_ECRITURE_ROUGE ||sliders[1].getValue() >= CHANGEMENT_ECRITURE_ROUGE||sliders[2].getValue() >= CHANGEMENT_ECRITURE_ROUGE) {
				labelRGB.setForeground(Color.BLACK);					
			}else {
				if(sliders[0].getValue() <= 40 ||sliders[1].getValue() <= 40 ||sliders[2].getValue() <= 40 ) {
					labelRGB.setForeground(Color.WHITE);
				}else {
					labelRGB.setForeground(Color.RED);					
				}
			}

			panneauPrincipal.setBackground(new Color(sliders[0].getValue(),sliders[1].getValue(),sliders[2].getValue()));			
			labelRGB.setText("("+sliders[0].getValue()+","+sliders[1].getValue()+","+sliders[2].getValue()+")");		
		}
		
	}	
	
}
