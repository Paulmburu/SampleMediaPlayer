package com.example.android.samplemediaplayer;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Song> mySongs=new ArrayList<>();
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void getMusic(View view){
        ContentResolver contentResolver=getContentResolver();
        Uri songUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor=contentResolver.query(songUri,null,null,null,null);

        if(songCursor!=null && songCursor.moveToFirst()){
            int songId=songCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int songTitle=songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int albumNameId=songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            int albumID=songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
//            int imageID=songCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);



            do{
                long currentId = songCursor.getLong(songId);
                String currentTitle = songCursor.getString(songTitle);
                String currentAlbum = songCursor.getString(albumNameId);
                long currentAlbumId = songCursor.getLong(albumID);

                textView=(TextView)findViewById(R.id.song_title);
                textView.append("TITLE: "+currentTitle+"\nALBUM: "+currentAlbum+"\nALBUM ID: "+currentAlbumId+"\n\n");
            } while(songCursor.moveToNext());


        }
    }
}
