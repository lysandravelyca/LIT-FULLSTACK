package vtlx;

import java.util.*;

public class Main {	
	
	class sort implements Comparator<movie> {

		@Override
		public int compare(movie o1, movie o2) {
			// TODO Auto-generated method stub
			return o1.getName().compareTo(o2.getName());
		}
		
		
	}
	
	class sort1 implements Comparator<movie> {

		@Override
		public int compare(movie o1, movie o2) {
			// TODO Auto-generated method stub
			return o2.getName().compareTo(o1.getName());
		}
		
		
	}
	
	ArrayList<movie> newMovie = new ArrayList<>();
	ArrayList<movie> newMovie1 = new ArrayList<>();
	
	Scanner sc = new Scanner(System.in);
	
	boolean cekName (String inName) {
		
		if(inName.length() > 3 && inName.length() < 15) return true;
		return false;
		
	}
	
	boolean cekType (String inType) {
		
		if(inType.equalsIgnoreCase("Regular") || inType.equalsIgnoreCase("Premier")) return true;
		return false;
		
	}
	
	boolean cekPrice (int inPrice) {
		
		if(inPrice > 0) return true;
		return false;
		
	}
	
	boolean cekCode (String inCode) {
		
		for (movie m : newMovie) {
			
			if(m.getId().equals(inCode)) {
				return true;
			}
		}
		System.out.println("Ticket ID must be exist on the ticket");
		return false;
	}
	void printTable() {
		
		System.out.println("=====================================================================================");
		System.out.println("|    Username   | Ticket ID |   Room Type	  |   Total Seat   |     Total Price    |");
		System.out.println("=====================================================================================");
		
	}
	
	void view() {
		
		if(newMovie.isEmpty()) {
			
			printTable();
			System.out.println("| There's is no data...                                                             |");
			System.out.println("=====================================================================================");
			
		}
		else {
			
			printTable();
			for (movie m: newMovie) {
				
				System.out.printf("|  %5s    |  %6s   | %13s      | %10d      |   %5d \n ", 
						m.getName(), m.getId(), m.getType(), m.getSeat(), m.getPrice());
				
			}
			System.out.println("=====================================================================================");
			
			
		}
		
	}
	
	void viewSort() {
		
		if(newMovie1.isEmpty()) {
			
			printTable();
			System.out.println("| There's is no data...                                                             |");
			System.out.println("=====================================================================================");
			
		}
		else {
			
			printTable();
			for (movie m: newMovie1) {
				
				System.out.printf("|  %5s    |  %6s   | %13s      | %10d      |   %5d \n ", 
						m.getName(), m.getId(), m.getType(), m.getSeat(), m.getPrice());
				
			}
			System.out.println("=====================================================================================");
			
			
		}
		
	}
	
	public Main() {
		
		String id = null;
		int choice;
		String inName;
		String inType;
		int inPrice;
		int regular = 30;
		int premier = 15;
		String inCode;
		int inBook;
		do {
			
			System.out.println("Welcome to vtlx");
			System.out.println("===============");
			System.out.println("1. Add Ticket");
			System.out.println("2. View List Order");
			System.out.println("3. Manage Order");
			System.out.println("4. Sorting");
			System.out.println("5. Exit");
			
			choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			
			case 1:
				
				do {
					
					System.out.print("Input the movie name [must be between 3 - 15 characters] : ");
					inName = sc.nextLine();
					
				} while(!cekName(inName));
				
				do {
					
					System.out.println("Input the room type [must be either (Regular | Premier) (case insensitive)]");
					inType = sc.nextLine();
					
				} while(!cekType(inType));
				
				do {
					
					System.out.println("Input price [must be more than 0]");
					inPrice = sc.nextInt();
					sc.nextLine();
					
				} while(!cekPrice(inPrice));
				
				if(inType.equalsIgnoreCase("Regular")) {
					
					movie newM = new movie(id,inName,inType,inPrice,regular);
					newMovie.add(newM);
					newMovie1.add(newM);
					
				}
				else if(inType.equalsIgnoreCase("Premier")){
					
					movie newM = new movie(id,inName,inType,inPrice,premier);
					newMovie.add(newM);
					newMovie1.add(newM);
				}	
				
				break;
				
			case 2:
				
				view();
				
				break;
				
			case 3:
				
				view();
				
				do {
					
					System.out.print("Input customer name [must be between 3 - 15 characters] : ");
					inName = sc.nextLine();
					
				} while (!cekName(inName));
				
				do {
					
					System.out.println("Choose the ID for booking the ticket: ");
					inCode = sc.nextLine();
					
				}  while(!cekCode(inCode));
				
					System.out.println("Input the total seats that want to be book : ");
					inBook = sc.nextInt();
					sc.nextLine();
				
					for(int i = 0 ; i < newMovie.size() ; i++) {
						
						movie newSeat = newMovie.get(i);
						
						if(newMovie.get(i).getId().equals(inCode)){
							
							newSeat.setSeat(newSeat.getSeat() -  inBook);

						}
					}
				
				break;
				
			case 4:
				
				int choose;
				
				do {
					
					System.out.println("1. sort accending");
					System.out.println("2. sort decending");
					choose = sc.nextInt();
					sc.nextLine();
					
					switch(choose) {
					
					case 1:
						
						Collections.sort(newMovie1, new sort());
						System.out.println("data sort by name");
						viewSort();
						
						
						break;
					case 2:
						
						Collections.sort(newMovie1, new sort1());
						System.out.println("data sort by name");
						viewSort();
						
						
						break;
					
					}
					
				} while (choose != 3);
				
				
				break;
				
			case 5:
				
				System.out.println("bye tenks");
				
				break;
			
			}
			
			
		} while (choice != 5);
		
	}
	
	
	public static void main(String[] args) {
		
		new Main();
	}

}
