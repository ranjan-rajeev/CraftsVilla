package com.craftsvilla.dashboard;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.craftsvilla.BaseFragment;
import com.craftsvilla.R;
import com.craftsvilla.productdetails.ProductDetailsActivity;
import com.craftsvilla.productdetails.ProductZoomActivity;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment {

    RecyclerView rvHome;
    DashboardRowAdapter mAdapter;
    List<String> corosoulList;
    CarouselView carouselView;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initialise(view);

        //region setting Corousal view
        corosoulList = new ArrayList<String>();
        corosoulList.add("https://img3.craftsvilla.com/image/upload/f_auto/assets1/banner-craftsvilla/cvfeeds/1529560947_sarees_340.jpg");
        corosoulList.add("https://img3.craftsvilla.com/image/upload/f_auto/assets1/banner-craftsvilla/cvfeeds/1529573596_suits_hero.jpg");
        corosoulList.add("https://img3.craftsvilla.com/image/upload/f_auto/assets1/banner-craftsvilla/cvfeeds/1529572983_jewellery_hero.jpg");
        corosoulList.add("https://img3.craftsvilla.com/image/upload/f_auto/assets1/banner-craftsvilla/cvfeeds/1529573553_kurtis_hero.jpg");
        corosoulList.add("https://img3.craftsvilla.com/image/upload/f_auto/assets1/banner-craftsvilla/cvfeeds/1529573573_lehengas_hero.jpg");
        corosoulList.add("https://img3.craftsvilla.com/image/upload/f_auto/assets1/banner-craftsvilla/cvfeeds/1529572970_anarkalis_hero.jpg");

        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(corosoulList.size());

        carouselView.setImageListener(imageListener);
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                //startActivity(new Intent(MainActivity.this, WebViewActivity.class).putExtra("URL", productsEntities.get(position).getProduct_Url()));
                startActivity(new Intent(getActivity(), ProductZoomActivity.class).putExtra("URL", corosoulList.get(position)));
            }
        });
        //endregion

        mAdapter = new DashboardRowAdapter(DashboardFragment.this);
        this.rvHome.setAdapter(mAdapter);
        return view;
    }

    private void initialise(View view) {
        rvHome = (RecyclerView) view.findViewById(R.id.rvHome);
        rvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //region Corousal Listener
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            //imageView.setImageResource(productsEntities.get(position).getProduct_Image_Url());

            Glide.with(getActivity()).load(corosoulList.get(position))
                    .fitCenter()
                    .placeholder(R.drawable.ic_action_cart).into(imageView);
        }
    };
    //endregion
}
