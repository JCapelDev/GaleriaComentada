package com.capel.galery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter customAdapter;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    private ArrayList<String> alprogramComments;
    private ArrayList<String> alprogramImages;

    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            obtainData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*recyclerView = findViewById(R.id.id_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        customAdapter = new CustomAdapter(this,alprogramComments,alprogramImages);
        recyclerView.setAdapter(customAdapter);*/

    }

    private void obtainData() throws IOException {
        file = new File(getFilesDir() + "/data.xml");


        if(!file.exists()){
            createBaseXML(file.getPath());

        }


        alprogramImages = XML.getTextByTag(XML.getDocument(getFilesDir() + "/data.xml"),"id");
        alprogramComments = XML.getTextByTag(XML.getDocument(getFilesDir() + "/data.xml"),"comment");
    }

    public void editComment(View v){

    }

    public void takePhoto(View v){
        dispatchTakePictureIntent();
        /*Toast.makeText(this, "Haz la foto hostia", Toast.LENGTH_SHORT).show();*/
    }

    public static void createBaseXML(String url) {
        File f = new File(url);
        Log.v("Debug",f.getAbsolutePath());
        XML.createDocument("data", f.getAbsolutePath());

        Document doc = XML.getDocument(f.getAbsolutePath());

        XML.addNode(doc, XML.getRoot(doc), "images");
        XML.addNode(doc, XML.getNodeByTag(doc, "images"), "image");

        XML.addNode(doc, XML.getNodeList(doc, ".//image").item(0), "id");
        XML.addNode(doc, XML.getNodeList(doc, ".//image").item(0), "comment");

        XML.createDocument(doc, f.getAbsolutePath());
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            /*Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);*/
        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }



}