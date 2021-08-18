package com.gusman.uas.biodata.ui.form;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.gusman.uas.biodata.R;
import com.gusman.uas.biodata.databinding.ActivityAddBioBinding;
import com.gusman.uas.biodata.helper.DBHelper;
import com.gusman.uas.biodata.model.BioData;
import com.gusman.uas.biodata.utilities.Utility;

import java.util.Date;

public class AddBioActivity extends AppCompatActivity {

    ActivityAddBioBinding binding;
    private final BioData data = new BioData();
    private Date tglLahir = new Date();

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_bio);
        binding.setData(data);
        binding.setLifecycleOwner(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        binding.btnSimpan.setOnClickListener(v -> checkFormValid());
        binding.etDob.setOnClickListener(v -> Utility.DatePickerDialog((DatePickerDialog.OnDateSetListener) (view, year, month, dayOfMonth) -> {
            tglLahir = Utility.intToDate(year, month, dayOfMonth);
            binding.etDob.setText(Utility.formatDate(tglLahir, "dd MMMM yyyy"));
        }, this, tglLahir).show());

        binding.btnBatal.setOnClickListener(v ->
                Utility.showConfirm(this,
                        "Data yang sudah diisi akan hilang, yakin ingin keluar?",
                        "Ya, Keluar",
                        "Tidak",
                        (dialog1, id) -> {
                            finish();
                            dialog1.dismiss();
                        }));
    }

    private void checkFormValid() {
        if (data.getNama().trim().isEmpty()) {
            binding.etNama.setError("Masukan nama");
        } else if (data.getAlamat().trim().isEmpty()) {
            binding.etAlamat.setError("Masukan alamat");
        } else if (data.getNomorHp().trim().isEmpty()) {
            binding.etNomorHp.setError("Masukan nomor handphone");
        } else if (data.getEmail().trim().isEmpty()) {
            binding.etEmail.setError("Masukan email");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(data.getEmail().trim()).matches()) {
            binding.etEmail.setError("Masukan email yang valid");
        } else if (data.getTanggalLahir().trim().isEmpty()) {
            binding.etDob.setError("Masukan tanggal lahir");
        } else {
            saveData();
        }
    }

    private void saveData() {
        DBHelper helper = new DBHelper(this);
        boolean success = helper.saveBioData(data);
        if (success) {
            Utility.showMessage(this, "Data berhasil disimpan", ((dialog, which) -> {
                dialog.dismiss();
                finish();
            }));
        } else {
            Utility.showMessage(this, "Data gagal disimpan", null);
        }
    }
}