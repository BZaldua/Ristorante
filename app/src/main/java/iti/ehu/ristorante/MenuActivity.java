package iti.ehu.ristorante;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
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


        final ArrayList<String> primeros_aux = new ArrayList<>();
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
        mListViewPrimeros.setAdapter(arrayAdapter);
        mListViewPrimeros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id_row) {
                TextView texto = (TextView) view.findViewById(R.id.row_num);
                int valor_num = Integer.parseInt(texto.getText().toString())+1;
                texto.setText(String.valueOf(valor_num));
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
        mListViewSegundos.setAdapter(arrayAdapter);
        mListViewSegundos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id_row) {
                TextView texto = (TextView) view.findViewById(R.id.row_num);
                int valor_num = Integer.parseInt(texto.getText().toString())+1;
                texto.setText(String.valueOf(valor_num));
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
        mListViewPostres.setAdapter(arrayAdapter);
        mListViewPostres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id_row) {
                TextView texto = (TextView) view.findViewById(R.id.row_num);
                int valor_num = Integer.parseInt(texto.getText().toString())+1;
                texto.setText(String.valueOf(valor_num));
            }
        });

        Button processButton = (Button) findViewById(R.id.process_button);
        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), CommandActivity.class);
                    startActivity(i);
                    //Toast.makeText(LoginActivity.this, "Existe el usuario", Toast.LENGTH_SHORT).show(); //Sustituir por la pantalla de menu


            }
        });

        Button clearButton = (Button) findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //borramos todos los contadores de los platos

                ListView lv = (ListView)findViewById(R.id.list_view_primeros);
                int count = lv.getAdapter().getCount();

                for (int i = 0; i < count; i++)
                {
                    ViewGroup row = (ViewGroup) lv.getChildAt(i);
                    TextView texto = (TextView) row.findViewById(R.id.row_num);
                    int valor_num = 0;
                    texto.setText(String.valueOf(valor_num));
                }

                lv = (ListView)findViewById(R.id.list_view_segundos);
                count = lv.getAdapter().getCount();

                for (int i = 0; i < count; i++)
                {
                    ViewGroup row = (ViewGroup) lv.getChildAt(i);
                    TextView texto = (TextView) row.findViewById(R.id.row_num);
                    int valor_num = 0;
                    texto.setText(String.valueOf(valor_num));
                }

                lv = (ListView)findViewById(R.id.list_view_postres);
                count = lv.getAdapter().getCount();

                for (int i = 0; i < count; i++)
                {
                    ViewGroup row = (ViewGroup) lv.getChildAt(i);
                    TextView texto = (TextView) row.findViewById(R.id.row_num);
                    int valor_num = 0;
                    texto.setText(String.valueOf(valor_num));
                }
            }
        });






    }
}
