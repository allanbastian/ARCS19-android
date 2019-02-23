package android.ieeecsvit.com.arcs19.Profile;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.ieeecsvit.com.arcs19.APIClient;
import android.ieeecsvit.com.arcs19.APIInterface;
import android.ieeecsvit.com.arcs19.Login.LoginActivity;
import android.ieeecsvit.com.arcs19.Login.UserClass;
import android.ieeecsvit.com.arcs19.MainActivity;
import android.ieeecsvit.com.arcs19.R;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Profile extends AppCompatActivity {
    private static final int SELECT_PICTURE = 1, CAPTURE_IMAGE = 2;
    DiscreteScrollView scrollView;
    ArrayList<ProfileScrollClass> items;
    ProfileScrollAdapter adapter;
    TextView phoneNumber,emailID, name, registration, barcodeText;
    ImageButton qrButton, backButton;
    FloatingActionButton signOutButton;
    LinearLayout progressSection;
    ImageView profileImage, barcodeImage;

    String USERNAME = "";
    String EMAIL = "";
    String MOBNUMBER = "";
    String REGNUMBER = "";
    String JWTTOKEN = "";
    SharedPreferences sp;

    ArrayList<String> regEvents;

    Boolean profileUpdateAvail = false;

    APIInterface apiInterface;

    Dialog myDialog;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        qrButton = findViewById(R.id.qr_code_button);
        backButton = findViewById(R.id.back_button);
        signOutButton = findViewById(R.id.sign_out_button);
        profileImage = findViewById(R.id.profile_pic);
        name = findViewById(R.id.name);
        emailID = findViewById(R.id.email_id);
        phoneNumber = findViewById(R.id.mobile_number);
        registration = findViewById(R.id.registration_number);
        progressSection = findViewById(R.id.progress_section);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        sp = getSharedPreferences("key",0);

        USERNAME = sp.getString("username","");
        EMAIL = sp.getString("email","");
        MOBNUMBER = sp.getString("phoneNumber","");
        REGNUMBER = sp.getString("regNumber","");
        JWTTOKEN = sp.getString("jwtToken","");
        profileUpdateAvail = sp.getBoolean("profileUpdateAvail",false);
        regEvents = new ArrayList(sp.getStringSet("events",new HashSet<String>()));

        //Barcode dialog
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_barcode);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        barcodeImage = myDialog.findViewById(R.id.barcode_image);
        barcodeText = myDialog.findViewById(R.id.barcode_regno);

        for(int i =0 ;i<regEvents.size() ; i++)
        {
            Log.e("events",regEvents.get(i));
        }

        name.setText(USERNAME);
        emailID.setText(EMAIL);
        phoneNumber.setText(MOBNUMBER);
        registration.setText(REGNUMBER);

        String previouslyEncodedImage = sp.getString("image_data", "");

        if( !previouslyEncodedImage.equalsIgnoreCase("") ){
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            profileImage.setImageBitmap(bitmap);
        }
        else
        {
            profileImage.setImageDrawable(getDrawable(R.drawable.default_profile_img));
        }
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //To display the barcode for scanning
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(REGNUMBER, BarcodeFormat.CODE_128,600,300);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    barcodeImage.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                barcodeText.setText(REGNUMBER);
                myDialog.show();

            }
        });


        //Registered Events retrieval
        getEvents();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Logout();

            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePic(profileImage);
            }
        });



        scrollView = findViewById(R.id.profileScroll);

        //Set events scroll view
        setEvents();


    }

    private void onItemChanged(ProfileScrollClass profileScrollClass) {
    }

    private void Logout()
    {
        SharedPreferences.Editor editor= sp.edit();
        editor.remove("username").commit();
        editor.remove("email").commit();
        editor.remove("password").commit();
        editor.remove("loginStatus").commit();
        editor.remove("image_data").commit();
        editor.remove("jwtToken").commit();
        editor.remove("phoneNumber").commit();
        editor.remove("updateAvail").commit();
        editor.remove("loginStatus").commit();
        editor.remove("teamName").commit();
        editor.remove("profileUpdateAvail").commit();
        editor.remove("events").commit();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    //function to change profile picture
    public void changePic(View V) {
        final String[] items = {"Take picture", "Choose Picture",
                "Cancel"};

        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("Add Photo");
        build.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which].equals("Choose Picture")) {
                    Log.d("test","bh");

                    Intent i = new Intent(Intent.ACTION_PICK);
                    i.setType("image/*");
                    startActivityForResult(i, SELECT_PICTURE);

                } else if (items[which].equals("Take picture")) {

                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(i, CAPTURE_IMAGE);
                }
            }
        }).create().show();

    }
    //Handling the Request cases from changePic
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            storeImage(thumbnail);
        } else if (requestCode == SELECT_PICTURE) {
            Uri mImageUri = data.getData();
            Bitmap thumbnail = null;
            try {
                thumbnail = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            storeImage(thumbnail);
        }
    }

    //Storing profile image using shared preferences
    private void storeImage(Bitmap thumbnail) {
        // Removing image saved earlier in shared preferences
        SharedPreferences.Editor edit= sp.edit();
        edit.remove("image_data");
        edit.apply();

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        profileImage.setImageBitmap(thumbnail);
        byte[] b = bytes.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        //saving image to shared preferences
        edit.putString("image_data",encodedImage);
        edit.commit();
    }


    private void setEvents()
    {
        adapter = new ProfileScrollAdapter(regEvents);

        scrollView.setOffscreenItems(4);
        scrollView.setOverScrollEnabled(true);
        scrollView.setAdapter(adapter);
        scrollView.setSlideOnFling(true);
        scrollView.setItemTransitionTimeMillis(150);
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
        //onItemChanged(items.get(0));
    }

    private void getEvents()
    {
        regEvents = new ArrayList<String>();
        if(!profileUpdateAvail)
        {
            progressSection.setVisibility(View.VISIBLE);
            Call<ArrayList<HashMap<String,String>>> call = apiInterface.getEvents(JWTTOKEN);

            call.enqueue(new Callback<ArrayList<HashMap<String, String>>>() {
                @Override
                public void onResponse(Call<ArrayList<HashMap<String, String>>> call, Response<ArrayList<HashMap<String, String>>> response) {
                    ArrayList<HashMap<String, String>> eventList = response.body();
                    try {
                        for (int i = 0; i < eventList.size(); i++) {
                            String s = eventList.get(i).get("eventName");
                            String tempList[] = s.split("\\+");
                            for (int j = 0; j < tempList.length; j++) {
                                regEvents.add(tempList[j]);
                            }
                        }
                        progressSection.setVisibility(View.GONE);
                        SharedPreferences.Editor editor = sp.edit();
                        Set<String> eventSet = new HashSet<String>(regEvents);
                        editor.putStringSet("events", eventSet);
                        editor.putBoolean("profileUpdateAvail", true);
                        editor.commit();
                        setEvents();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),eventList.get(0).get("error"),Toast.LENGTH_LONG).show();
                        Logout();
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<HashMap<String, String>>> call, Throwable t) {

                }
            });
        }
        else
        {

            Call<ArrayList<HashMap<String,String>>> call = apiInterface.getEvents(JWTTOKEN);
            call.enqueue(new Callback<ArrayList<HashMap<String, String>>>() {
                @Override
                public void onResponse(Call<ArrayList<HashMap<String, String>>> call, Response<ArrayList<HashMap<String, String>>> response) {
                    ArrayList<HashMap<String, String>> eventList = response.body();
                    try {
                        for (int i = 0; i < eventList.size(); i++) {
                            String s = eventList.get(i).get("eventName");
                            String tempList[] = s.split("\\+");
                            for (int j = 0; j < tempList.length; j++) {
                                regEvents.add(tempList[j]);
                            }
                        }
                        SharedPreferences.Editor editor = sp.edit();
                        Set<String> eventSet = new HashSet<String>(regEvents);
                        editor.putStringSet("events", eventSet);
                        editor.putBoolean("profileUpdateAvail", true);
                        editor.commit();
                        setEvents();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),eventList.get(0).get("error"),Toast.LENGTH_LONG).show();
                        Logout();
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<HashMap<String, String>>> call, Throwable t) {

                }
            });

        }
    }

}
