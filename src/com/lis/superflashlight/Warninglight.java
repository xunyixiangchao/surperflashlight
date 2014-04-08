package com.lis.superflashlight;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.lis.surperflashlight.R;

public class Warninglight extends Flashlight {

	protected boolean mWarninglightFlicker;  // true:闪烁 false:停止闪烁
	protected boolean mWarninglightstate;    // true:on-off false:off-on

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWarninglightFlicker = true;
	}

	class WarninglightThread extends Thread {
		public void run() {
			mWarninglightFlicker = true;
			while (mWarninglightFlicker) {
				try {
					Thread.sleep(300);
					mWarningHandler.sendEmptyMessage(0);
				} catch (Exception e) {
				}
			}
		}
	}

	private Handler mWarningHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (mWarninglightstate) {
				mImageViewWarninglight1.setImageResource(R.drawable.warning_light_on);
				mImageViewWarninglight2.setImageResource(R.drawable.warning_light_off);
				mWarninglightstate = false;
			} else {
				mImageViewWarninglight1.setImageResource(R.drawable.warning_light_off);
				mImageViewWarninglight2.setImageResource(R.drawable.warning_light_on);
				mWarninglightstate = true;
			}
		}
	};

}
