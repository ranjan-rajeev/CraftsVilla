package com.craftsvilla.splashscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.craftsvilla.MainActivity;
import com.craftsvilla.R;
import com.craftsvilla.database.AsyncProductMaster;
import com.craftsvilla.database.DatabaseController;
import com.craftsvilla.model.ProductDetailsEntity;
import com.craftsvilla.model.ProductEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreenActivity extends AppCompatActivity {


    private final int SPLASH_DISPLAY_LENGTH = 3000;
    List<ProductEntity> productEntityList;
    DatabaseController databaseController;
    int prodId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        databaseController = new DatabaseController(this);
        if (databaseController.getProductList().size() == 0) {
            productEntityList = new ArrayList<>();
            addJustLaunch();
            addExclusiveCollection();
            addTrendingSaree();
            new AsyncProductMaster(this, productEntityList).execute();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    private void addTrendingSaree() {
        productEntityList.add(new ProductEntity(++prodId, 3, 5000, 2500, 50, "https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg-1526298928"));
        productEntityList.add(new ProductEntity(++prodId, 3, 6000, 3000, 50, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF86978978780-1525952824-Craftsvilla_1.jpg-1526294468"));
        productEntityList.add(new ProductEntity(++prodId, 3, 3000, 1000, 66, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF77291130060-1522062244-Craftsvilla_1.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 3, 4567, 3000, 23, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF31778505270-1509966065-Craftsvilla_1.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 3, 6789, 2000, 34, "https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF41280071060-1522062244-Craftsvilla_1.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 3, 7897, 2400, 56, "https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg-1526298928"));
        productEntityList.add(new ProductEntity(++prodId, 3, 5000, 2500, 57, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF89157770280-1522062265-Craftsvilla_1.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 3, 9876, 1500, 90, "https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg-1526298928"));
        productEntityList.add(new ProductEntity(++prodId, 3, 5000, 2500, 50, "https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg-1526298928"));
        productEntityList.add(new ProductEntity(++prodId, 3, 6000, 3000, 50, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF86978978780-1525952824-Craftsvilla_1.jpg-1526294468"));
        productEntityList.add(new ProductEntity(++prodId, 3, 3000, 1000, 66, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF77291130060-1522062244-Craftsvilla_1.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 3, 4567, 3000, 23, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF31778505270-1509966065-Craftsvilla_1.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 3, 6789, 2000, 34, "https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF41280071060-1522062244-Craftsvilla_1.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 3, 7897, 2400, 56, "https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg-1526298928"));
        productEntityList.add(new ProductEntity(++prodId, 3, 5000, 2500, 57, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF89157770280-1522062265-Craftsvilla_1.jpg"));

    }

    private void addExclusiveCollection() {
        productEntityList.add(new ProductEntity(++prodId, 2, 5000, 2500, 50, "https://img3.craftsvilla.com/image/upload/f_auto/assets1/banner-craftsvilla/cvfeeds/1529574328_saree_Webexc.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 2, 6000, 3000, 50, "https://img3.craftsvilla.com/image/upload/f_auto/assets1/banner-craftsvilla/cvfeeds/1529574334_saree1_Web.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 2, 3000, 1000, 66, "https://img3.craftsvilla.com/image/upload/f_auto/assets1/banner-craftsvilla/cvfeeds/1529574341_saree2_Web.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 2, 4567, 3000, 23, "https://img3.craftsvilla.com/image/upload/f_auto/assets1/banner-craftsvilla/cvfeeds/1529574346_saree3_Web.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 2, 6789, 2000, 34, "https://img3.craftsvilla.com/image/upload/f_auto/assets1/banner-craftsvilla/cvfeeds/1529574341_saree2_Web.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 2, 7897, 2400, 56, "https://img3.craftsvilla.com/image/upload/f_auto/assets1/banner-craftsvilla/cvfeeds/1529574328_saree_Webexc.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 2, 5000, 2500, 57, "https://img3.craftsvilla.com/image/upload/f_auto/assets1/banner-craftsvilla/cvfeeds/1529574334_saree1_Web.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 2, 9876, 1500, 90, "https://img3.craftsvilla.com/image/upload/f_auto/assets1/banner-craftsvilla/cvfeeds/1529574346_saree3_Web.jpg"));
    }

    private void addJustLaunch() {
        productEntityList.add(new ProductEntity(++prodId, 1, 5000, 2500, 50, "https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg-1526298928"));
        productEntityList.add(new ProductEntity(++prodId, 1, 6000, 3000, 50, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF86978978780-1525952824-Craftsvilla_1.jpg-1526294468"));
        productEntityList.add(new ProductEntity(++prodId, 1, 3000, 1000, 66, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF77291130060-1522062244-Craftsvilla_1.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 1, 4567, 3000, 23, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF31778505270-1509966065-Craftsvilla_1.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 1, 6789, 2000, 34, "https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF41280071060-1522062244-Craftsvilla_1.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 1, 7897, 2400, 56, "https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg-1526298928"));
        productEntityList.add(new ProductEntity(++prodId, 1, 5000, 2500, 57, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF89157770280-1522062265-Craftsvilla_1.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 1, 9876, 1500, 90, "https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg-1526298928"));
        productEntityList.add(new ProductEntity(++prodId, 1, 5000, 2500, 50, "https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg-1526298928"));
        productEntityList.add(new ProductEntity(++prodId, 1, 6000, 3000, 50, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF86978978780-1525952824-Craftsvilla_1.jpg-1526294468"));
        productEntityList.add(new ProductEntity(++prodId, 1, 3000, 1000, 66, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF77291130060-1522062244-Craftsvilla_1.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 1, 4567, 3000, 23, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF31778505270-1509966065-Craftsvilla_1.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 1, 6789, 2000, 34, "https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF41280071060-1522062244-Craftsvilla_1.jpg"));
        productEntityList.add(new ProductEntity(++prodId, 1, 7897, 2400, 56, "https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg-1526298928"));
        productEntityList.add(new ProductEntity(++prodId, 1, 5000, 2500, 57, "https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF89157770280-1522062265-Craftsvilla_1.jpg"));


    }

    private RealmList<ProductDetailsEntity> getProductDetails() {
        RealmList<ProductDetailsEntity> productDetailsEntities = new RealmList<>();
        productDetailsEntities.add(new ProductDetailsEntity("Has Blouse Peice", "Yes"));
        productDetailsEntities.add(new ProductDetailsEntity("Saree Color", "Multicolor"));
        productDetailsEntities.add(new ProductDetailsEntity("Wash Care", "Dry Clean"));
        productDetailsEntities.add(new ProductDetailsEntity("Blouse Peice Fabric", "Cotton Silk"));
        productDetailsEntities.add(new ProductDetailsEntity("Pattern ", "Traditional Prints"));
        productDetailsEntities.add(new ProductDetailsEntity("Saree Fabrics", "Cotton Silk"));
        productDetailsEntities.add(new ProductDetailsEntity("Style", "ReadyMAde Saree"));
        productDetailsEntities.add(new ProductDetailsEntity("Saree Work", "Patola"));
        productDetailsEntities.add(new ProductDetailsEntity("Blouse Peice Size", "0.8 Meters"));
        productDetailsEntities.add(new ProductDetailsEntity("Saree Length", "5.5 Meters"));
        productDetailsEntities.add(new ProductDetailsEntity("Occassion", "Traditional Festival"));
        return productDetailsEntities;
    }

    private RealmList<String> getDeliveryDetails() {
        RealmList<String> stringRealmList = new RealmList<>();
        stringRealmList.add("Available for Cash On Delivery");
        stringRealmList.add("Estimated Delivery 6-8 Working Days");
        stringRealmList.add("7 days Free return");
        stringRealmList.add("RS 99 Shipping charges");
        return stringRealmList;
    }

    private RealmList<String> getImages() {
        RealmList<String> stringRealmList = new RealmList<>();
        stringRealmList.add("https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg-1526298928");
        stringRealmList.add("https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF41280071060-1522062244-Craftsvilla_1.jpg");
        stringRealmList.add("https://img3.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF31778505270-1509966065-Craftsvilla_1.jpg");
        stringRealmList.add("https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF41280071060-1522062244-Craftsvilla_1.jpg");
        stringRealmList.add("https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg-1526298928");
        return stringRealmList;
    }

}
