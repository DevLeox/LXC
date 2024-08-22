package com.LeoxDev.LXC;

import android.os.*;
import android.graphics.drawable.*;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class BoostActivity extends  Activity {
	
	LinearLayout linear;
	LinearLayout linearOther;
	LinearLayout linearMenu;
	LinearLayout linearBoostBio;
	LinearLayout linearEvery;
	LinearLayout linearEnergy;
	ImageView imageviewBoostBio;
	TextView textviewBoostBio;
	ImageView imageviewEvery;
	LinearLayout linearEveryInfo;
	TextView textviewEvery;
	LinearLayout linearEveryCost;
	ImageView imageviewEveryCoin;
	TextView textviewEveryCost;
	ImageView imageviewEnergy;
	LinearLayout linearEnergyInfo;
	TextView textviewEnergy;
	LinearLayout linearEnergyCost;
	ImageView imageviewEnergyCoin;
	TextView textviewEnergyCost;
	LinearLayout linearCoin;
	LinearLayout linearBoost;
	ImageView imageviewCoin;
	TextView textviewCoin;
	ImageView imageviewBoost;
	TextView textviewBoost;
	TextView slash;
	TextView textviewEnergyNow;
	
	SharedPreferences Save;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_boost);
		linear = (LinearLayout) findViewById(R.id.linear);
		linearOther = (LinearLayout) findViewById(R.id.linearOther);
		linearMenu = (LinearLayout) findViewById(R.id.linearMenu);
		linearBoostBio = (LinearLayout) findViewById(R.id.linearBoostBio);
		linearEvery = (LinearLayout) findViewById(R.id.linearEvery);
		linearEnergy = (LinearLayout) findViewById(R.id.linearEnergy);
		imageviewBoostBio = (ImageView) findViewById(R.id.imageviewBoostBio);
		textviewBoostBio = (TextView) findViewById(R.id.textviewBoostBio);
		imageviewEvery = (ImageView) findViewById(R.id.imageviewEvery);
		linearEveryInfo = (LinearLayout) findViewById(R.id.linearEveryInfo);
		textviewEvery = (TextView) findViewById(R.id.textviewEvery);
		linearEveryCost = (LinearLayout) findViewById(R.id.linearEveryCost);
		imageviewEveryCoin = (ImageView) findViewById(R.id.imageviewEveryCoin);
		textviewEveryCost = (TextView) findViewById(R.id.textviewEveryCost);
		imageviewEnergy = (ImageView) findViewById(R.id.imageviewEnergy);
		linearEnergyInfo = (LinearLayout) findViewById(R.id.linearEnergyInfo);
		textviewEnergy = (TextView) findViewById(R.id.textviewEnergy);
		linearEnergyCost = (LinearLayout) findViewById(R.id.linearEnergyCost);
		imageviewEnergyCoin = (ImageView) findViewById(R.id.imageviewEnergyCoin);
		textviewEnergyCost = (TextView) findViewById(R.id.textviewEnergyCost);
		linearCoin = (LinearLayout) findViewById(R.id.linearCoin);
		linearBoost = (LinearLayout) findViewById(R.id.linearBoost);
		imageviewCoin = (ImageView) findViewById(R.id.imageviewCoin);
		textviewCoin = (TextView) findViewById(R.id.textviewCoin);
		imageviewBoost = (ImageView) findViewById(R.id.imageviewBoost);
		textviewBoost = (TextView) findViewById(R.id.textviewBoost);
		slash = (TextView) findViewById(R.id.slash);
		textviewEnergyNow = (TextView) findViewById(R.id.textviewEnergyNow);
		Save = getSharedPreferences("Save", Activity.MODE_PRIVATE);
		
		linearMenu.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFF212121));
		linearBoost.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFF424242));
		linearEvery.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFF212121));
		linearEnergy.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFF212121));
		
		textviewEveryCost.setText(FormattedNumber(String.valueOf(Integer.parseInt(Save.getString("EveryBoost", "")))));
		textviewEnergyNow.setText(FormattedNumber(String.valueOf(Integer.parseInt(Save.getString("EnergyBoost", "")))));
	    
		linearCoin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intentCoin = new Intent(getApplicationContext() , MainActivity.class);
				startActivity(intentCoin);
			}
		});
		linearEvery.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(Integer.parseInt(Save.getString("number" , "")) >= Integer.parseInt(Save.getString("EveryBoost" , ""))){
					Save.edit().putString("number", String.valueOf(Integer.parseInt(Save.getString("number", "")) - Integer.parseInt(Save.getString("EveryBoost" , "")))).commit();
					Save.edit().putString("EveryBoost", String.valueOf((Integer.parseInt(Save.getString("plus", "")) * 64000))).commit();
					Save.edit().putString("plus", String.valueOf(Integer.parseInt(Save.getString("plus", "")) + 1)).commit();
					textviewEveryCost.setText(FormattedNumber(String.valueOf(Integer.parseInt(Save.getString("EveryBoost", "")))));
					}else{
					Toast.makeText(getApplicationContext() , "Your coins is low for this operation" , Toast.LENGTH_SHORT);
				}
			}
		});
		linearEnergy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(Integer.parseInt(Save.getString("EnergyBoost" , "")) != 0){
					if (!Save.getString("energyNow", "").equals(Save.getString("energyAll", ""))) {
						Save.edit().putString("EnergyBoost", String.valueOf(Integer.parseInt(Save.getString("EnergyBoost", "")) - 1)).commit();
						Save.edit().putString("energyNow", Save.getString("energyAll", "")).commit();
						textviewEnergyNow.setText(FormattedNumber(String.valueOf(Integer.parseInt(Save.getString("EnergyBoost", "")))));
					}
					}else{
					Toast.makeText(getApplicationContext() , "Your coins is low for this operation" , Toast.LENGTH_SHORT);
				}
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		Intent intentBack = new Intent(getApplicationContext() , MainActivity.class);
		startActivity(intentBack);
		finish();
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
}