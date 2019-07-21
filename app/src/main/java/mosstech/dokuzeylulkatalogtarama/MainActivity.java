package mosstech.dokuzeylulkatalogtarama;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextKelime;
    private Spinner spinnerArama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAra = (Button) findViewById(R.id.buttonAra);
        buttonAra.setOnClickListener(this);

        editTextKelime = (EditText) findViewById(R.id.editTextKelime);

        spinnerArama = (Spinner) findViewById(R.id.spinnerArama);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.ana_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuItemHknda:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.menuHakkindaBaslik));
                builder.setMessage(getString(R.string.menuHakkindaMesaj));
                builder.setPositiveButton(getString(R.string.menuHakkindaButon), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
                return true;
            case R.id.menuItemYardm:
                startActivity(new Intent(Intent.ACTION_DEFAULT, Uri.parse(getString(R.string.menuLinkYardim))));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonAra:
                String anahtarKelime = editTextKelime.getText().toString();
                if(anahtarKelime.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),getString(R.string.errbos),Toast.LENGTH_SHORT).show();
                    break;
                }
                Intent i = new Intent(this,SonuclarActivity.class);
                i.putExtra("AnahtarKelime",anahtarKelime);
                i.putExtra("NeyeGore",spinnerArama.getSelectedItem().toString());
                startActivity(i);
                break;
            default:
                break;
        }
    }

}
