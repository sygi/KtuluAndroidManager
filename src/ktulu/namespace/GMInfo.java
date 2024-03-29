package ktulu.namespace;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GMInfo extends Activity {
	Activity goraa;
	MediaPlayer mediaPlayer;
	//public GMInfo(Activity gora) {
	public void onCreate(Bundle savedInstanceState) {
	//	super(gora);

        super.onCreate(savedInstanceState);
		goraa = this;
		this.setContentView(R.layout.rozkladgracze);
		LinearLayout l = (LinearLayout)this.findViewById(R.id.graczep);
		Button bOk = (Button)this.findViewById(R.id.button1);
		mediaPlayer = MediaPlayer.create(goraa, R.raw.ufokikrotkie);
		l.removeView(bOk);
		Button bUf = new Button(this);
		bUf.setText("Sygnał ufoków");
		bUf.setMinimumWidth(120);
		bUf.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mediaPlayer.start(); // no need to call prepare(); create() does that for you
			}
		});
		bOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 if (!mediaPlayer.isPlaying()){
					 setResult(42);
					 goraa.finish();
				 }
			}
		});
		for(int i = 0; i < Glowna.liczbaGraczy; i++){
			TextView a = new TextView(this);
			a.setText(Rozklad.gracze[i].imie + ": " + Rozklad.gracze[i].postac);
			l.addView(a);
		}
		l.addView(bUf);
		l.addView(bOk);
		TextView t = new TextView(this);
		t.setText("Made by Sygi, 1-2.04.2012");
		l.addView(t);
	}

}
