package iti.ehu.ristorante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        final ModelManagement db = new ModelManagement(this);

        /******
         *  BORRAR DATOS DE LAS TABLAS
         *  QUITAR PARA LA APLICACION FINAL
         * ******/
        db.dropTables();


        /******
         *  AÑADIR PLATOS A LA BASE DE DATOS
         * ******/
        Dish uno = new Dish(1, "Arroz", 2.0f);
        Dish dos = new Dish(2, "Huevo", 1.0f);
        Dish tres = new Dish(3, "Coulant de chocolate", 3.50f);
        db.addDish(uno);
        db.addDish(dos);
        db.addDish(tres);


        /******
         *  AÑADIR COMANDA
         * ******/
        ArrayList<Dish> listDishes = new ArrayList<>();
        listDishes.add(uno);
        listDishes.add(dos);
        listDishes.add(tres);
        Command command = new Command(listDishes);
        db.addCommand(command);

        /******
         *  VISUALIZAR PLATOS
         * ******/
        List<Dish> dishes = db.getDishes();

        for(Dish d:dishes){
            String lag = "";
            switch (d.getDishType()){
                case 1:
                    lag+="Primer plato, ";
                    break;
                case 2:
                    lag+="Segundo plato, ";
                    break;
                case 3:
                    lag+="Postre, ";
                    break;
            }

            lag+="Nombre plato: "+d.getName()+", ";
            lag+="Precio: "+d.getPrice()+"\n";

            text+=lag;
        }

        text+="\n\n";

        /******
         *  VISUALIZAR COMANDA
         * ******/
        List<Command> commands = db.getCommands();

        for(Command c:commands)
            text+="Platos: "+c.getDishListString()+" PrecioTotal: "+c.getTotalPrice();

        textView.setText(text);
    }
}
