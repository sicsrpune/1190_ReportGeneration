package com.maulik.readexcel;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appspot.maulikabd.mresultreport.model.ResultResultStudentId;

public class StudentAdapter extends ArrayAdapter<ResultResultStudentId> 
{

	private final Context context;
	private ArrayList<ResultResultStudentId> stu_list;
	
	public StudentAdapter(Context context, ArrayList<ResultResultStudentId> stu_list) 
	{
		super(context, R.layout.row_student, stu_list);
		this.stu_list = stu_list;
		this.context = context;
	
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		LayoutInflater inflater =  (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row_student,null);
		
		
		String student_name = stu_list.get(position).getStudentName();
		String student_id = stu_list.get(position).getStudentId();
		
		TextView textViewStudentId = (TextView) rowView.findViewById(R.id.textViewStudentId);
		textViewStudentId.setText(student_id);
		
		TextView textViewStudentName = (TextView) rowView.findViewById(R.id.textViewStudentName);
		textViewStudentName.setText(student_name);
		
		return rowView;
		
	}

}
