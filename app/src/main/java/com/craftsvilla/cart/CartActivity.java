package com.craftsvilla.cart;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.craftsvilla.BaseActivity;
import com.craftsvilla.R;
import com.craftsvilla.database.AsyncCartMaster;
import com.craftsvilla.database.DatabaseController;
import com.craftsvilla.model.CartEntity;
import com.craftsvilla.model.ProductEntity;

import java.util.List;

import io.realm.RealmList;

public class CartActivity extends BaseActivity implements View.OnClickListener {

    CartEntity cartEntity;
    CartAdapter cartAdapter;
    RealmList<ProductEntity> productEntities;
    DatabaseController databaseController;
    //region  widgets
    LinearLayout llMAin;
    TextView tvItems, tvTotalPayableTop, tvProceedToCheckoutTop, tvSubTotal, tvShipping, tvTotalPayable, tvProceedToCheckout;
    RecyclerView rvCart;

    //endregion
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseController = new DatabaseController(this);

        init_widgets();
        setListener();

        cartEntity = databaseController.getCart();
        if (cartEntity != null && cartEntity.getProductEntities() != null && cartEntity.getProductEntities().size() != 0) {
            bindCart(cartEntity);
            llMAin.setVisibility(View.VISIBLE);
        } else {
            llMAin.setVisibility(View.GONE);
            Toast.makeText(this, "No items in cart .", Toast.LENGTH_SHORT).show();
        }
        countDownTimer = new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
                //here you can have your logic to set text to edittext
                showDialog();
            }

            public void onFinish() {
                cartEntity = databaseController.getCart();
                bindCart(cartEntity);
                cancelDialog();
            }

        };
    }

    private void bindCart(CartEntity cartEntity) {
        tvItems.setText("ITEMS (" + cartEntity.getTotalItem() + ")");
        tvTotalPayableTop.setText("Total Payable " + "\u20B9 " + cartEntity.getTotalAmount());
        productEntities = cartEntity.getProductEntities();
        cartAdapter = new CartAdapter(this, productEntities);
        rvCart.setAdapter(cartAdapter);
        tvSubTotal.setText("\u20B9 " + cartEntity.getSubAmount());
        tvShipping.setText("\u20B9 " + cartEntity.getShipping());
        tvTotalPayable.setText("\u20B9 " + cartEntity.getTotalAmount());

    }

    private void setListener() {
        tvProceedToCheckoutTop.setOnClickListener(this);
        tvProceedToCheckout.setOnClickListener(this);
    }

    private void init_widgets() {
        llMAin = (LinearLayout) findViewById(R.id.llMAin);
        tvItems = (TextView) findViewById(R.id.tvItems);
        tvTotalPayableTop = (TextView) findViewById(R.id.tvTotalPayableTop);
        tvProceedToCheckoutTop = (TextView) findViewById(R.id.tvProceedToCheckoutTop);
        tvSubTotal = (TextView) findViewById(R.id.tvSubTotal);
        tvShipping = (TextView) findViewById(R.id.tvShipping);
        tvTotalPayable = (TextView) findViewById(R.id.tvTotalPayable);
        tvProceedToCheckout = (TextView) findViewById(R.id.tvProceedToCheckout);
        rvCart = (RecyclerView) findViewById(R.id.rvCart);
        rvCart.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvProceedToCheckout:
            case R.id.tvProceedToCheckoutTop:
                Toast.makeText(this, "Redirect To Payment", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void minusProduct(ProductEntity productEntity) {
        new AsyncCartMaster(this, productEntity.getProdId(), (productEntity.getProdQuantity() - 1)).execute();
        countDownTimer.start();
    }

    public void addProduct(ProductEntity productEntity) {
        new AsyncCartMaster(this, productEntity.getProdId(), (productEntity.getProdQuantity() + 1)).execute();
        countDownTimer.start();
    }

    public void deleteProduct(ProductEntity productEntity) {
        new AsyncCartMaster(this, productEntity.getProdId(), 0).execute();
        countDownTimer.start();
    }

    public void refreshAdapter(final CartEntity cartEntity) {

    }
}



