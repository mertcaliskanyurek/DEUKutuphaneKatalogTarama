package mosstech.dokuzeylulkatalogtarama.Model;

import java.util.ArrayList;

/**
 * Created by Mert on 29.12.2017.
 */

public class Kitap {
    private String ktpAdi;
    private String yzrAdiSoyadi;
    private String yaynEviTarihi;
    //int ynTarihi;

    private ArrayList<Yer> bulnduguYerler;

    public Kitap(String ktpAdi, String yzrAdiSoyadi, String yaynEviTarihi, ArrayList<Yer> bulndYerler)
    {
        this.ktpAdi = ktpAdi;
        this.yzrAdiSoyadi = yzrAdiSoyadi;
        this.yaynEviTarihi = yaynEviTarihi;
        this.bulnduguYerler = bulndYerler;
    }

    public String getKtpAdi() {
        return ktpAdi;
    }

    public String getYzrAdiSoyadi() {
        return yzrAdiSoyadi;
    }

    public String getYaynEviTarihi() {
        return yaynEviTarihi;
    }


    //public String getBuldYerler() {return buldYerler;}

    public ArrayList<Yer> getBulnduguYerler() {
        return bulnduguYerler;
    }
}
