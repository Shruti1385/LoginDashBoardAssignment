package com.example.logindashboardassignment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button uploadPanCard_btn,next_btn;
    private EditText datePicker_txt,panNumber_txt,fullName_txt,fatherName_txt;
    private TextView error_txt;
    private String datePicker_str = "";
    private int year ,dayOfMonth, monthOfYear;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.upload_pancard_imgview);
        uploadPanCard_btn = (Button) findViewById(R.id.uploadbtn);
        next_btn = (Button) findViewById(R.id.button);
        datePicker_txt = (EditText) findViewById(R.id.dob);
        error_txt = (TextView) findViewById(R.id.error_txt);
        panNumber_txt = (EditText) findViewById(R.id.pan_number);
        fullName_txt = (EditText) findViewById(R.id.full_name);
        fatherName_txt = (EditText) findViewById(R.id.fathers_name);

        Calendar calendar = Calendar.getInstance();
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        monthOfYear = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        uploadPanCard_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(MainActivity.this);
            }
        });

        //show datePicker
        datePicker_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog(MainActivity.this);
            }
        });

        //validate all fields and navigate to dashboard screen
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                //Pan card validation
                boolean valid = true;
               if(panNumber_txt.getText().toString().length()==0 ||(!isValidPanNumber(panNumber_txt.getText().toString()))) {
                    displayErrorMessage("Please enter valid Pan number");
                    valid = false;

                }
                else if(fullName_txt.getText().length()==0){
                    displayErrorMessage("Full name field can't be empty");
                    valid = false;
                }
                else if(fatherName_txt.getText().length() == 0){
                    displayErrorMessage("Father's name field can't be empty");
                    valid = false;
                }
                else if(datePicker_txt.getText().length() == 0){
                    displayErrorMessage("Please enter DOB");
                    valid = false;
                }
                if (valid){
                    error_txt.setText("");
                    //navigate to next screen
                    Intent intent = new Intent(MainActivity.this, DashBoardActivity.class);
                    intent.putExtra("FullName", fullName_txt.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }

    public boolean isValidPanNumber(final String panNumber) {
        Pattern pattern;
        Matcher matcher;
        String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(panNumber);

        return matcher.matches();

    }

    private void displayErrorMessage(String error_msg) {
        error_txt.setText(error_msg);
    }

    public void DateDialog(Context context){

        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
            {   dayOfMonth = dayOfMonth; monthOfYear = monthOfYear; year = year;
                datePicker_txt.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
            }};

        DatePickerDialog dpDialog=new DatePickerDialog(context, listener, year, monthOfYear, dayOfMonth);
        dpDialog.show();
    }

    // upload image from Gallery or take a photo
    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    pickPhoto.setType("image/*");
                    pickPhoto.putExtra("crop", "true");
                    pickPhoto.putExtra("scale", true);
                    pickPhoto.putExtra("outputX", 256);
                    pickPhoto.putExtra("outputY", 256);
                    pickPhoto.putExtra("aspectX", 1);
                    pickPhoto.putExtra("aspectY", 1);
                    pickPhoto.putExtra("return-data", true);
                    startActivityForResult(pickPhoto, 1);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        final Bundle extras = data.getExtras();
                        if (extras != null) {
                            //Get image
                            Bitmap ProfilePic = extras.getParcelable("data");
                            imageView.setImageBitmap(ProfilePic);

                        }

                    }
                    break;
            }
        }
    }

}