package com.expandablelistsimple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.expandablelistsimple.expandablelist.ExpandableListDataPump;
import com.expandablelistsimple.expandablelist.SimpleAdapter;
import com.expandablelistsimple.expandablelist.SimpleExpandableListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleExpandableListView simpleExpandableListView = (SimpleExpandableListView) findViewById(R.id.expandable_listview);

        LinkedHashMap<String, List<String>> itemData = ExpandableListDataPump.getData();
        List<String> title = new ArrayList<>(itemData.keySet());
        SimpleAdapter adapter = new SimpleAdapter(title, itemData);
        simpleExpandableListView.setGroupIndicator(null);
        simpleExpandableListView.setAdapter(adapter);
    }
}
