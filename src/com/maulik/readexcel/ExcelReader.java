package com.maulik.readexcel;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import android.os.AsyncTask;
import android.util.Log;

import com.appspot.maulikabd.mresultreport.Mresultreport;
import com.appspot.maulikabd.mresultreport.model.ResultResultRowMessage;

public class ExcelReader
{

	private String inputFile;
	private Mresultreport endPoint;
	private String marks;
	private String subjects;
	private String file_name;

	public ExcelReader(Mresultreport endPoint)
	{
		this.endPoint = endPoint;
	}

	private class SendRowToServer extends AsyncTask<ResultResultRowMessage, Void, Void>
	{

		@Override
		protected Void doInBackground(ResultResultRowMessage... param) 
		{
			try
			{
				endPoint.mresultreport().addResultRow(param[0]).execute();
				//endPoint.mresultreport().addResultFileName(content)
				
			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
	
	public void setFileName(String file_name)
	{
		this.file_name = file_name;
	}
	
	public void setInputFile(String inputFile) 
	{
		this.inputFile = inputFile;
	}
	
	public void read() throws IOException 
	{
		File inputWorkbook = new File(inputFile);
		Workbook w;
		subjects="";
		marks="";
		
		
		try 
		{
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines

			int col_counter=0;
			int row_counter=0;
			String temp="";
			int rows = sheet.getRows();
			int cols = sheet.getColumns();
			
			Log.e("counted rows and cols", rows+" "+cols);
			
			
			
			for (int j = 0; j < rows+1; j++) 
			{
				row_counter++;
				ResultResultRowMessage rowMessage = new ResultResultRowMessage();;
				rowMessage.setFile(file_name);
				
				if(subjects.length() > 0)
				{
					Log.e("subjects", subjects);
					if(row_counter==1)
						rowMessage.setStudentSub(subjects);
					
				}
				
				if(marks.length() > 0)
				{
					Log.e("marks ", marks);
					if(row_counter>1)
					{
						rowMessage.setStudentMarks(marks);
						rowMessage.setStudentSub(subjects);
						new SendRowToServer().execute(rowMessage);
					}
					if(j == rows+1)
						break;
				}
				
				
				marks="";
				col_counter=0;
				
				for (int i = 0; i < cols; i++) 
				{
					Cell cell = sheet.getCell(i, j);
						
					col_counter++;
					if(row_counter == 1)
					{
					    if(col_counter > 4)
					    {
					        if(subjects.equals(""))  
					            subjects += ""+cell.getContents();
					        else
					            subjects += "#"+cell.getContents();
					    }
					}


					if(row_counter > 1)
					{	
					    if(col_counter == 1)
					    {
					        rowMessage.setStudentId(""+cell.getContents());
					        Log.d("id", cell.getContents());
					    }
					    else if(col_counter == 2)
					    {
					        rowMessage.setStudentName(""+cell.getContents());
					        Log.d("name", cell.getContents());
					        
					    }
					    else if(col_counter == 3)
					    {
					        rowMessage.setStudentClass(""+cell.getContents());
					        Log.d("class", cell.getContents());
					    }
					    else if(col_counter == 4)
					    {
					        rowMessage.setStudentDiv(""+cell.getContents());
					        Log.d("div", cell.getContents());
					    }
					    else
					    {	
					        if(marks.equals(""))  
					            marks += ""+cell.getContents();
					        else
					            marks += "#"+cell.getContents();
					    }
						
					}
				}
			}
		}
		catch (BiffException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
	}


}