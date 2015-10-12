package johansterrarium.model.entities;

import java.util.Collections;
import java.util.List;

import johansterrarium.model.Richting;

public abstract class Dier extends Organisme implements Beweegbaar {
	
	private boolean actieOndernomen = false;

	public boolean isActieOndernomen() {
		return actieOndernomen;
	}

	public void setActieOndernomen(boolean actieOndernomen) {
		this.actieOndernomen = actieOndernomen;
	}

	@Override
	public Richting doeStap(List<Richting> richtingen) {		
		if (richtingen == null || richtingen.isEmpty()) {
			return null;
		}
		Collections.shuffle(richtingen);
		return richtingen.get(richtingen.size() - 1);
	}	

}
