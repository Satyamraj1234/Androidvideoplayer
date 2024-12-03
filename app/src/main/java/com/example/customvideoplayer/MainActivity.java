package com.example.customvideoplayer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button playPauseButton, nextButton, prevButton;
    private String[] videoPaths;
    private int currentVideoIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        playPauseButton = findViewById(R.id.playPauseButton);
        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);

        // Defined video paths
        videoPaths = new String[]{
                "android.resource://" + getPackageName() + "/" + R.raw.sample,

        };

        // Set initial video
        setVideo(videoPaths[currentVideoIndex]);

        // Play/Pause button functionality
        playPauseButton.setOnClickListener(v -> {
            if (videoView.isPlaying()) {
                videoView.pause();
                playPauseButton.setText("Play");
            } else {
                videoView.start();
                playPauseButton.setText("Pause");
            }
        });

        // Next button functionality
        nextButton.setOnClickListener(v -> {
            if (currentVideoIndex < videoPaths.length - 1) {
                currentVideoIndex++;
            } else {
                currentVideoIndex = 0; // Loop back to the first video
            }
            setVideo(videoPaths[currentVideoIndex]);
        });

        // Previous button functionality
        prevButton.setOnClickListener(v -> {
            if (currentVideoIndex > 0) {
                currentVideoIndex--;
            } else {
                currentVideoIndex = videoPaths.length - 1; // Loop back to the last video
            }
            setVideo(videoPaths[currentVideoIndex]);
        });
    }

    // helper fun
    private void setVideo(String videoPath) {
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);
        videoView.start();
        playPauseButton.setText("Pause");
    }
}
