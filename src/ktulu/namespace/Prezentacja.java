package ktulu.namespace;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Prezentacja extends View {
	Activity goraa;
	int nrGracza;
	public Prezentacja(final Activity gora, final int nr) {
		super(gora);
		goraa = gora;
		nrGracza = nr;
		System.out.printf("Będę robił prezentację dla %dtego gracza\nMiejsca dla %d\n", nr,Glowna.gracze.length);
		gora.setContentView(R.layout.zobacz);
		TextView powitanie = (TextView)gora.findViewById(R.id.textView1);
		if (Glowna.gracze[nr].imie == "")
			powitanie.setText("Witaj!");
		else
			powitanie.setText("Witaj " + Glowna.gracze[nr].imie + "!");
		Button bPoznaj = (Button)gora.findViewById(R.id.button1);
		bPoznaj.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				View postac = new Postac(gora, nr);	
			}
		});
	}

}
