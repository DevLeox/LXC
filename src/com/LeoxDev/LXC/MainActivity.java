package com.LeoxDev.LXC;

import java.util.Timer;
import java.util.TimerTask;
import android.os.*;
import android.view.animation.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.view.View;

public class MainActivity extends  Activity {
	
	
	String formattedNumber = "";
	String formattedNeed = "";
	
	LinearLayout linearAll;
	LinearLayout linearTop;
	LinearLayout linearVT;
	LinearLayout linearNum;
	ImageView imageCoin;
	LinearLayout linearEnergy;
	LinearLayout linearVB;
	LinearLayout linearMenu;
	LinearLayout linearPlus;
	LinearLayout linearNeed;
	LinearLayout linearLvl;
	TextView textviewEvery;
	TextView textviewPlus;
	TextView textviewRequired;
	TextView textviewNeed;
	TextView textviewLvl;
	TextView textviewLevel;
	ImageView imageviewNcoin;
	TextView numberCoin;
	ImageView imageviewEnergy;
	TextView textviewNowEnergy;
	TextView textviewFloat;
	TextView textviewAllEnergy;
	LinearLayout linearCoin;
	LinearLayout linearBoost;
	ImageView imageviewCoin;
	TextView textviewCoin;
	ImageView imageviewBoost;
	TextView textviewBoost;
	TextView textviewEarn;
	
	Timer time = new Timer();
	TimerTask timer;
	SharedPreferences Save;
	Intent in = new Intent();
	int back = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		linearAll = (LinearLayout) findViewById(R.id.linearAll);
		linearTop = (LinearLayout) findViewById(R.id.linearTop);
		linearVT = (LinearLayout) findViewById(R.id.linearVT);
		linearNum = (LinearLayout) findViewById(R.id.linearNum);
		imageCoin = (ImageView) findViewById(R.id.imageCoin);
		linearEnergy = (LinearLayout) findViewById(R.id.linearEnergy);
		linearVB = (LinearLayout) findViewById(R.id.linearVB);
		linearMenu = (LinearLayout) findViewById(R.id.linearMenu);
		linearPlus = (LinearLayout) findViewById(R.id.linearPlus);
		linearNeed = (LinearLayout) findViewById(R.id.linearNeed);
		linearLvl = (LinearLayout) findViewById(R.id.linearLvl);
		textviewEvery = (TextView) findViewById(R.id.textviewEvery);
		textviewPlus = (TextView) findViewById(R.id.textviewPlus);
		textviewRequired = (TextView) findViewById(R.id.textviewRequired);
		textviewNeed = (TextView) findViewById(R.id.textviewNeed);
		textviewLvl = (TextView) findViewById(R.id.textviewLvl);
		textviewLevel = (TextView) findViewById(R.id.textviewLevel);
		imageviewNcoin = (ImageView) findViewById(R.id.imageviewNcoin);
		numberCoin = (TextView) findViewById(R.id.numberCoin);
		imageviewEnergy = (ImageView) findViewById(R.id.imageviewEnergy);
		textviewNowEnergy = (TextView) findViewById(R.id.textviewNowEnergy);
		textviewFloat = (TextView) findViewById(R.id.textviewFloat);
		textviewAllEnergy = (TextView) findViewById(R.id.textviewAllEnergy);
		linearCoin = (LinearLayout) findViewById(R.id.linearCoin);
		linearBoost = (LinearLayout) findViewById(R.id.linearBoost);
		imageviewCoin = (ImageView) findViewById(R.id.imageviewCoin);
		textviewCoin = (TextView) findViewById(R.id.textviewCoin);
		imageviewBoost = (ImageView) findViewById(R.id.imageviewBoost);
		textviewBoost = (TextView) findViewById(R.id.textviewBoost);
		Save = getSharedPreferences("Save", Activity.MODE_PRIVATE);
		
		imageCoin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(Integer.parseInt(Save.getString("plus" , "")) < Integer.parseInt(Save.getString("energyNow" , ""))){
					Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyNow", "")) - Integer.parseInt(Save.getString("plus" , "")))).commit();
					Save.edit().putString("number", String.valueOf(Integer.parseInt(Save.getString("number", "")) + Integer.parseInt(Save.getString("plus", "")))).commit();
					formattedNumber = Save.getString("number", "");
					numberCoin.setText(FormattedNumber(formattedNumber));
					lvl(Save , linearNeed);
					formattedNeed = Save.getString("need", "");
					textviewNeed.setText(FormattedNumber(formattedNeed));
					textviewLevel.setText(Save.getString("lvl", ""));
					textviewPlus.setText(Save.getString("plus", ""));
					textviewNowEnergy.setText(Save.getString("energyNow", ""));
					textviewAllEnergy.setText(Save.getString("energyAll", ""));
				}
				ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
				fade_in.setDuration(30);
				fade_in.setFillAfter(true);
				imageCoin.startAnimation(fade_in);
			}
		});
		
		linearBoost.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intentBoost = new Intent(getApplicationContext() , BoostActivity.class);
				startActivity(intentBoost);
			}
		});
		
		if (!Save.getString("login", "").equals("true")){
			Save.edit().putString("login", "true").commit();
			Save.edit().putString("number", String.valueOf(0)).commit();
			Save.edit().putString("need", String.valueOf(500)).commit();
			Save.edit().putString("lvl", String.valueOf(1)).commit();
			Save.edit().putString("EveryBoost", String.valueOf(64000)).commit();
			Save.edit().putString("EnergyBoost", String.valueOf(10)).commit();
			Save.edit().putString("plus", String.valueOf(1)).commit();
			Save.edit().putString("energyAll", String.valueOf(1000)).commit();
			Save.edit().putString("energyNow", String.valueOf(1000)).commit();
		}
		textviewAllEnergy.setText(Save.getString("energyAll", ""));
		textviewNowEnergy.setText(Save.getString("energyNow", ""));
		linearNeed.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)4, 0xFF009688, Color.TRANSPARENT));
		linearLvl.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)4, 0xFF00BCD4, Color.TRANSPARENT));
		linearPlus.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)4, 0xFF2196F3, Color.TRANSPARENT));
		linearMenu.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFF212121));
		linearCoin.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFF424242));
		formattedNumber = Save.getString("number", "");
		numberCoin.setText(FormattedNumber(formattedNumber));
		lvl(Save , linearNeed);
		formattedNeed = Save.getString("need", "");
		textviewNeed.setText(FormattedNumber(formattedNeed));
		textviewLevel.setText(Save.getString("lvl", ""));
		textviewPlus.setText(Save.getString("plus", ""));
		textviewAllEnergy.setText(Save.getString("energyAll", ""));
		textviewNowEnergy.setText(Save.getString("energyNow", ""));
		// بررسی اینکه آیا energyNow کمتر از energyAll است
    /*if(Integer.parseInt(Save.getString("energyNow" , "")) == Integer.parseInt(Save.getString("energyAll" , ""))){
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyNow", "")) + 1)).commit();
                textviewNowEnergy.setText(Save.getString("energyNow", ""));
            }
        };
        time.schedule(timer, 1000, 1000);
        }*/
	}
	public static void lvl (SharedPreferences Save , LinearLayout linearNeed) {
		if (Integer.parseInt(Save.getString("number", "")) >= 500000000000000d) {
			if (!Save.getString("w20", "").equals("true")){
				Save.edit().putString("w20", "true").commit();
				Save.edit().putString("lvl", String.valueOf(20)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
				linearNeed.setVisibility(View.GONE);
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 200000000000000d) {
			if (!Save.getString("w19", "").equals("true")){
				Save.edit().putString("w19", "true").commit();
				Save.edit().putString("lvl", String.valueOf(19)).commit();
				Save.edit().putString("need", String.valueOf(500000000000000d)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 750000000000d) {
			if (!Save.getString("w18", "").equals("true")){
				Save.edit().putString("w18", "true").commit();
				Save.edit().putString("lvl", String.valueOf(18)).commit();
				Save.edit().putString("need", String.valueOf(200000000000000d)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 100000000000d) {
			if (!Save.getString("w17", "").equals("true")){
				Save.edit().putString("w17", "true").commit();
				Save.edit().putString("lvl", String.valueOf(17)).commit();
				Save.edit().putString("need", String.valueOf(750000000000d)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 1000000000) {
			if (!Save.getString("w16", "").equals("true")){
				Save.edit().putString("w16", "true").commit();
				Save.edit().putString("lvl", String.valueOf(16)).commit();
				Save.edit().putString("need", String.valueOf(100000000000d)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 500000000) {
			if (!Save.getString("w15", "").equals("true")){
				Save.edit().putString("w15", "true").commit();
				Save.edit().putString("lvl", String.valueOf(15)).commit();
				Save.edit().putString("need", String.valueOf(1000000000)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 100000000) {
			if (!Save.getString("w14", "").equals("true")){
				Save.edit().putString("w14", "true").commit();
				Save.edit().putString("lvl", String.valueOf(14)).commit();
				Save.edit().putString("need", String.valueOf(500000000)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 50000000) {
			if (!Save.getString("w13", "").equals("true")){
				Save.edit().putString("w13", "true").commit();
				Save.edit().putString("lvl", String.valueOf(13)).commit();
				Save.edit().putString("need", String.valueOf(100000000)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 10000000) {
			if (!Save.getString("w12", "").equals("true")){
				Save.edit().putString("w12", "true").commit();
				Save.edit().putString("lvl", String.valueOf(12)).commit();
				Save.edit().putString("need", String.valueOf(50000000)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 5000000) {
			if (!Save.getString("w11", "").equals("true")){
				Save.edit().putString("w11", "true").commit();
				Save.edit().putString("lvl", String.valueOf(11)).commit();
				Save.edit().putString("need", String.valueOf(10000000)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 1000000) {
			if (!Save.getString("w10", "").equals("true")){
				Save.edit().putString("w10", "true").commit();
				Save.edit().putString("lvl", String.valueOf(10)).commit();
				Save.edit().putString("need", String.valueOf(5000000)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 75000) {
			if (!Save.getString("w9", "").equals("true")){
				Save.edit().putString("w9", "true").commit();
				Save.edit().putString("lvl", String.valueOf(9)).commit();
				Save.edit().putString("need", String.valueOf(1000000)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 100000) {
			if (!Save.getString("w8", "").equals("true")){
				Save.edit().putString("w8", "true").commit();
				Save.edit().putString("lvl", String.valueOf(8)).commit();
				Save.edit().putString("need", String.valueOf(75000)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 50000) {
			if (!Save.getString("w7", "").equals("true")){
				Save.edit().putString("w7", "true").commit();
				Save.edit().putString("lvl", String.valueOf(7)).commit();
				Save.edit().putString("need", String.valueOf(100000)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 10000) {
			if (!Save.getString("w6", "").equals("true")){
				Save.edit().putString("w6", "true").commit();
				Save.edit().putString("lvl", String.valueOf(6)).commit();
				Save.edit().putString("need", String.valueOf(50000)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 5000) {
			if (!Save.getString("w5", "").equals("true")){
				Save.edit().putString("w5", "true").commit();
				Save.edit().putString("lvl", String.valueOf(5)).commit();
				Save.edit().putString("need", String.valueOf(10000)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 2000) {
			if (!Save.getString("w4", "").equals("true")){
				Save.edit().putString("w4", "true").commit();
				Save.edit().putString("lvl", String.valueOf(4)).commit();
				Save.edit().putString("need", String.valueOf(5000)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 1000) {
			if (!Save.getString("w3", "").equals("true")){
				Save.edit().putString("w3", "true").commit();
				Save.edit().putString("lvl", String.valueOf(3)).commit();
				Save.edit().putString("need", String.valueOf(2000)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		}
		else if (Integer.parseInt(Save.getString("number", "")) >= 500) {
			if (!Save.getString("w2", "").equals("true")){
				Save.edit().putString("w2", "true").commit();
				Save.edit().putString("lvl", String.valueOf(2)).commit();
				Save.edit().putString("need", String.valueOf(1000)).commit();
				Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
				Save.edit().putString("energyNow", String.valueOf(Integer.parseInt(Save.getString("energyAll", ""))+500)).commit();
			}
		} else {
			linearNeed.setVisibility(View.VISIBLE);
		}
		Save.edit().putString("energyAll", String.valueOf((Integer.parseInt(Save.getString("lvl", "")) * 500) + 500)).commit();
	}

	public static String FormattedNumber (String formattedNumber) {
		String[] parts = formattedNumber.split("\\.");
		String integerPart = parts[0];
		String decimalPart = parts.length > 1 ? parts[1] : "";
		int length = integerPart.length();
		for (int i = length - 3; i > 0; i -= 3) {
			integerPart = integerPart.substring(0, i) + " " + integerPart.substring(i);
		}
		if (decimalPart.length() > 2) {
			decimalPart = decimalPart.substring(0, 2);
		}
		formattedNumber = integerPart;
		return formattedNumber;
	}
	@Override
	public void onBackPressed() {
		back++;
		if(back == 2)
			finishAffinity();
	}
}