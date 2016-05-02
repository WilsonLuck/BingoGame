package org.wzg.activity;

import java.util.ArrayList;
import java.util.List;

import org.wzg.start.adapter.NumOfCardAdapter;
import org.wzg.start.adapter.RangeOfNumberAdapter;
import org.wzg.start.adapter.RoleAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;

import com.wzg.bingogame.R;

public class StartActivity extends Activity implements OnItemSelectedListener,
		OnClickListener {
	// ����ؼ�
	private Spinner mRangeOfNumberSpinner;
	private Spinner mRoleSpinner;
	private Spinner mNumOfCardSpinner;

	private Button mOkButton;
	private Button mExitButton;

	// ��������Դ
	private List<Integer> mDataRangeList;
	private List<String> mDataRoleList;
	private List<Integer> mDataCardList;

	// ����������
	private RangeOfNumberAdapter mRangeOfNumberAdapter;
	private RoleAdapter mRoleAdapter;
	private NumOfCardAdapter mNumOfCardAdapter;

	// private String host;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		initViews();
		initData();

	}

	private void initViews() {
		mRangeOfNumberSpinner = (Spinner) findViewById(R.id.spinner_rangeofnumber);
		mRoleSpinner = (Spinner) findViewById(R.id.spinner_role);
		mNumOfCardSpinner = (Spinner) findViewById(R.id.spinner_numofcard);
		mOkButton = (Button) findViewById(R.id.btn_ok);
		mExitButton = (Button) findViewById(R.id.btn_exit);

		mOkButton.setOnClickListener(this);
		mExitButton.setOnClickListener(this);

	}

	private void initData() {

		mDataRangeList = new ArrayList<Integer>();
		mDataRoleList = new ArrayList<String>();
		mDataCardList = new ArrayList<Integer>();
		// ��ʼ��������
		mRangeOfNumberAdapter = new RangeOfNumberAdapter(this, mDataRangeList);
		mRoleAdapter = new RoleAdapter(this, mDataRoleList);
		mNumOfCardAdapter = new NumOfCardAdapter(this, mDataCardList);

		mDataRangeList.add(32);
		mDataRangeList.add(64);
		mDataRangeList.add(128);

		mDataRoleList.add("������");
		mDataRoleList.add("�齱��");

		mDataCardList.add(1);
		mDataCardList.add(2);
		mDataCardList.add(3);

		// ��������_____***����İ����������ܷ��ڼ���������ǰ��
		mRangeOfNumberSpinner.setAdapter(mRangeOfNumberAdapter);
		mRoleSpinner.setAdapter(mRoleAdapter);
		mNumOfCardSpinner.setAdapter(mNumOfCardAdapter);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		switch (view.getId()) {
		case R.id.spinner_rangeofnumber:
			break;
		case R.id.spinner_role:
			break;
		case R.id.spinner_numofcard:
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_ok:
			extracted();

			break;

		case R.id.btn_exit:
			finish();
			break;
		}
	}

	private void extracted() {
		// ���￨�˺ܾã�Ҫ��סҪ��ȡÿһ�е�ʱ������Ǽ��ϣ�����ͨ����ȡ��ѡ���е�λ�û���id������±�ֵ
		// ��õľ���String���͵Ļ����Լ��������͵ģ��ڽ��в�������
		String host = mDataRoleList.get((int) mRoleSpinner
				.getSelectedItemPosition());
		if (host.equals("������")) {
			Intent intent = new Intent(StartActivity.this, HostActivity.class);
			intent.putExtra("num", mDataRangeList.get(mRangeOfNumberSpinner.getSelectedItemPosition()));
			startActivity(intent);
			finish();
		} else if (host.equals("�齱��")) {
			Intent intent2 = new Intent(StartActivity.this, PlayerActivity.class);
			intent2.putExtra("cardnum", mNumOfCardSpinner.getSelectedItemPosition()+1);
			intent2.putExtra("num", mDataRangeList.get(mRangeOfNumberSpinner.getSelectedItemPosition()));
//			Log.e("aaa", (String)mNumOfCardSpinner.getSelectedItem());
			startActivity(intent2);
			finish();
		}
	}
}
