package rd.la.chavales.rdrun;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUpActivity extends AppCompatActivity {
    // Explicit Variables
    private EditText nameEditText, surnameEditText, userEditText, passwordEditText;
    private RadioGroup radioGroup;
    private RadioButton avata1RadioButton, avata2RadioButton, avata3RadioButton, avata4RadioButton, avata5RadioButton;
    private String nameString, surnameString, userString, passwordString, avataString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // bind or Initial Widget
        nameEditText = (EditText) findViewById(R.id.editText);  // find into R and cast to EditText
        surnameEditText = (EditText) findViewById(R.id.editText2);
        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        radioGroup = (RadioGroup) findViewById(R.id.ragAvata);
        avata1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        avata2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        avata3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        avata4RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        avata5RadioButton = (RadioButton) findViewById(R.id.radioButton5);

        // Radio Controller (Check onClick radioButton)
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButton:
                        avataString = "0";
                        break;
                    case R.id.radioButton2:
                        avataString = "1";
                        break;
                    case R.id.radioButton3:
                        avataString = "2";
                        break;
                    case R.id.radioButton4:
                        avataString = "3";
                        break;
                    case R.id.radioButton5:
                        avataString = "4";
                        break;
                }

            }
        });




    } //Main Method

    public void clickSignUpSign(View view) {
        // Get Value from Edit Text
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        // Check Space
        if (clickSpace()) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, R.drawable.doremon48, "มีช่องว่าง", "กรุณากรอกข้อมูลให้ครบทุกช่อง !");
        } else if (checkChoose()) {
            // True ==> Have Choose
            confirmValue();

        } else {
            // False ==> Not Choose
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,R.drawable.nobita48,"ยังไม่มีการเลือก Avata","กรุณาเลือก Avata !");
        }


    }  // click Sign

    private void confirmValue() {
        MyConstant myConstant = new MyConstant();
        int[] avataInts = myConstant.getAvataInts();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(avataInts[Integer.parseInt(avataString)]);
        builder.setTitle("โปรดตรวจสอบข้อมูล");
        builder.setMessage("Name = " + nameString + "\n" +
                "Surname = " + surnameString + "\n" +
                "User = " + userString + "\n" +
                "Password = " + passwordString);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                uploadValueToServer();
                dialogInterface.dismiss();
            }
        });




    } //Confirm Values

    private void uploadValueToServer() {

    }

    private boolean checkChoose() {
        boolean result = false;
        if (avata1RadioButton.isChecked() ||
                avata2RadioButton.isChecked() ||
                avata3RadioButton.isChecked() ||
                avata4RadioButton.isChecked() ||
                avata5RadioButton.isChecked()) {
            result = true;
        }

        return false;
    }

    private boolean clickSpace() {
        boolean result = false;
        if (nameString.equals("") ||
                surnameString.equals("") ||
                userString.equals("") ||
                passwordString.equals("")){
            result = true;
        }

        return result;

    }
} // Main Class
