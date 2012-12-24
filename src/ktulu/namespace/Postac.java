package ktulu.namespace;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Postac extends android.view.View {
	static Activity goraa;
	private Random losuj;
	private int nrGracza;
	


	String prepared(String nazwa) {
		String res = nazwa.toLowerCase();
		res = res.replaceAll("\\s", "");
		res = res.replaceAll("ł", "l");
		res = res.replaceAll("ó", "o");
		res = res.replaceAll("ć", "c");
		res = res.replaceAll("ś", "s");
		res = res.replaceAll("ę", "e");
		res = res.replaceAll("ż", "z");
		res = res.replaceAll("ź", "z");
		res = res.replaceAll("ń", "n");
		res = res.replaceAll("ą", "a");
		return res;
	}

	String getPostac() {
		System.out.printf("Losuje postac dla %dtego gracza\n", nrGracza);
		System.out.printf("miejsca dla %d graczy\n", Rozklad.gracze.length);
		System.out.printf("ma byc %d frakcji a jest %d\n", Rozklad.liczbaFrakcji, Rozklad.Frakcje.length);
		int fr = losuj.nextInt(Rozklad.liczbaFrakcji);
		while (Rozklad.Frakcje[fr].wolnychPostaci == 0) {
			fr = losuj.nextInt(Rozklad.liczbaFrakcji);
		}
		System.out.printf("Wylosowalem frakcje %d\n", fr);
		int a = losuj.nextInt(Rozklad.Frakcje[fr].liczbaPostaci);
		while (Rozklad.Frakcje[fr].czyWolna[a] == false) {
			a = losuj.nextInt(Rozklad.Frakcje[fr].liczbaPostaci);
		}
		Rozklad.Frakcje[fr].czyWolna[a] = false;
		Rozklad.gracze[nrGracza].postac = Rozklad.Frakcje[fr].nazwyPostaci[a];
		Rozklad.Frakcje[fr].wolnychPostaci--;
		return Rozklad.Frakcje[fr].nazwyPostaci[a] + " ("
				+ Rozklad.Frakcje[fr].nazwa + ")";
	}
	public static int getStringIdentifier(String name) { //zaklada, ze zna wszystkie postaci!?
	    return goraa.getResources().getIdentifier(name, "string", goraa.getPackageName());
	}

	String getOpis(String postac) {
		int stringId = getStringIdentifier(prepared(postac));
		if (stringId == 0){
			System.out.printf("Nie znalazlem opisu tej postaci\n");
			return "Brak opisu tej postaci";
		}
		String opis = goraa.getResources().getString(stringId);
		System.out.printf("postac %s: %s\n", postac, opis);
		return opis;
	}
	public Postac(final Activity gora, int nr) {
		super(gora);
		losuj = new Random(System.currentTimeMillis());
		goraa = gora;
		nrGracza = nr;
		gora.setContentView(R.layout.pokaz);
		getPostac();
		TextView powitanie = (TextView)gora.findViewById(R.id.textView1);
		if (Rozklad.gracze[nr].imie == "")
			powitanie.setText("Witaj!");
		else
			powitanie.setText("Witaj " + Rozklad.gracze[nr].imie + "!");
		TextView postac = (TextView)gora.findViewById(R.id.textView2);
		postac.setText("Twoja postać to " + Rozklad.gracze[nrGracza].postac); //dodac nazwe frakcji w nawiasie
		TextView opis = (TextView)gora.findViewById(R.id.textView3);
		opis.setText(getOpis(Rozklad.gracze[nr].postac));
		Button bOk = (Button)gora.findViewById(R.id.button1);
		bOk.setMinimumWidth(150);
		bOk.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if (nrGracza == Rozklad.liczbaGraczy - 1){
					View pytanie = new GMPytanie(gora);
				} else {
					View prez = new Prezentacja(gora, nrGracza + 1);
				}
			}
		});

	}
}
