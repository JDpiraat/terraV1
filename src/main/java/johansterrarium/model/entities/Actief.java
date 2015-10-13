package johansterrarium.model.entities;

public interface Actief {
	
	void doeActie(Organisme organisme);
	
	// later hier toegevoegd
	public boolean isActieOndernomen();
	
	public void setActieOndernomen(boolean actieOndernomen) ;	

}
