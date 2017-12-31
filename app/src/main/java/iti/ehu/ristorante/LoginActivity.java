package iti.ehu.ristorante;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText userText, passwordText;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final ModelManagement db = new ModelManagement(this);

        addStartingData(db); // Para añadir platos y usuarios

        loginButton = (Button) findViewById(R.id.btn_login);


        userText = (EditText) findViewById(R.id.txt_user);
        passwordText = (EditText) findViewById(R.id.txt_pwd);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userText.getText().toString();
                String password = passwordText.getText().toString();
                User loginUser = new User(username, password);

                if(db.login(loginUser)){
                    Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(i);
                    //Toast.makeText(LoginActivity.this, "Existe el usuario", Toast.LENGTH_SHORT).show(); //Sustituir por la pantalla de menu
                }else{
                    Toast.makeText(LoginActivity.this, "No existe el usuario", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void addStartingData(ModelManagement db){
        /******
         *  BORRAR DATOS DE LAS TABLAS
         *  QUITAR PARA LA APLICACION FINAL
         * ******/
        db.dropTables();


        /******
         *  AÑADIR PLATOS A LA BASE DE DATOS
         * ******/
        Dish primero1 = new Dish(1, "Arroz", 2.0f);
        Dish segundo1 = new Dish(2, "Huevo", 1.0f);
        Dish postre1 = new Dish(3, "Coulant de chocolate", 3.50f);
        db.addDish(primero1);
        db.addDish(segundo1);
        db.addDish(postre1);

        Dish primero2 = new Dish(1, "Lentejas", 3.0f);
        Dish segundo2 = new Dish(2, "Filete", 4.0f);
        Dish postre2 = new Dish(3, "Tiramisú", 3.50f);
        db.addDish(primero2);
        db.addDish(segundo2);
        db.addDish(postre2);

        Dish primero3 = new Dish(1, "Ensalada mixta", 2.0f);
        Dish segundo3 = new Dish(2, "Pollo al horno", 2.50f);
        Dish postre3 = new Dish(3, "Fruta natural", 2f);
        db.addDish(primero3);
        db.addDish(segundo3);
        db.addDish(postre3);



        /*******
         *  AÑADIR USUARIOS
         *******/
        User user = new User("ITI", "123456");
        db.addUser(user);
    }

}
