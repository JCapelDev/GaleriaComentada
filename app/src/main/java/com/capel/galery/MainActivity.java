package com.capel.galery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter customAdapter;
    RecyclerView.LayoutManager layoutManager;
    Context context;

    private String[] programComments = {
            /*"Comentario 1",
            "Comentario 2",
            "Comentario 3",
            "Comentario 4",
            "Comentario 5",
            "Comentario 6",
            "Comentario 7",
            "Comentario 8",
            "Comentario 9"*/
    };

    private int[] programImages = {
            R.drawable._1,
            R.drawable._2,
            R.drawable._3,
            R.drawable._4,
            R.drawable._5,
            R.drawable._6,
            R.drawable._7,
            R.drawable._8,
            R.drawable._9
    };

    private ArrayList<String> alprogramComments;
    private ArrayList<String> alprogramImages;

    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        obtainData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.id_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        customAdapter = new CustomAdapter(this,alprogramComments,alprogramImages);
        recyclerView.setAdapter(customAdapter);
    }

    private void obtainData(){

        /*try {
            FileOutputStream fOut = openFileOutput("data.xml", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        //File file = new File(context.getFilesDir(), "data.xml");
        alprogramImages = XML.getTextByTag(XML.getDocument("/data/data/com.capel.galery/files/data.xml"),"id");
        alprogramComments = XML.getTextByTag(XML.getDocument("/data/data/com.capel.galery/files/data.xml"),"comment");
    }
}