package com.dobmob.doblist.listeners;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

import com.dobmob.doblist.controllers.DobListController;
import com.dobmob.doblist.events.OnLoadMoreListener;

public class OnListScrollListener implements OnScrollListener {

	private DobListController dobListController;
	private int lastTotalItemCount;

	public OnListScrollListener(DobListController dobListController) {
		super();
		this.dobListController = dobListController;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {

		if (totalItemCount - dobListController.getFooterViewsCount() == 0) {
			return;
		}

		int lastVisibleItem = firstVisibleItem + visibleItemCount;
		boolean isLoading = dobListController.isLoading();
		boolean isLastItem = lastVisibleItem == totalItemCount;
		OnLoadMoreListener onLoadMoreListener = dobListController
				.getOnLoadMoreListener();

		if (!isLoading) {
			if (visibleItemCount == totalItemCount) {
				loadMore(onLoadMoreListener, totalItemCount);
				return;
			}

			if (dobListController.isThereMaxItemsCount()
					&& totalItemCount >= dobListController.getMaxItemsCount()) {
				dobListController.setFooterLoadViewVisibility(false);
				return;

			} else if (totalItemCount == 0) {
				dobListController.setFooterLoadViewVisibility(false);
				return;
			}

			if (isLastItem) {
				loadMore(onLoadMoreListener, totalItemCount);
			}
		}

		if (dobListController.getOnScrollListener() != null) {
			dobListController.getOnScrollListener().onScroll(view,
					firstVisibleItem, visibleItemCount, totalItemCount);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (dobListController.getOnScrollListener() != null) {
			dobListController.getOnScrollListener().onScrollStateChanged(view,
					scrollState);
		}
	}

	private void loadMore(OnLoadMoreListener onLoadMoreListener,
			int totalItemCount) {

		if (this.lastTotalItemCount < totalItemCount) {
			this.lastTotalItemCount = totalItemCount;

			dobListController.setLoading(true);
			dobListController.setFooterLoadViewVisibility(true);
			if (onLoadMoreListener != null) {
				onLoadMoreListener.onLoadMore(totalItemCount);
			}
		}
	}

}
