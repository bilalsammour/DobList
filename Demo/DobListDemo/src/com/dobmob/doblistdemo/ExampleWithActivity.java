package com.dobmob.doblistdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.dobmob.doblist.DobList;
import com.dobmob.doblist.events.OnLoadMoreListener;
import com.dobmob.doblist.exceptions.EmptyViewNotAttachedException;
import com.dobmob.doblist.exceptions.ListViewNotAttachedException;

public class ExampleWithActivity extends ActionBarActivity {

	private static final String TAG = ExampleWithActivity.class.getSimpleName();

	private ItemAdapter adapter;

	private DobList dobList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_example_with);

		adapter = new ItemAdapter(this);

		// ListView initializing
		ListView mListView = (ListView) findViewById(R.id.mListView);

		// Set DobList before setting adapter
		initDobList(mListView);

		// Set adapter
		mListView.setAdapter(adapter);
	}

	private void initDobList(ListView listView) {

		// DobList initializing
		dobList = new DobList();
		try {

			// Register ListView
			//
			// NoListViewException will be thrown when
			// there is no ListView
			dobList.register(listView);

			// Add ProgressBar to footers of ListView
			// to be shown in loading more
			dobList.addDefaultLoadingFooterView();

			// Sets the view to show if the adapter is empty
			// see startCentralLoading() method
			View noItems = findViewById(R.id.noItems);
			dobList.setEmptyView(noItems);

			// Callback called when reaching last item in ListView
			dobList.setOnLoadMoreListener(new OnLoadMoreListener() {

				@Override
				public void onLoadMore(final int totalItemCount) {
					Log.i(TAG, "onStart totalItemCount " + totalItemCount);

					// Just inserting some dummy data after
					// period of time to simulate waiting
					// data from server
					addDummyData(10);
				}
			});

		} catch (ListViewNotAttachedException e) {
			e.printStackTrace();
		}

		try {
			// Show ProgressBar at the center of ListView
			// this can be used while loading data from
			// server at the first time
			//
			// setEmptyView() must be called before
			//
			// NoEmptyViewException will be thrown when
			// there is no EmptyView
			dobList.startCentralLoading();

		} catch (EmptyViewNotAttachedException e) {
			e.printStackTrace();
		}
		// Simulate adding data at the first time
		addDummyData(20);
	}

	protected void addDummyData(final int itemsCount) {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				addItems(adapter.getCount(), adapter.getCount() + itemsCount);

				// We must call finishLoading
				// when finishing adding data
				dobList.finishLoading();
			}

		}, 3000);
	}

	protected void addItems(int from, int to) {
		String strPlainItem = getString(R.string.item_text);

		for (int i = from; i < to; i++) {
			String strItem = String.format(strPlainItem, i);
			adapter.addItem(strItem);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == R.id.action_clear) {
			adapter.clear();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
