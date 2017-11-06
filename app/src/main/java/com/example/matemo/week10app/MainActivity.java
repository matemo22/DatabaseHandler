package com.example.matemo.week10app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editTextName, editTextPhone;
    Button btnAdd, btnEdit, btnDelete;
    ListView listView;
    ArrayList<Contact> arrContact = new ArrayList<Contact>();
    ArrayAdapter<Contact> adapter;
    DatabaseHandler dbHandler;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        listView = (ListView) findViewById(R.id.listView);

        dbHandler = new DatabaseHandler(this);
        arrContact = dbHandler.getAllContact();
        adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, arrContact);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = new Contact();
                contact.setName(editTextName.getText().toString());
                contact.setPhone(editTextPhone.getText().toString());
                dbHandler.addContact(contact);
//                arrContact.add(contact);
                arrContact.clear();
                arrContact.addAll(dbHandler.getAllContact());
                adapter.notifyDataSetChanged();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = new Contact();
                contact.setId(arrContact.get(index).getId());
                contact.setName(editTextName.getText().toString());
                contact.setPhone(editTextPhone.getText().toString());
                dbHandler.updateContact(contact);
                arrContact.clear();
                arrContact.addAll(dbHandler.getAllContact());
                adapter.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.deleteContact(arrContact.get(index).getId());
                arrContact.clear();
                arrContact.addAll(dbHandler.getAllContact());
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Contact contact = (Contact) adapterView.getItemAtPosition(position);
                editTextName.setText(contact.getName());
                editTextPhone.setText(contact.getPhone());
                index = position;
            }
        });
    }
}
