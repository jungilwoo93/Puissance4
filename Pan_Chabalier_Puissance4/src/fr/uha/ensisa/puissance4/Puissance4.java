package fr.uha.ensisa.puissance4;

import fr.uha.ensisa.puissance4.ui.controller.Console;
import fr.uha.ensisa.puissance4.ui.controller.GUI;

public abstract class Puissance4 {

	public static void main(String[] args) {
		int mode;
		if (args.length == 0) { //if no argument is provided, mode will be 2 (GUI)
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
