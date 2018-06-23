package com.craftsvilla.dashboard;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.craftsvilla.R;
import com.craftsvilla.database.DatabaseController;
import com.craftsvilla.model.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class DashboardRowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ROW_JUST_LAUNCHED = 0;
    private static final int ROW_EXCLUSIVE_COLLECTION = 1;
    private static final int ROW_TRENDING_SAREES = 2;
    private static int TOTAL_ROW = 3;
    Fragment mFragment;
    DatabaseController databaseController;

    public DashboardRowAdapter(Fragment fragment) {
        mFragment = fragment;
        databaseController = new DatabaseController(mFragment.getActivity());
    }

    public class JustLaunchHolder extends RecyclerView.ViewHolder {
        RecyclerView rvDashboard;
        TextView tvHeader, tvViewAll;

        public JustLaunchHolder(View view) {
            super(view);
            rvDashboard = (RecyclerView) view.findViewById(R.id.rvDashboard);
            tvHeader = (TextView) view.findViewById(R.id.tvHeader);
            tvViewAll = (TextView) view.findViewById(R.id.tvViewAll);
        }
    }

    public class ExclusiveCollectionHolder extends RecyclerView.ViewHolder {
        RecyclerView rvDashboard;
        TextView tvHeader;

        public ExclusiveCollectionHolder(View view) {
            super(view);
            rvDashboard = (RecyclerView) view.findViewById(R.id.rvDashboard);
            tvHeader = (TextView) view.findViewById(R.id.tvHeader);
        }
    }

    public class TrendingSareeHolder extends RecyclerView.ViewHolder {
        RecyclerView rvDashboard;
        TextView tvHeader, tvViewAll;

        public TrendingSareeHolder(View view) {
            super(view);
            rvDashboard = (RecyclerView) view.findViewById(R.id.rvDashboard);
            tvHeader = (TextView) view.findViewById(R.id.tvHeader);
            tvViewAll = (TextView) view.findViewById(R.id.tvViewAll);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
//            case ROW_HEADER:
//                view = LayoutInflater.from(parent.getContext()).inflate(
//                        R.layout.layout_dashboard_header, parent, false);
//                return new HeaderRow(view);

            case ROW_JUST_LAUNCHED:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.layout_dashboard_just_launched, parent, false);
                return new JustLaunchHolder(view);

            case ROW_EXCLUSIVE_COLLECTION:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.layout_dashboard_exclusive_collection, parent, false);
                return new ExclusiveCollectionHolder(view);

            case ROW_TRENDING_SAREES:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.layout_dashboard_just_launched, parent, false);
                return new TrendingSareeHolder(view);

            default:
                break;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof JustLaunchHolder) {
            ((JustLaunchHolder) holder).tvHeader.setText("JUST LAUNCHED");
            List<ProductEntity> productEntities = databaseController.getProductList(1);

            ((JustLaunchHolder) holder).rvDashboard.setLayoutManager(new LinearLayoutManager(mFragment.getActivity(), LinearLayoutManager.HORIZONTAL, false));
            ((JustLaunchHolder) holder).rvDashboard.setAdapter(new DashboardJustLaunchedtemAdapter(mFragment, productEntities));
            ((JustLaunchHolder) holder).rvDashboard.smoothScrollToPosition(0);
        } else if (holder instanceof ExclusiveCollectionHolder) {
            ((ExclusiveCollectionHolder) holder).tvHeader.setText("CRAFTSVILLA EXCLUSIVE COLLECTION");
            List<ProductEntity> productEntities = databaseController.getProductList(2);
            ((ExclusiveCollectionHolder) holder).rvDashboard.setLayoutManager(new LinearLayoutManager(mFragment.getActivity()));
            ((ExclusiveCollectionHolder) holder).rvDashboard.setAdapter(new DashboardExclusiveCollectiontemAdapter(mFragment, productEntities));

        } else if (holder instanceof TrendingSareeHolder) {
            ((TrendingSareeHolder) holder).tvHeader.setText("TRENDING SAREES");
            List<ProductEntity> productEntities = databaseController.getProductList(3);
            ((TrendingSareeHolder) holder).rvDashboard.setLayoutManager(new LinearLayoutManager(mFragment.getActivity(), LinearLayoutManager.HORIZONTAL, false));
            ((TrendingSareeHolder) holder).rvDashboard.setAdapter(new DashboardJustLaunchedtemAdapter(mFragment, productEntities));
            ((TrendingSareeHolder) holder).rvDashboard.smoothScrollToPosition(0);
        }

    }


    @Override
    public int getItemCount() {
        return TOTAL_ROW;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {

            case ROW_JUST_LAUNCHED:
                return ROW_JUST_LAUNCHED;
            case ROW_EXCLUSIVE_COLLECTION:
                return ROW_EXCLUSIVE_COLLECTION;
            case ROW_TRENDING_SAREES:
                return ROW_TRENDING_SAREES;
            default:
                break;
        }
        return position;
    }


}