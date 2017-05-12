package br.edu.ifsp.mpj.view;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import br.edu.ifsp.mpj.R;
import br.edu.ifsp.mpj.entity.Contact;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactActivity extends AppCompatActivity {

    @BindView(R.id.tilName) TextInputLayout mTilName;
    @BindView(R.id.tilPhone) TextInputLayout mTilPhone;
    @BindView(R.id.tilLat) TextInputLayout mTilLat;
    @BindView(R.id.tilLon) TextInputLayout mTilLog;
    @BindView(R.id.btSave) Button mBtSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btSave)
    void onSave() {
        Contact contact = new Contact();
        contact.setName(mTilName.getEditText().getText().toString());
        contact.setPhone(mTilPhone.getEditText().getText().toString());
        contact.setLatitude(Double.parseDouble(mTilLat.getEditText().getText().toString()));
        contact.setLongitude(Double.parseDouble(mTilLog.getEditText().getText().toString()));
        contact.save();

        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
