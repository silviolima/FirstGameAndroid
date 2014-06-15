package com.example.android;

import com.example.android.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	
	// Keep all Images in array
	public  int[] mThumbIds = {
			R.drawable.canario, R.drawable.cao,
			R.drawable.elefante, R.drawable.cavalo,
			R.drawable.galo, R.drawable.ganso,
			R.drawable.golfinho, R.drawable.gato,
			R.drawable.lobo, R.drawable.ovelha,
		    R.drawable.vaca,R.drawable.macaco,
		    R.drawable.mula, R.drawable.porco,
		    R.drawable.leao, R.drawable.baleia,
		    R.drawable.pato, R.drawable.urso,
		    R.drawable.sapo, R.drawable.abelha,
		    R.drawable.aguia
	};
	
	// Constructor
	public ImageAdapter(Context c){
		mContext = c;
	}

	@Override
	public int getCount() {
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		return mThumbIds[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {			
		ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(220, 100));
        return imageView;
	}

}
