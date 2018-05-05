package fr.uha.ensisa.puissance4;

import fr.uha.ensisa.puissance4.ui.Console;
import fr.uha.ensisa.puissance4.ui.GUI;

public abstract class Puissance4 {

	public static void main(String[] args) {
		int mode;
		if (args[0] == null) {
			mode = 2;
		} else {
			mode = Integer.parseInt(args[0]);
		}

		// Indique la bonne interface et la lance dans un thread différent
		switch (mode) {
		case 2:
			GUI.main(args);
			break;
		default:
			Console console = new Console();
			console.start();
			break;
		}

	}

}
