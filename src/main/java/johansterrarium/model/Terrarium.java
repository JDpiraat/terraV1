package johansterrarium.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import johansterrarium.model.entities.Actief;
import johansterrarium.model.entities.Beweegbaar;
import johansterrarium.model.entities.Carnivoor;
import johansterrarium.model.entities.Dier;
import johansterrarium.model.entities.Herbivoor;
import johansterrarium.model.entities.Omnivoor;
import johansterrarium.model.entities.Organisme;
import johansterrarium.model.entities.Plant;

public enum Terrarium {

	INSTANCE;

//	private static final int GROOTTE_TERRARIUM = 10;
	private Organisme[][] terrarium; // = new Organisme[GROOTTE_TERRARIUM][GROOTTE_TERRARIUM];
//	private static final int START_AANTAL_PLANTEN = 6;
//	private static final int START_AANTAL_HERBIVOREN = 12;
//	private static final int START_AANTAL_CARNIVOREN = 10;
//	private final static int AANTAL_PLANTEN_PER_DAG = 2;
	
//	private static final int START_AANTAL_OMNIVOREN = 6;

	public void initialiseer(int grootteTerrarium, int startAantalPlanten, int startAantalHerbivoren, int startAantalCarnivoren) {
		terrarium = new Organisme[grootteTerrarium][grootteTerrarium];
		List<Coordinaat> vrijeplaatsen = getAlleVrijePlaatsen();
		Collections.shuffle(vrijeplaatsen);
		// veel werk want elke keer indexen aanpassen van de hele list
		for (int i = 0; i < startAantalPlanten; i++) {
			terrarium[vrijeplaatsen.get(0).getX()][vrijeplaatsen.remove(0)
					.getY()] = new Plant(1);
		}
		// dus beter:
		for (int i = 0; i < startAantalHerbivoren; i++) {
			int laatste = vrijeplaatsen.size() - 1;
			terrarium[vrijeplaatsen.get(laatste).getX()][vrijeplaatsen.remove(laatste)
					.getY()] = new Herbivoor(1);
		}
		for (int i = 0; i < startAantalCarnivoren; i++) {
			int laatste = vrijeplaatsen.size() - 1;
			terrarium[vrijeplaatsen.get(laatste).getX()][vrijeplaatsen.remove(laatste)
					.getY()] = new Carnivoor(0);
		}
//		for (int i = 0; i < START_AANTAL_OMNIVOREN; i++) {
//			int laatste = vrijeplaatsen.size() - 1;
//			terrarium[vrijeplaatsen.get(laatste).getX()][vrijeplaatsen.remove(laatste)
//					.getY()] = new Omnivoor(1);
//		}
	}
	
	public void initialiseer(int grootteTerrarium, int startAantalPlanten, int startAantalHerbivoren, 
			int startAantalCarnivoren, int startAantalOmnivoren){
		this.initialiseer(grootteTerrarium, startAantalPlanten, startAantalHerbivoren, startAantalCarnivoren);
		List<Coordinaat> vrijeplaatsen = getAlleVrijePlaatsen();
		Collections.shuffle(vrijeplaatsen);
		for (int i = 0; i < startAantalOmnivoren; i++) {
			int laatste = vrijeplaatsen.size() - 1;
			terrarium[vrijeplaatsen.get(laatste).getX()][vrijeplaatsen.remove(laatste)
					.getY()] = new Omnivoor(1);
		}
	}

//	public void actiesDag() {
//		voegPlantenToe(AANTAL_PLANTEN_PER_DAG);
//		acties();
//		beweeg();
//		resetActieOndernomen();
//	}

	public void acties() {
		for (int i = 0; i < terrarium.length; i++) {
			for (int j = 0; j < terrarium.length - 1; j++) {
				if (terrarium[i][j] instanceof Actief) {
					((Actief) terrarium[i][j]).doeActie(terrarium[i][j + 1]);
				}
			}
		}
	}

	public void voegNieuwOrganismeToe(Organisme organisme) {
		List<Coordinaat> vrijeplaatsen = getAlleVrijePlaatsen();
		if (vrijeplaatsen != null && !vrijeplaatsen.isEmpty()) {
			Collections.shuffle(vrijeplaatsen);
			Coordinaat coordinat = vrijeplaatsen.get(0);
			terrarium[coordinat.x][coordinat.y] = organisme;
		}
	}

	public void voegPlantenToe(int aantalPlantenPerDag) {
		List<Coordinaat> vrijeplaatsen = getAlleVrijePlaatsen();
		if (vrijeplaatsen != null && !vrijeplaatsen.isEmpty()) {
			Collections.shuffle(vrijeplaatsen);
			for (int i = 0; i < aantalPlantenPerDag; i++) {
				terrarium[vrijeplaatsen.get(0).getX()][vrijeplaatsen.remove(0)
						.getY()] = new Plant(1);
				if (vrijeplaatsen.isEmpty()){
					// om een arrayindexoutofbounce te vermijden als er geen plaats meer is ...					
					break;
				}
			}
		}
	}

	public void verwijderOrganisme(Organisme organisme) {
		for (int i = 0; i < terrarium.length; i++) {
			for (int j = 0; j < terrarium.length; j++) {
				if (terrarium[i][j] == organisme) {
					terrarium[i][j] = null;
				}
			}
		}
	}

	public void resetActieOndernomen() {
		for (int i = 0; i < terrarium.length; i++) {
			for (int j = 0; j < terrarium.length; j++) {
				if (terrarium[i][j] instanceof Dier) {
					((Dier) terrarium[i][j]).setActieOndernomen(false);
				}
			}
		}
	}

	public void beweeg() {
		for (int i = 0; i < terrarium.length; i++) {
			for (int j = 0; j < terrarium.length; j++) {
				if (terrarium[i][j] instanceof Beweegbaar) {
					Beweegbaar beweegbaar = (Beweegbaar) terrarium[i][j];
					if (!((Dier) beweegbaar).isActieOndernomen()) {
						((Dier) beweegbaar).setActieOndernomen(true);
						Richting richting = beweegbaar
								.doeStap(getBeschikbarerichtingen(i, j));
						if (richting != null) {
							terrarium[i][j] = null;
							Organisme organisme = (Organisme) beweegbaar;
							switch (richting) {
							case NOORD:
								terrarium[i - 1][j] = organisme;
								break;
							case OOST:
								terrarium[i][j + 1] = organisme;
								break;
							case ZUID:
								terrarium[i + 1][j] = organisme;
								break;
							case WEST:
								terrarium[i][j - 1] = organisme;
								break;
							}
						}
					}
				}
			}
		}
	}

	public Organisme[][] getSituatie() {
		return terrarium;
	}

	private List<Coordinaat> getAlleVrijePlaatsen() {
		List<Coordinaat> vrijeplaatsen = new ArrayList<>();
		for (int x = 0; x < terrarium.length; x++) {
			for (int y = 0; y < terrarium.length; y++) {
				if (terrarium[x][y] == null)
					vrijeplaatsen.add(new Coordinaat(x, y));
			}
		}
		return vrijeplaatsen;
	}

	private List<Richting> getBeschikbarerichtingen(int x, int y) {
		List<Richting> richtingen = new ArrayList<Richting>();
		// don't do this :
		try {
			if (terrarium[x - 1][y] == null) {
				richtingen.add(Richting.NOORD);
			}
		} catch (Exception e) {
			// and we ignore the exception
		}
		// do it like this : check it yourself instead of counting on an exception being thrown
		if (x + 1 < terrarium.length && terrarium[x + 1][y] == null) {
			richtingen.add(Richting.ZUID);
		}
		if (y - 1 >= 0 && terrarium[x][y - 1] == null) {
			richtingen.add(Richting.WEST);
		}
		if (y + 1 < terrarium.length && terrarium[x][y + 1] == null) {
			richtingen.add(Richting.OOST);
		}
		return richtingen;
	}

	class Coordinaat {
		int x;
		int y;

		Coordinaat(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof Coordinaat)) {
				return false;
			}
			Coordinaat other = (Coordinaat) obj;
			if (!getOuterType().equals(other.getOuterType())) {
				return false;
			}
			if (x != other.x) {
				return false;
			}
			if (y != other.y) {
				return false;
			}
			return true;
		}

		private Terrarium getOuterType() {
			return Terrarium.this;
		}

	}
}
