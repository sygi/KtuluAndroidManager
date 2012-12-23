package ktulu.namespace;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GMInfo extends View {
	Activity goraa;
	MediaPlayer mediaPlayer;
	public GMInfo(Activity gora) {
		super(gora);
		goraa = gora;
		gora.setContentView(R.layout.rozkladgracze);
		LinearLayout l = (LinearLayout)gora.findViewById(R.id.graczep);
		Button bOk = (Button)gora.findViewById(R.id.button1);
		l.removeView(bOk);
		Button bUf = new Button(gora);
		bUf.setText("Sygnał ufoków");
		bUf.setMinimumWidth(120);
		bUf.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mediaPlayer = MediaPlayer.create(goraa, R.raw.ufokikrotkie);
				mediaPlayer.start(); // no need to call prepare(); create() does that for you
			}
		});
		bOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 if (!mediaPlayer.isPlaying())
					 goraa.finish();
			}
		});
		for(int i = 0; i < Glowna.liczbaGraczy; i++){
			TextView a = new TextView(gora);
			a.setText(Glowna.gracze[i].imie + ": " + Glowna.gracze[i].postac);
			l.addView(a);
		}
		l.addView(bUf);
		l.addView(bOk);
		TextView t = new TextView(gora);
		t.setText("Made by Sygi, 1-2.04.2012");
		l.addView(t);
	}

}
