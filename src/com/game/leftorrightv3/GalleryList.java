package com.game.leftorrightv3;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GalleryList extends ListActivity {

	static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
		"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
		"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery_display);
		TextView title = (TextView)findViewById(R.id.galleryTitle);
		// no more this
		// setContentView(R.layout.list_fruit);
		int galOrLog = this.getIntent().getIntExtra("galOrLog", 0);
		int sizeOf = this.getIntent().getIntExtra("sizeOf", 0);

		String[] items = new String[sizeOf]; 

		if(galOrLog == 0){
			title.setText("Gallery!");
			int[] galleryItems = this.getIntent().getIntArrayExtra("itemsFound");

			items = getItems(galleryItems);
		}
		else{
			title.setText("Logbook!");
			ArrayList<String> logItems = this.getIntent().getStringArrayListExtra("logItems");
			int[] logValues = this.getIntent().getIntArrayExtra("logValues");
			items = getItems(logItems, logValues);
		}


		setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_gallery_list,items));

		ListView listView = (ListView)findViewById(android.R.id.list);
		listView.setTextFilterEnabled(true);

		//		listView.setOnItemClickListener(new OnItemClickListener() {
		//			public void onItemClick(AdapterView<?> parent, View view,
		//					int position, long id) {
		//			    // When clicked, show a toast with the TextView text
		//			    Toast.makeText(getApplicationContext(),
		//				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
		//			}
		//		});

	}

	public String[] getItems(int[] galleryItems){
		String[] toReturn = new String[galleryItems.length];

		//		String text = "";
		//		
		for (int i = 0; i < galleryItems.length; i++){
			if(galleryItems[i] == 0){
				toReturn[i] = "???????";
			}
			else{
				toReturn[i] = StartMenu.items[i] + " x " + galleryItems[i];
			}
		}
		//		
		//		TextView textView = (TextView)findViewById(R.id.itemsText);
		//		textView.setText(text);

		return toReturn;
	}

	public String[] getItems(ArrayList<String> logItems, int[] logValues){
		String[] toReturn = new String[logItems.size()];

		for (int i = 0; i < logItems.size(); i++){
			String newLog = logItems.get(i);
			String[] logArr = newLog.split("\\.");
//			if(logValues[i] == 0){
//				toReturn[i] = "???????";
//			}
//			else{
				String text = "";
				text += logArr[0] + " + " + logArr[1] + " = " + logArr[2];
//				if(logArr[2].equals("true")){
//					text += "Alive! 8D";
//				}
//				else{
//					text += "Dead...X(";
//				}
				text += " x " + logValues[i] + "";
				toReturn[i] = text;
			}

//		}


		return toReturn;
	}

}
