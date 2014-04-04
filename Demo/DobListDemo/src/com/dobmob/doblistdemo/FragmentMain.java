package com.dobmob.doblistdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dobmob.doblist.DobList;
import com.dobmob.doblist.events.OnLoadMoreListener;
import com.dobmob.doblist.exceptions.NoEmptyViewException;
import com.dobmob.doblist.exceptions.NoListViewException;

public class FragmentMain extends Fragment {

	private static final String TAG = FragmentMain.class.getSimpleName();

	private ItemAdapter adapter;

	private DobList dobList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setHasOptionsMenu(true);

		adapter = new ItemAdapter(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);

		// ListView initializing
		ListView mListView = (ListView) rootView.findViewById(R.id.mListView);

		// Set DobList before setting adapter
		initDobList(rootView, mListView);

		// Set adapter
		mListView.setAdapter(adapter);

		return rootView;
	}

	private void initDobList(View rootView, ListView listView) {

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
			View noItems = rootView.findViewById(R.id.noItems);
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

		} catch (NoListViewException e) {
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

		} catch (NoEmptyViewException e) {
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
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);

		inflater.inflate(R.menu.list, menu);
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
