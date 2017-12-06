package mfie1944.ubb.ro.travellerapp.model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import mfie1944.ubb.ro.travellerapp.BestDestinationsActivity;

/**
 * Created by fmatica on 12/6/17.
 */

public class RemoveDialogFragment extends DialogFragment {

    int itemPos = 0;

    public void setItemPos(int itemPos){
        this.itemPos = itemPos;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to remove the item?")
                .setPositiveButton("YES", (dialogInterface, i) -> {
                    ((BestDestinationsActivity)(RemoveDialogFragment.this.getActivity())).onClickRemove(itemPos);
                    dismiss();
                })
                .setNegativeButton("NO", (dialogInterface, i) -> dismiss());

        return builder.create();
    }
}
