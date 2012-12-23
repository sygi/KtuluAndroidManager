package ktulu.namespace;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class KtuluAndroidManagerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View wszystko = new Glowna(this);
    }
    protected Dialog onCreateDialog(int x){
    	if (x==0){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Liczba graczy powinna wynosić między 8 a 30")
    	.setCancelable(false)
    	.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
           }

		});
    	AlertDialog alert = builder.create();
		return alert;
    	}
    	return null;
    }
}