package br.edu.ifsp.mpj;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifsp.mpj.entity.User;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout mTilEmail;
    private TextInputLayout mTilPassword;
    private Button mBtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Shift + F6 = Renomear...

        // Bind (ligacão) entre nossos atributos e os elementos visuais:
        mTilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        mTilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        mBtLogin = (Button) findViewById(R.id.btLogin);

        mBtLogin.setOnClickListener(new MyClick());
    }

    private class MyClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(isValid(mTilEmail) & isValid(mTilPassword)) {
                User user = new User();
                user.setEmail(mTilEmail.getEditText().getText().toString());
                user.setPassword(mTilPassword.getEditText().getText().toString());

                //FEITO (1) Redirecionar para a ListActivity via Intent (falar sobre Parcelable)
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra(ListActivity.KEY_USER, user);
                startActivity(intent);

                //FEITO (2) Registrar a ListActivity no AndroidManifest.xml
            }
        }

        private boolean isValid(TextInputLayout field) {
            if (TextUtils.isEmpty(field.getEditText().getText())) {
                field.setError(getString(R.string.msg_required));
                return false;
            } else {
                field.setErrorEnabled(false);
                return true;
            }
        }
    }
}
