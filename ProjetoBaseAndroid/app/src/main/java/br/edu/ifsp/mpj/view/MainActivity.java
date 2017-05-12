package br.edu.ifsp.mpj.view;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.edu.ifsp.mpj.R;
import br.edu.ifsp.mpj.entity.User;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tilEmail) TextInputLayout mTilEmail;
    @BindView(R.id.tilPassword) TextInputLayout mTilPassword;
    @BindView(R.id.btLogin) Button mBtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Shift + F6 = Renomear...

        // Bind (ligacão) entre nossos atributos e os elementos visuais:
        ButterKnife.bind(this);

        mBtLogin.setOnClickListener(new MyClick());
    }

    private class MyClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(isValid(mTilEmail) & isValid(mTilPassword)) {

                User user = new User();
                user.setEmail(mTilEmail.getEditText().getText().toString());
                user.setPassword(mTilPassword.getEditText().getText().toString());

                final List<User> users = User.find(User.class, "email=? AND password=?", user.getEmail(), user.getPassword());
                if (users.size() > 0) {
                    //FEITO (1) Redirecionar para a ListActivity via Intent (falar sobre Parcelable)
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    intent.putExtra(ListActivity.KEY_USER, user);
                    startActivity(intent);
                } else {
                    // 1. Cria um "Builder" para exibicão da Dialog
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage(R.string.msg_login_error)
                            .setTitle(R.string.dialog_alert)
                            .setPositiveButton(android.R.string.ok, null)
                            .create().show();
                }
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
