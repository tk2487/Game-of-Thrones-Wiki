public class Battle implements Comparable<Battle> {
	String name;
	String attackerKing;
	String defenderKing;
	String attackerOutcome;
	String battleType;
	String location;
	String region;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param attackerKing
	 * @param defenderKing
	 * @param attackerOutcome
	 * @param battleType
	 * @param location
	 * @param region
	 * @return A battle object
	 */
	public Battle(String name, String attackerKing, String defenderKing, String attackerOutcome, String battleType,
			String location, String region) {
		this.name = name;
		this.attackerKing = attackerKing;
		this.defenderKing = defenderKing;
		this.attackerOutcome = attackerOutcome;
		this.battleType = battleType;
		this.location = location;
		this.region = region;
	}

	public String getName() {
		return name;
	}

	public String getAttackerKing() {
		return attackerKing;
	}

	public String getDefenderKing() {
		return defenderKing;
	}

	public String getAttackerOutcome() {
		return attackerOutcome;
	}

	public String getBattleType() {
		return battleType;
	}

	public String getLocation() {
		return location;
	}

	public String getRegion() {
		return region;
	}

	@Override
	public String toString() {
		return String.format(" - " + name + ", when " + attackerKing + " attacked " + defenderKing + ", resulting in a "
				+ attackerOutcome + ", through a " + battleType + ", at " + location + ", in the region of " + region
				+ "\n");
	}

	@Override
	public int compareTo(Battle a) {
		return this.getName().compareToIgnoreCase(a.getName());
	}
}
