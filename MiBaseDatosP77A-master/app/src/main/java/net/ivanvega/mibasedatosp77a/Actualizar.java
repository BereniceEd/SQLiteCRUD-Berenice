package net.ivanvega.mibasedatosp77a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Actualizar extends AppCompatActivity {
    private EditText nombre, mail, telefono, nac;
    Contacto contacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        nombre = (EditText)findViewById(R.id.nombre);
        mail= (EditText)findViewById(R.id.mail);
        telefono = (EditText)findViewById(R.id.tel);
        // nac = (EditText)findViewById(R.id.editText2);
        Bundle bundle=getIntent().getExtras();
        DAOContactos dao=new DAOContactos(this);
        contacto=dao.SeleccionarUno(bundle.getInt("_id"));
        nombre.setText(contacto.getUsuario());
        mail.setText((contacto.getEmail()));
        telefono.setText(contacto.getTel());
       // String[] arr=contacto.getFecNac().split("-");
        //dtpFecha.updateDate(Integer.parseInt(arr[2]),Integer.parseInt(arr[1]),Integer.parseInt(arr[0]));
    }

    public void Guardar(View v){
        DAOContactos dao=new DAOContactos(this);
        //String fecha=dtpFecha.getDayOfMonth()+"-"+dtpFecha.getMonth()+"-"+dtpFecha.getYear();
        Contacto obj=new Contacto(contacto.getId(),nombre.getText().toString(), mail.getText().toString(),telefono.getText().toString());
        long result=dao.Actualizar(obj);
        if(result>0){
            Toast.makeText(this,"Se modifico",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }
}
