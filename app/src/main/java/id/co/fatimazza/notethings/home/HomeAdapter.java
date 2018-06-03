package id.co.fatimazza.notethings.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.fatimazza.notethings.R;
import id.co.fatimazza.notethings.database.Things;

/**
 * Created by fatimazza on 6/3/18.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private List<Things> thingsList;

    private Context context;

    public HomeAdapter(Context context) {
        this.context = context;
        thingsList = new ArrayList<>();
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootview = LayoutInflater.from(context).inflate(R.layout.item_things, parent, false);
        return new HomeViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        if (null != thingsList) {
            holder.tvThingName.setText(thingsList.get(position).getName());
            holder.tvThingQuantity.setText(thingsList.get(position).getQuantity());
            holder.tvThingDate.setText(thingsList.get(position).getDate());
            holder.tvThingSupplier.setText(thingsList.get(position).getSupplier());
        }
    }

    @Override
    public int getItemCount() {
        return (null != thingsList) ? thingsList.size() : 0;
    }

    void updateListOfThings(List<Things> thingList) {
        this.thingsList.clear();
        this.thingsList.addAll(thingList);
        notifyDataSetChanged();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        View rootView;

        @BindView(R.id.tv_thing_name)
        TextView tvThingName;

        @BindView(R.id.tv_thing_quantity)
        TextView tvThingQuantity;

        @BindView(R.id.tv_thing_supplier)
        TextView tvThingSupplier;

        @BindView(R.id.tv_thing_date)
        TextView tvThingDate;

        public HomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.rootView = itemView;
            tvThingName = rootView.findViewById(R.id.tv_thing_name);
            tvThingQuantity = rootView.findViewById(R.id.tv_thing_quantity);
            tvThingSupplier = rootView.findViewById(R.id.tv_thing_supplier);
            tvThingDate = rootView.findViewById(R.id.tv_thing_date);
        }
    }

}
