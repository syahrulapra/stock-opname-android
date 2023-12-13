package com.syahrul.stockopnamebarang.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.syahrul.stockopnamebarang.Model.Item;
import com.syahrul.stockopnamebarang.Config.Config;
import com.syahrul.stockopnamebarang.Model.ItemName;
import com.syahrul.stockopnamebarang.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ListViewHolder> {
    private List<Item> listItem;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ItemAdapter(List<Item> itemList) {
        this.listItem = itemList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        try {
            Item item = listItem.get(position);
            ItemName name = item.getItems();
            Glide.with(holder.itemView.getContext()).load(listItem.get(position).get_image()).apply(new RequestOptions().override(520,520)).into(holder.ivImage);
            holder.tvName.setText(name.getName() + " " + item.get_series());
            holder.tvTotal.setText("Item Tersedia : " + item.get_total());

            holder.itemView.setOnClickListener(v -> showBottomSheetDialog(holder.itemView.getContext(), listItem.get(holder.getAdapterPosition())));

        } catch (Exception ex) {
            Log.d("ini eksepsi", ex.toString());
        }
    }

    private void showBottomSheetDialog(Context context, Item item) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.activity_detail, null);
        bottomSheetDialog.setContentView(view);

        ImageView ivDetImage = view.findViewById(R.id.iv_image);
        Glide.with(context).load(item.get_image()).into(ivDetImage);

        TextView tvDetName = view.findViewById(R.id.tv_name);
        ItemName name = item.getItems();
        tvDetName.setText(name.getName() + " "  + item.get_series());

        TextView tvDetDeskripsi = view.findViewById(R.id.tv_deskripsi);
        tvDetDeskripsi.setText(item.get_description());

        bottomSheetDialog.show();
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvName, tvTotal;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTotal = itemView.findViewById(R.id.tv_total);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Item data);
    }
}
