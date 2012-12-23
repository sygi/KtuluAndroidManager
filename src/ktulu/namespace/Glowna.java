package ktulu.namespace;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Glowna extends View{

	static Rozklad rozklad;
	static Gracz gracze[];
	public static int liczbaGraczy;
	public Glowna(final Activity gora) {
		super(gora);

		liczbaGraczy = 0;
		gora.setContentView(R.layout.powitanie);
		Button b = (Button)gora.findViewById(R.id.button1);
		final TextView t = (TextView)gora.findViewById(R.id.editText1);
	    b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.printf("Wybrano liczbę graczy\n");
				liczbaGraczy = (Integer.parseInt(t.getText().toString()));
				if (liczbaGraczy < 8 || liczbaGraczy > 30)
					gora.showDialog(0);
				else{
				gracze = new Gracz[liczbaGraczy];
				gora.setContentView(R.layout.main);
				String[] lista = gora.getResources().getStringArray(R.array.sklad);
				rozklad = new Rozklad(gora, false);
				}
			}
		});
	}
	
}







/*public class Glowna extends KtuluAndroidManagerActivity.Widok{
    
	static Rozklad rozklad;
//	static Gracz gracze[];
	public static int liczbaGraczy;
	
	public Glowna(final KtuluAndroidManagerActivity ktuluAndroidManagerActivity) {
		ktuluAndroidManagerActivity.super();
		liczbaGraczy = 0;
		ktuluAndroidManagerActivity.setContentView(R.layout.powitanie);
		Button b = (Button)ktuluAndroidManagerActivity.findViewById(R.id.button1);
		final TextView t = (TextView)ktuluAndroidManagerActivity.findViewById(R.id.editText1);
	    b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.printf("ASdfA");
				liczbaGraczy = (Integer.parseInt(t.getText().toString()));
				ktuluAndroidManagerActivity.setContentView(R.layout.main);
				KtuluAndroidManagerActivity.Widok sklad = new Rozklad(ktuluAndroidManagerActivity);
			}
		});
	}

}*/
