package com.in.tallyho.adapters;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.in.tallyho.R;
import com.in.tallyho.pojo.Tournament;

public class TournamentAdapter extends ArrayAdapter<Tournament>{

	private Context context;
	private int resource;
	private List<Tournament> tournaments;

	public TournamentAdapter(Context context, int resource,
			List<Tournament> tournaments) {
		super(context, resource, tournaments);
		this.context = context;
		this.resource = resource;
		this.tournaments = tournaments;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Tournament tournament = tournaments.get(position);
		ViewHolder holder = null;
		if(convertView == null) {
			convertView = LayoutInflater.from(context).inflate(resource, null);
			holder = new ViewHolder();
			holder.locationView = (TextView) convertView.findViewById(R.id.location);
			holder.startView = (TextView) convertView.findViewById(R.id.start_date);
			holder.status = (TextView) convertView.findViewById(R.id.status);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.locationView.setText(tournament.getLocation());
		holder.startView.setText(tournament.getStartDate());
		holder.status.setText(tournament.getStatus());
		return convertView;
	}

	static class ViewHolder {
		TextView locationView;
		TextView startView;
		TextView status;
	}
}
