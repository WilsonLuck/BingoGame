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

public class ViewPagerFragmentThree extends Fragment implements
		OnItemClickListener {

	private List<String> mlist;
	private GridView mGridView;
	private GridViewAdapter mAdapter;
	private int mRandom;
	// �Ƿ��
	private boolean isAward;
	// ����һ����ά���飬GridView������5*5�ķ���
	private int[][] mEveryGridView = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.viewpager_fragment_3, container,
				false);
		initViews(view);
		initDatas();
		return view;
	}

	private void initViews(View view) {
		mGridView = (GridView) view.findViewById(R.id.gv_table3);
		mGridView.setOnItemClickListener(this);
	}

	private void initDatas() {
		int cardnumtwo = ((PlayerActivity) getActivity()).getnum();
		mlist = new ArrayList<String>();
		while (mlist.size() < 25) {
			mRandom = (int) (Math.random() * cardnumtwo + 1);
			if (!mlist.contains(mRandom)) {
				mlist.add(mRandom + "");
			}
		}
		mlist.set(12, "����");

		mAdapter = new GridViewAdapter(getContext(), mlist);
		mGridView.setAdapter(mAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		CheckBox checkBox = (CheckBox) view;
		checkBox.setChecked(!checkBox.isChecked());

		int x = position / 5;
		int y = position % 5;
		if (position == 12) {
			return;
		}
		if (checkBox.isChecked()) {
			mEveryGridView[x][y] = 1;
		} else {
			mEveryGridView[x][y] = 0;
		}
		// �����
		int hSum = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				hSum += mEveryGridView[i][j];
				if (hSum == 5) {
					isAward = true;
					Toast.makeText(getActivity(), "��ϲ���н��ˣ������佱�콱������ʱ��������ȷ��",
							Toast.LENGTH_LONG).show();
					return;
				}
			}
		}

		// �����
		int vSum = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				vSum += mEveryGridView[j][i];
				if (vSum == 5) {
					isAward = true;
					Toast.makeText(getActivity(), "��ϲ���н��ˣ������佱�콱������ʱ��������ȷ��",
							Toast.LENGTH_LONG).show();
					return;
				}
			}
		}

		// ���¶Խ��߻�
		int ldSum = 0;
		for (int i = 0; i < 5; i++) {
			ldSum += mEveryGridView[i][i];
			if (ldSum == 5) {
				isAward = true;
				Toast.makeText(getActivity(), "��ϲ���н��ˣ������佱�콱������ʱ��������ȷ��",
						Toast.LENGTH_LONG).show();
				return;
			}
		}

		// ���¶Խ��߻�
		int rdSum = 0;
		for (int i = 0; i < 5; i++) {
			rdSum += mEveryGridView[i][4 - i];
			if (rdSum == 5) {
				isAward = true;
				Toast.makeText(getActivity(), "��ϲ���н��ˣ������佱�콱������ʱ��������ȷ��",
						Toast.LENGTH_LONG).show();
				return;
			}
		}
		
	}
	public boolean isAward() {
		return isAward;
	}

}
