package com.example.lenovo.laba;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import static com.example.lenovo.laba.MainActivity.db;

public class MedicineDialog extends DialogFragment {
    public interface NoticeDialogListener {
        public void onDialogUpdateClick(int id);
        public void onDialogDeleteClick(int id);
    }

    NoticeDialogListener mListener;

    public void setDialogListener(NoticeDialogListener listener) {
        this.mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.choosing_med_action)
                .setItems(R.array.med_action_items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int _id = getArguments().getInt("medicine_id");
                        switch (which) {
                            case 0:
                                mListener.onDialogUpdateClick(_id);
                                break;
                            case 1:
                                db.delete(DatabaseHelper.TABLE, "_id = ?", new String[]{String.valueOf(_id)});
                                mListener.onDialogDeleteClick(_id);
                                break;
                        }
                    }
                });
        return builder.create();
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        // Verify that the host activity implements the callback interface
//        try {
//            // Instantiate the NoticeDialogListener so we can send events to the host
//            mListener = (NoticeDialogListener) context;
//        } catch (ClassCastException e) {
//            // The activity doesn't implement the interface, throw exception
//            throw new ClassCastException(context.toString()
//                    + " must implement NoticeDialogListener");
//        }
//    }

}
