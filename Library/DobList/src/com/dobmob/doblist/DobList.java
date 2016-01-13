package com.dobmob.doblist;

import android.view.View;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

import com.dobmob.doblist.controllers.DobListController;
import com.dobmob.doblist.events.OnLoadMoreListener;
import com.dobmob.doblist.exceptions.EmptyViewNotAttachedException;
import com.dobmob.doblist.exceptions.ListViewNotAttachedException;

public class DobList {

	private DobListController dobListController;

	public DobList() {
		super();

		dobListController = new DobListController();
	}

	public void register(ListView listView) throws ListViewNotAttachedException {
		dobListController.register(listView);
	}

	public void finishLoading() {
		dobListController.finishLoading();
	}

	public ListView getListView() {
		return dobListController.getListView();
	}

	public void setListView(ListView listView) {
		dobListController.setListView(listView);
	}

	public View getFooterLoadingView() {
		return dobListController.getFooterLoadingView();
	}

	public void setFooterLoadingView(View footerLoadingView) {
		dobListController.setFooterLoadingView(footerLoadingView);
	}

	public void setFooterLoadingView(int loadingViewRes) {
		dobListController.setFooterLoadingView(loadingViewRes);
	}

	public void addDefaultLoadingFooterView() {
		dobListController.addDefaultLoadingFooterView();
	}

	public void setEmptyView(View emptyView) {
		dobListController.setEmptyView(emptyView);
	}

	public View getEmptyView() {
		return dobListController.getEmptyView();
	}

	public int getMaxItemsCount() {
		return dobListController.getMaxItemsCount();
	}

	public void setMaxItemsCount(int maxItemsCount) {
		dobListController.setMaxItemsCount(maxItemsCount);
	}

	public void removeMaxItemsCount() {
		dobListController.removeMaxItemsCount();
	}

	public boolean isLoading() {
		return dobListController.isLoading();
	}
	
	public void startCentralLoading() throws EmptyViewNotAttachedException {
		dobListController.startCentralLoading();
	}

	public OnLoadMoreListener getOnLoadMoreListener() {
		return dobListController.getOnLoadMoreListener();
	}

	public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
		dobListController.setOnLoadMoreListener(onLoadMoreListener);
	}

	public OnScrollListener getOnScrollListener() {
		return dobListController.getOnScrollListener();
	}

	public void setOnScrollListener(OnScrollListener onScrollListener) {
		dobListController.setOnScrollListener(onScrollListener);
	}
}
