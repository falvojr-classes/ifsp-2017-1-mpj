package br.edu.ifsp.mpj.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import br.edu.ifsp.mpj.R;
import br.edu.ifsp.mpj.entity.Contact;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactActivity extends AppCompatActivity {

    public static final String KEY_EDIT_DATA = "MY_EDIT_DATA_KEY";
    public static final int RESULT_DELETE = 12;

    @BindView(R.id.tilName) TextInputLayout mTilName;
    @BindView(R.id.tilPhone) TextInputLayout mTilPhone;
    @BindView(R.id.tilLat) TextInputLayout mTilLat;
    @BindView(R.id.tilLon) TextInputLayout mTilLong;
    @BindView(R.id.btSave) Button mBtSave;

    private Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ButterKnife.bind(this);

        if (getIntent().hasExtra(KEY_EDIT_DATA)) {
            mContact = getIntent().getParcelableExtra(KEY_EDIT_DATA);
            mTilName.getEditText().setText(mContact.getName());
            mTilPhone.getEditText().setText(mContact.getPhone());
            mTilLat.getEditText().setText(mContact.getLatitude().toString());
            mTilLong.getEditText().setText(mContact.getLongitude().toString());
        }
    }

    @OnClick(R.id.btSave)
    void onSave() {
        if(mContact == null) {
            mContact = new Contact();
        }
        mContact.setName(mTilName.getEditText().getText().toString());
        mContact.setPhone(mTilPhone.getEditText().getText().toString());
        mContact.setLatitude(Double.parseDouble(mTilLat.getEditText().getText().toString()));
        mContact.setLongitude(Double.parseDouble(mTilLong.getEditText().getText().toString()));
        mContact.save();

        returnToList(Activity.RESULT_OK);
    }

    private void returnToList(int result) {
        Intent returnIntent = new Intent();
        setResult(result, returnIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(mContact != null) {
            getMenuInflater().inflate(R.menu.options_menu, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mDelete) {
            new AlertDialog.Builder(this)
                    .setMessage(R.string.msg_delete)
                    .setTitle(R.string.dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mContact.delete();
                            returnToList(RESULT_DELETE);
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .create().show();
        }
        return super.onOptionsItemSelected(item);
    }
}
