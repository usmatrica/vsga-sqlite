package com.gusman.uas.biodata.utilities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.gusman.uas.biodata.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utility {

    public static Date intToDate(int year, int month, int day) {
        return intToDate(null, year, month, day);
    }

    public static Date intToDate(Date date, int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        } else {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        }
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    public static Dialog DatePickerDialog(DatePickerDialog.OnDateSetListener listener, Context context, Date selectedDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(selectedDate);
        cal.clear(Calendar.HOUR);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        final DatePickerDialog dialog = new DatePickerDialog(context, listener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMaxDate(new Date().getTime());
        return dialog;
    }

    public static void showConfirm(Context context, String message, String positiveText, String negativeText,
                                   DialogInterface.OnClickListener positiveAction) {
        Typeface face = ResourcesCompat.getFont(context, R.font.poppins);

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Konfirmasi");
        dialog.setMessage(message);
        dialog.setPositiveButton(positiveText, positiveAction);
        dialog.setNegativeButton(negativeText, (dialog1, id) -> dialog1.dismiss());
        dialog.setCancelable(false);
        dialog.create();

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        TextView textView = alertDialog.getWindow().findViewById(android.R.id.message);
        if (textView != null) {
            textView.setTypeface(face);
        }
    }

    public static void showMessage(Context context, String message, DialogInterface.OnClickListener listener) {
        Typeface face = ResourcesCompat.getFont(context, R.font.poppins);

        DialogInterface.OnClickListener clickListener = (dialog1, which) -> dialog1.dismiss();
        if (listener != null) {
            clickListener = listener;
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Pemberitahuan");
        dialog.setMessage(message);
        dialog.setPositiveButton("OK", clickListener);
        dialog.setCancelable(false);
        dialog.create();

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        TextView textView = alertDialog.getWindow().findViewById(android.R.id.message);
        if (textView != null) {
            textView.setTypeface(face);
        }
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format, Constants.LOCALE_ID);
        return formater.format(date);
    }
}
