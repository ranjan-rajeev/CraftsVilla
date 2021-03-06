package com.craftsvilla;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;

public class BaseActivity extends AppCompatActivity {

    public Realm realm;
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Realm
        Realm.init(this);
        // Get a Realm instance for this thread
        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void cancelDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    protected void showDialog() {
//        dialog = ProgressDialog.show(BaseActivity.this, "", "Loading...", true);
        showDialog("Loading...");
    }

    protected void showDialog(String msg) {
        if (dialog == null) {
            dialog = ProgressDialog.show(BaseActivity.this, "", msg, true);
        } else {
            if (!(dialog.isShowing())) {
                dialog = ProgressDialog.show(BaseActivity.this, "", msg, true);
            }
        }

    }
}

