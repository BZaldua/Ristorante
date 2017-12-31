package iti.ehu.ristorante;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    ListView mListViewPrimeros, mListViewSegundos, mListViewPostres;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //Toast.makeText(MenuActivity.this, "Existe el usuario", Toast.LENGTH_SHORT).show(); //Sustituir por la pantalla de menu
        /****
         * BORRAR DESPUES DE ACABAR CON EL DISEÑO DE INTERFACES
         */

        final ModelManagement db = new ModelManagement(this);
        List<Dish> dishes = db.getDishes();    // Datos recuperados de la BBDD

        //creamos una lista para cada tipo de plato, primero, segundo y postre
        List<Dish> primeros = new ArrayList<>();
        List<Dish> segundos = new ArrayList<>();
        List<Dish> postres = new ArrayList<>();

        //separar los platos en 3 listas en funcion del tipo de plato
        for (Dish d : dishes) {
            switch (d.getDishType()) {
                case 1:
                    primeros.add(d);
                    break;
                case 2:
                    segundos.add(d);
                    break;
                case 3:
                    postres.add(d);
                    break;
                default:
                    break;
            }
        }


        ArrayList<String> primeros_aux = new ArrayList<>();
        for (Dish d : primeros) {
            primeros_aux.add(d.getName() + " (" + d.getPrice() + ")" );
        }

        mListViewPrimeros = (ListView) findViewById(R.id.list_view_primeros);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                //context
                MenuActivity.this,
                //layout (view)
                R.layout.menu_row,
                //row (view)
                R.id.row_dish,
                    //data (model) with bogus data to test our listview
                primeros_aux);
        mListViewPrimeros.setAdapter(arrayAdapter);

        /// click en botones
        mListViewPrimeros.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                Toast.makeText(MenuActivity.this, "Pulsado posicion" + position + parent.getId(), Toast.LENGTH_SHORT).show();
                ListView lv = (ListView) findViewById(parent.getId());
//                lv.getItemAtPosition(position);

            }

        });


        ArrayList<String> segundos_aux = new ArrayList<>();
        for (Dish d : segundos) {
            segundos_aux.add(d.getName() + " (" + d.getPrice() + ")" );
        }

        mListViewSegundos = (ListView) findViewById(R.id.list_view_segundos);
        arrayAdapter = new ArrayAdapter<String>(
                //context
                MenuActivity.this,
                //layout (view)
                R.layout.menu_row,
                //row (view)
                R.id.row_dish,
                //data (model) with bogus data to test our listview
                segundos_aux);
        mListViewSegundos.setAdapter(arrayAdapter);
        mListViewSegundos.getHeight();

        /// click en botones
        mListViewSegundos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                Toast.makeText(MenuActivity.this, "Pulsado posicion" + position + parent.getId(), Toast.LENGTH_SHORT).show();
            }

        });



        ArrayList<String> postres_aux = new ArrayList<>();
        for (Dish d : postres) {
            postres_aux.add(d.getName() + " (" + d.getPrice() + ")" );
        }

        mListViewPostres = (ListView) findViewById(R.id.list_view_postres);
        arrayAdapter = new ArrayAdapter<String>(
                //context
                MenuActivity.this,
                //layout (view)
                R.layout.menu_row,
                //row (view)
                R.id.row_dish,
                //data (model) with bogus data to test our listview
                postres_aux);
        mListViewPostres.setAdapter(arrayAdapter);

        /// click en botones
        mListViewPostres.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MenuActivity.this, "Pulsado posicion" + position + parent.getId(), Toast.LENGTH_SHORT).show();


            }

        });





    }
}
