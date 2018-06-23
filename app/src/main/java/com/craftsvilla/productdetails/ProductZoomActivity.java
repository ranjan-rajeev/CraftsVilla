package com.craftsvilla.productdetails;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;
import com.craftsvilla.R;
import com.craftsvilla.customviews.TouchImageView;

public class ProductZoomActivity extends AppCompatActivity {

    TouchImageView ivProdImg;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_zoom);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ivProdImg = (TouchImageView) findViewById(R.id.ivProdImg);
        if (getIntent().hasExtra("URL")) {
            url = getIntent().getStringExtra("URL");
            if (!url.equals("")) {
                Glide.with(ProductZoomActivity.this).load(url)
                        .fitCenter()
                        .placeholder(R.drawable.ic_action_cart).into(ivProdImg);
            }

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
