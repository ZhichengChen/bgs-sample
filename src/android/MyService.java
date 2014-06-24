package com.red_folder.phonegap.plugin.myservice;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.util.Log;

import com.red_folder.phonegap.plugin.backgroundservice.BackgroundService;

public class MyService extends BackgroundService {
	
	private final static String TAG = MyService.class.getSimpleName();
	
	private String mHelloTo = "World";
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	public MyService(){
		preferences = getSharedPreferences("HelloWorld",Context.MODE_PRIVATE);
		editor = preferences.edit();
	}

	@Override
	protected JSONObject doWork() {
		JSONObject result = new JSONObject();
		
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
			String now = df.format(new Date(System.currentTimeMillis())); 

			String msg = "Hello " + this.mHelloTo + " - its currently " + now;
			result.put("Message", msg);

			Log.d(TAG, msg);
		} catch (JSONException e) {
		}
		
		return result;	
	}

	@Override
	protected JSONObject getConfig() {
		JSONObject result = new JSONObject();
		
		String passphase = preferences.getString("HelloTo", null);
		
		try {
			result.put("HelloTo", passphase);
		} catch (JSONException e) {
		}
		
		return result;
	}

	@Override
	protected void setConfig(JSONObject config) {
		
		try {
			if (config.has("HelloTo")){
				editor.putString("HelloTo", config.getString("HelloTo"));
				editor.commit();
			}
		} catch (JSONException e) {
		}
		
	}     

	@Override
	protected JSONObject initialiseLatestResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onTimerEnabled() {
		// TODO Auto-generated method stub
		Log.i("TAG", "TimeEnabled");
		
	}

	@Override
	protected void onTimerDisabled() {
		// TODO Auto-generated method stub
		Log.i("TAG", "TimeDisEnabled");
	}


}
