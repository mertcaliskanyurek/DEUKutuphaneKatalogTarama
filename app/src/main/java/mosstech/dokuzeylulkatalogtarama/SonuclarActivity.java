package mosstech.dokuzeylulkatalogtarama;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import mosstech.dokuzeylulkatalogtarama.Helper.BaglantiYardimcisi;
import mosstech.dokuzeylulkatalogtarama.Model.Kitap;
import mosstech.dokuzeylulkatalogtarama.Adapter.KitapListAdapter;


public class SonuclarActivity extends AppCompatActivity implements View.OnClickListener,
        BaglantiYardimcisi.BaglantiDinleyici {

    private String ARANAN_KELIME_EXTRA;
    private String ARAMA_NEYE_GORE_EXTRA;

    private BaglantiYardimcisi connHelper;

    private ListView mListViewSonuc;
    private ProgressBar mProgressBar;

    private boolean mSonSayfa=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuclar);

        mListViewSonuc = (ListView) findViewById(R.id.listViewSonuclar);

        ImageView buttonGeri = (ImageView) findViewById(R.id.imageViewOnckSyf);
        buttonGeri.setOnClickListener(this);

        ImageView buttonIleri = (ImageView) findViewById(R.id.imageViewSnrkSyf);
        buttonIleri.setOnClickListener(this);

        ImageView buttonAnaSyf = (ImageView) findViewById(R.id.imageViewAnaSyf);
        buttonAnaSyf.setOnClickListener(this);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBarSonuc);
        mProgressBar.setVisibility(View.INVISIBLE);

        Intent i = getIntent();
        ARANAN_KELIME_EXTRA = i.getStringExtra("AnahtarKelime");
        ARAMA_NEYE_GORE_EXTRA = i.getStringExtra("NeyeGore");

        connHelper = new BaglantiYardimcisi(this,this);
        registerForContextMenu(mListViewSonuc);
    }

    @Override
    protected void onStart() {
        super.onStart();
        connHelper.ilkSayfGetir(ARANAN_KELIME_EXTRA,ARAMA_NEYE_GORE_EXTRA);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Kitap k = (Kitap)mListViewSonuc.getItemAtPosition(info.position);
        switch (item.getItemId())
        {
            case R.id.contxMenuAra:
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,k.getKtpAdi()); // query contains search string
                startActivity(intent);
                break;
            case R.id.contxMenuHaritGost:
                Intent i = new Intent(this.getApplicationContext(), MapsActivity.class);
                i.putExtra(getString(R.string.isimExtra),k.getBulnduguYerler().get(0).getAdi());
                startActivity(i);
                break;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.imageViewOnckSyf:
                    connHelper.onckiSayfGetir();
                    break;
                case R.id.imageViewSnrkSyf:
                    if(mSonSayfa)
                        Toast.makeText(this,R.string.errSonSayfa,Toast.LENGTH_SHORT).show();
                    else
                        connHelper.sonrakiSayfGetir();
                    break;
                case R.id.imageViewAnaSyf:
                    finish();
            }
    }

    private void listGoster(List<Kitap> kitaplar)
    {
        if(kitaplar.size()==0)
            Toast.makeText(this,R.string.errUygunKayt, Toast.LENGTH_LONG).show();

        KitapListAdapter adapter = new KitapListAdapter(this,kitaplar);
        mListViewSonuc.setAdapter(adapter);

    }

    @Override
    public void baglantiOncesi() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void baglantiSonrasi(List<Kitap> kitaplar) {
        mProgressBar.setVisibility(View.GONE);
        if(kitaplar.size()==0) {
            Toast.makeText(this, R.string.errUygunKayt, Toast.LENGTH_SHORT).show();
            mSonSayfa=true;
        }
        else
            listGoster(kitaplar);
    }

    @Override
    public void hata(int err) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }
}
