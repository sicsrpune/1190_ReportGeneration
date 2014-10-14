package com.maulik.readexcel;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.appspot.maulikabd.mresultreport.Mresultreport;
import com.appspot.maulikabd.mresultreport.model.ResultResultFileMessage;
import com.appspot.maulikabd.mresultreport.model.ResultResultStudentId;
import com.appspot.maulikabd.mresultreport.model.ResultResultStudentIdCollection;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

public class ListStudent extends Activity 
{
	String file_name;
	ResultResultFileMessage resultFileMessage;
	Mresultreport endPoint;
	ListView listViewStudent;
	
	private class RetriverStudents extends AsyncTask<ResultResultFileMessage, Void, ResultResultStudentIdCollection>
	{
		ResultResultStudentIdCollection stu_list;
		@Override
		protected ResultResultStudentIdCollection doInBackground(ResultResultFileMessage... param) 
		{
			try 
			{
				stu_list = endPoint.mresultreport().getStudent(param[0]).execute();
				return stu_list;
			} 
			catch (IOException e)
			{
				
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(ResultResultStudentIdCollection result) 
		{
			ArrayList<ResultResultStudentId> stu_list = (ArrayList<ResultResultStudentId>) result.getItems();
			StudentAdapter adapter = new StudentAdapter(getApplicationContext(), stu_list);
			listViewStudent.setAdapter(adapter);
			
		}
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_list);
		Intent fileIntent = getIntent();
		resultFileMessage = new ResultResultFileMessage();
		
		file_name="";
		
		if(fileIntent != null)
		{
			file_name = fileIntent.getStringExtra("file_name");
			resultFileMessage.setFileName(file_name);
			
		}
		else
		{
			resultFileMessage.setFileName(null);
		}
		Mresultreport.Builder builder = new Mresultreport.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
		endPoint = builder.build();
		new RetriverStudents().execute(resultFileMessage);
		listViewStudent = (ListView) findViewById(R.id.listViewStudent);
		
		
		
		listViewStudent.setOnItemClickListener(new OnItemClickListener()
		{
			TextView textViewStudentId;
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) 
			{
				textViewStudentId = (TextView) view.findViewById(R.id.textViewStudentId);
				
				Intent intentStuList = new Intent(getApplicationContext(),Graph.class);
				intentStuList.putExtra("student_id", textViewStudentId.getText().toString());
				startActivity(intentStuList);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.student_list, menu);
		return true;
	}

}
