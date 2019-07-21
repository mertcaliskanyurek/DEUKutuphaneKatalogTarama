package mosstech.dokuzeylulkatalogtarama;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String KUTUPHANE_ISMI_EKSTRA;
    private LatLng bulndYer;
    private ImageView imageViewHomeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        KUTUPHANE_ISMI_EKSTRA = getIntent().getStringExtra(getString(R.string.isimExtra));
        koordinatiHazirla();
        if (mMap == null)
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.haritaFragment))
                    .getMapAsync(this);
        else
            koordinatiYerlestir();

        imageViewHomeBtn = (ImageView) findViewById(R.id.imageViewHomeBtn);
        imageViewHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent("android.intent.action.MAIN"));
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

    }

    private void koordinatiHazirla()
    {
        if(KUTUPHANE_ISMI_EKSTRA.contains("Merkez"))
            bulndYer =(new LatLng(38.370270,27.202545));

        if(KUTUPHANE_ISMI_EKSTRA.contains("İlahiyat Fakültesi"))
            bulndYer =(new LatLng(38.394971,27.096231));

        if(KUTUPHANE_ISMI_EKSTRA.contains("Tıp Fakültesi"))
            bulndYer =(new LatLng(38.394415, 27.033025));

        if(KUTUPHANE_ISMI_EKSTRA.contains("Güzel Sanatlar Fakültesi"))
            bulndYer =(new LatLng(38.394393, 27.025029));

        if(KUTUPHANE_ISMI_EKSTRA.contains("Hukuk Fakültesi"))
            bulndYer =(new LatLng(38.371889, 27.197363));

        if(KUTUPHANE_ISMI_EKSTRA.contains("Bergama"))
            bulndYer =(new LatLng(39.107452, 27.190861));

        if(KUTUPHANE_ISMI_EKSTRA.contains("Torbalı Meslek"))
            bulndYer =(new LatLng(38.183142, 27.364321));

        if(KUTUPHANE_ISMI_EKSTRA.contains("Atatürk İlkeleri"))
            bulndYer =(new LatLng(38.371703, 27.198985));

        if(KUTUPHANE_ISMI_EKSTRA.contains("İktisadi ve İdari"))
            bulndYer =(new LatLng(38.384605, 27.182495));

        if(KUTUPHANE_ISMI_EKSTRA.contains("Buca Eğitim"))
            bulndYer =(new LatLng(38.386606, 27.169044));

        if(KUTUPHANE_ISMI_EKSTRA.contains("Foça Turizm"))
            bulndYer =(new LatLng(38.658075, 26.753322));

        if(KUTUPHANE_ISMI_EKSTRA.contains("Seferihisar"))
            bulndYer =(new LatLng(38.196929, 26.845698));

        if(KUTUPHANE_ISMI_EKSTRA.contains("Deniz"))
            bulndYer =(new LatLng(38.319095, 26.643206));

        if(KUTUPHANE_ISMI_EKSTRA.contains("Devlet"))
            bulndYer =(new LatLng(38.394400, 27.030472));

        if(KUTUPHANE_ISMI_EKSTRA.contains("Merkez Kütüphane Tarih"))
            bulndYer =(new LatLng(38.416444, 27.129471));
    }

    private void koordinatiYerlestir(){
        if(bulndYer==null)
            Toast.makeText(getApplicationContext(),getString(R.string.errLokasyn),Toast.LENGTH_LONG).show();
        else{
            mMap.addMarker(new MarkerOptions().position(bulndYer));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bulndYer,10));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        koordinatiYerlestir();
    }
}
