package org.wzg.start.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;

import com.wzg.bingogame.R;

public class GridViewAdapter extends BaseAdapter {

	private Context mcontext;
	private List<String> mlist;

	public GridViewAdapter(Context mcontext, List<String> mlist) {
		this.mcontext = mcontext;
		this.mlist = mlist;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mlist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" }) @Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = LayoutInflater.from(mcontext).inflate(
				R.layout.gridview_item, null);
		CheckBox mCheckBox = (CheckBox) convertView.findViewById(R.id.gv_item);
		mCheckBox.setText((mlist.get(position)));
		if (position == 12) {
			mCheckBox.setChecked(true);
			mCheckBox.setClickable(false);
		} else {
			mCheckBox.setClickable(false);
		}
		return convertView;
	}

}
