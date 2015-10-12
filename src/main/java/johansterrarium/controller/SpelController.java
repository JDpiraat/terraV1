package johansterrarium.controller;

import johansterrarium.model.Terrarium;
import johansterrarium.model.entities.Organisme;

public class SpelController {
	
	private final static int AANTAL_PLANTEN_PER_DAG = 2;
	
	public SpelController() {
		Terrarium.INSTANCE.initialiseer();
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
