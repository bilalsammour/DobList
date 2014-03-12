package com.dobmob.doblist.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.dobmob.doblist.R;

public class EmptyViewManager {

	public static void switchEmptyContentView(Context context,
			ListView listView, boolean isLoading, ViewGroup emptyViewParent,
			View emptyView) {

		if (listView.getEmptyView() != null) {
			View currentEmptyView = listView.getEmptyView();
			emptyViewParent.removeView(currentEmptyView);

			View newView;

			if (isLoading) {
				View loadingView = ResInflater.inflate(context,
						R.layout.loading, null, false);
				newView = loadingView;

				RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);

				layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

				loadingView.setLayoutParams(layoutParams);
				emptyViewParent.addView(loadingView);

			} else {
				newView = emptyView;

				emptyViewParent.addView(emptyView);
			}

			listView.setEmptyView(newView);
		}
	}
}
