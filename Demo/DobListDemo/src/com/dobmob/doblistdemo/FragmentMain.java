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
import com.dobmob.doblist.exceptions.NoListviewException;

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

		ListView mListView = (ListView) rootView.findViewById(R.id.mListView);
		initDobList(rootView, mListView);
		mListView.setAdapter(adapter);

		return rootView;
	}

	private void initDobList(View rootView, ListView listView) {
		dobList = new DobList();
		try {
			dobList.register(listView);
			dobList.addLoadingFooterView();

			View noItems = rootView.findViewById(R.id.noItems);
			dobList.setEmptyView(noItems);

			dobList.setOnLoadMoreListener(new OnLoadMoreListener() {

				@Override
				public void onLoadMore(final int totalItemCount) {
					Log.i(TAG, "onStart totalItemCount " + totalItemCount);
					
					runHandler();
				}
			});

		} catch (NoListviewException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dobList.setLoading(true);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Log.i(TAG, "first add");

				addItems(0, 5);
				dobList.finishLoading();
			}

		}, 2000);
	}

	protected void runHandler() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				addItems(adapter.getCount(),
						adapter.getCount() + 10);

				dobList.finishLoading();
			}

		}, 2000);
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
