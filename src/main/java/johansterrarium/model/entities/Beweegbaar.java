package johansterrarium.model.entities;

import java.util.List;

import johansterrarium.model.Richting;

public interface Beweegbaar {	

	Richting doeStap(List<Richting> richtingen);

}
