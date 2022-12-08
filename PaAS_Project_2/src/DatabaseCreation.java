import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class DatabaseCreation {
	
	public DatabaseCreation(int count) throws IOException {
		FileWriter w = new FileWriter("CarData.csv");
		PrintWriter pw = new PrintWriter(w);
		Random rng = new Random();
		int cost = 0;
		int maint = 0;
		int doors = 0;
		int passengers = 0;
		int luggage = 0;
		int safety = 0;
		
		pw.println("Buy Cost,Maintenance Cost,Num Doors,Num Passengers,Luggage Capacity,Safety");
		for(int i = 0; i < count; i++) {
			cost = rng.nextInt(1,5);//1=low, 2=med, 3=high, 4=very high
			if(cost >= 3) {
				maint = rng.nextInt(2,5);//same as cost, higher cost means lower maintenance
				safety = rng.nextInt(2,4);//1=low, 2=med, 3=high, higher cost means higher safety
			} else {
				maint = rng.nextInt(1,4);
				safety = rng.nextInt(1,3);
			}
			doors = rng.nextInt(1,3)*2;//2 or 4 doors, not counting hatch
			if(doors == 2) {
				passengers = rng.nextInt(2,4);//2-3 if 2 doors, 4-8 if 4 doors
			} else {
				passengers = rng.nextInt(4,9);
			}
			luggage = rng.nextInt(0,4);//0=none, 1=small, 2=med, 3=big
			pw.println(cost+","+maint+","+doors+","+passengers+","+luggage+","+safety);
		}
		
		pw.close();
		w.close();
	}
	
	public static void main(String[] agrs) throws IOException {
		DatabaseCreation run = new DatabaseCreation(15000);
	}
	
}
