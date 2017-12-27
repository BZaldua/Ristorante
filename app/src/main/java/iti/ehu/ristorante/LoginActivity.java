package iti.ehu.ristorante;

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
                    Toast.makeText(LoginActivity.this, "Existe el usuario", Toast.LENGTH_SHORT).show(); //Sustituir por la pantalla de menu
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
        Dish uno = new Dish(1, "Arroz", 2.0f);
        Dish dos = new Dish(2, "Huevo", 1.0f);
        Dish tres = new Dish(3, "Coulant de chocolate", 3.50f);
        db.addDish(uno);
        db.addDish(dos);
        db.addDish(tres);

        /*******
         *  AÑADIR USUARIOS
         *******/
        User user = new User("ITI", "123456");
        db.addUser(user);
    }

}
