public class Prediction implements Comparable<Prediction> {

	private float plod;
	private Character character;

	/*
	 * Constructor for Prediction class that takes in the PLOD float number
	 * and the character object.
	 */
	public Prediction(float plod, Character character) {
		this.character = character;
		this.plod = plod;
	}

	public float getPlod() {
		return plod;
	}

	public Character getCharacter() {
		return character;
	}
	
	//Compares PLOD numbers of the two prediction objects.
	@Override
	public int compareTo(Prediction p) {
		return Float.valueOf(this.getPlod()).compareTo(Float.valueOf(p.getPlod()));
	}

	@Override
	public String toString() {
		return character.getName() + " with PLOD of " + plod;
	}

}
