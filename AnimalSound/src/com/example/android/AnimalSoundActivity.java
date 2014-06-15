package com.example.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class AnimalSoundActivity extends Activity {
    MediaPlayer mp = null;
	Button btSound, btStop;
	Random gerador;
	int numeroSorteado;
	int posAleatoria;
	TextView tvPlacar;
	TextView tvPonto;
	int pontos=0;
	int[] listaSomAnimais;
	List<Integer> listaSomJaAcertados = new ArrayList<Integer>() ;
	int i;
	Boolean jaSorteado = false;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
        
		        listaSomAnimais= new int[]{R.raw.canario, R.raw.caozinho,
				R.raw.elefante, R.raw.cavalo,
				R.raw.galo, R.raw.ganso,
				R.raw.golfinho, R.raw.gato,
				R.raw.lobo, R.raw.ovelha,
			    R.raw.vaca,R.raw.macaco,
			    R.raw.mula, R.raw.porco,
			    R.raw.leao, R.raw.baleia,
			    R.raw.pato, R.raw.urso,
			    R.raw.sapo, R.raw.abelha,
			    R.raw.aguia};
		
		
		
		 gerador = new Random();

		final GridView gridView = (GridView) findViewById(R.id.grid_view);
		
		btSound = (Button) findViewById(R.id.btSound);
		btStop = (Button) findViewById(R.id.btStop);
		tvPlacar = (TextView) findViewById(R.id.tvPlacar);
		tvPonto = (TextView) findViewById(R.id.tvPonto);
		btStop.setEnabled(false);
		System.out.println("Botao Stop desligado");
		
		btSound.setEnabled(true);
		
		System.out.println("Botao Sound ligado");
		
		btSound.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				    System.out.println("Botao Sound clicado");				    				    
				
				    numeroSorteado = gerarSorteio();
				    
				    i = 0;
				    
				    System.out.println("Sorteio");
				    
		    		 System.out.println("listaSomJaAcertados size= "+listaSomJaAcertados.size());

				    	
				     while(i < listaSomJaAcertados.size()){
				    	 
                         System.out.println("Entrada -- i: "+i+" ---- listaSomJaAcertados.size(): "+listaSomJaAcertados.size());
				    	 
                         if (numeroSorteado != listaSomJaAcertados.get(i)){
				    		 System.out.println("Diferentes -> numeroSorteado: "+numeroSorteado+
				    				 " listaSomJaAcertados["+i+"]"+"= "+listaSomJaAcertados.get(i));
				    		 i++;
				    		 System.out.println("listaSomJaAcertados size= "+listaSomJaAcertados.size());
				    		 System.out.println("i: "+i);}
				    	 
                         else if  (numeroSorteado == listaSomJaAcertados.get(i)){
				    		 System.out.println("Iguais -> numeroSorteado: "+numeroSorteado+" " +
				    		 		"listaSomJaAcertados["+i+"]"+"= "+listaSomJaAcertados.get(i));
				    		 System.out.println("Novo sorteio");
				    		 numeroSorteado = gerarSorteio();
				    		 i=0;
				    		 ;
				    	 }
				     }				   
				     
				    System.out.println("numeroSorteado a ser usado: "+numeroSorteado);
				    	 
				    posAleatoria = listaSomAnimais[numeroSorteado];
				    
				    //System.out.println("numeroSorteado: "+numeroSorteado+" positionAleatoria: "+posAleatoria);
				    
			        mp = MediaPlayer.create(AnimalSoundActivity.this, posAleatoria);
			        
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
			        btSound.setEnabled(false);		
			        btStop.setEnabled(true);
			        
			        showToast("Quem faz este som?");
			        
			        }	
			}
		);
		
		btStop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				    
				    System.out.println("Botao Stop clicado");
					btSound.setEnabled(true);
					btStop.setEnabled(false);
			}

		}
				);
		
		// Instance of ImageAdapter Class
		gridView.setAdapter(new ImageAdapter(this));

		/**
		 * On Click event for Single Gridview Item
		 * */
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				System.out.println("GridView clicado");
				
				System.out.println("Pontos: "+pontos+" NumeroSorteado: "+numeroSorteado+" Position: "+position);
				
				if (pontos == 0){
					Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
				    // passing array index
				    i.putExtra("id", position);
				    
				    System.out.println("Chamando FullImageActivity quando pontos = 0");
				    
				    startActivity(i);
				    
				    System.out.println("Apos startActivity(i)");
				}
				
				verificarSelecao(numeroSorteado, position);	
				
				if( pontos > 0 && btSound.isEnabled())
				{
					//verificarSelecao(numeroSorteado, position);	
				    // Sending image id to FullScreenActivity
				    Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
				    // passing array index
				    i.putExtra("id", position);
				    if(mp.isPlaying()){
				       mp.stop();
				       mp.release();
				    }
				    System.out.println("Chamando FullImageActivity quando pontos > 0 ");
				    startActivity(i);			
				}
				if( pontos == 2)
				  {
					System.out.println("Chamando Fotos.class");
					
					Intent it = new Intent(getApplicationContext(), Fotos.class);
					
					/*System.out.println("Chamando mp.stop()");
					
					//mp.stop();
					
					System.out.println("Chamando mp.release()");
					
					mp.release();*/
					
					startActivity(it);	
				}
				
			}
		});
		
	}
	
	private void verificarSelecao(int numeroSorteado, int position) {
		if(numeroSorteado == position){
			pontos+=1;
			tvPonto.setText(String.valueOf(pontos));
			System.out.println("Incluindo na listaSomJaAcertados :"+numeroSorteado);
			listaSomJaAcertados.add(numeroSorteado);
		}
	}
	
	public int gerarSorteio(){
		numeroSorteado = gerador.nextInt(listaSomAnimais.length);
		System.out.println("Numero Sorteado: "+numeroSorteado);
		return numeroSorteado;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("onResume()");
		btStop.setEnabled(false);
        btSound.setEnabled(true);
		
		    if(pontos == 2)
		    {
		    	pontos = 0;
		        tvPonto.setText(String.valueOf(pontos));
		        btStop.setEnabled(false);
		        btSound.setEnabled(true);
		        numeroSorteado = gerarSorteio();
		    }
		}
	
	

	public void showToast(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	    }

}