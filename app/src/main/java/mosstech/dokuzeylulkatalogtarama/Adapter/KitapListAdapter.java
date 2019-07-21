package mosstech.dokuzeylulkatalogtarama.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mosstech.dokuzeylulkatalogtarama.Model.Kitap;
import mosstech.dokuzeylulkatalogtarama.Model.Yer;
import mosstech.dokuzeylulkatalogtarama.R;

/**
 * Created by Mert on 30.12.2017.
 */

public class KitapListAdapter extends BaseAdapter {


    private List<Kitap> mKitaplar;
    private LayoutInflater mLayoutInflater;

    public KitapListAdapter(Context ac, List<Kitap> kitaplar) {
        this.mKitaplar = kitaplar;
        mLayoutInflater = (LayoutInflater) ac.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(mKitaplar ==null)
            return 0;
        else
            return mKitaplar.size();
    }

    @Override
    public Object getItem(int position) {
        return mKitaplar.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView = mLayoutInflater.inflate(R.layout.kitap_layout,null);

        TextView baslik = (TextView) satirView.findViewById(R.id.textViewBaslik);
        TextView yazar = (TextView) satirView.findViewById(R.id.textViewYazar);
        TextView buldYer = (TextView) satirView.findViewById(R.id.textViewBulndYer);
        TextView rafNo = (TextView) satirView.findViewById(R.id.textViewRafNo);
        TextView durumu = (TextView) satirView.findViewById(R.id.textViewDurumu);
        TextView yaynEviTarh = (TextView) satirView.findViewById(R.id.textViewYaynEviTarh);

        ImageView kitap = (ImageView) satirView.findViewById(R.id.imageViewKitap);


        baslik.setText(mKitaplar.get(position).getKtpAdi());
        yazar.setText(mKitaplar.get(position).getYzrAdiSoyadi());
        if(yazar.getText().length()==0)
            yazar.setText(R.string.errYazarBulunamadi);
        yaynEviTarh.setText(mKitaplar.get(position).getYaynEviTarihi());

        ArrayList<Yer> buldYerler = mKitaplar.get(position).getBulnduguYerler();
        for (Yer y:buldYerler) {
            buldYer.append(y.getAdi()+"\n");
            rafNo.append(y.getRafNo()+"\n");
            //eger rafta yazıyorsa iki bosluk degilse tek bosluk
            //duzenl gorunmesi acısından
            if(y.getStatusu().contains("RAFTA"))
                durumu.append(y.getStatusu()+"\n\n");
            else
                durumu.append(y.getStatusu()+"\n");
        }

        /*buldYer.setText(mKitaplar.get(position).bulnduguYerler.get(0).getAdi());
        rafNo.setText(mKitaplar.get(position).bulnduguYerler.get(0).getRafNo());
        durumu.setText(mKitaplar.get(position).bulnduguYerler.get(0).getStatusu());*/

        if(durumu.getText().toString().contains("RAFTA"))
            kitap.setImageResource(R.drawable.alinabilir_kitap);
        else if(durumu.getText().toString().contains("DÖNÜŞ"))
            kitap.setImageResource(R.drawable.donecek_kitap);
        else
            kitap.setImageResource(R.drawable.alinamaz_kitap);

        return satirView;
    }
}
