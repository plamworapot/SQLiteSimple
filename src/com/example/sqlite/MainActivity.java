package com.example.sqlite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	MemberData member = new MemberData();
	List<MemberData> allMember  = new ArrayList<MemberData>();
	PhoneBookDataSource dataSource = new PhoneBookDataSource(this);
	SimpleAdapter sAdap;
	Button btncancel;
	EditText editname;
	EditText edittel;
	EditText editemail;
	TextView txtaction;
	Button btndel;
	ListView list;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		list = (ListView)findViewById(R.id.listView1);
		btncancel = (Button)findViewById(R.id.button1);
		btndel = (Button)findViewById(R.id.button2);
		editname = (EditText)findViewById(R.id.editText_name);
		edittel = (EditText)findViewById(R.id.editText_tel);
		editemail = (EditText)findViewById(R.id.editText_email);
		txtaction = (TextView)findViewById(R.id.textView_action);

		

		txtaction.setText("ADD");
		btncancel.setVisibility(View.INVISIBLE);
		btndel.setVisibility(View.INVISIBLE);
		
		
		dataSource.open();
////		
//		member.setName("Kanyanat");
//		member.setTel("0823123");
//		member.setEmail("เมลปลอม@เมล");
//		dataSource.insertMember(member);
////		


		updatescreen();
	
		
		list.setOnItemClickListener(new OnItemClickListener() {

			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int item,
					long arg3) {
				// TODO Auto-generated method stub
				Log.i("XXXXXXXXXXX",item+" "+sAdap.getItem(item).toString());
				HashMap<String, String> obj = (HashMap<String, String>) sAdap.getItem(item);
				 				 
				txtaction.setText("EDIT");				 
				editname.setText(obj.get("NAME"));
				edittel.setText(obj.get("TEL"));
				editemail.setText(obj.get("E-MAIL"));
				
				member = new MemberData();
				member.setName(editname.getText().toString());
				member.setEmail(editemail.getText().toString());
				member.setTel(edittel.getText().toString());
				member.setId(Long.parseLong(obj.get("ID")));
				
				btncancel.setVisibility(View.VISIBLE);
				btndel.setVisibility(View.VISIBLE);
				
				
			}
			
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	void updatescreen(){
		allMember = dataSource.getAllMember();
			
		HashMap<String, String> map;
		ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
			if(allMember.size()>0){
	
				
				
				for(MemberData m:allMember){	
					map = new HashMap<String, String>();
					map.put("ID", m.getId()+"");
					map.put("NAME", m.getName());
					map.put("TEL", m.getTel());
					map.put("E-MAIL", m.getEmail());
					MyArrList.add(map);
					
				}
	

		}
		
		
		sAdap = new SimpleAdapter(MainActivity.this, MyArrList, R.layout.activity_column,			
		new String[] {"NAME", "TEL", "E-MAIL"}, new int[] {R.id.ColMemberName, R.id.ColMemberNameTel, R.id.ColMemberEmail});     
		
		list.setAdapter(sAdap);
	}
	
	public void cancel(View v){
		 txtaction.setText("ADD");
		 editname.setText("");
		 edittel.setText("");
		 editemail.setText("");
		 btncancel.setVisibility(View.INVISIBLE);
		 btndel.setVisibility(View.INVISIBLE);
		 
	}
	public void addOrEdit(View v){


		
		if(txtaction.getText().toString().equals("ADD")){
			
			if(editname.getText().toString().equals("") || editname.getText().toString().equals("") || editname.getText().toString().equals("")){
				Toast.makeText(this, "โปรด ใส่ข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
			}else{			
				member = new MemberData();
				member.setName(editname.getText().toString());
				member.setEmail(editemail.getText().toString());
				member.setTel(edittel.getText().toString());
				dataSource.insertMember(member);
				
				editname.setText("");
				edittel.setText("");
				editemail.setText("");
				btncancel.setVisibility(View.INVISIBLE);
				btndel.setVisibility(View.INVISIBLE);
			}
		}else{
			 dataSource.updateMember(member);			
			 editname.setText("");
			 edittel.setText("");
			 editemail.setText("");
			 btncancel.setVisibility(View.INVISIBLE);
			 btndel.setVisibility(View.INVISIBLE);
		}
		 txtaction.setText("ADD");

		 
		 updatescreen();
		
	}
	public void delete(View v){
		
		 txtaction.setText("ADD");
		 editname.setText("");
		 edittel.setText("");
		 editemail.setText("");
		 btncancel.setVisibility(View.INVISIBLE);
		 btndel.setVisibility(View.INVISIBLE);
		
		dataSource.deleteMember(member);
		updatescreen();
	}


}
