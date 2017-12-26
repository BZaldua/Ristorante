package iti.ehu.ristorante;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
}
