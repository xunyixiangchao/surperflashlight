package com.lis.superflashlight;

import java.util.HashMap;
import java.util.Map;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Morse extends Warninglight {

	private final int DOT_TIME = 200;           // 点停留的时间 单位：毫秒
	private final int LINE_TMIE = DOT_TIME * 3; // 线停留的时间
	private final int DOT_LINE_TIME = DOT_TIME; // 点到线的时间间隔

	private final int CHAR_CHAR_TIME = DOT_TIME * 3; // 字符到字符之间的时间间隔

	private final int WORD_WORD_TIME = DOT_TIME * 7; // 单词到单词之间的时间间隔

	private Map<Character, String> mMorseCodeMap = new HashMap<Character, String>();
	private String mMorseCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMorseCodeMap.put('a', ".-");
		mMorseCodeMap.put('b', "-...");
		mMorseCodeMap.put('c', "-.-.");
		mMorseCodeMap.put('d', "-..");
		mMorseCodeMap.put('e', ".");
		mMorseCodeMap.put('f', "..-.");
		mMorseCodeMap.put('g', "--.");
		mMorseCodeMap.put('h', "....");
		mMorseCodeMap.put('i', "..");
		mMorseCodeMap.put('j', ".---");
		mMorseCodeMap.put('k', "-.-");
		mMorseCodeMap.put('l', ".-..");
		mMorseCodeMap.put('m', "--");
		mMorseCodeMap.put('n', "-.");
		mMorseCodeMap.put('o', "---");
		mMorseCodeMap.put('p', ".--.");
		mMorseCodeMap.put('q', "--.-");
		mMorseCodeMap.put('r', ".-.");
		mMorseCodeMap.put('s', "...");
		mMorseCodeMap.put('t', "-");
		mMorseCodeMap.put('u', "..-");
		mMorseCodeMap.put('v', "...-");
		mMorseCodeMap.put('w', ".--");
		mMorseCodeMap.put('x', "-..-");
		mMorseCodeMap.put('y', "-.--");
		mMorseCodeMap.put('z', "--..");

		mMorseCodeMap.put('0', "-----");
		mMorseCodeMap.put('1', ".----");
		mMorseCodeMap.put('2', "..---");
		mMorseCodeMap.put('3', "...--");
		mMorseCodeMap.put('4', "....-");
		mMorseCodeMap.put('5', ".....");
		mMorseCodeMap.put('6', "-....");
		mMorseCodeMap.put('7', "--...");
		mMorseCodeMap.put('8', "---..");
		mMorseCodeMap.put('9', "----.");
	}

	public void onClick_SendMorseCode(View view) {
		if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
			Toast.makeText(this, "没有闪光灯可用", Toast.LENGTH_LONG).show();
			return;
		}
		if (verifyMorseCode()) {
			sendSentense(mMorseCode);
		}
	}

	private void sleep(int t) {
		try {
			Thread.sleep(t);

		} catch (Exception e) {
		}
	}

	// 发送点
	private void sendDot() {
		openFlashlight();
		sleep(DOT_TIME);
		closeFlashlight();
	}

	// 发送线
	private void sendLine() {
		openFlashlight();
		sleep(LINE_TMIE);
		closeFlashlight();
	}

	// 发送字符
	private void sendChar(char c) {
		String morseCode = mMorseCodeMap.get(c);
		if (morseCode != null) {
			char lastChar = ' ';
			for (int i = 0; i < morseCode.length(); i++) {

				char dotLine = morseCode.charAt(i);
				if (dotLine == '.') {
					sendDot();
				} else if (dotLine == '-') {
					sendLine();
				}
				if (i > 0 && i < morseCode.length()) {
					if (lastChar == '.' && dotLine == '-') {
						sleep(DOT_LINE_TIME);
					}
				}
				lastChar = dotLine;

			}
		}
		// openFlashlight(); //这个会导致发送完后，闪光灯常亮
	}

	// 发送单词
	private void sendWord(String s) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			sendChar(c);
			if (i < s.length() - 1) {
				sleep(CHAR_CHAR_TIME);
			}
		}
	}

	// 发送句子
	private void sendSentense(String s) {

		String[] words = s.split(" +");
		for (int i = 0; i < words.length; i++) {
			sendWord(words[i]);
			if (i < words.length - 1) {
				sleep(WORD_WORD_TIME);
			}
		}
		Toast.makeText(this, "摩尔斯电码已经发送完成！", Toast.LENGTH_SHORT);
	}

	private boolean verifyMorseCode() {
		mMorseCode = mEditTextMorseCode.getText().toString().toLowerCase();
		if ("".equals(mMorseCode)) {
			Toast.makeText(this, "请输入摩尔斯电码字符！", Toast.LENGTH_SHORT);
			return false;
		}
		for (int i = 0; i < mMorseCode.length(); i++) {
			char c = mMorseCode.charAt(i);
			if (!(c >= 'a' && c <= 'z') && !(c >= '0' && c <= '9') && c != ' ') {
				Toast.makeText(this, "摩尔斯电码只能是字母和数字！", Toast.LENGTH_SHORT);
				return false;
			}

		}
		return true;

	}
}
