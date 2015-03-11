package com.in.tallyho;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.in.tallyho.pojo.Category;
import com.in.tallyho.tasks.CategoryTasks;

public class CategoryActivity extends Activity implements CategoryTasks.CategoryCallback {

	public List<Category> categoryList;
	private ListView lv;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_layout);
		
		Intent intent = getIntent();
		int tourId = intent.getExtras().getInt("tour_id");
		
		lv = (ListView) findViewById(R.id.category_list);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
			}
		});
		new CategoryTasks(this).execute("http://tallyho.in/apps/index.php/tour/default/view/id/" + tourId + "?ctyp=json");
		
	}

	@Override
	public void setCategoryList(List<Category> list) {
		categoryList = list;
		String[] categoryDescLists = new String[categoryList.size()];
		for(int i = 0; i < categoryList.size(); ++i) {
			categoryDescLists[i] = categoryList.get(i).getCategoryDescription();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, categoryDescLists);
//		adapter = new TournamentAdapter(this, R.layout.tournament_item, tournamentList);
		lv.setAdapter(adapter);
	}
}
