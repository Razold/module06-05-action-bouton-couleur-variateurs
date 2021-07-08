package fr.eni.ihm;

import javax.swing.JSlider;

public class monSlider extends JSlider {
	
	public monSlider() {
		super(JSlider.HORIZONTAL,0, 255, 255);
		setMinorTickSpacing(10);
		setMajorTickSpacing(100);
		setPaintTicks(true);
		setPaintLabels(true);
		setOpaque(false);
	}
}
