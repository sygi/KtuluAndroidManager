package ktulu.namespace;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class UstalFrakcje extends Activity {
	Activity gora;
    String[] listaFrakcji;
    Spinner[] spinery;
    String[][] frakcjeS;
    EditText[] graczy;
    LinearLayout l;
    Button bOk;
    boolean[] chosen;
    int liczbaF;
    EditText frakcji;
    boolean wszystkoOk(){
    	for(int i = 0; i < spinery.length; i++){
    		for(int j = i+1;j<spinery.length; j++){
    			if (spinery[i].getSelectedItemPosition() == spinery[j].getSelectedItemPosition()){
    				AlertDialog.Builder b = new AlertDialog.Builder(this);
    				b.setMessage("Frakcja " + spinery[i].getSelectedItem() + " występuje więcej niż raz")
    				 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
							
						}
					})
					 .show();
    				return false; //dwie te same frakcje
    			}
    		}
    	}
    	for(int i = 0; i < graczy.length; i++){
    		if (graczy[i].getText().toString().equals("")){
    			AlertDialog.Builder b = new AlertDialog.Builder(this);
    			b.setMessage("Nie podano liczebności wszystkich frakcji")
    			 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				})
				 .show();
    			 return false;
    		}
    		if (Integer.parseInt(graczy[i].getText().toString()) == 0){
    			AlertDialog.Builder b = new AlertDialog.Builder(this);
    			b.setMessage("Frakcja " + spinery[i].getSelectedItem() + " ma 0 postaci")
    			 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				})
				.show();
    			return false;
    		}
    	}
    	int lacznie = 0;
    	for(int i = 0; i < graczy.length; i++)
    		lacznie += Integer.parseInt(graczy[i].getText().toString());
    	
    	if (lacznie != Rozklad.liczbaGraczy){
    		AlertDialog.Builder b = new AlertDialog.Builder(this);
    		b.setMessage("Podane liczby postaci nie sumują się do liczby graczy")
    		 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			})
			.show();
    		return false;
    	}
    	
    	return true;
    }
    int fractionToNumber(String frakcja){
    	for(int i = 0; i<listaFrakcji.length; i++)
    		if (listaFrakcji[i] == frakcja)
    			return i;
    	return -1;
    }
    /*void aktualizujFrakcje(){ //mialo dawac do spinerow tylko opcje niewybrancych frakcji
    	frakcjeS = new String[liczbaF][];
    	System.out.printf("Aktualizuje frakcje\n");
    	for(int i = 0; i < listaFrakcji.length; i++)
    		chosen[i] = false;
    	for(int i = 0; i <liczbaF; i++){
    		chosen[fractionToNumber(spinery[i].getAdapter().getItem(spinery[i].getSelectedItemPosition()).toString())] = true;
    		System.out.printf("frakcja %s jest wybrana\n",spinery[i].getAdapter().getItem(spinery[i].getSelectedItemPosition()).toString());
    	}
    	int ile = 0;
    	for(int i = 0; i < listaFrakcji.length; i++)
    		if (!chosen[i]) ile++;
    	String frakcje[] = new String[ile+1];
    	int it = 0;
    	for(int i = 0; i < listaFrakcji.length; i++)
    		if (!chosen[i]) 
    			frakcje[++it] = listaFrakcji[i];
    	
    	for(int i = 0; i < liczbaF; i++){
    		frakcje[0] = spinery[i].getAdapter().getItem(spinery[i].getSelectedItemPosition()).toString();
    		
    		System.out.printf("frakcje dostepne dla spinera: ");
    		for(int j= 0; j< frakcje.length; j++) System.out.printf("%s ", frakcje[j]); System.out.printf("\n");
    		frakcjeS[i] = frakcje.clone();
    		//spinery[i] = new Spinner(goraa);
    		ArrayAdapter<String> adapter = new ArrayAdapter<String>(goraa, android.R.layout.simple_spinner_item, frakcjeS[i]);
    		spinery[i].setAdapter(adapter);
    		spinery[i].setSelection(0);
    		spinery[i].refreshDrawableState();
    	}
    }*/
	void dodajFrakcje(int nr){
		System.out.printf("Dodaje frakcje do okienka\n");
	    spinery[nr] = new Spinner(gora);
	    l.addView(spinery[nr]);
	    graczy[nr] = new EditText(gora);
	    graczy[nr].setHint("liczba graczy");
	    graczy[nr].setInputType(2); //type_class_number
	    l.addView(graczy[nr]); //nie zawali sie?
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaFrakcji);
	    spinery[nr].setAdapter(adapter);
	    spinery[nr].setSelection(nr);
	    chosen[nr] = true;
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//super(gora);
		gora = this;
		gora.setContentView(R.layout.ustalf);
		l = (LinearLayout)gora.findViewById(R.id.frakcje);
		frakcji = (EditText)gora.findViewById(R.id.editText1);
		frakcji.setHint("Blablabla");
	    listaFrakcji = gora.getResources().getStringArray(R.array.frakcje);
	    chosen = new boolean[listaFrakcji.length];
	    for(int i = 0; i < chosen.length; i++)
	    	chosen[i] = false;
	    bOk = new Button(gora);
	    bOk.setText("OK");
	    bOk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (wszystkoOk()){
					Rozklad.liczbaFrakcji = liczbaF;
					Rozklad.nazwyFrakcji = new String[liczbaF];
					Rozklad.Frakcje = new Frakcja[liczbaF];
					for(int i = 0; i < liczbaF; i++){
						Rozklad.nazwyFrakcji[i] = spinery[i].getAdapter().getItem(spinery[i].getSelectedItemPosition()).toString();
						Rozklad.Frakcje[i] = new Frakcja(Rozklad.nazwyFrakcji[i], Integer.parseInt(graczy[i].getText().toString()));
					}
					View postac = new UstalPostacie(gora, 0);
				}
			}
		});
	    bOk.setMinimumWidth(150);
		Button btn = (Button)gora.findViewById(R.id.button1);

		btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				if (!frakcji.getText().toString().equals(""))
					liczbaF = Integer.parseInt(frakcji.getText().toString());
				else
					liczbaF = 0;
				if (liczbaF == 0 || liczbaF >= 6){
					AlertDialog.Builder b = new AlertDialog.Builder(gora);
					b.setMessage("Podaje liczbę frakcji z przedziału [1, 5]")
					 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					})
					.show();
					return;
				}
				spinery = new Spinner[liczbaF];
				l.removeAllViews();
				graczy = new EditText[liczbaF];
				for(int i = 0; i < listaFrakcji.length; i++)
					chosen[i] = false;
				if (liczbaF > 0 && liczbaF < 6){ //ograniczenie na liczbę frakcji
					for(int i = 0; i < liczbaF; i++)
						dodajFrakcje(i);
				//	for(int i = 0; i < liczbaF; i++){
				//		spinery[i].setOnItemSelectedListener(new OnItemSelectedListener() {
					  //      @Override
					      //  public void onItemSelected(AdapterView<?> arg0, View arg1,
					      //          int position, long id) {
					      //  		aktualizujFrakcje();
					      //  }
					    //    @Override
					  //      public void onNothingSelected(AdapterView<?> arg0) {
					//            // TODO Auto-generated method stub
				//	        }});
			//		}
					l.addView(bOk);
				}
			}
		});
		/*frakcji.setOnFocusChangeListener(new View.OnFocusChangeListener() {
		    @Override
		    public void onFocusChange(View v, boolean hasFocus) {
		        if (hasFocus) {
		            gora.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		        }
		    }
		});*/

		

	  /*  frakcji.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//gora.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
				if (frakcji.getText().toString().equals("")) return;
				liczbaF = Integer.parseInt(frakcji.getText().toString());
				spinery = new Spinner[liczbaF];
				l.removeAllViews();
				graczy = new EditText[liczbaF];
				for(int i = 0; i < listaFrakcji.length; i++)
					chosen[i] = false;
				if (liczbaF > 0 && liczbaF < 6){ //ograniczenie na liczbę frakcji
					for(int i = 0; i < liczbaF; i++)
						dodajFrakcje(i);
				/*	for(int i = 0; i < liczbaF; i++){
						spinery[i].setOnItemSelectedListener(new OnItemSelectedListener() {
					        @Override
					        public void onItemSelected(AdapterView<?> arg0, View arg1,
					                int position, long id) {
					        		aktualizujFrakcje();
					        }
					        @Override
					        public void onNothingSelected(AdapterView<?> arg0) {
					            // TODO Auto-generated method stub
					        }});
					}
					l.addView(bOk);
				}
			}
		});*/
			
	}
	
}
