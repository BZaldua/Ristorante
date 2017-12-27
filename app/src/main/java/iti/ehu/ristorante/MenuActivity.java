package iti.ehu.ristorante;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    protected void onCrete(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        final ModelManagement db = new ModelManagement(this);

        List<Dish> dishes = db.getDishes();    // Datos recuperados de la BBDD

    }
}
