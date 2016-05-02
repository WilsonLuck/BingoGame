package org.wzg.start.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wzg.bingogame.R;

public class NumOfCardAdapter extends BaseAdapter {
	private List<Integer> mDataCardList;
	private Context mContext;

	public NumOfCardAdapter(Context mContext, List<Integer> mDataCardList2) {
		this.mContext = mContext;
		this.mDataCardList = mDataCardList2;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDataCardList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("InflateParams") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// 解析布局文件，转换成视图
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.activity_start_item, null);
		}
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		if (viewHolder == null) {
			viewHolder = new ViewHolder();
			viewHolder.textView3 = (TextView) convertView
					.findViewById(R.id.start_item);
			convertView.setTag(viewHolder);
		}
		viewHolder.textView3.setText(mDataCardList.get(position)+"");
		// 绑定控件，并进行操作
		return convertView;
	}

}

class ViewHolder {
	TextView textView3;
}
