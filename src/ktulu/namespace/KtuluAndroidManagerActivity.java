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
		startActivityForResult(myI, 1);
	}



	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
		//default = 0 
		if (resultCode == 42) {
			finish();
		}
	}
}