package ktulu.namespace;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Glowna extends Activity{

	//static Rozklad rozklad;
	public static int liczbaGraczy;
	public Activity gora;
	
	protected Dialog onCreateDialog(int x) {
		if (x == 0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Liczba graczy powinna wynosić między 8 a 30")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}

							});
			AlertDialog alert = builder.create();
			return alert;
		}
		return null;
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gora = this;
		//View wszystko = new Glowna(this);
		//super(gora);

		liczbaGraczy = 0;
		this.setContentView(R.layout.powitanie);
		Button b = (Button)this.findViewById(R.id.button1);
		final TextView t = (TextView)this.findViewById(R.id.editText1);
		setResult(42); //zawsze bede konczyl, jak to zginie
	    b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.printf("Wybrano liczbę graczy\n");
				liczbaGraczy = (Integer.parseInt(t.getText().toString()));
				if (liczbaGraczy < 8 || liczbaGraczy > 30)
					gora.showDialog(0); //poprawic kiedys na fajniejsze dialogi
				else{
				//gracze = new Gracz[liczbaGraczy];
			//	gora.setContentView(R.layout.main);
			//	String[] lista = gora.getResources().getStringArray(R.array.sklad);
				Intent mI = new Intent(gora, Rozklad.class);
				mI.putExtra("gen", false);
				gora.startActivityForResult(mI, 5);
			//	gora.setContentView(R.layout.powitanie);
				//rozklad = new Rozklad(gora, false);
				}
			}
		});
	    
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//pewnie trzeba bedzie przeniesc nizej
		//default = 0 
		if (resultCode == 42) {
			finish();
		}
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
