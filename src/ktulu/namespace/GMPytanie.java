package ktulu.namespace;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class GMPytanie extends View {
	Activity goraa;
	public GMPytanie(final Activity gora) {
		super(gora);
		goraa = gora;
		gora.setContentView(R.layout.pytanie);
		Button bOk = (Button)gora.findViewById(R.id.button1);
		bOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goraa.startActivityForResult(new Intent(goraa, GMInfo.class), 2);
				//View sklad = new GMInfo(gora);
			}
		});
	}


}
