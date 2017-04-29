package br.edu.ifsp.mpj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEtEmail;
    private EditText mEtPassword;
    private Button mBtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Shift + F6 = Renomear...

        // Bind (ligacão) entre nossos atributos e os elementos visuais:
        mEtEmail = (EditText) findViewById(R.id.etEmail);
        mEtPassword = (EditText) findViewById(R.id.etPassword);
        mBtLogin = (Button) findViewById(R.id.btLogin);

        mBtLogin.setOnClickListener(new MyClick());
    }

    private class MyClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(isValid(mEtEmail) & isValid(mEtPassword)) {

            }
        }

        private boolean isValid(EditText field) {
            if (TextUtils.isEmpty(field.getText())) {
                field.setError(getString(R.string.msg_required));
                return false;
            } else {
                field.setError(null);
                return true;
            }
        }
    }
}
