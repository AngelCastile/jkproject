package com.castiel.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button btnSend, btnPost;
	private TextView tvTest;
	private int UPDATETV = 0;

	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.arg1 == UPDATETV) {
				tvTest.setText("中国必胜");
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnSend = (Button) findViewById(R.id.btn_send);
		btnPost = (Button) findViewById(R.id.btn_post);
		tvTest = (TextView) findViewById(R.id.tv_test);
		btnPost.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						mHandler.post(new Runnable() {

							@Override
							public void run() {
								tvTest.setText("我的中国梦");
							}
						});
					}
				}).start();
			}
		});

		btnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						Message myMessage = new Message();
						myMessage.arg1 = UPDATETV;
						mHandler.sendMessage(myMessage);
					}
				}).start();
			}
		});
	}

}
