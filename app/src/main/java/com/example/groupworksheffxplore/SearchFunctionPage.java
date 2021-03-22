package com.example.groupworksheffxplore;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchFunctionPage extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] touristHotspotNamesList;
    ArrayList<TouristHotspots> arraylist = new ArrayList<TouristHotspots>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu_page);

        // Generate sample data

        touristHotspotNamesList = new String[]{"NorFolk Park", "Weston Park", "Endcliffe Park",
                "Crooks Valley Park", "West Street Live ", "The Common Room", "Kukcoo", "Nursery Tavern ",
                "How St ","Napoli Cento Pizza ","Shef Island", "Heart of the Campus","Owen","The Diamond ","Sheffield Students' Union"};

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        for (int i = 0; i < touristHotspotNamesList.length; i++) {
            TouristHotspots hotSpotname = new TouristHotspots(touristHotspotNamesList[i]);
            // Binds all strings into an array
            arraylist.add(hotSpotname);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}