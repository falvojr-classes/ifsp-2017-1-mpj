package br.edu.ifsp.mpj.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;
import java.util.Locale;

import br.edu.ifsp.mpj.R;
import br.edu.ifsp.mpj.entity.Contact;
import br.edu.ifsp.mpj.entity.User;
import br.edu.ifsp.mpj.view.adaper.ContactAdapter;

public class ListActivity extends AppCompatActivity {

    public static final String KEY_USER = "MY_USER_KEY";
    public static final int REQUEST_EDIT = 100;
    public static final int REQUEST_NEW = 99;

    private RecyclerView mRecyclerView;
    private ContactAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton mFabNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Não usamos o Butter Knife de propósito!
        mRecyclerView = (RecyclerView) findViewById(R.id.rvTelefones);
        mFabNovo = (FloatingActionButton) findViewById(R.id.fabNovo);

        // Cria um adaptador simples para os itens do ListView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final List<Contact> all = Contact.listAll(Contact.class);
        // specify an adapter (see also next example)
        mAdapter = new ContactAdapter(all, new ContactAdapter.OnItemClickListener() {
            @Override
            public void onCallClick(Contact contact) {
                //FEITO (4) Realizar uma chamada telefônica via Intent
                // http://stackoverflow.com/a/13123613/3072570
                Uri uri = Uri.parse("tel:" + contact.getPhone());
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }

            @Override
            public void onEditClick(Contact contact) {
                Intent intentEdit = new Intent(ListActivity.this, ContactActivity.class);
                intentEdit.putExtra(ContactActivity.KEY_EDIT_DATA, contact);
                startActivityForResult(intentEdit, REQUEST_EDIT);
            }

            @Override
            public void onMapsClick(Contact contact) {
                //TODO Pegar a localizacao atual!
                String origem = "saddr=-21.783873,-48.210575";
                String destino = String.format(Locale.ENGLISH, "daddr=%.6f,%.6f", contact.getLatitude(), contact.getLongitude());
                String uri = "http://maps.google.com/maps?f=d&hl=en&" + origem +"&" + destino;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(Intent.createChooser(intent, "Select an application"));
            }

        });
        mRecyclerView.setAdapter(mAdapter);

        if (getIntent().hasExtra(KEY_USER)) {
            //FEITO (3) Dizer "Olá" para o usuário logado (via Toast ou Snackbar)
            // https://developer.android.com/guide/topics/ui/notifiers/toasts.html
            // https://developer.android.com/training/snackbar/action.html
            User user = getIntent().getParcelableExtra(KEY_USER);
            String message = getString(R.string.hello_user, user.getEmail());
            Snackbar.make(mRecyclerView, message, Snackbar.LENGTH_LONG).show();
        }

        mFabNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNew = new Intent(ListActivity.this, ContactActivity.class);
                startActivityForResult(intentNew, REQUEST_NEW);
            }
        });
    }

    private void updateList() {
        mAdapter.setDataSet(Contact.listAll(Contact.class));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final boolean isNew = requestCode == REQUEST_NEW;
        if (isNew || requestCode == REQUEST_EDIT) {
            final boolean isDelete = resultCode == ContactActivity.RESULT_DELETE;
            if(resultCode == Activity.RESULT_OK || isDelete) {
                updateList();
                String op;
                if (isNew) {
                    op = "inserido";
                } else if (isDelete) {
                    op = "excluido";
                } else {
                    op = "alterado";
                }
                String msg = String.format("Contato %s com sucesso!", op);
                Snackbar.make(mRecyclerView, msg, Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
