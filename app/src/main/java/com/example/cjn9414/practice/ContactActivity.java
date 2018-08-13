package com.example.cjn9414.practice;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {
    private static final int RESULT_PICK_CONTACT = 6291;
    private TextView tv_contactName;
    private TextView tv_contactNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        tv_contactName = findViewById(R.id.contact_name);
        tv_contactNumber = findViewById(R.id.contact_number);
        pickContact(this.getCurrentFocus());
    }

    public void pickContact(View v) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else throw new UnsupportedOperationException();
    }
    private void contactPicked(Intent data) {
        try {
            String phone, name;
            Uri uri = data.getData();
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phone = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);
            tv_contactName.setText(name);
            tv_contactNumber.setText(phone);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
