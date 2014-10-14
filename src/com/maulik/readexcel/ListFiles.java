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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.appspot.maulikabd.mresultreport.Mresultreport;
import com.appspot.maulikabd.mresultreport.model.ResultFileCollectionMessage;
import com.appspot.maulikabd.mresultreport.model.ResultResultFileMessage;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

public class ListFiles extends Activity 
{
	ListView listViewFileList;
	
	private class GetFileNames extends AsyncTask<Void, Void, ResultFileCollectionMessage>
	{
		ResultFileCollectionMessage resultFileCollectionMessage;
		
		@Override
		protected ResultFileCollectionMessage doInBackground(Void... param) 
		{
			try
			{
				resultFileCollectionMessage = endPoint.mresultreport().getFiles().execute();
				return resultFileCollectionMessage;
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(ResultFileCollectionMessage result) 
		{
			
			ArrayList<ResultResultFileMessage> file_list = (ArrayList<ResultResultFileMessage>) result.getItems();
			FileNameAdapter adapter = new FileNameAdapter(getApplicationContext(), file_list);
			listViewFileList.setAdapter(adapter);
		}
		
	}
	
	
	Mresultreport endPoint;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_files);
		
		Mresultreport.Builder builder = new Mresultreport.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
		endPoint = builder.build();
		
		listViewFileList = (ListView) findViewById(R.id.listViewFileName);
		
		new GetFileNames().execute();
		
		listViewFileList.setOnItemClickListener(new OnItemClickListener() 
		{
			TextView textViewFileName;
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) 
			{
				textViewFileName = (TextView) view.findViewById(R.id.textViewFileName);
				
				Intent intentStuList = new Intent(getApplicationContext(),ListStudent.class);
				intentStuList.putExtra("file_name", textViewFileName.getText().toString());
				startActivity(intentStuList);
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		
		getMenuInflater().inflate(R.menu.list_files, menu);
		return true;
	}

}
