package mosstech.dokuzeylulkatalogtarama.Helper;

/**
 * Created by Mert on 21.10.2017.
 */

public class UrlOlusturucu {

    public UrlOlusturucu(){}

    public static String urlOlustur(String arananKelime,String neyeGore, int sayfaKac)
    {
        switch (neyeGore)
        {
            case "Anahtar Kelime":

                if(sayfaKac==0)
                    return "http://katalog.adm.deu.edu.tr/search~S0*tur?/X"
                            +arananKelime+"&SORT=D/X"+arananKelime+"&SORT=D&SUBKEY="+arananKelime+"/1%2C130%2C130%2CB/browse";
                else
                    return "http://katalog.adm.deu.edu.tr/search~S0*tur?/X"
                            +arananKelime+"&SORT=D/X"+arananKelime+"&SORT=D&SUBKEY="+arananKelime+"/"+(1+(sayfaKac*50))+"%2C130%2C130%2CB/browse";
            case "Başlık":
                if(sayfaKac==0)
                    return "http://katalog.adm.deu.edu.tr/search~S0*tur/?searchtype=t&searcharg="+arananKelime+"&sortdropdown=t&SORT=D&extended=0&SUBMIT=Ara&searchlimits=&searchorigarg=t"+arananKelime;

                else
                    return "http://katalog.adm.deu.edu.tr/search~S0*tur?/t"+arananKelime+"/t"+arananKelime+"/"+(1+(sayfaKac*50))+"%2C188%2C188%2CB/browse/indexsort=t";

            case "Yazar":
                if(sayfaKac==0)
                    return "http://katalog.adm.deu.edu.tr/search~S0*tur/?searchtype=a&searcharg="+arananKelime+"&sortdropdown=a&SORT=DZ&extended=0&SUBMIT=Ara&searchlimits=&searchorigarg=X"+arananKelime+"%26SORT%3DD";
                else
                    return "http://katalog.adm.deu.edu.tr/search~S0*tur?/a"+arananKelime+"/a"+arananKelime+"/"+(1+(sayfaKac*50))+"%2C228%2C228%2CB/browse/indexsort=a";


                case "Konu":
                if(sayfaKac==0)
                    return "http://katalog.adm.deu.edu.tr/search~S0*tur/?searchtype=d&searcharg="+arananKelime+"&sortdropdown=t&SORT=D&extended=0&SUBMIT=Ara&searchlimits=&searchorigarg=t"+arananKelime;

                else
                    return "http://katalog.adm.deu.edu.tr/search~S0*tur?/d"+arananKelime+"/d"+arananKelime+"/"+(1+(sayfaKac*50))+"%2C82%2C82%2CB/browse/indexsort=t";

            default:
                return null;
        }


    }


}
