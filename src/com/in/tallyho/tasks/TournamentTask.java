package com.in.tallyho.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.in.tallyho.MainActivity;
import com.in.tallyho.constants.CommonConstants;
import com.in.tallyho.pojo.Tournament;

public class TournamentTask extends AsyncTask<String, Void, Void> {


	private List<Tournament> list;
	private Activity activity;

	public TournamentTask(Activity activity) {
		list = new ArrayList<Tournament>();
		this.activity = activity;
		
	}
	public interface TournamentCallBack {
		public void setTournamentList(List<Tournament> list);
	}
	@Override
	protected Void doInBackground(String... params) {
		String url = params[0];
		StringBuilder sb = new StringBuilder();
		try {
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = client.execute(httpGet);
		InputStream is = response.getEntity().getContent();
		String line = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		} catch(IOException ie) {
			ie.printStackTrace();
		}

		try {
		JSONArray jArray = new JSONArray(sb.toString());
		for(int i = 0; i < jArray.length(); ++i) {
			JSONObject jsonObject = jArray.getJSONObject(i);
			int id = jsonObject.getInt(CommonConstants.ID);
			String location = jsonObject.getString(CommonConstants.LOCATION);
			String startDate = jsonObject.getString(CommonConstants.START_DATE);
			int status = jsonObject.getInt(CommonConstants.STATUS);
			Tournament tournament = new Tournament(id, location, startDate, status);
			Log.e("item", location + " " + startDate + " " + status);
			list.add(tournament);
		}
			
		} catch (JSONException jse) {
			jse.printStackTrace();
		}
		return null;
	}

	@Override
    protected void onPostExecute(Void obj) {
		((MainActivity)activity).setTournamentList(list);
		
    }
}
