package com.lis.superflashlight;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lis.surperflashlight.R;

public class BaseActivity extends Activity {
	protected enum UIType {
		UI_TYPE_MAIN, UI_TYPE_FLASHLIGHT, UI_TYPE_WARNINGLIGHT, UI_TYPE_MORSE, UI_TYPE_BLUB, UI_TYPE_COLOR, UI_TYPE_POLICE, UI_TYPE_SETTINGS
	}

	protected ImageView mImageViewFlashlight;
	protected ImageView mImageViewFlashlightController;
	protected ImageView mImageViewWarninglight1;
	protected ImageView mImageViewWarninglight2;
	protected ImageView mImageViewBulb;

	protected EditText mEditTextMorseCode;

	protected Camera mCamera;
	protected Parameters mParameters;

	protected FrameLayout mUIFlashlight;
	protected LinearLayout mUIMain;
	protected LinearLayout mUIWarninglight;
	protected LinearLayout mUIMorse;
	protected FrameLayout mUIBulb;

	protected UIType mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
	protected UIType mLastUIType = UIType.UI_TYPE_FLASHLIGHT;

	protected int mDefaulScreenBrightness;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mUIFlashlight = (FrameLayout) findViewById(R.id.framelayout_flashlight);
		mUIBulb = (FrameLayout) findViewById(R.id.framelayout_bulb);
		mUIMain = (LinearLayout) findViewById(R.id.linearlayout_main);
		mUIWarninglight = (LinearLayout) findViewById(R.id.linearlayout_warning_light);
		mUIMorse = (LinearLayout) findViewById(R.id.linearlayout_morse);

		mImageViewFlashlight = (ImageView) findViewById(R.id.imageview_flashlight);
		mImageViewFlashlightController = (ImageView) findViewById(R.id.imageview_flashlight_controller);
		mImageViewWarninglight1 = (ImageView) findViewById(R.id.imageview_warning_light1);
		mImageViewWarninglight2 = (ImageView) findViewById(R.id.imageview_warning_light2);
		mImageViewBulb = (ImageView) findViewById(R.id.imageview_bulb);
		mEditTextMorseCode = (EditText) findViewById(R.id.edittext_morse_code);

		mDefaulScreenBrightness = getScreenBrightneww();

	}

	protected void hideAllUI() {
		mUIMain.setVisibility(View.GONE);
		mUIFlashlight.setVisibility(View.GONE);
		mUIWarninglight.setVisibility(View.GONE);
		mUIMorse.setVisibility(View.GONE);
		mUIBulb.setVisibility(View.GONE);
	}

	protected void screenBrightness(float value) {
		try {
			WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
			layoutParams.screenBrightness = value;
			getWindow().setAttributes(layoutParams);
		} catch (Exception e) {
		}
	}

	protected int getScreenBrightneww() {
		int value = 0;
		try {
			value = android.provider.Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
		} catch (Exception e) {
		}
		return value;
	}
}
