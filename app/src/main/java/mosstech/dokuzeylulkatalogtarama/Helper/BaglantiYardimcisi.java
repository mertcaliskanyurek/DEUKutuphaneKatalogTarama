package mosstech.dokuzeylulkatalogtarama.Helper;

import android.content.Context;
import android.net.ConnectivityManager;

import androidx.annotation.NonNull;

import java.util.List;

import mosstech.dokuzeylulkatalogtarama.Model.Kitap;
import mosstech.dokuzeylulkatalogtarama.R;

/**
 * Created by Mert on 29.12.2017.
 */

public class BaglantiYardimcisi {

    private BaglantiDinleyici mDinleyici;

    private String mArananKelime;
    private String mAramaNeyeGore;
    private int mSayfa =0;
    private Context mContext;

    public BaglantiYardimcisi(@NonNull Context context,@NonNull BaglantiDinleyici dinleyici)
    {
        mDinleyici = dinleyici;
        mContext = context;
    }

    public void ilkSayfGetir(String arananKelime, String aramaNeyeGore)
    {
        mArananKelime = arananKelime;
        mAramaNeyeGore = aramaNeyeGore;
        sayfaGetir();
    }

    public void sonrakiSayfGetir()
    {
        mSayfa++;
        sayfaGetir();
    }

    public void onckiSayfGetir()
    {
        if(mSayfa==0){
            mDinleyici.hata(R.string.errIlkSayfa);
            return;
        }
        mSayfa--;
        sayfaGetir();
    }

    private void sayfaGetir(){
        if(!isNetworkConnected())
        {
            mDinleyici.hata(R.string.errInternet);
            return;
        }

        try {
            String sayfa = String.valueOf(mSayfa);
            new KitapGetirTask(mDinleyici).execute(mArananKelime,mAramaNeyeGore,sayfa);
        }catch (Exception e){
            mDinleyici.hata(R.string.errBirHata);
            e.printStackTrace();
        }

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public interface BaglantiDinleyici {
        void baglantiOncesi();
        void baglantiSonrasi(List<Kitap> kitaplar);
        /**@param err String kaynagi idsi(Resource id) */
        void hata(int err);
    }

}
