package johansterrarium.model.entities;

import johansterrarium.model.Terrarium;

public class Herbivoor extends Dier implements Actief {

	public Herbivoor(int levenskracht) {
		super.setLevenskracht(levenskracht);
	}

	@Override
	public void doeActie(Organisme organisme) {
		if (organisme instanceof Plant) {
			this.addLevenskracht(organisme.getLevenskracht());
			this.setActieOndernomen(true);
			Terrarium.INSTANCE.verwijderOrganisme(organisme);
		}
		if (organisme instanceof Herbivoor) {
			Terrarium.INSTANCE.voegNieuwOrganismeToe(new Herbivoor(0));
			this.setActieOndernomen(true);
		}		
	}

}
