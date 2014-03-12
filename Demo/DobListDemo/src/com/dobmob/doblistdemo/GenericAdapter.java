package com.dobmob.doblistdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class GenericAdapter<T> extends BaseAdapter {

	public static final String TAG = GenericAdapter.class.getName();

	protected List<T> items = new ArrayList<T>();
	protected LayoutInflater layoutInflater;

	protected int selectedPosition;

	public GenericAdapter(Context context) {
		this.layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public T getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView,
			ViewGroup parent);

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
		notifyDataSetChanged();
	}

	public void addItem(T item) {
		items.add(item);
		notifyDataSetChanged();
	}

	public void addItems(List<T> items) {
		this.items.addAll(items);
		notifyDataSetChanged();
	}

	public void addItems(List<T> items, int count) {
		addItems(items.subList(0, count));
	}

	public void addItems(T[] array) {
		List<T> lst = new ArrayList<T>(Arrays.asList(array));
		addItems(lst);
	}

	public void addItems(T[] array, int count) {
		List<T> lst = new ArrayList<T>(Arrays.asList(array));
		addItems(lst, count);
	}

	public void setItem(int position, T item) {
		items.set(position, item);
		notifyDataSetChanged();
	}

	public void remove(int index) {
		items.remove(index);
		notifyDataSetChanged();
	}

	public void remove(T item) {
		items.remove(item);
		notifyDataSetChanged();
	}

	public void clear() {
		items.clear();
		notifyDataSetChanged();
	}

	public int indexOf(T item) {
		return items.indexOf(item);
	}

	public T getSelectedItem() {
		if (selectedPosition < 0 || selectedPosition >= getCount()) {
			return null;
		} else {
			return items.get(selectedPosition);
		}
	}

	public int getSelectedPosition() {
		return selectedPosition;
	}

	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
	}
}
