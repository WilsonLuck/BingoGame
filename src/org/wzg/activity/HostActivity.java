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

	// �齱ʹ����ת��TextView
	private TextView mRotateTextView;
	// ������ʾ�н����ֵ�TextView
	private TextView mShowTextView;

	// ��startҳ���л�ȡ�����ַ�Χ
	private int num;
	private Intent mGetIntent;
//	private Intent mSendIntent;
	// ��ת��TextView������ʾ������
	private String textNum;
	// ����תTextView�л�ȡ������Դ����
	private List<String> intdatas;

	// ���󶯻�
	private ObjectAnimator objectAnimator;
	// ��ת���ʱ��
	private int duration = 500;
	// ����ˢ��ʹ��
	private int count = 0;
	// �ж�TextView��ת�Ƿ�ֹͣ��Ĭ����false����ת״̬
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
	 * ���ö�����ת
	 * 
	 * @param view
	 */
	public void rotateyAnimRun(View view) {
		// ������ת�ķ����Լ��Ƕ�
		objectAnimator = ObjectAnimator
				.ofFloat(view, "rotationY", 0.0F, 360.0F);
		// ���ò�ͣ����ת
		objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
		// ���ö������ʱ��Ϊ0.5��
		objectAnimator.setDuration(duration);
		objectAnimator.start();

		objectAnimator.addListener(this);

		// ���ö������¼����¼���ʹ�ö�������Ч��������
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

		//ʹ��TextView�տ�ʼ����text
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
		//����ٶȽ������������ֵ��ʱ����ת�����Ա��������ʣ������ٵ���˼
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
			//����Ϊʲô��ȡ������case���ѽ��
			if (!isStop) {
				rotateyAnimRun(mRotateTextView);
			} else {
				//������ͼƬ�տ�ʼת�����ɵ��
				if (duration > 200) {
					Toast.makeText(this, "��ҪƵ�������~", Toast.LENGTH_SHORT).show();
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
			//�ָ�������ֹͣ״̬
			isStop = !isStop;
			break;
		}
		
	}
    
	//������ð�ťʱ���¼�
	private void extracted() {
		if (System.currentTimeMillis() - exitTime > 2000) {
			Toast.makeText(HostActivity.this, "�ٰ�һ�ν�������ҳ��",
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
