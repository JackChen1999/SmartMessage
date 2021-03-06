package com.google.smartmessage.adapter;


import android.content.Context;
import android.database.Cursor;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.google.smartmessage.R;
import com.google.smartmessage.bean.Group;
/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * 博客：     http://blog.csdn.net/axi295309066
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：SmartMessage
 * Package_Name：PACKAGE_NAME
 * Version：1.0
 * time：2016/2/16 12:35
 * des ：${TODO}
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class GroupListAdapter extends CursorAdapter {

	public GroupListAdapter(Context context, Cursor c) {
		super(context, c);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return View.inflate(context, R.layout.item_group_list, null);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder holder = getHolder(view);
		
		//将结果集转化为一个bean对象。
		Group group = Group.createFromCursor(cursor);
		
		holder.tv_grouplist_name.setText(group.getName() + " (" + group.getThread_count() + ")");
		if(DateUtils.isToday(group.getCreate_date())){
			holder.tv_grouplist_date.setText(DateFormat.getTimeFormat(context).format(group.getCreate_date()));
		}
		else{
			holder.tv_grouplist_date.setText(DateFormat.getDateFormat(context).format(group.getCreate_date()));
		}

	}

	private ViewHolder getHolder(View view){
		ViewHolder holder = (ViewHolder) view.getTag();
		if(holder == null){
			holder = new ViewHolder(view);
			view.setTag(holder);
		}
		return holder;
	}
	class ViewHolder{
		private TextView tv_grouplist_name;
		private TextView tv_grouplist_date;

		public ViewHolder(View view) {
			tv_grouplist_name = (TextView) view.findViewById(R.id.tv_grouplist_name);
			tv_grouplist_date = (TextView) view.findViewById(R.id.tv_grouplist_date);
		}
	}
}
