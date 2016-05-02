package org.wzg.player.fragment;

import java.util.ArrayList;
import java.util.List;

import org.wzg.activity.PlayerActivity;
import org.wzg.start.adapter.GridViewAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.Toast;

import com.wzg.bingogame.R;

public class ViewPagerFragmentOne extends Fragment implements
		OnItemClickListener {

	// 定义集合，数据源
	private List<String> mlist;
	private GridView mGridView;
	public  CheckBox mCheckBox;
	private GridViewAdapter mAdapter;
	private int mRandom;
	// 是否获奖
	private boolean isAward;
	// 定义一个二维数组，GridView正好是5*5的方块
	private int[][] mEveryGridView = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.viewpager_fragment_1, container,
				false);

		initViews(view);
		initDatas();
		return view;
	}

	private void initViews(View view) {
		mGridView = (GridView) view.findViewById(R.id.gv_table1);
		mCheckBox = (CheckBox) view.findViewById(R.id.gv_item);
		mGridView.setOnItemClickListener(this);

	}

	private void initDatas() {
		// playActivity里面获得了从startActivity中传过来的数据
		int randofnum = ((PlayerActivity) getActivity()).getnum();
		mlist = new ArrayList<String>();

		while (mlist.size() < 25) {
			mRandom = (int) (Math.random() * randofnum + 1);
			if (!mlist.contains(mRandom)) {
				mlist.add(mRandom + "");
			}
		}
		mlist.set(12, "鸿鹄");

		mAdapter = new GridViewAdapter(getContext(), mlist);
		mGridView.setAdapter(mAdapter);
	}

	/**
	 * 设置GridView里面CheckBox的点击事件
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mCheckBox = (CheckBox) view;
		mCheckBox.setChecked(!mCheckBox.isChecked());
		if (position == 12) {
			return;
		}
		// 初始化每小格横坐标和纵坐标
		int x = position / 5;
		int y = position % 5;
		// 建立CheckBox与点击事件的关系
		if (mCheckBox.isChecked()) {
			mEveryGridView[x][y] = 1;
		} else {
			mEveryGridView[x][y] = 0;
		}
		
		// 横向判断中奖
		for (int i = 0; i < 5; i++) {
			int hSum = 0;
			for (int j = 0; j < 5; j++) {
				hSum += mEveryGridView[i][j];
				if (hSum == 5) {
					isAward = true;
					Toast.makeText(getActivity(), "恭喜您中奖了！请点击颁奖领奖，并及时找主持人确认",
							Toast.LENGTH_LONG).show();
					return;
				}
			}
		}
		// 竖向判断中奖
		for (int i = 0; i < 5; i++) {
			int vSum = 0;
			for (int j = 0; j < 5; j++) {
				vSum += mEveryGridView[j][i];
				if (vSum == 5) {
					isAward = true;
					Toast.makeText(getActivity(), "恭喜您中奖了！请点击颁奖领奖，并及时找主持人确认",
							Toast.LENGTH_LONG).show();
					return;
				}
			}
		}
		//左下对角线判断中奖
		int ldSum = 0;
		for (int i = 0; i < 5; i++) {
			ldSum += mEveryGridView[i][i];
			if (ldSum == 5) {
				isAward = true;
				Toast.makeText(getActivity(), "恭喜您中奖了！请点击颁奖领奖，并及时找主持人确认",
						Toast.LENGTH_SHORT).show();
				return;
			}
		}
		//右下对角线判断中奖
		int rdSum3 = 0;
		for (int i = 0; i < 5; i++) {
			rdSum3 += mEveryGridView[i][4 - i];
			if (rdSum3 == 5) {
				isAward = true;
				Toast.makeText(getActivity(), "恭喜您中奖了！请点击颁奖领奖，并及时找主持人确认",
						Toast.LENGTH_SHORT).show();
				return;
			}
		}
	}

	public boolean isAward() {
		return isAward;
	}

}
