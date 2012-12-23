package ktulu.namespace;

import android.content.Context;
import android.widget.TextView;

public class Frakcja{
	public int liczbaPostaci, wolnychPostaci;
	public String[] nazwyPostaci;
	public boolean[] czyWolna;
	public String nazwa;
	public Frakcja(String nazwa, int liczbaGraczy){
		this.nazwa = nazwa;
		liczbaPostaci = liczbaGraczy;
		wolnychPostaci = liczbaGraczy;
	}
}
