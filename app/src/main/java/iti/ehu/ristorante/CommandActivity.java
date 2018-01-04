package iti.ehu.ristorante;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import android.content.Intent;

public class CommandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        // Cargar los datos del anterior intent
        HashMap<String, Integer> comanda = (HashMap<String, Integer>) getIntent().getSerializableExtra("comanda");


        // Instancia base de datos
        final ModelManagement db = new ModelManagement(this);

        // Crear el ListView para la interfaz
        ListView mListViewCommand = (ListView)findViewById(R.id.list_view_command);
        ViewGroup.LayoutParams params = mListViewCommand.getLayoutParams();
        params.height = 150 * comanda.size();


        // Separar los datos de comanda
        ArrayList<String> command_aux = new ArrayList<String>();
        ArrayList<Float> unidad_aux = new ArrayList<Float>();

        ArrayList<Dish> platos_lista = new ArrayList<Dish>();

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

            // Para la comanda
            Dish aux_dish = new Dish();
            aux_dish.setName(e.getKey().toString());
            aux_dish.setPrice(Float.parseFloat(e.getValue().toString()) * Float.parseFloat(aux));
            platos_lista.add(aux_dish);


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

        TextView text = (TextView) findViewById(R.id.text_total);
        text.setText("Total: " + total + " €");

        final Command new_command = new Command(platos_lista);


        Button btn_ticket = (Button) findViewById(R.id.btn_ticket);
        btn_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardar datos en BD
                db.addCommand(new_command);
                System.out.println("Guardado");

                Toast.makeText(CommandActivity.this, "Se ha generado la factura y se ha enviado el menu a cocina", Toast.LENGTH_SHORT).show();

                Intent ventana = new Intent(getApplicationContext(), MenuActivity.class);
                //finishActivity(100);
                //CommandActivity.super.onDestroy();
                //finishActivityFromChild(CommandActivity.class,100);
                //finishActivity(100);
                finish();
                startActivity(ventana);
            }
        });
    }

}
