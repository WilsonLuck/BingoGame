package org.wzg.activity;

import org.wzg.start.adapter.MyViewPagerAdapter;
import org.wzg.view.MyDialog;
import org.wzg.view.MyDialog.CloseDialogListener;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wzg.bingogame.R;

public class PlayerActivity extends FragmentActivity implements
		OnPageChangeListener, OnClickListener, CloseDialogListener {
	private ViewPager mViewPager;
	private Button mGivePrizeButton;
	private Button mResetButton;
	private TextView mCardOneTextView;
	private TextView mCardTwoTextView;
	private TextView mCardThreeTextView;
	private long exitTime = 0;

	private MyViewPagerAdapter mAdapter;

	public static final int PAGE_ONE = 0;
	public static final int PAGE_TWO = 1;
	public static final int PAGE_THREE = 2;

	public int cardcount;
	public int count;
//	private Intent intent;
	private MyDialog myDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);

		// 这里出现了未先绑定就就对textview进行操作
		// 通过看logcat发现textview空指针，应检查
		// textview是否赋值，如果赋值了还是不行，再
		// 去检查是否在未绑定控件之间就进行了使用
		initViews();
		initData();
		getcardnum();

	}

	private void initData() {
		Intent intent = getIntent();
		cardcount = intent.getIntExtra("cardnum", 0);
		count = intent.getIntExtra("num", 0);

		mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), this);
		mViewPager.setAdapter(mAdapter);
	}

	private void initViews() {

		mCardOneTextView = (TextView) findViewById(R.id.card1);
		mCardTwoTextView = (TextView) findViewById(R.id.card2);
		mCardThreeTextView = (TextView) findViewById(R.id.card3);
		mViewPager = (ViewPager) findViewById(R.id.viewPager);
		mGivePrizeButton = (Button) findViewById(R.id.giveprize);
		mResetButton = (Button) findViewById(R.id.reset);

		mViewPager.setCurrentItem(0);

		mViewPager.addOnPageChangeListener(this);

		mCardOneTextView.setOnClickListener(this);
		mCardTwoTextView.setOnClickListener(this);
		mCardThreeTextView.setOnClickListener(this);

		mResetButton.setOnClickListener(this);
		mGivePrizeButton.setOnClickListener(this);
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		// state的状态有三个，0表示什么都没滑，1正在滑动，2滑动完毕
		if (state == 2) {
			switch (mViewPager.getCurrentItem()) {
			case PAGE_ONE:
				mCardOneTextView.setTextColor(Color.GRAY);
				mCardTwoTextView.setTextColor(Color.BLACK);
				mCardThreeTextView.setTextColor(Color.BLACK);
				break;
			case PAGE_TWO:
				mCardTwoTextView.setTextColor(Color.GRAY);
				mCardOneTextView.setTextColor(Color.BLACK);
				mCardThreeTextView.setTextColor(Color.BLACK);
				break;
			case PAGE_THREE:
				mCardThreeTextView.setTextColor(Color.GRAY);
				mCardOneTextView.setTextColor(Color.BLACK);
				mCardTwoTextView.setTextColor(Color.BLACK);
				break;
			}
		}
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.card1:
			mViewPager.setCurrentItem(PAGE_ONE);
			break;
		case R.id.card2:
			mViewPager.setCurrentItem(PAGE_TWO);
			break;
		case R.id.card3:
			mViewPager.setCurrentItem(PAGE_THREE);
			break;
		case R.id.reset:
			extracted();
			break;
		case R.id.giveprize:
			// 这里使用dialog做,dialog里面的第一个参数为日期，第二个是中奖号码intent.getStringExtra("showTextNum")
			if (mAdapter.isAward()) {
				myDialog = new MyDialog(this, R.layout.my_dialog, "", "", this);
				myDialog.showDialog();
			}
		}
	}

	private void extracted() {
		// TODO Auto-generated method stub
		if (System.currentTimeMillis() - exitTime > 2000) {
			Toast.makeText(PlayerActivity.this, "再按一次进入设置页面",
					Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {

			Intent intent = new Intent(PlayerActivity.this, StartActivity.class);
			startActivity(intent);
			finish();
		}
	}

	public int getnum() {
		// TODO Auto-generated method stub
		return count;
	}

	public int getcardnum() {
		if (cardcount == 1) {
			// 这个gone是消失的
			mCardTwoTextView.setVisibility(View.GONE);
			mCardThreeTextView.setVisibility(View.GONE);
		} else if (cardcount == 2) {
			mCardThreeTextView.setVisibility(View.GONE);
		}
		return cardcount;
	}

	// 重写接口方法
	@Override
	public void onCloseClick(View view) {
		// TODO Auto-generated method stub
		myDialog.dismiss();
	}

}
