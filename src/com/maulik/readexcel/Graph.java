package com.maulik.readexcel;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebView;

import com.appspot.maulikabd.mresultreport.Mresultreport;
import com.appspot.maulikabd.mresultreport.model.ResultGraphMessage;
import com.appspot.maulikabd.mresultreport.model.ResultMarkMessage;
import com.appspot.maulikabd.mresultreport.model.ResultResultStudentId;
import com.appspot.maulikabd.mresultreport.model.ResultSubMessage;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

public class Graph extends Activity 
{
	ResultResultStudentId resultResultStudentId;
	Mresultreport endPoint;
	
	
	private class RetriverResultStats extends AsyncTask<ResultResultStudentId, Void, ResultGraphMessage>
	{
		 ResultGraphMessage row;
		@Override
		protected ResultGraphMessage doInBackground(ResultResultStudentId... params) 
		{
			try 
			{
				row = endPoint.mresultreport().getGraph(resultResultStudentId).execute();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			return row;
		}
		
		@Override
		protected void onPostExecute(ResultGraphMessage result) 
		{
		
			ArrayList<ResultMarkMessage> marks_list = (ArrayList<ResultMarkMessage>) result.getStudentMarks();
			ArrayList<ResultSubMessage> sub_list = (ArrayList<ResultSubMessage>) result.getStudentSub();
			ArrayList<ResultMarkMessage> avg_list = (ArrayList<ResultMarkMessage>) result.getAverageMarks();
			
			String marks = "";
			String average = "";
			String subjects = "";
			int counter=0;
			for (ResultMarkMessage mark : marks_list)
			{
				counter++;
				if(counter == marks_list.size())
					marks = marks+mark.getMark();
				else
					marks = marks+mark.getMark()+",";
			}
			counter=0;
			
			for (ResultMarkMessage avg : avg_list) 
			{
				counter++;
				if(counter == avg_list.size())
					average = average+avg.getMark();
				else
					average = average+avg.getMark()+",";
			}
			counter=0;
			
			for (ResultSubMessage sub : sub_list) 
			{
				counter++;
				if(counter == avg_list.size())
					subjects = subjects+sub.getSubName();
				else
					subjects = subjects+sub.getSubName()+"|";
					
			}
			String GraphURL = "http://chart.apis.google.com/chart?"+
				"&cht=bvg&chs=400x300&chd=t:"+marks+"|"+average+
				"&chxr=2,0,100&chds=0,100&"+
				"chco=0A8C8A,EBB671&"+
				"chbh=15,0,20&"+
				"chxt=x,y&chxl=0:|"+subjects+"&"+
				"chdl=Student|Class&"+
				"chg=0,8.3,5,5"+
				"&chtt=Name:"+result.getStudentName()+"--class:"+result.getStudentClass()+"--div:"+result.getStudentDiv()+"--roll no:"+result.getStudentId();
	        Log.v("GraphURL :", GraphURL);
	        WebView webview = (WebView) findViewById(R.id.webView1);
	        webview.loadUrl(GraphURL);
			
		}
	
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graph);
		Intent intentStudentId = getIntent();
		resultResultStudentId = new ResultResultStudentId();
		
		String studentId="";
		
		if(intentStudentId != null)
		{
			studentId = intentStudentId.getStringExtra("student_id");
			resultResultStudentId.setStudentId(studentId);
			
		}
		Mresultreport.Builder builder = new Mresultreport.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
		endPoint = builder.build();
		
		new RetriverResultStats().execute(resultResultStudentId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.graph, menu);
		return true;
	}

}
