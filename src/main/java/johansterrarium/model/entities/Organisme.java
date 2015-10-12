package johansterrarium.model.entities;

public abstract class Organisme {
	
	private int levenskracht;

	public int getLevenskracht() {
		return levenskracht;
	}

	public void setLevenskracht(int levenskracht) {
		this.levenskracht = levenskracht;
	}
	
	public void addLevenskracht(int levenskracht) {
		this.levenskracht += levenskracht;
	}
}
