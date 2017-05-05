package br.edu.ifsp.mpj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import br.edu.ifsp.mpj.entity.User;

public class ListActivity extends AppCompatActivity {

    public static final String KEY_USER = "MY_USER_KEY";

    private static final String[] PHONES = {
            "+55 16 111111111",
            "+55 16 222222222",
            "+55 16 333333333",
            "+55 16 444444444",
            "+55 16 555555555",
            "+55 16 666666666",
            "+55 16 777777777",
            "+55 16 888888888",
            "+55 16 999999999"
    };

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvTelefones);

        // Cria um adaptador simples para os itens do ListView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ContactAdapter(PHONES, new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String phone) {
                //FEITO (4) Realizar uma chamada telefônica via Intent
                // http://stackoverflow.com/a/13123613/3072570
                Uri uri = Uri.parse("tel:" + phone);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        //FEITO (3) Dizer "Olá" para o usuário logado (via Toast ou Snackbar)
        // https://developer.android.com/guide/topics/ui/notifiers/toasts.html
        // https://developer.android.com/training/snackbar/action.html
        User user = getIntent().getParcelableExtra(KEY_USER);
        String message = getString(R.string.hello_user, user.getEmail());
        Snackbar.make(mRecyclerView, message, Snackbar.LENGTH_LONG).show();
    }
}
