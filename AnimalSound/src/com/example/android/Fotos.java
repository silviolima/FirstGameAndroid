package com.example.android;

import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

public class Fotos extends Activity {
	
	int[] listaSomFinal;
	Random gerador;
	MediaPlayer mp;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.grid3);

			ImageView img=(ImageView)findViewById(R.id.imageView1);
			Drawable myDrawable = getResources().getDrawable(R.drawable.familiapeppa);
			img.setImageDrawable(myDrawable);
		    
			System.out.println("Fotos: Antes de MediaPlayer");

	        listaSomFinal= new int[]{R.raw.se_enamora};
			
			mp =  MediaPlayer.create(Fotos.this,listaSomFinal[0] );
			
			System.out.println("Duração: "+mp.getDuration());
			
			try {
				mp.prepare();
				mp.prepareAsync();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			mp.start();
			
			//mp.setVolume(30, 30);			           
  
	mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
	    public void onCompletion(MediaPlayer mp) {
	    	System.out.println("Fotos: Antes de mp.stop()");
	    	mp.stop();
	    	System.out.println("Fotos: Antes de mp.release()");
	    	mp.release();
	    }
	    });
	}



	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("Fotos: OnDestroy()");
		
		
		if(mp.isPlaying()){
			super.onDestroy();
			System.out.println("Chamando mp.stop()");
			mp.stop();
		    System.out.println("Chamando mp.release()");
		    mp.release();
		} 
	}
	
	
}
