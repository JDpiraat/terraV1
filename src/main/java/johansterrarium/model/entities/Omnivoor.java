package johansterrarium.model.entities;

import johansterrarium.model.Terrarium;

public class Omnivoor extends Dier implements Actief {

	public Omnivoor(int levenskracht) {
		setLevenskracht(levenskracht);
	}
	
	@Override
	public void doeActie(Organisme organisme) {
		if (organisme instanceof Plant) {
			this.addLevenskracht(organisme.getLevenskracht());
			this.setActieOndernomen(true);
			Terrarium.INSTANCE.verwijderOrganisme(organisme);
		}
		if (organisme instanceof Herbivoor) {
			this.addLevenskracht(organisme.getLevenskracht());
			Terrarium.INSTANCE.verwijderOrganisme(organisme);
			this.setActieOndernomen(true);
		}
//		if (organisme instanceof Omnivoor) {
//			Terrarium.INSTANCE.voegNieuwOrganismeToe(new Omnivoor(0));
//			this.setActieOndernomen(true);
//		}
		if (organisme instanceof Carnivoor || organisme instanceof Omnivoor) { // || organisme instanceof Omnivoor
			if (this.getLevenskracht() > organisme.getLevenskracht()) {
				this.addLevenskracht(organisme.getLevenskracht());
				Terrarium.INSTANCE.verwijderOrganisme(organisme);
				this.setActieOndernomen(true);
			} else if (this.getLevenskracht() < organisme.getLevenskracht()) {
				organisme.addLevenskracht(this.getLevenskracht());
				Terrarium.INSTANCE.verwijderOrganisme(this);
			}
			// gelijke levenskracht doet niks
		}
	}

}
