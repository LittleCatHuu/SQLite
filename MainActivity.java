package com.example.demo;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	private Button btn_create_db;
	private Button btn_create_table;
	private Button btn_insert;
	private Button btn_read;
	private EditText edt_stuno;
	private EditText edt_name;
	private EditText edt_age;
	
	private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findviews();
    }

    private void findviews() {
    	initBtns();
    	
    	initEdts();
    	
    	
    	
	}

	private void initEdts() {
		edt_stuno = (EditText) findViewById(R.id.edt_stuno);
    	edt_name = (EditText) findViewById(R.id.edt_name);
    	edt_age = (EditText) findViewById(R.id.edt_age);
	}

	private void initBtns() {
        btn_create_db = (Button) findViewById(R.id.btn_create_db);
    	
    	final String path =  getFilesDir().getAbsolutePath() + File.separator + "my.db";
    	
    	Log.i("AAA", path);
    	
    	btn_create_db.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				db = SQLiteDatabase.openOrCreateDatabase(path, null);
			}
		});
		
    	btn_create_table = (Button) findViewById(R.id.btn_create_table);
        
    	btn_create_table.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String createStr = 
						"create table if not exists student (id integer, name varchar(10), age integer)";
				db.execSQL(createStr);
			}
		});
    	
    	btn_insert = (Button) findViewById(R.id.btn_insert);
    	btn_insert.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				int no = Integer.parseInt(edt_stuno.getText().toString());
				String name = edt_name.getText().toString();
				int age = Integer.parseInt(edt_age.getText().toString());
						
				
				String insertStr = "insert into student values (" + no+ ", '" +name+  "', " +age+ ")";
				db.execSQL(insertStr);	
			}
		});
		
    	btn_read = (Button) findViewById(R.id.btn_read);
    	btn_read.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			     Cursor cur = db.query("student", null, null, null, null, null, null);
			     
			     ArrayList<Student> stuList = new ArrayList<Student>();
			   
			     for(cur.moveToFirst();   !cur.isAfterLast()   ; cur.moveToNext()){
			    	 int stuno = cur.getInt(0);
			    	 String name = cur.getString(1);
			    	 int age = cur.getInt(2);
			    	 Student s = new Student(stuno, name, age);
			    	 stuList.add(s);
			     }
			     
			     for(int i=0; i<stuList.size();i++){
			    	 Log.i("AAA", stuList.get(i).getInfo());
			     }
				
			}
		});
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
