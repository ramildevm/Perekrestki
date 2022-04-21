package com.example.perekrestki;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;

public class CustomDialogFragment extends DialogFragment {
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Как играть")
                .setIcon(android.R.drawable.ic_menu_help)
                .setMessage("   В этой игре вам предстоит из 3-ех вариантов ответа выбрать правильную очередность проезда машин на перекрестке.")
                .setPositiveButton("OK", null)
                .create();
    }
}
