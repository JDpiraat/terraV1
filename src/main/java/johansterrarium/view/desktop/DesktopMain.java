package johansterrarium.view.desktop;

import java.util.Scanner;

import johansterrarium.controller.SpelController;
import johansterrarium.model.entities.Carnivoor;
import johansterrarium.model.entities.Herbivoor;
import johansterrarium.model.entities.Omnivoor;
import johansterrarium.model.entities.Organisme;
import johansterrarium.model.entities.Plant;

public class DesktopMain {

	public static void main(String[] args) {
		SpelController spelController = new SpelController();
		Organisme[][] terrarium = spelController.getTerrarium();
		System.out.println("Het terrarium:");
		printToConsole(terrarium);
		try (Scanner scanner = new Scanner(System.in);) {
			while (!scanner.next().equals("s")) {
				spelController.volgendeDag();
				printToConsole(terrarium);
			}
		}
		System.out.println("the end");
	}

	private static void printToConsole(Organisme[][] terrarium) {
		for (int i = 0; i < terrarium.length; i++) {
			for (int j = 0; j < terrarium.length; j++) {
				System.out.print(consoleVoorstelling(terrarium[i][j]));
			}
			System.out.println();
		}
	}

	private static String consoleVoorstelling(Organisme organisme) {		
		if (organisme instanceof Plant) {
			return "P " + organisme.getLevenskracht() + "\t";
		} else if (organisme instanceof Herbivoor) {
			return "H " + organisme.getLevenskracht() + "\t";
		} else if (organisme instanceof Carnivoor) {
			return "C " + organisme.getLevenskracht() + "\t";
		} else if (organisme instanceof Omnivoor) {
			return "O " + organisme.getLevenskracht() + "\t";
		}
		return "." + "\t";
	}

}
