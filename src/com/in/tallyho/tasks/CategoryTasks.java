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

import com.in.tallyho.CategoryActivity;
import com.in.tallyho.constants.CommonConstants;
import com.in.tallyho.pojo.Category;

public class CategoryTasks extends AsyncTask<String, Void, Void>{

	public interface CategoryCallback {
		void setCategoryList(List<Category> list);
	}

	private Activity activity;
	private List<Category> categoryList;
	public CategoryTasks(Activity activity) {
		this.activity = activity;
		categoryList = new ArrayList<Category>();
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
			int categoryId = jsonObject.getInt(CommonConstants.CATEGORY_ID);
			int categoryCode = jsonObject.getInt(CommonConstants.CATEGORY_CODE);
			String categoryDescription = "Category " + i;
			Category category = new Category(categoryId, categoryDescription, categoryCode);
			Log.e("category", categoryId + " " + categoryCode + " " + categoryDescription);
			categoryList.add(category);
		}
		} catch (JSONException jse) {
			jse.printStackTrace();
		}
		return null;
	}



	@Override
    protected void onPostExecute(Void obj) {
		((CategoryActivity)activity).setCategoryList(categoryList);
		
    }
}
