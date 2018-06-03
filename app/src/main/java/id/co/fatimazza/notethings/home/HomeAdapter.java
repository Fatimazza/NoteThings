package id.co.fatimazza.notethings.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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

    private ItemListener itemListener;

    public HomeAdapter(Context context, ItemListener listener) {
        this.context = context;
        thingsList = new ArrayList<>();
        itemListener = listener;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootview = LayoutInflater.from(context).inflate(R.layout.item_things, parent, false);
        return new HomeViewHolder(rootview, itemListener);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        if (null != thingsList) {
            holder.tvThingName.setText(thingsList.get(position).getName());
            holder.tvThingQuantity.setText(thingsList.get(position).getQuantity());
            holder.tvThingDate.setText(thingsList.get(position).getDate());
            holder.tvThingSupplier.setText(thingsList.get(position).getSupplier());
        }
        holder.setOnItemClickListener(itemListener);
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

    public List<Things> getListOfThings() {
        return thingsList;
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        View rootView;

        @BindView(R.id.rl_things)
        RelativeLayout rlThing;

        @BindView(R.id.tv_thing_name)
        TextView tvThingName;

        @BindView(R.id.tv_thing_quantity)
        TextView tvThingQuantity;

        @BindView(R.id.tv_thing_supplier)
        TextView tvThingSupplier;

        @BindView(R.id.tv_thing_date)
        TextView tvThingDate;

        ItemListener itemListener;

        public HomeViewHolder(View itemView, final ItemListener itemListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.rootView = itemView;
            this.itemListener = itemListener;
        }

        public void setOnItemClickListener(final ItemListener itemListener) {
            if (rootView != null && itemListener != null) {
                rlThing.setSelected(true);
                rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        itemListener.onItemClick(getAdapterPosition());
                    }
                });
            }
        }
    }

    public interface ItemListener {
        void onItemClick(int position);
    }

}
