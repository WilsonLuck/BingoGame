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

public class RangeOfNumberAdapter extends BaseAdapter {
	private List<Integer> mDataRangeList;
	private Context mContext;

	public RangeOfNumberAdapter(Context mContext, List<Integer> mDataRangeList2) {
		this.mContext = mContext;
		this.mDataRangeList = mDataRangeList2;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return mDataRangeList.size();
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
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.activity_start_item, null);
		}
		RangeViewHolder viewHolder = (RangeViewHolder) convertView.getTag();
		if (viewHolder == null) {
			viewHolder = new RangeViewHolder();
			viewHolder.textView1 = (TextView) convertView
					.findViewById(R.id.start_item);
			convertView.setTag(viewHolder);
		}
		viewHolder.textView1.setText(mDataRangeList.get(position) + "");
		// 绑定控件，并进行操作
		return convertView;
	}

}

class RangeViewHolder {
	TextView textView1;
}
