package com.craftsvilla.database;

import android.content.Context;
import android.os.AsyncTask;

import com.craftsvilla.model.ProductEntity;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Rajeev Ranjan on 23-Jun-18.
 */
public class AsyncProductMaster extends AsyncTask<Void, Void, Void> {
    Context mContext;
    List<ProductEntity> productEntities;

    public AsyncProductMaster(Context context, List<ProductEntity> list) {
        productEntities = list;
        mContext = context;
    }


    @Override
    protected Void doInBackground(Void... voids) {

        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(productEntities);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
