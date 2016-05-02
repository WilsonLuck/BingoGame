package org.wzg.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.wzg.bingogame.R;

public class MyDialog extends Dialog implements OnClickListener {
    //定义窗口，来调用下面的Dialog展示动画
	private Window window = null;
	
	private TextView mTimeTextView;
	private TextView mContentTextView;
	private TextView mCloseTextView;
	
	private CloseDialogListener listener;

	public interface CloseDialogListener {
		public void onCloseClick(View view);
	}

	public MyDialog(Context context, int layoutResID, String time,
			String content, CloseDialogListener listener) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(layoutResID);
		
		this.listener = listener;
		initViews(time, content);
		
	}

	private void initViews(String time, String content) {
		mTimeTextView = (TextView) findViewById(R.id.tv_time);
		mContentTextView = (TextView) findViewById(R.id.tv_content);
		mCloseTextView = (TextView) findViewById(R.id.tv_close);
		mTimeTextView.setText(time);
		mContentTextView.setText(content);
		mCloseTextView.setOnClickListener(this);
	}

	public void showDialog() {
		setCanceledOnTouchOutside(false);
		// 得到对话框
		window = getWindow(); 
		// 设置窗口弹出动画
		window.setWindowAnimations(R.style.my_dialog); 
		show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		listener.onCloseClick(v);
	}
}
