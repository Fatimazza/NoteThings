package id.co.fatimazza.notethings.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.fatimazza.notethings.R;

/**
 * Created by fatimazza on 6/3/18.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private String [] dummyHome = { "Data1", "Data2", "Data3"};

    private Context context;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootview = LayoutInflater.from(context).inflate(R.layout.item_things, parent, false);
        return new HomeViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        String dummyHomeData = dummyHome[position];
        holder.tvThingName.setText(dummyHomeData);
        holder.tvThingQuantity.setText(dummyHomeData);
        holder.tvThingDate.setText(dummyHomeData);
        holder.tvThingSupplier.setText(dummyHomeData);
    }

    @Override
    public int getItemCount() {
        if (null == dummyHome) return 0;
        return dummyHome.length;
    }

    void setListOfThingsData(String[] homeData) {
        dummyHome = homeData;
        notifyDataSetChanged();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        View rootView;

        TextView tvThingName;

        TextView tvThingQuantity;

        TextView tvThingSupplier;

        TextView tvThingDate;

        public HomeViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            tvThingName = rootView.findViewById(R.id.tv_thing_name);
            tvThingQuantity = rootView.findViewById(R.id.tv_thing_quantity);
            tvThingSupplier = rootView.findViewById(R.id.tv_thing_supplier);
            tvThingDate = rootView.findViewById(R.id.tv_thing_date);
        }
    }

}
