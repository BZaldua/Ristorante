package iti.ehu.ristorante;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    ListView mListView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        Toast.makeText(MenuActivity.this, "Existe el usuario", Toast.LENGTH_SHORT).show(); //Sustituir por la pantalla de menu

        /****
         * BORRAR DESPUES DE ACABAR CON EL DISEÑO DE INTERFACES
         */
        mListView = (ListView) findViewById(R.id.list_view_primeros);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                //context
                MenuActivity.this,
                //layout (view)
                R.layout.menu_row,
                //row (view)
                R.id.row_dish,
                    //data (model) with bogus data to test our listview
                new String[]{"first record", "second record", "third record"});
        mListView.setAdapter(arrayAdapter);

        mListView = (ListView) findViewById(R.id.list_view_segundos);
        arrayAdapter = new ArrayAdapter<String>(
                //context
                MenuActivity.this,
                //layout (view)
                R.layout.menu_row,
                //row (view)
                R.id.row_dish,
                //data (model) with bogus data to test our listview
                new String[]{"first record", "second record", "third record"});
        mListView.setAdapter(arrayAdapter);

        mListView = (ListView) findViewById(R.id.list_view_postres);
        arrayAdapter = new ArrayAdapter<String>(
                //context
                MenuActivity.this,
                //layout (view)
                R.layout.menu_row,
                //row (view)
                R.id.row_dish,
                //data (model) with bogus data to test our listview
                new String[]{"first record", "second record", "third record"});
        mListView.setAdapter(arrayAdapter);





        final ModelManagement db = new ModelManagement(this);

        List<Dish> dishes = db.getDishes();    // Datos recuperados de la BBDD



    }
}
