package johansterrarium.model.entities;

import java.util.Collections;
import java.util.List;

import johansterrarium.model.Richting;

// interface Actief pas later toegevoegd
public abstract class Dier extends Organisme implements Beweegbaar, Actief {
	
	private boolean actieOndernomen = false;

	// ...ActieOndernomen later in interface actief gezet
	@Override
	public boolean isActieOndernomen() {
		return actieOndernomen;
	}

	@Override
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
