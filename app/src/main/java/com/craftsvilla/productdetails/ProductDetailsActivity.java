package com.craftsvilla.productdetails;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.craftsvilla.BaseActivity;
import com.craftsvilla.R;
import com.craftsvilla.cart.CartActivity;
import com.craftsvilla.database.AsyncCartMaster;
import com.craftsvilla.database.DatabaseController;
import com.craftsvilla.model.CartEntity;
import com.craftsvilla.model.ProductEntity;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener {

    ProductEntity productEntity;
    TextView textCartItemCount;
    int mCartItemCount = 0;
    DatabaseController databaseController;
    CartEntity cartEntity;
    //region widgets
    CarouselView carouselView;
    TextView tvGoToCart, tvBuyNow, prodDesc, tvSeller, tvSproductCode,
            tvDiscountedPrice, tvActualPrice, tvPercentOff;
    ImageView ivShare;
    RatingBar rating;
    RecyclerView rvDelivery, rvProductDetails;
    ProductDeliveryAdapter productDeliveryAdapter;
    ProductDetailsAdapter productDetailsAdapter;
    List<String> corosoulList;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseController = new DatabaseController(this);
        if (getIntent().hasExtra("PRODUCT_ENTITY")) {
            productEntity = getIntent().getParcelableExtra("PRODUCT_ENTITY");
        }
        init_widgets();
        setListeners();

        if (productEntity != null) {
            bindProductDetails(productEntity);
        }

    }


    private void bindProductDetails(final ProductEntity productEntity) {
        //region setting Corousal view
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(productEntity.getProdImages().size());

        carouselView.setImageListener(imageListener);
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                startActivity(new Intent(ProductDetailsActivity.this, ProductZoomActivity.class).putExtra("URL", productEntity.getProdImages().get(position)));
                //Toast.makeText(ProductDetailsActivity.this, "Clicked item: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        //endregion

        prodDesc.setText("" + productEntity.getProdDesc());
        tvDiscountedPrice.setText("\u20B9 " + productEntity.getProdDiscountedPrice());
        tvActualPrice.setPaintFlags(tvActualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvActualPrice.setText("\u20B9 " + productEntity.getProdActualPrice());
        tvPercentOff.setText("SAVE " + productEntity.getProdDiscPercentage() + "%");
        productDeliveryAdapter = new ProductDeliveryAdapter(this, productEntity.getDeliveryDetails());
        rvDelivery.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvDelivery.setAdapter(productDeliveryAdapter);

        productDetailsAdapter = new ProductDetailsAdapter(this, productEntity.getProductDetails());
        rvProductDetails.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvProductDetails.setAdapter(productDetailsAdapter);

        tvSeller.setText("Sold By - " + productEntity.getProdSellerName());
        tvSproductCode.setText("SKU - " + productEntity.getProdUniqueCode());
    }

    private void setListeners() {
        tvBuyNow.setOnClickListener(this);
        tvGoToCart.setOnClickListener(this);
    }

    private void init_widgets() {
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        tvBuyNow = (TextView) findViewById(R.id.tvBuyNow);
        tvGoToCart = (TextView) findViewById(R.id.tvGoToCart);
        tvGoToCart.setText("Add to Cart");
        prodDesc = (TextView) findViewById(R.id.prodDesc);
        tvDiscountedPrice = (TextView) findViewById(R.id.tvDiscountedPrice);
        tvActualPrice = (TextView) findViewById(R.id.tvActualPrice);
        tvPercentOff = (TextView) findViewById(R.id.tvPercentOff);
        ivShare = (ImageView) findViewById(R.id.ivShare);
        rating = (RatingBar) findViewById(R.id.rating);
        rvDelivery = (RecyclerView) findViewById(R.id.rvDelivery);
        rvProductDetails = (RecyclerView) findViewById(R.id.rvProductDetails);
        tvSeller = (TextView) findViewById(R.id.tvSeller);
        tvSproductCode = (TextView) findViewById(R.id.tvSproductCode);

    }

    //region menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_cart: {
                startActivity(new Intent(this, CartActivity.class));
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    //endregion

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvGoToCart:

                CartEntity cartEntity = databaseController.getCart();
                if (checkIfProdAlreadyExhist(cartEntity, productEntity.getProdId())) {
                    Toast.makeText(this, "Item Already in Cart !!!", Toast.LENGTH_SHORT).show();
                    tvGoToCart.setText("Go to Cart");
                } else {
                    if (cartEntity != null)
                        mCartItemCount = cartEntity.getTotalItem() + 1;
                    else
                        mCartItemCount = 1;
                    new AsyncCartMaster(this, productEntity.getProdId(), 1).execute();
                    //addProduct(cartEntity, productEntity);
                    Toast.makeText(this, "Item Added to Cart ", Toast.LENGTH_SHORT).show();
                    tvGoToCart.setText("Go to Cart");
                    setupBadge();
                }
                break;
            case R.id.tvBuyNow:
                CartEntity cartEntitynew = databaseController.getCart();
                if (checkIfProdAlreadyExhist(cartEntitynew, productEntity.getProdId())) {
                    startActivity(new Intent(this, CartActivity.class));
                } else {
                    if (cartEntitynew != null)
                        mCartItemCount = cartEntitynew.getTotalItem() + 1;
                    else
                        mCartItemCount = 1;
                    new AsyncCartMaster(this, productEntity.getProdId(), 1).execute();
                    startActivity(new Intent(this, CartActivity.class));
                    setupBadge();
                }
                break;
        }
    }

    public boolean checkIfProdAlreadyExhist(CartEntity cartEntity, int prodId) {
        boolean exhist = false;
        if (cartEntity != null) {
            for (ProductEntity productEntity : cartEntity.getProductEntities()) {
                if (productEntity.getProdId() == prodId)
                    return true;
            }
        }
        return exhist;
    }

    //region Corousal Listener
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            //imageView.setImageResource(productsEntities.get(position).getProduct_Image_Url());

            Glide.with(ProductDetailsActivity.this).load(productEntity.getProdImages().get(position))
                    .override(250, 300)
                    .placeholder(R.drawable.ic_action_cart).into(imageView);
        }
    };

    //endregion

    @Override
    protected void onResume() {
        super.onResume();
        cartEntity = databaseController.getCart();
        if (cartEntity != null && cartEntity.getProductEntities() != null) {
            mCartItemCount = cartEntity.getTotalItem();
            setupBadge();
        }
    }

    public void refreshAdapter(CartEntity cartEntity) {
        this.cartEntity = cartEntity;
    }
}
