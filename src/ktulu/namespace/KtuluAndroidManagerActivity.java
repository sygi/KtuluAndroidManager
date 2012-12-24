package ktulu.namespace;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class KtuluAndroidManagerActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent myI = new Intent(this, Glowna.class);
	//	View wszystko = new Glowna(this);
		startActivity(myI);
	}



	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
		// trzeba bedzie bedzie przeniesc do nizszej klasy potem
		//default = 0 
		if (resultCode == 15) {
			finish();
		}
	}
}