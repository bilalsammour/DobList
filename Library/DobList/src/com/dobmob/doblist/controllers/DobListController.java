package com.dobmob.doblist.controllers;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.dobmob.doblist.R;
import com.dobmob.doblist.events.OnLoadMoreListener;
import com.dobmob.doblist.exceptions.EmptyViewNotAttachedException;
import com.dobmob.doblist.exceptions.ListViewNotAttachedException;
import com.dobmob.doblist.listeners.OnListScrollListener;
import com.dobmob.doblist.utils.EmptyViewManager;
import com.dobmob.doblist.utils.ResInflater;

public class DobListController {

	public static final int DEFAULT_INT = -1;

	private Activity activity;
	private ListView listView;
	private View loadingView;
	private View footerLoadingView;
	private View emptyView;
	private int maxItemsCount = DEFAULT_INT;
	private OnLoadMoreListener onLoadMoreListener;
	private OnScrollListener onScrollListener;

	private boolean isLoading;
	private ViewGroup emptyViewParent;

	public void register(ListView listView) throws ListViewNotAttachedException {
		this.listView = listView;

		init();
	}

	private void init() throws ListViewNotAttachedException {
		if (listView == null) {
			throw new ListViewNotAttachedException();
		}

		activity = (Activity) listView.getContext();

		OnListScrollListener onListScrollListener = new OnListScrollListener(
				this);
		listView.setOnScrollListener(onListScrollListener);
	}

	public void setFooterLoadViewVisibility(boolean visible) {
		if (footerLoadingView != null) {
			footerLoadingView.setVisibility(visible ? View.VISIBLE : View.GONE);
		}
	}

	public void finishLoading() {
		setFooterLoadViewVisibility(false);
		setLoading(false);
	}

	public ListView getListView() {
		return listView;
	}

	public void setListView(ListView listView) {
		this.listView = listView;
	}

	public View getLoadingView() {
		return loadingView;
	}

	public void setLoadingView(View loadingView) {
		this.loadingView = loadingView;
	}

	public View getFooterLoadingView() {
		return footerLoadingView;
	}

	public void setFooterLoadingView(View footerLoadingView) {
		this.footerLoadingView = footerLoadingView;

		if (footerLoadingView == null) {
			this.footerLoadingView = ResInflater.inflate(activity,
					R.layout.loading, null, false);
		}

		listView.addFooterView(this.footerLoadingView);
	}

	public void setFooterLoadingView(int loadingViewRes) {
		footerLoadingView = ResInflater.inflate(activity, loadingViewRes, null,
				false);

		setFooterLoadingView(loadingView);
	}

	public boolean hasFotter() {
		if (listView == null) {
			return false;

		} else {
			return listView.getFooterViewsCount() > 0;
		}
	}

	public int getFooterViewsCount() {
		if (listView == null) {
			return 0;

		} else {
			return listView.getFooterViewsCount();
		}
	}

	public void addDefaultLoadingFooterView() {
		footerLoadingView = ResInflater.inflate(activity, R.layout.loading,
				null, false);

		setFooterLoadingView(loadingView);
	}

	public void setEmptyView(View emptyView) {
		this.emptyView = emptyView;
		listView.setEmptyView(this.emptyView);
		this.emptyViewParent = (ViewGroup) emptyView.getParent();
	}

	public View getEmptyView() {
		return this.emptyView;
	}

	public boolean hasEmptyView() {
		return listView.getEmptyView() != null;
	}

	public boolean isLoading() {
		return isLoading;
	}

	public void setLoading(boolean isLoading) {
		this.isLoading = isLoading;

		EmptyViewManager.switchEmptyContentView(activity, listView,
				this.isLoading, emptyViewParent, emptyView);
	}
	
	public void startCentralLoading() throws EmptyViewNotAttachedException {
		if (this.emptyView == null) {
			throw new EmptyViewNotAttachedException();
			
		} else {
			setLoading(true);
		}
	}

	public int getMaxItemsCount() {
		return maxItemsCount;
	}

	public void setMaxItemsCount(int maxItemsCount) {
		this.maxItemsCount = maxItemsCount;
	}

	public boolean isThereMaxItemsCount() {
		return maxItemsCount > DEFAULT_INT;
	}

	public void removeMaxItemsCount() {
		this.maxItemsCount = DEFAULT_INT;
	}

	public OnLoadMoreListener getOnLoadMoreListener() {
		return onLoadMoreListener;
	}

	public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
		this.onLoadMoreListener = onLoadMoreListener;
	}

	public OnScrollListener getOnScrollListener() {
		return onScrollListener;
	}

	public void setOnScrollListener(OnScrollListener onScrollListener) {
		this.onScrollListener = onScrollListener;
	}
}
