package mosstech.dokuzeylulkatalogtarama.Helper;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mosstech.dokuzeylulkatalogtarama.Model.Kitap;
import mosstech.dokuzeylulkatalogtarama.Model.Yer;
import mosstech.dokuzeylulkatalogtarama.R;


public class KitapGetirTask extends AsyncTask<String,Void, List<Kitap>> {

    private static final String TAG = KitapGetirTask.class.getSimpleName();
    private BaglantiYardimcisi.BaglantiDinleyici mDinleyici;

    public KitapGetirTask(BaglantiYardimcisi.BaglantiDinleyici dinleyici) {
        this.mDinleyici = dinleyici;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(mDinleyici !=null)
            mDinleyici.baglantiOncesi();
    }

    /**@param strings [0] aranan kelime [1] aramaNeyeGore [2] sayfa
     * @return geri donen kitap listesi
     * */
    @Override
    protected List<Kitap> doInBackground(String... strings) {
        List<Kitap> kitaplar = new ArrayList<>();
        Document doc;
        //text - kitap adları
        Elements titles;
        //textNode 3 - yazar adı textNode 4 yayınevi yayın tarihi
        Elements details;
        //childs - bulundugu yerlerin elementleri
        //her child'ın 1. childNode 'unun 1. child node'u (textnode) bulund yer
        //her child'ın 1. childNode 'unun 3. child node'u (textnode) raf no
        //her child'ın 1. childNode 'unun 5. child node'u (textnode) durumu
        Elements items; //bulundugu yerler

        String arananKelime = strings[0];
        String aramaNeyeGore = strings[1];
        int sayfa;
        try {
            sayfa = Integer.valueOf(strings[2]);
        }catch (NumberFormatException e)
        {
            sayfa=0;
            Log.e(TAG,"Sayfa Parametresi Integer Değil");
        }

        String url = UrlOlusturucu.urlOlustur(arananKelime,aramaNeyeGore,sayfa);

        try {
            doc  = Jsoup.connect(url).get();

            //basliklari sec
            titles = doc.select("span.briefcitTitle");
            //yazar bilgisi
            details = doc.select("td[align=left].briefcitDetail");
            //bulunduğu yeri
            items = doc.select("div[class=briefcitItems]");

            kitaplar.clear();
            for(int i=0;i<titles.size();i++)
            {
                //bulundugu yer yoksa web kaynaktır , web kaynakları atlıyoruz
                if(!items.get(i).text().isEmpty()){

                    ArrayList<Yer> yerler = new ArrayList<>();

                    //bulundugu yerleri getir
                    Elements itemsChilds = items.get(i).select("tr.bibItemsEntry");
                    for (Element e:itemsChilds) {
                        TextNode bulundYer = (TextNode) e.childNode(1).childNode(1);
                        TextNode rafNo = (TextNode) e.childNode(3).childNode(1);
                        TextNode statusu = (TextNode) e.childNode(5).childNode(1);

                        Yer yer = new Yer(bulundYer.text(),rafNo.text(),statusu.text());
                        yerler.add(yer);
                    }

                    kitaplar.add(new Kitap(titles.get(i).text(), details.get(i).textNodes().get(3).text()
                            ,details.get(i).textNodes().get(4).text(),yerler));

                }
                //items.get(i).text())
            }

            return kitaplar;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Kitap> kitaplar) {
        super.onPostExecute(kitaplar);

        if(mDinleyici !=null)
        {
            if(kitaplar==null)
                mDinleyici.hata(R.string.errBirHata);
            else
                mDinleyici.baglantiSonrasi(kitaplar);
        }

    }
}
