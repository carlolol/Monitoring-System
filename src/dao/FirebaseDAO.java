package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Queue;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDAO 
{
	private LinkedList<Long> date;
	private LinkedList<Float> temperature;
	private final String key = "../Thesis/temperature-19044-firebase-adminsdk-an3c5-261e0460e3.json";
	
	private int keyCount = 0;
	private int dataCount = 0;
	
	public FirebaseDAO() throws FileNotFoundException
	{
		FileInputStream serviceAccount = new FileInputStream(key);

		FirebaseOptions options = new FirebaseOptions.Builder()
		  .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
		  .setDatabaseUrl("https://temperature-19044.firebaseio.com/")
		  .build();

		FirebaseApp.initializeApp(options);
		
		date = new LinkedList<Long>();
		temperature = new LinkedList<Float>();
		
	}
	
	public void startRetrieveData()
	{
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("sensor/temperature");
		
		ref.child("").limitToLast(1)
			.addValueEventListener(new ValueEventListener() 
			{
				
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) 
				{
					Object[] d1 = dataSnapshot.getValue().toString().replace("=", "").split("\\{");
					Object[] d2 = new Object[2];
					
					long time = 0;
					float value = 0;
					
					int counter = 0;

					for (Object data : d1)
					{
						if(counter == 2)
							d2 = data.toString().replace("}", "")
							.replace("date", "").replace("temp", "").split(",");
						counter++;
					}
					
//					Object temp[] = data[1].toString().split(",");
//					data[1] = Integer.parseInt(temp[0].toString());
//					data[2] = Double.parseDouble(temp[1].toString());
					
//					Object[] d3 = d2[1].toString().split(",");
					int count = 0;
					
					for (Object dat : d2) 
					{
						dat = dat.toString().trim();
						
						if(count == 0)
							date.addFirst(Long.parseLong(dat.toString()));
						else
							temperature.addFirst(Float.parseFloat(dat.toString()));
						
						if(date.size() == 60)
						{
							date.removeLast();
							temperature.removeLast();
						}
						count++;
					}
//					
//					System.out.println(time);
//					System.out.println(value);
//
//					System.out.println();
//					System.out.println(dataSnapshot.getValue());

				}
				
				@Override
				public void onCancelled(DatabaseError error) 
				{
					// TODO Auto-generated method stub
					
				}
		    });
	}
	
	public LinkedList<Long> getDate()
	{
		return date;
	}
	
	public LinkedList<Float> getTemperature()
	{
		return temperature;
	}
	
//	public static void main(String args[]) throws FileNotFoundException, InterruptedException
//	{
//		FirebaseDAO test = new FirebaseDAO();
//		test.startRetrieveData();
//		Thread.sleep(10000);
//		
//		while(true)
//		{
//			System.out.println(test.getDate().size());
//			Thread.sleep(1000);
//
//		}
//		
//	}
}
