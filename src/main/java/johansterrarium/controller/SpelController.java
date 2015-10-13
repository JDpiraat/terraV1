package johansterrarium.controller;

import johansterrarium.model.Terrarium;
import johansterrarium.model.entities.Organisme;

public class SpelController {
	
	private final static int AANTAL_PLANTEN_PER_DAG = 2;
	
	private static final int GROOTTE_TERRARIUM = 10;	
	private static final int START_AANTAL_PLANTEN = 6;
	private static final int START_AANTAL_HERBIVOREN = 12;
	private static final int START_AANTAL_CARNIVOREN = 10;
	
	private static final int START_AANTAL_OMNIVOREN = 6;
	
	public SpelController() {
		Terrarium.INSTANCE.initialiseer(GROOTTE_TERRARIUM, START_AANTAL_PLANTEN, 
				START_AANTAL_HERBIVOREN, START_AANTAL_CARNIVOREN, START_AANTAL_OMNIVOREN);
	}
	
	public Organisme[][] getTerrarium(){
		return Terrarium.INSTANCE.getSituatie();
	}

	public void volgendeDag(){
//		Terrarium.INSTANCE.actiesDag();
		Terrarium.INSTANCE.voegPlantenToe(AANTAL_PLANTEN_PER_DAG);
		Terrarium.INSTANCE.acties();
		Terrarium.INSTANCE.beweeg();
		Terrarium.INSTANCE.resetActieOndernomen();
	}	
}