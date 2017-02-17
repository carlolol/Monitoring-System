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
	private LinkedList<Float> moisture;
	
	private final String key = "../Thesis/temperature-19044-firebase-adminsdk-an3c5-261e0460e3.json";
	
	
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
		moisture = new LinkedList<Float>();
		
	}
	
	public void startRetrieveData()
	{
		retrieveTemperature();
		
		retrieveMoisture();
	}
	
	private void retrieveTemperature()
	{
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("sensor/temperature");
		
		ref.child("").limitToLast(1).addValueEventListener(new ValueEventListener() 
			{
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) 
				{
					Object[] d1 = dataSnapshot.getValue().toString().replace("=", "").split("\\{");
					Object[] d2 = new Object[2];

					int counter = 0;

					for (Object data : d1)
					{
						if(counter == 2)
							d2 = data.toString().replace("}", "")
							.replace("date", "").replace("temp", "").split(",");
						counter++;
					}
					
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

				}
				
				@Override
				public void onCancelled(DatabaseError error) {}
		    });
	}
	
	private void retrieveMoisture()
	{
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("sensor/moisture");
		
		ref.child("").limitToLast(1).addValueEventListener(new ValueEventListener() 
			{
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) 
				{
					Object[] d1 = dataSnapshot.getValue().toString().replace("=", "").split("\\{");
					Object[] d2 = new Object[2];

					int counter = 0;

					for (Object data : d1)
					{
						if(counter == 2)
							d2 = data.toString().replace("}", "")
							.replace("date", "").replace("moisture", "").split(",");
						counter++;
					}
					
					int count = 0;
					
					for (Object dat : d2) 
					{
						dat = dat.toString().trim();
						
						if(count == 1)
							moisture.addFirst(Float.parseFloat(dat.toString()));
						
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
