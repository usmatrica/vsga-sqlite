package com.gusman.uas.biodata.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gusman.uas.biodata.R;
import com.gusman.uas.biodata.databinding.ItemBiodataBinding;
import com.gusman.uas.biodata.helper.DBHelper;
import com.gusman.uas.biodata.model.BioData;
import com.gusman.uas.biodata.ui.form.EditBioActivity;

import java.util.ArrayList;

public class BioDataAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    ArrayList<BioData> dataList;

    public BioDataAdapter(Context context, ArrayList<BioData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void setDataList(ArrayList<BioData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemBiodataBinding binding = ItemBiodataBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BioData data = dataList.get(position);
        holder.bind(context, data);
        holder.binding.llBioData.setOnClickListener(v -> {
            CharSequence[] options = new CharSequence[]{"Ubah", "Hapus"};

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(data.getNama());
            builder.setItems(options, (dialog, which) -> {
                if (which == 0) {
                    Intent intent = new Intent(context, EditBioActivity.class);
                    intent.putExtra(EditBioActivity.DATA, data);
                    context.startActivity(intent);
                    dialog.dismiss();
                } else if (which == 1) {
                    DBHelper helper = new DBHelper(context);
                    boolean success = helper.deleteBioData(data.getId());
                    if (success) {
                        dataList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeRemoved(position, dataList.size());
                        Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    final ItemBiodataBinding binding;

    public ViewHolder(ItemBiodataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Context context, BioData data) {
        binding.tvNama.setText(data.getNama());
        binding.tvAlamat.setText(data.getAlamat());

        if (data.getEmail().trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(data.getEmail().trim()).matches()) {
            binding.tvEmail.setEnabled(false);
            binding.tvEmail.setTextColor(ContextCompat.getColor(context, R.color.color_disabled));
            binding.tvEmail.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.ic_email_disabled), null, null, null);
        } else {
            binding.tvEmail.setEnabled(true);
            binding.tvEmail.setTextColor(ContextCompat.getColor(context, R.color.color_primary));
            binding.tvEmail.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.ic_email), null, null, null);

            binding.tvEmail.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + data.getEmail()));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Halo");
                intent.putExtra(Intent.EXTRA_TEXT, "Hai, apa kabar? Sudah lama kita tidak berjumpa");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(intent, "Send Email"));
            });
        }

        if (data.getNomorHp().trim().isEmpty()) {
            binding.tvPhone.setEnabled(false);
            binding.tvPhone.setTextColor(ContextCompat.getColor(context, R.color.color_disabled));
            binding.tvPhone.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.ic_phone_disabled), null, null, null);
        } else {
            binding.tvPhone.setEnabled(true);
            binding.tvPhone.setTextColor(ContextCompat.getColor(context, R.color.color_primary));
            binding.tvPhone.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.ic_phone), null, null, null);

            binding.tvPhone.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+62" + data.getNomorHp()));
                context.startActivity(intent);
            });
        }
    }
}
