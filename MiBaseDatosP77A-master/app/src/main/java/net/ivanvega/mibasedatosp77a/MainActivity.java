package net.ivanvega.mibasedatosp77a;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Contacto com;
    List<Contacto> lista=new ArrayList<>();
    int posicion=-1;
private DAOContactos dao;
TextView txtNomnbre;
ImageButton ir;
//int cont;
//private EditText Nombre, Mail, Tel, editText6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lv = findViewById(R.id.lv);
        txtNomnbre = findViewById(R.id.txtNombre);
        ir =  findViewById(R.id.btnIr);
rellenar();


    }



    public void rellenar(){

      //  SimpleCursorAdapter adp =
        //        new SimpleCursorAdapter(
          //              this,
            //            android.R.layout.simple_list_item_2,
              //          dao.getAllCursor(),
                //        new String[]{"usuario","email"},
                  //      new int[]{android.R.id.text1, android.R.id.text2
                    //    },
                      //  SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE

                //);


        DAOContactos dao=new DAOContactos(this);
        Cursor c=dao.getAllCursor();
        lista=dao.getAll();
        SimpleCursorAdapter adp =new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,dao.getAllCursor(), new String[]{"usuario","email"},
                new int[]{android.R.id.text1,android.R.id.text2},SimpleCursorAdapter.NO_SELECTION);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicion=position;
            }
        });
    }
   public void Agregar(View view){

       Intent intent=new Intent(view.getContext(),Agregar.class);
       startActivity(intent);
    }
    public void Eliminar(View v){
        if(posicion>=0){
            DAOContactos dao=new DAOContactos(this);
            int result = dao.delete(lista.get(posicion));
            if(result>0){
                Toast.makeText(this,"Se elimino",Toast.LENGTH_LONG).show();
                Actualizar();

            }
        }else{
            Toast.makeText(this,"Seleccione uno primero",Toast.LENGTH_LONG).show();
        }

    }

    public void Actualizar(){
        DAOContactos dao=new DAOContactos(this);
        Cursor c=dao.getAllCursor();
        lista=dao.getAll();
        SimpleCursorAdapter adp=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c,MiDB.COLUMNS_NAME_CONTACTO,
                new int[]{android.R.id.text1,android.R.id.text2},SimpleCursorAdapter.NO_SELECTION);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicion=position;
            }
        });
    }

    public void Ir(View view){
   txtNomnbre.setVisibility(View.VISIBLE);
   ir.setVisibility(View.VISIBLE);

    }

    public void Buscar(View view){
        String desc=txtNomnbre.getText().toString();
        //if(!desc.equals("")){
          //  actualizarTabla(desc);
        //}
        DAOContactos dao=new DAOContactos(this);
        com = dao.Buscar(desc);
        Toast toast1 = Toast.makeText(this,"Nombre: " + com.getUsuario() + "\n Mail: " + com.getEmail() + "/n Telefono: " + com.getTel(), Toast.LENGTH_LONG);
        //toast1.setGravity(Gravity.CENTER, , );

        toast1.show();
    }


    public void Modificar(View view){
        if(posicion>=0){
            Intent intent=new Intent(getApplicationContext(),Actualizar.class);
            intent.putExtra("_id",lista.get(posicion).getId());
            startActivity(intent);
        }else{
            Toast.makeText(this,"NO ha seleccionado nada",Toast.LENGTH_LONG).show();
        }
    }

}
