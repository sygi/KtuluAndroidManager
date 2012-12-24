package ktulu.namespace;

import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class Rozklad extends Activity{
	public static Frakcja[] Frakcje;
	public static int liczbaFrakcji;
	public static String[] nazwyFrakcji;
	public static Gracz gracze[];
	static Activity goraa;
	String rozklad;
	//public Gracz[] Gracze;
	public static int liczbaGraczy;
	boolean gen;
	public static void wczytajFrakcje(int nr){
		System.out.printf("Wczytuje postaci frakcji %s\n", nazwyFrakcji[nr]);
		
		Frakcje[nr].nazwyPostaci = new String[Frakcje[nr].liczbaPostaci];
		Frakcje[nr].czyWolna = new boolean[Frakcje[nr].liczbaPostaci];
		String[] s = new String[0];
		if (nr == 0)
			s = goraa.getResources().getStringArray(R.array.miastowi);
		else if (nr == 1)
			s = goraa.getResources().getStringArray(R.array.bandyci);
		else if (nr == 2)
			s = goraa.getResources().getStringArray(R.array.indianie);
		else if (nr == 3)
			s = goraa.getResources().getStringArray(R.array.ufoki);
		for (int i=0; i < Frakcje[nr].liczbaPostaci; i++){
				Frakcje[nr].nazwyPostaci[i] = s[i];
				Frakcje[nr].czyWolna[i] = true;
			}
	}
	public void wczytajPostacie(){
		for (int i = 0; i < liczbaFrakcji; i++)
			wczytajFrakcje(i);
	}
	public TableRow makeRow(String[] tb){
		int cellLength = 0;
		if (tb.length == 4){
			cellLength = 83;
		} else
			cellLength = 110;
		TableRow row = new TableRow(goraa);
		for (int i = 0; i < tb.length; i++){
			TextView a = new TextView(goraa);
			a.setWidth(cellLength);
			a.setText(tb[i]);
			row.addView(a);
		}
		return row;
	}
	public String[] getNthChar(int n){
		System.out.printf("generuje %dty wiersz\n", n);
		String[] t = new String[liczbaFrakcji];
		for(int i = 0; i < liczbaFrakcji; i++){
			if (Frakcje[i].nazwyPostaci.length <= n)
				t[i] = "";
			else 
				t[i] = Frakcje[i].nazwyPostaci[n];
		}
		return t;
	}
	public String[] getNthCharGen(int n){
		System.out.printf("generujeg %dty wiersz\n", n);
		String[] t = new String[liczbaFrakcji];
		for(int i = 0; i < liczbaFrakcji; i++){
			if (Frakcje[i].nazwyPostaci.length <= n)
				t[i] = "";
			else 
				t[i] = Frakcje[i].nazwyPostaci[n];
		}
		return t;
	}
	public void generujTablice(){ //dziala tylko dla 3 i 4 frakcji obecnie
		int maxPostaci = 0;
		if (gen){
			System.out.printf("Gfrakcji%d\n",liczbaFrakcji);
			for(int i = 0 ; i < liczbaFrakcji;i++)
				maxPostaci = Math.max(maxPostaci, Frakcje[i].liczbaPostaci);
		} else {
		for (int i = 0; i < liczbaFrakcji; i++)
			maxPostaci = Math.max(maxPostaci, Frakcje[i].liczbaPostaci);
		}
		System.out.printf("najwiecej postaci jest %d\n", maxPostaci);
		
	 if (liczbaFrakcji == 4){
			TextView t1 = (TextView) goraa.findViewById(R.id.editText1);
			t1.setText(Frakcje[0].nazwa);
			t1.setWidth(83);
			TextView t2 = (TextView) goraa.findViewById(R.id.editText2);
			t2.setText(Frakcje[1].nazwa);
			t2.setWidth(83);
			TextView t3 = (TextView) goraa.findViewById(R.id.editText3);
			t3.setText(Frakcje[2].nazwa);
			t3.setWidth(83);
			TextView t4 = new TextView(goraa);
			t4.setText(Frakcje[3].nazwa);
			t4.setWidth(83);
			TableRow row = (TableRow) goraa.findViewById(R.id.tableRow1);
			row.addView(t4);
		} else if (liczbaFrakcji == 3) {
			TextView t1 = (TextView) goraa.findViewById(R.id.editText1);
			t1.setText(Frakcje[0].nazwa);
			t1.setWidth(110);
			TextView t2 = (TextView) goraa.findViewById(R.id.editText2);
			t2.setText(Frakcje[1].nazwa);
			t2.setWidth(110);
			TextView t3 = (TextView) goraa.findViewById(R.id.editText3);
			t3.setText(Frakcje[2].nazwa); //teraz tez widze, ze to zle :P
			t3.setWidth(110);
		} else if (liczbaFrakcji == 2){
		} else if (liczbaFrakcji == 5){ //will do later
		}
	    TableLayout table = (TableLayout)goraa.findViewById(R.id.table2);
	    table.removeAllViews();
	    for(int i = 0; i < maxPostaci; i++){
	    	TableRow row;
	    	if(gen)
	    		row = makeRow(getNthCharGen(i));
	    	else
	    		row = makeRow(getNthChar(i));
	    	table.addView(row);
	    }
	}
	public void wczytajLiczbePostaci(){
		String[] lista = this.getResources().getStringArray(R.array.sklad);
		rozklad = lista[liczbaGraczy - 8];
		Scanner czytaj = new Scanner(rozklad);
		System.out.printf("Wczytuje liczby postaci we frakcji\n");
		int[] weFrakcji = new int[4];
		for (int i=0; i<4; i++)
			weFrakcji[i] = czytaj.nextInt();
		
		if (weFrakcji[3] == 0){
			liczbaFrakcji = 3;
		nazwyFrakcji = new String[] {"miastowi", "bandyci", "indianie"};
		} else {
			liczbaFrakcji = 4;
			nazwyFrakcji = new String[] {"miastowi", "bandyci", "indianie", "ufoki" };
		}
		Frakcje = new Frakcja[liczbaFrakcji];
		for (int i=0; i < liczbaFrakcji; i++){
			Frakcje[i] = new Frakcja(nazwyFrakcji[i], weFrakcji[i]);
		}
		rozklad = rozklad.replaceAll(" 0", "");
	}
	public void onCreate(Bundle savedInstanceState) {
	//	super(gora);
		super.onCreate(savedInstanceState);
		Intent mI = getIntent();
		gen = mI.getBooleanExtra("gen", false);
		goraa = this;
		liczbaGraczy = Glowna.liczbaGraczy;
		gracze = new Gracz[liczbaGraczy];
		TextView t = new TextView(this);
		
		t.setText("Liczba graczy: " + liczbaGraczy);
		if (!gen){
		wczytajLiczbePostaci();
		wczytajPostacie();
		} else {
			rozklad = "";
			for (int i = 0; i < liczbaFrakcji; i++){
				Frakcje[i].czyWolna = new boolean[Frakcje[i].liczbaPostaci];
				for(int j = 0; j < Frakcje[i].liczbaPostaci; j++)
					Frakcje[i].czyWolna[j] = true;
				rozklad = rozklad + " " + Frakcje[i].liczbaPostaci;
			}
		}
		this.setContentView(R.layout.sklad);
		TextView rozkl = (TextView)this.findViewById(R.id.textView1);
		rozkl.setText("Proponowany rozklad: " + rozklad);
		TextView grac = (TextView)this.findViewById(R.id.textView2);
		grac.setText("Liczba graczy: " + liczbaGraczy);
		generujTablice();
		Button bOk = (Button)this.findViewById(R.id.button2);
		bOk.setMinWidth(100);
	    bOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.printf("Zaakceptowano skład\n");
				View imiona = new Imiona(goraa);
			}
		});
	    Button bZmien = (Button)this.findViewById(R.id.button1);
	    bZmien.setMaxWidth(150);
	    bZmien.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myI = new Intent(goraa, UstalFrakcje.class);
				goraa.startActivityForResult(myI, 2);
				//View zmiana = new UstalFrakcje(goraa);
			}
		});
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//pewnie trzeba bedzie przeniesc nizej
		//default = 0 
		if (requestCode == 2 && resultCode != 1){ 
			System.out.printf("Anulowano wybieranie swojego skladu");
			wczytajLiczbePostaci();
			wczytajPostacie();
		}
		if (resultCode == 42) {
			setResult(42);
			finish();
		}
	}


}
