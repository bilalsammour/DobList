package com.dobmob.doblistdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ItemAdapter extends GenericAdapter<String> {

	public ItemAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.item, null);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);

			convertView.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) convertView.getTag();
		String item = getItem(position);

		holder.title.setText(item);

		return convertView;
	}

	class ViewHolder {

		public TextView title;

	}

}
