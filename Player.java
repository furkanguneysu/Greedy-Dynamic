
public class Player implements Comparable<Player> {
	private String Name;
	private int Position;
	private int Rating;
	private int Price;
	
	public Player(String name, int position, int rating, int price) {
		super();
		Name = name;
		Position = position;
		Rating = rating;
		Price = price;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getPosition() {
		return Position;
	}

	public void setPosition(int position) {
		Position = position;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	@Override
	public String toString() {
		return "Player [Name=" + Name + ", Position=" + Position + ", Rating=" + Rating + ", Price=" + Price + "]";
	}

	@Override
	public int compareTo(Player comparePlayer) {
		int compareRating = ((Player)comparePlayer).getRating();
		return compareRating-this.Rating;
	}
	
	
	
	
	
	
	
	
	
}
