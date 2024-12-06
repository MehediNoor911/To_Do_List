package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        EditText editText = findViewById(R.id.editText);
        Button addButton = findViewById(R.id.addButton);

        // Initialize the list
        items = new ArrayList<>();
        listView.setBackgroundResource(R.drawable.background_image);

        // Custom adapter to show numbered list items
        adapter = new ArrayAdapter<String>(this, R.layout.list_item_numbered, R.id.itemTextView, items) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                // Set the number
                TextView numberTextView = view.findViewById(R.id.numberTextView);
                numberTextView.setText(String.valueOf(position + 1 + "."));

                return view;
            }
        };

        listView.setAdapter(adapter);

        // Add button click listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = editText.getText().toString().trim();
                if (!newItem.isEmpty()) {
                    items.add(newItem);  // Add item to the list
                    adapter.notifyDataSetChanged();  // Update the ListView
                    editText.setText("");  // Clear the EditText
                }
            }
        });

        // Set long-click listener to delete item
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);  // Remove the item
                adapter.notifyDataSetChanged();  // Update the ListView
                Toast.makeText(MainActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
