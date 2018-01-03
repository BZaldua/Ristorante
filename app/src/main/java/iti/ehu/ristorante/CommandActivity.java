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
        ArrayList<Float> unidad_aux = new ArrayList<Float>();
        float total = 0;
        float precio_p = 0;
        Iterator it = comanda.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            command_aux.add(e.getKey().toString());
            unidad_aux.add(Float.parseFloat(e.getValue().toString()));
            String[] s = (e.getKey().toString()).split("\\(");
            String aux = s[1].substring(0,s[1].length()-1);
            total  = total + (Float.parseFloat(e.getValue().toString()) * Float.parseFloat(aux));
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
