package info.androidhive.recyclerview;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPersonDialog extends DialogFragment {
    Button mButton;
    EditText firstNameeditText, lastNameeditText;
    onSubmitListener mListener;


    interface onSubmitListener {
        void setOnSubmitListener(String firstName, String lastName);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.person_dialog);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        mButton = (Button) dialog.findViewById(R.id.addButton);
        firstNameeditText = (EditText) dialog.findViewById(R.id.firstNameeditText);
        lastNameeditText = (EditText) dialog.findViewById(R.id.lastNameeditText);

        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String strFirstName = firstNameeditText.getText().toString();
                String strLastName = lastNameeditText.getText().toString();

                if(TextUtils.isEmpty(strFirstName)) {
                    Toast.makeText(getActivity(), "Please enter First Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(strLastName)) {
                    Toast.makeText(getActivity(), "Please enter Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                mListener.setOnSubmitListener(strFirstName, strLastName);
                dismiss();
            }
        });
        return dialog;
    }
}
