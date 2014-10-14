package com.maulik.readexcel;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appspot.maulikabd.mresultreport.Mresultreport;
import com.appspot.maulikabd.mresultreport.model.ResultResultFileMessage;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.mburman.fileexplore.FileExplore;

public class MainActivity extends Activity 
{
	
	private class SendFileNameToServer extends AsyncTask<ResultResultFileMessage, Void, Void>
	{

		@Override
		protected Void doInBackground(ResultResultFileMessage... param) 
		{
			try
			{
				endPoint.mresultreport().addResultFileName(param[0]).execute();
				
			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}

	Button select,files,read;
	Mresultreport endPoint;
	MainActivity mainActivity;
	ExcelReader reader;
	String file,file_name;
	EditText editTextFileName;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//String path  = Environment.getExternalStorageDirectory().getAbsolutePath();
		//Log.d("path",path);
	
		final SendFileNameToServer fileNameToServer = new SendFileNameToServer();
		final ResultResultFileMessage resultFile = new ResultResultFileMessage();
		
		Mresultreport.Builder builder = new Mresultreport.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
		endPoint = builder.build();
		
		reader = new ExcelReader(endPoint);
		
		select = (Button) findViewById(R.id.ButtonSelect);
		files = (Button) findViewById(R.id.files);
		read = (Button) findViewById(R.id.buttonRead);
		file="";
		editTextFileName = (EditText) findViewById(R.id.editTextFile);
		
		Intent myIntent = getIntent();
		
		
		if(myIntent!=null)
		{
			file = myIntent.getStringExtra("file");
			file_name = myIntent.getStringExtra("file_name");
			editTextFileName.setText(file);
			resultFile.setFileName(file_name);
			
		}
		
		
		read.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				if(file.equals(""))
				{	
					Toast.makeText(getApplicationContext(), "Please select File", Toast.LENGTH_LONG).show();
				}
				else
				{	
					reader.setInputFile(file);
					reader.setFileName(file_name);
					try 
					{
						fileNameToServer.execute(resultFile);
						reader.read();
						
					}
					catch(IOException e) 
					{
						e.printStackTrace();
					}
				}
			}
		});
		
		files.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) 
			{
				Intent listFileIntent = new Intent(getApplicationContext(),ListFiles.class);
				startActivity(listFileIntent);
				
			}
		});
		
		
		select.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) 
			{
				
				Intent i = new Intent(getApplicationContext(),FileExplore.class);
				startActivity(i);
				
			}
		});
		
		
//		reader.setInputFile("/storage/emulated/0/stu_result1.xls");
//		
//		try 
//		{
//			reader.read();
//			
//		}
//		catch(IOException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
		/*
		ExcelReader obj = new ExcelReader();
		
		obj.setInputFile("/storage/emulated/0/stu_excel.xls");
		
		try 
		{
			obj.read();
			
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
