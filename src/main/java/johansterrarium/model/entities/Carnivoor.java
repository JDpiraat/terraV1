package johansterrarium.model.entities;

import johansterrarium.model.Terrarium;

//interface Actief pas later hier verwijderd (zie Dier)
public class Carnivoor extends Dier {

	public Carnivoor(int levenskracht) {
		setLevenskracht(levenskracht);
	}	

	@Override
	public void doeActie(Organisme organisme) {
		if (organisme instanceof Herbivoor) {
			this.addLevenskracht(organisme.getLevenskracht());
			Terrarium.INSTANCE.verwijderOrganisme(organisme);
			this.setActieOndernomen(true);
		}
		if (organisme instanceof Carnivoor || organisme instanceof Omnivoor) {
			if (this.getLevenskracht() > organisme.getLevenskracht()){
				this.addLevenskracht(organisme.getLevenskracht());
				Terrarium.INSTANCE.verwijderOrganisme(organisme);
				this.setActieOndernomen(true);
			} else if (this.getLevenskracht() < organisme.getLevenskracht()){
				organisme.addLevenskracht(this.getLevenskracht());
				Terrarium.INSTANCE.verwijderOrganisme(this);				
			} 
			// gelijke levenskracht doet niks
		}		
	}

}
