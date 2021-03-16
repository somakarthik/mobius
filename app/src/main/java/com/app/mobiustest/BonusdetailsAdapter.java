package com.app.mobiustest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BonusdetailsAdapter extends RecyclerView.Adapter<BonusdetailsAdapter.ViewHolder> {
    List<Example> examples = new ArrayList<>();
    MainActivity mainActivity;

    public BonusdetailsAdapter(MainActivity mainActivity, List<Example> examples) {
        this.mainActivity = mainActivity;
        this.examples = examples;
    }


    @NonNull
    @Override
    public BonusdetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bonus_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BonusdetailsAdapter.ViewHolder holder, int position) {


        holder.tvcode.setText(examples.get(position).getCode());
        holder.tvdiscount.setText(examples.get(position).getRibbonMsg());
        holder.tvbet.setText("For every " + examples.get(position).getWagerToReleaseRatioNumerator() + "bet " + examples.get(position).getWagerToReleaseRatioDenominator() + "will be released from the bonus amount");
        holder.tvexpiry.setText("Bonus expiry from " + examples.get(position).getWagerBonusExpiry() + " from the issue");

        List<Slab> slabs = examples.get(position).getSlabs();
        double slabamount = 0.0;
        double otcpercent = 0.0;
        double totalper = 0.0;
        double maxamount = 0.0;
        double otcmax = 0.0;
        double totalmax = 0.0;
        for (int i = 0; i < slabs.size(); i++) {
            Slab slab = slabs.get(i);
            View viewChild = LayoutInflater.from(mainActivity).inflate(R.layout.item_slab_layout, holder.llslabs, false);
            ((TextView) viewChild.findViewById(R.id.tv_slab_purchase_amount)).setText("" + slab.getMin() + " & " + slab.getMax());
            ((TextView) viewChild.findViewById(R.id.tv_slab_bonus_amount)).setText("" + slab.getWageredPercent() + " (" + slab.getWageredMax() + ")");
            ((TextView) viewChild.findViewById(R.id.tv_instant_cash)).setText("" + slab.getOtcPercent() + " (" + slab.getOtcMax() + ")");
            holder.llslabs.addView(viewChild);
            slabamount = slabamount + slab.getWageredPercent();
            otcpercent = otcpercent + slab.getOtcPercent();
            totalper = slabamount + otcpercent;

            maxamount = maxamount + slab.getWageredMax();
            otcmax = otcmax + slab.getOtcMax();
            totalmax = maxamount + otcmax;

            holder.tv_slab_amount.setText("" + slab.getMin());
        }
        holder.tvpercent.setText("Get " + totalper + " % " + " upto " + totalmax);

    }


    @Override
    public int getItemCount() {
        return examples.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvcode;
        TextView tvdiscount;
        TextView tvpercent;
        TextView tv_slab_amount;
        TextView tvbet;
        TextView tvexpiry;
        LinearLayout llslabs;
        TextView tvpurchase;
        TextView tvinstant;
        TextView tvbonus;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvcode = itemView.findViewById(R.id.tv_code);
            tvdiscount = itemView.findViewById(R.id.tv_discout);
            tvpercent = itemView.findViewById(R.id.tv_slab);
            tv_slab_amount = itemView.findViewById(R.id.tv_min_slab);
            tvbet = itemView.findViewById(R.id.tv_bet);
            tvexpiry = itemView.findViewById(R.id.tv_bonus_expiry);
            llslabs = itemView.findViewById(R.id.llslab);


        }
    }
}
