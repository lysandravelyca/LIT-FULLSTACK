package vtlx;

import java.util.Random;

public class movie {

	private String id;
	private String name;
	private String type;
	private int price;
	private int seat;
	
	Random rand = new Random();
	
	int a = rand.nextInt(9);
	int b = rand.nextInt(9);
	int c = rand.nextInt(9);
	
	
	public movie(String id, String inName, String inType, int inPrice, int seat) {
		super();
		this.id = String.format("T%d%d%d", a,b,c);
		this.name = inName;
		this.type = inType;
		this.price = inPrice;
		this.seat = seat;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}
	
}
