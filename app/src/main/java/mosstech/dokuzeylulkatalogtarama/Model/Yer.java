package mosstech.dokuzeylulkatalogtarama.Model;

/**
 * Created by Mert on 30.12.2017.
 */

public class Yer {

    private String adi;
    private String rafNo;
    private String statusu;

    public Yer(String adi, String rafNo, String statusu)
    {
        this.adi =adi;
        this.rafNo = rafNo;
        this.statusu = statusu;
    }

    public String getAdi() {return adi;}
    public String getRafNo() {return rafNo;}
    public String getStatusu() {return statusu;}
}
