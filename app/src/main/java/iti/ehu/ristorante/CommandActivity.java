package iti.ehu.ristorante;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.ViewGroupCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;

public class CommandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        HashMap<String, Integer> comanda = (HashMap<String, Integer>) getIntent().getSerializableExtra("comanda");

        System.out.println("Tamaño: " + comanda.size());
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

        //System.out.println("Tamaño: asdasda");


        ListView mListViewCommand = (ListView)findViewById(R.id.list_view_command);
        ViewGroup.LayoutParams params = mListViewCommand.getLayoutParams();
        params.height = 150 * comanda.size();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                //context
                CommandActivity.this,
                //layout (view)
                R.layout.menu_row,
                //row (view)
                R.id.row_dish,
                //data (model) with bogus data to test our listview
                command_aux);
        mListViewCommand.setAdapter(arrayAdapter);
        mListViewCommand.getHeight();

        TextView txt_total = (TextView) findViewById(R.id.text_total);
        txt_total.setText("Total: " + total + " €");
        //System.out.println("precio:" + total);




    }

}
