package net.ivanvega.mibasedatosp77a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Agregar extends AppCompatActivity {
private EditText nombre, mail, telefono, nac;
Contacto contacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        nombre = (EditText)findViewById(R.id.nombre2);
        mail= (EditText)findViewById(R.id.mail2);
        telefono = (EditText)findViewById(R.id.tel2);
       // nac = (EditText)findViewById(R.id.editText2);


}
    public void Agregar(View view){
        DAOContactos dao=new DAOContactos(this);
        //String fecha=dtp.getDayOfMonth()+"-"+dtp.getMonth()+"-"+dtp.getYear();
        dao.insert(new Contacto(0,nombre.getText().toString(), mail.getText().toString(),telefono.getText().toString()));

            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);

    }


}