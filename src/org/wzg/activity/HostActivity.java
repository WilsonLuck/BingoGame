package org.wzg.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wzg.bingogame.R;

public class HostActivity extends Activity implements AnimatorListener,
		OnClickListener {

	private Button mResetButton;
	private long exitTime = 0;

	// 抽奖使用旋转的TextView
	private TextView mRotateTextView;
	// 用来显示中奖数字的TextView
	private TextView mShowTextView;

	// 从start页面中获取的数字范围
	private int num;
	private Intent mGetIntent;
//	private Intent mSendIntent;
	// 旋转的TextView上面显示的数字
	private String textNum;
	// 从旋转TextView中获取的数据源集合
	private List<String> intdatas;

	// 对象动画
	private ObjectAnimator objectAnimator;
	// 旋转间隔时间
	private int duration = 500;
	// 动画刷新使用
	private int count = 0;
	// 判断TextView旋转是否停止，默认是false，旋转状态
	private boolean isStop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_host);
		mGetIntent = getIntent();
		initview();

	}

	/**
	 * 设置动画旋转
	 * 
	 * @param view
	 */
	public void rotateyAnimRun(View view) {
		// 设置旋转的方向以及角度
		objectAnimator = ObjectAnimator
				.ofFloat(view, "rotationY", 0.0F, 360.0F);
		// 设置不停的旋转
		objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
		// 设置动画间隔时间为0.5秒
		objectAnimator.setDuration(duration);
		objectAnimator.start();

		objectAnimator.addListener(this);

		// 设置动画更新监听事件，使得动画加速效果更明显
		objectAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				if (count < 10000) {
					count = count + 30;
				}

			}
		});

	}

	private void initview() {

		mResetButton = (Button) findViewById(R.id.btn_reset);
		mRotateTextView = (TextView) findViewById(R.id.rotate);
		mShowTextView = (TextView) findViewById(R.id.tv_showluckynum);

		num = mGetIntent.getIntExtra("num", 32);
		intdatas = new ArrayList<String>();
		
		getRandom();

		mResetButton.setOnClickListener(this);
		mRotateTextView.setOnClickListener(this);

		//使得TextView刚开始就有text
		mRotateTextView.setText(textNum + "");
	}

	@Override
	public void onAnimationStart(Animator animation) {

	}

	@Override
	public void onAnimationEnd(Animator animation) {
	}

	@Override
	public void onAnimationCancel(Animator animation) {
	}

	@Override
	public void onAnimationRepeat(Animator animation) {

		if (duration > 200) {
			duration = duration - 200;
		}
		getRandom();
		mRotateTextView.setText(textNum + "");
		//这里百度解释是增加这个值逆时针旋转，可以比喻成速率，即加速的意思
		mRotateTextView.setRotationY(count);
		
		objectAnimator.setDuration(duration);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_reset:
			extracted();
			break;
        
		case R.id.rotate:
			//这里为什么抽取不出来case情况呀？
			if (!isStop) {
				rotateyAnimRun(mRotateTextView);
			} else {
				//这里是图片刚开始转，不可点击
				if (duration > 200) {
					Toast.makeText(this, "不要频繁点击呦~", Toast.LENGTH_SHORT).show();
					return;
				}else{
				objectAnimator.cancel();
				mRotateTextView.setRotationY(0);
				intdatas.add(mRotateTextView.getText().toString().trim());
				mShowTextView.setText(mShowTextView.getText() + ""
						+ mRotateTextView.getText() + ",");
//				mSendIntent.putExtra("showTextNum", mShowTextView + "");
				duration = 500;
				}
			}
			//恢复到动画停止状态
			isStop = !isStop;
			break;
		}
		
	}
    
	//点击重置按钮时的事件
	private void extracted() {
		if (System.currentTimeMillis() - exitTime > 2000) {
			Toast.makeText(HostActivity.this, "再按一次进入设置页面",
					Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			Intent intent = new Intent(HostActivity.this,
					StartActivity.class);
			startActivity(intent);
			finish();
		}
	}

	public void getRandom() {
		Random random = new Random();
		String textRandom = random.nextInt(num) + 1 + "";
		if (!intdatas.contains(textRandom)) {
			textNum = textRandom;
		}
	}

}
