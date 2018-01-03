package iti.ehu.ristorante;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CommandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        HashMap<String, Integer> comanda = (HashMap<String, Integer>) getIntent().getSerializableExtra("comanda");


        // Crear el ListView para la interfaz
        ListView mListViewCommand = (ListView)findViewById(R.id.list_view_command);
        ViewGroup.LayoutParams params = mListViewCommand.getLayoutParams();
        params.height = 150 * comanda.size();


        // Separar los datos de comanda
        ArrayList<String> command_aux = new ArrayList<String>();
        ArrayList<Integer> unidad_aux = new ArrayList<Integer>();
        Iterator it = comanda.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            command_aux.add(e.getKey().toString());
            unidad_aux.add(Integer.parseInt(e.getValue().toString()));
            System.out.println(e.getKey() + " " + e.getValue());
        }


        // Crear adapter para interfaz
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        DishAdapter adapter = new DishAdapter(this, dishes);

        // Crear objetos para despues añadir al adapter
        for(int i = 0; i < comanda.size(); i++){
            Dish dish = new Dish();
            dish.setName(command_aux.get(i));
            dish.setPrice((float)unidad_aux.get(i));
            adapter.add(dish);
        }

        mListViewCommand.setAdapter(adapter);

    }

}
