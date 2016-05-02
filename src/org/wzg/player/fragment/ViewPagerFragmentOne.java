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

	// ���弯�ϣ�����Դ
	private List<String> mlist;
	private GridView mGridView;
	public  CheckBox mCheckBox;
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
		// playActivity�������˴�startActivity�д�����������
		int randofnum = ((PlayerActivity) getActivity()).getnum();
		mlist = new ArrayList<String>();

		while (mlist.size() < 25) {
			mRandom = (int) (Math.random() * randofnum + 1);
			if (!mlist.contains(mRandom)) {
				mlist.add(mRandom + "");
			}
		}
		mlist.set(12, "����");

		mAdapter = new GridViewAdapter(getContext(), mlist);
		mGridView.setAdapter(mAdapter);
	}

	/**
	 * ����GridView����CheckBox�ĵ���¼�
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mCheckBox = (CheckBox) view;
		mCheckBox.setChecked(!mCheckBox.isChecked());
		if (position == 12) {
			return;
		}
		// ��ʼ��ÿС��������������
		int x = position / 5;
		int y = position % 5;
		// ����CheckBox�����¼��Ĺ�ϵ
		if (mCheckBox.isChecked()) {
			mEveryGridView[x][y] = 1;
		} else {
			mEveryGridView[x][y] = 0;
		}
		
		// �����ж��н�
		for (int i = 0; i < 5; i++) {
			int hSum = 0;
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
		// �����ж��н�
		for (int i = 0; i < 5; i++) {
			int vSum = 0;
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
		//���¶Խ����ж��н�
		int ldSum = 0;
		for (int i = 0; i < 5; i++) {
			ldSum += mEveryGridView[i][i];
			if (ldSum == 5) {
				isAward = true;
				Toast.makeText(getActivity(), "��ϲ���н��ˣ������佱�콱������ʱ��������ȷ��",
						Toast.LENGTH_SHORT).show();
				return;
			}
		}
		//���¶Խ����ж��н�
		int rdSum3 = 0;
		for (int i = 0; i < 5; i++) {
			rdSum3 += mEveryGridView[i][4 - i];
			if (rdSum3 == 5) {
				isAward = true;
				Toast.makeText(getActivity(), "��ϲ���н��ˣ������佱�콱������ʱ��������ȷ��",
						Toast.LENGTH_SHORT).show();
				return;
			}
		}
	}

	public boolean isAward() {
		return isAward;
	}

}
