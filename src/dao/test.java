package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class test 
{
	public int keyCount = 0;
	public int dataCount = 0;
	public Object[][] data;
	
	public test() throws FileNotFoundException
	{
		FileInputStream serviceAccount = new FileInputStream("../Thesis/temperature-19044-firebase-adminsdk-an3c5-261e0460e3.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
		  .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
		  .setDatabaseUrl("https://temperature-19044.firebaseio.com/")
		  .build();

		FirebaseApp.initializeApp(options);
		
	}
	
	public void testConnection()
	{
		DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
		connectedRef.addValueEventListener(new ValueEventListener() 
		{
			  @Override
			  public void onDataChange(DataSnapshot snapshot) 
			  {
			    boolean connected = snapshot.getValue(Boolean.class);
				System.out.println(connectedRef == null);

				for (DataSnapshot child : snapshot.getChildren()) 
				{ 
			        System.out.println(child == null);
			    } 
			    System.out.println("pass");

//			    if (connected) {
//			      System.out.println("connected");
//			    } else {
//			      System.out.println("not connected");
//			      List<FirebaseApp> appList = FirebaseApp.getApps();
//			      System.out.println(appList.get(0));
//			      System.out.println(FirebaseApp.getInstance().getName());
//			    }
			  }

			  @Override
			  public void onCancelled(DatabaseError error) 
			  {
			    System.err.println("Listener was cancelled");
			  }
			});
	}
	
	public void testMethod()
	{
//		DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//		rootRef.
		
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("sensor/temperature");
		ref.addValueEventListener(new ValueEventListener() 
		{
		    @Override
		    public void onDataChange(DataSnapshot dataSnapshot) 
		    {
		    	
				@SuppressWarnings("unchecked")
				
				HashMap<String, Object> document = (HashMap<String, Object>) dataSnapshot.getValue();
				
			    System.out.println(dataSnapshot.getChildrenCount());
//				Set set = document.entrySet();
//				Iterator iterator = set.iterator();
//			    while(iterator.hasNext()) 
//			    {
//			    	Map.Entry mentry = (Map.Entry)iterator.next();
//			        System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
//			        System.out.println(mentry.getValue());
//			    }
			    System.out.println("pass");
//		        System.out.println(document.toString());
		    }

			@Override
			public void onCancelled(DatabaseError arg0) 
			{
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void testQueryV()
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
							time = Long.parseLong(dat.toString());
						else
							value = Float.parseFloat(dat.toString());
						count++;
					}
					
					System.out.println(time);
					System.out.println(value);

					System.out.println();
//					System.out.println(dataSnapshot.getValue());

				}
				
				@Override
				public void onCancelled(DatabaseError error) 
				{
					// TODO Auto-generated method stub
					
				}
		    });
	}
	
	public void testQuery()
	{
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("sensor/temperature");
		
		ref.child("").limitToLast(1)
			.addValueEventListener(new ValueEventListener() 
			{
				
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) 
				{
					if(keyCount == 33)
					{
						printData();
					    keyCount = 0;

					}
					int num = (int) dataSnapshot.getChildrenCount();
	
					data = new Object[num][3];

					HashMap<String, Object> document = (HashMap<String, Object>) dataSnapshot.getValue();
					
					Set set = document.entrySet();
					Iterator iterator = set.iterator();
				    while(iterator.hasNext()) 
				    {
				    	Map.Entry mentry = (Map.Entry)iterator.next();
				    	data[keyCount][dataCount] = mentry.getKey();
				    	ref.child(mentry.getKey().toString()).addValueEventListener(new ValueEventListener()
				    			{

									@Override
									public void onDataChange(DataSnapshot snapshot) 
									{
										HashMap<String, Object> document1 = (HashMap<String, Object>) snapshot.getValue();
										
								    	if(dataCount == 3)
								    		keyCount++;
										
										dataCount = 1;
										
										Set set1 = document1.entrySet();
										Iterator iterator1 = set1.iterator();
										int t = 0;
										
									    while(iterator1.hasNext()) 
									    {
									    	Map.Entry mentry1 = (Map.Entry)iterator1.next();
									    	
									    	if(keyCount > 32)
									    		break;
									    	
									    	data[keyCount][dataCount] = mentry1.getValue();
									    	dataCount++;
									    }
									    
									}
									
									
									@Override
									public void onCancelled(DatabaseError error) 
									{
										// TODO Auto-generated method stub
										
									}
				    			});
				    	keyCount++;
				    }
				    keyCount = 0;
				}
				
				@Override
				public void onCancelled(DatabaseError error) 
				{
					// TODO Auto-generated method stub
					
				}
		    });
		

	}
	
	public void printData()
	{
//		java.util.Arrays.sort(data, java.util.Comparator.comparingInt(a -> a[0]));
		
//		long asd = (long) data[0][1];
//		System.out.println(asd + "         ");
		
		for(int a = 0; a < 33; a++)
		{
			for(int b = 0; b < 3; b++)
				System.out.println(data[1][b]);
			System.out.println();
		}
	}

	public static void main(String args[]) throws FileNotFoundException, InterruptedException
	{
//		test asd = new test();
//		
//
//		
//		while(true)
//		{
//			asd.testQueryV();
//			Thread.sleep(10000);
//
//			asd.printData();
//		}
		
		LinkedList<String> t = new LinkedList<String>();
		t.addFirst("first");
		System.out.println(t.getFirst() + " " + t.size());
		t.addFirst("second");
		System.out.println(t.getFirst() + " " + t.size());
		t.removeLast();
		System.out.println(t.getFirst() + " " + t.size());
		
	}
}
