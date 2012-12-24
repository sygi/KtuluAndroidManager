package ktulu.namespace;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class UstalPostacie extends View {
	static Activity goraa;
	Spinner spinery[];
	public static int getStringArrayIdentifier(String name) { //zaklada, ze zna wszystkie postaci!?
		return goraa.getResources().getIdentifier(name, "array", goraa.getPackageName());
	}

	String[] getPostaci(String frakcja) {
		int stringArrayId = getStringArrayIdentifier(frakcja);
		if (stringArrayId == 0){
			System.out.printf("Nie znalazlem opisu tej postaci\n");
			return null;
		}
		return goraa.getResources().getStringArray(stringArrayId);
	}
	public UstalPostacie(Activity gora, final int nrFrakcji) {
		super(gora);
		goraa = gora;
		gora.setContentView(R.layout.wyborpostaci);
		TextView t = (TextView)gora.findViewById(R.id.textView1);
		t.setText("Frakcja " + Rozklad.nazwyFrakcji[nrFrakcji]);
		LinearLayout l = (LinearLayout)gora.findViewById(R.id.lp);
		spinery = new Spinner[Rozklad.Frakcje[nrFrakcji].liczbaPostaci];
		for(int i = 0; i<spinery.length; i++){
			spinery[i] = new Spinner(gora);
			String[] postaci = getPostaci(Rozklad.nazwyFrakcji[nrFrakcji]);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(goraa, android.R.layout.simple_spinner_item, postaci);
			spinery[i].setAdapter(adapter);
			spinery[i].setSelection(i);
			l.addView(spinery[i]);
		}
		Button bOk = new Button(gora);
		bOk.setText("OK");
		bOk.setMinimumWidth(100);
		l.addView(bOk);
		bOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//na razie nie sprawdzam czy nie ma kolizji
				Rozklad.Frakcje[nrFrakcji].nazwyPostaci = new String[Rozklad.Frakcje[nrFrakcji].liczbaPostaci];
				for(int i = 0; i < spinery.length; i++){
					Rozklad.Frakcje[nrFrakcji].nazwyPostaci[i] = spinery[i].getSelectedItem().toString();
				}
				if (nrFrakcji == Rozklad.liczbaFrakcji - 1){
					Intent mI = new Intent(goraa, Rozklad.class);
					mI.putExtra("gen", true);
					goraa.startActivity(mI);
					//View sklad = new Rozklad(goraa, true);
				} else {
					View postac = new UstalPostacie(goraa, nrFrakcji+1);
				}
			}
		});
	}

}
