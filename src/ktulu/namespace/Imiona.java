package ktulu.namespace;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Imiona extends View {
	EditText imiona[];
	Activity goraa;
	boolean wszyscy;
	RadioButton rdbJeden, rdbWszyscy;
	public Imiona(final Activity gora) {
		super(gora);
		goraa = gora;
		gora.setContentView(R.layout.wyborimion);
		final Button bOk = (Button) gora.findViewById(R.id.button1);

		rdbWszyscy = (RadioButton)gora.findViewById(R.id.radioButton2);
		rdbJeden = (RadioButton) gora.findViewById(R.id.radioButton1);
		rdbWszyscy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.printf("Wybrano opcję z podaniem imion wszystkich\n");
				if (!wszyscy){
				imiona = new EditText[Glowna.liczbaGraczy];
				LinearLayout l = (LinearLayout)gora.findViewById(R.id.listaim);
				for(int i = 0; i < Glowna.liczbaGraczy; i++){
					imiona[i] = new EditText(gora);
					l.addView(imiona[i]);
				}
				TableLayout t = (TableLayout)gora.findViewById(R.id.imion);
				t.removeView(bOk);
				l.addView(bOk);
				wszyscy = true;
				}
				rdbJeden.setChecked(false);
			}
		});
		rdbJeden.setOnClickListener(new View.OnClickListener() { //to pewnie da sie zrobic ladniej
			@Override
			public void onClick(View v) {
				rdbWszyscy.setChecked(false);
			}
		});
		bOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!rdbWszyscy.isChecked() && !rdbJeden.isChecked()){
					System.out.printf("Zadna opcja nie zostala wybrana\n");
				} else {
				if (rdbWszyscy.isChecked()){
					for (int i = 0; i < Glowna.liczbaGraczy; i++){
						Glowna.gracze[i] = new Gracz(imiona[i].getText().toString());
					}
				} else {
					EditText ed = (EditText)gora.findViewById(R.id.editText1);
					Glowna.gracze[0] = new Gracz(ed.getText().toString());
					for(int i = 1; i<Glowna.liczbaGraczy; i++)
						Glowna.gracze[i] = new Gracz();
				}
				View prezentacja = new Prezentacja(gora, 0);
				}
			}
		});
	}

}
