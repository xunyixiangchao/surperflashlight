package com.lis.superflashlight;

import android.view.View;

public class MainActivity extends Bulb {

	public void onClick_ToFlashlight(View view) {
		hideAllUI();
		mUIFlashlight.setVisibility(View.VISIBLE);
		mLastUIType = UIType.UI_TYPE_FLASHLIGHT;
		mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
	}

	public void onClick_ToWarninglight(View view) {

		hideAllUI();
		mUIWarninglight.setVisibility(View.VISIBLE);
		mLastUIType = UIType.UI_TYPE_WARNINGLIGHT;
		mCurrentUIType = mLastUIType;
		screenBrightness(1f);
		new WarninglightThread().start();
	}

	public void onClick_ToMorse(View view) {

		hideAllUI();
		mUIMorse.setVisibility(View.VISIBLE);
		mLastUIType = UIType.UI_TYPE_MORSE;
		mCurrentUIType = mLastUIType;
	}

	public void onClick_ToBulb(View view) {

		hideAllUI();
		mUIBulb.setVisibility(View.VISIBLE);
		screenBrightness(1f);
		mLastUIType = UIType.UI_TYPE_BLUB;
		mCurrentUIType = mLastUIType;

	}

	public void onClick_Controller(View view) {
		hideAllUI();
		if (mCurrentUIType != UIType.UI_TYPE_MAIN) {
			mUIMain.setVisibility(View.VISIBLE);
			mCurrentUIType = UIType.UI_TYPE_MAIN;
			mWarninglightFlicker = false;
			screenBrightness(mDefaulScreenBrightness / 255f);
			if (mBulbCrossFadeFlag) {
				// mDrawable.reverseTransition(0);
				mBulbCrossFadeFlag = false;
			}
		} else {
			switch (mLastUIType) {
				case UI_TYPE_FLASHLIGHT:
					mUIFlashlight.setVisibility(View.VISIBLE);
					screenBrightness(1f);
					mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
					break;
				case UI_TYPE_WARNINGLIGHT:
					mUIWarninglight.setVisibility(View.VISIBLE);
					mCurrentUIType = UIType.UI_TYPE_WARNINGLIGHT;
					new WarninglightThread().start();
				case UI_TYPE_MORSE:
					mUIMorse.setVisibility(View.VISIBLE);
					mCurrentUIType = UIType.UI_TYPE_MORSE;
				case UI_TYPE_BLUB:
					mUIBulb.setVisibility(View.VISIBLE);
					mCurrentUIType = UIType.UI_TYPE_BLUB;

				default:
					break;
			}
		}
	}
}
