DobLis
======


DobLis [(Google Play Demo)](https://play.google.com/store/apps/details?id=com.dobmob.doblistdemo)
--------------------------------------------------

Any Android developer needs Endless ListView, and needs progress dialog to be shown in the middle of his own ListView at first loading data, so he will write the code line by line or use a custom ListView to get some features and lost his own ListView.

What about getting desired features with your own ListView and its derivatives!

DobLis is an Open Source Android library that provides to ListView adding ProgressBar (or any view) to the footer of ListView to be shown in loading more, and callback that is called when reaching last item in ListView.

![alt tag](https://raw.github.com/bilalsammour/DobLis/master/screenshot.png)

Here is a short video for the example application in this repository : [http://youtu.be/1TPzJDpUwug](http://youtu.be/1TPzJDpUwug)


Setup
-----

In Eclipse, just import the library as an Android library project. Project > Clean to generate the binaries you need, like R.java, etc. Then, just add DobLis as a dependency to your existing project.


How?
----

```java
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
		addDummyData(5);
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

		}, 2000);
	}
```

Properties
----------

* Set / get ListView - for ListView and its derivatives such as ExpandableListView
* Set / get footerLoadingView - to define and access a custom footer that will be shown in loading more
* Set / get emptyView - Sets and get the view to show if the adapter is empty. See startCentralLoading() method
* Set / get maxItemsCount - Here you can specify the maximum number of items in ListView.
* Set / get onLoadMoreListener - Callback that is called when reaching last item in ListView.
* Set / get onScrollListener - Because DobList consumes OnScrollListener, here you can use OnScrollListener.



Methods
-------

* register - Register ListView. NoListViewException will be thrown when there is no ListView.
* addDefaultLoadingFooterView - Add ProgressBar to footers of ListView to be shown in loading more.
* startCentralLoading - Show ProgressBar at the center of ListView this can be used while loading data from server at the first time.

setEmptyView() must be called before.

NoEmptyViewException will be thrown when there is no EmptyView.


Events
------

* OnLoadMoreListener - Callback that is called when reaching last item in ListView.


Developed By
------------

Bilal Sammour - bilalsammour@gmail.com


License
-------

    Copyright 2014 Bilal Sammour
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

