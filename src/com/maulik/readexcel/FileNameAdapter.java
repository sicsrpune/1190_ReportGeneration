package com.maulik.readexcel;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appspot.maulikabd.mresultreport.model.ResultResultFileMessage;

public class FileNameAdapter extends ArrayAdapter<ResultResultFileMessage> 
{

	private final Context context;
	private ArrayList<ResultResultFileMessage> file_list;
	
	public FileNameAdapter(Context context, ArrayList<ResultResultFileMessage> file_list) 
	{
		super(context, R.layout.row_file_name, file_list);
		this.file_list = file_list;
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
	
		LayoutInflater inflater =  (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row_file_name,null);
		
		
		String file_name = file_list.get(position).getFileName();
		
		
		TextView textViewFileName = (TextView) rowView.findViewById(R.id.textViewFileName);
		textViewFileName.setText(file_name);
		
		return rowView;
	}

}
