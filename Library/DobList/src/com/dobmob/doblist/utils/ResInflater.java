package com.dobmob.doblist.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ResInflater {

	public static View inflate(Context context, int resource, ViewGroup root) {
		return inflate(context, resource, root, false);
	}

	public static View inflate(Context context, int resource, ViewGroup root,
			boolean attachToRoot) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(resource, root, attachToRoot);

		return view;
	}
}
