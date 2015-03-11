package com.in.tallyho;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.in.tallyho.adapters.TournamentAdapter;
import com.in.tallyho.pojo.Tournament;
import com.in.tallyho.tasks.TournamentTask;

public class MainActivity extends Activity implements TournamentTask.TournamentCallBack {

	private List<Tournament> tournamentList;
	private ListView lv;
	private ArrayAdapter<Tournament> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.tournament_list);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
				intent.putExtra("tour_id", tournamentList.get(position).getId());
				startActivity(intent);
			}
		});
		new TournamentTask(this).execute("http://tallyho.in/apps/index.php?ctyp=json");
	}

	@Override
	public void setTournamentList(List<Tournament> list) {
		tournamentList = new ArrayList<Tournament>();
		tournamentList = list;
		adapter = new TournamentAdapter(this, R.layout.tournament_item, tournamentList);
		lv.setAdapter(adapter);
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
	
}
