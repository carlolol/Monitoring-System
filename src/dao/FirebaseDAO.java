package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

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
	private LinkedList<Float> moisture;
	
	//private key from the firebase project
	private final String key = "../Thesis/temperature-19044-firebase-adminsdk-an3c5-261e0460e3.json";
	
	public FirebaseDAO() throws FileNotFoundException
	{
		FileInputStream serviceAccount = new FileInputStream(key);

		FirebaseOptions options = new FirebaseOptions.Builder()
		  .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
		  .setDatabaseUrl("https://temperature-19044.firebaseio.com/")
		  .build();

		FirebaseApp.initializeApp(options);
		
		//initialize LinkedLists to be used as storage
		date = new LinkedList<Long>();
		temperature = new LinkedList<Float>();
		moisture = new LinkedList<Float>();
	}
	
	public void startRetrieveData()
	{
		try
		{
			retrieveTemperature();
			retrieveMoisture();
		}
		catch(Exception e)
		{
			
		}
	}
	
	private void retrieveTemperature()
	{
		//gets the reference sensor/temperature from the firebase project
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("sensor/temperature");
		
		//limitToLast(1) to get only the latest data stored
		ref.child("").limitToLast(1).addValueEventListener(new ValueEventListener() 
			{
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) 
				{
					//store the latest data into an temporary array
					String[] d1 = dataSnapshot.getValue().toString().replace("=", "").split("\\{");
					String[] d2 = new String[2]; //storage to be used for parsing and splitting
					System.out.println(d1[0]);
					int counter = 0;
					//d1 split into 3 parts: blank space, unique key, and the date and time combined together
					for (String data : d1)
					{
						if(counter == 2) //skip the other and straight to the date and time
							d2 = data.replace("}", "").replace("date", "")
								.replace("temp", "").split(","); //parse and split the data into d2
						counter++;
					}
					
					int count = 0;
					
					for (String dat : d2) 
					{
						dat = dat.trim();
						//storing the parsed String to the LinkedList
						if(count == 0)
							date.addFirst(Long.parseLong(dat));
						else
							temperature.addFirst(Float.parseFloat(dat));
						
						if(date.size() == 60) //limit the List into 60 only
						{
							date.removeLast();
							temperature.removeLast();
						}
						count++;
					}

				}
				
				@Override
				public void onCancelled(DatabaseError error) {}
		    });
	}
	
	private void retrieveMoisture()
	{
		//same thing, only this time from sensor/moisture
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("sensor/moisture");
		
		ref.child("").limitToLast(1).addValueEventListener(new ValueEventListener() 
			{
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) 
				{
					String[] d1 = dataSnapshot.getValue().toString().replace("=", "").split("\\{");
					String[] d2 = new String[2];

					int counter = 0;

					for (String data : d1)
					{
						if(counter == 2)
							d2 = data.replace("}", "").replace("date", "")
								.replace("moisture", "").split(",");
						counter++;
					}
					
					int count = 0;
					
					for (String dat : d2) 
					{
						dat = dat.toString().trim();
						//only getting the moisture, because there is already a date
						if(count == 1)
							moisture.addFirst(Float.parseFloat(dat.toString()));
						//remove from moisture list only, the latter already have a date limiter
						if(date.size() == 60)
							moisture.removeLast();
						
						count++;
					}

				}
				
				@Override
				public void onCancelled(DatabaseError error) {}
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
	
	public LinkedList<Float> getMoisture()
	{
		return moisture;
	}
}
