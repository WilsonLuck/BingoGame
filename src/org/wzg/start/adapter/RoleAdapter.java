package org.wzg.start.adapter;

import java.util.List;

import com.wzg.bingogame.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RoleAdapter extends BaseAdapter{
	private List<String> mDataRoleList;
	private Context mContext;
	
	public RoleAdapter(Context mContext, List<String> mDataRoleList) {
		this.mContext = mContext;
		this.mDataRoleList = mDataRoleList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//定义适配器的时候，一定要把构造方法和对应的方法返回值写好！
		return mDataRoleList.size();
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.activity_start_item, null);
		}
		RoleViewHolder viewHolder = (RoleViewHolder)convertView.getTag();
		if(viewHolder == null){
			viewHolder = new RoleViewHolder();
			viewHolder.textView2 = (TextView)convertView.findViewById(R.id.start_item);
			convertView.setTag(viewHolder);
		}
		viewHolder.textView2.setText(mDataRoleList.get(position));
		
		// 绑定控件，并进行操作
		return convertView;
	}

}
class RoleViewHolder{
	TextView textView2;
}
