package com.dominiks.playvideos;

import android.os.Bundle;

import com.brightcove.player.analytics.Analytics;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.Video;
import com.brightcove.player.view.BrightcoveExoPlayerVideoView;
import com.brightcove.player.view.BrightcovePlayer;

import java.net.URISyntaxException;

public class MainActivity extends BrightcovePlayer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        brightcoveVideoView = (BrightcoveExoPlayerVideoView) findViewById(R.id.brightcove_video_view);
        super.onCreate(savedInstanceState);

        Analytics analytics = brightcoveVideoView.getAnalytics();
        analytics.setAccount("6136710637001");

        Video video = Video.createVideo("https://sdks.support.brightcove.com/assets/videos/hls/greatblueheron/greatblueheron.m3u8", DeliveryType.HLS);
        try {
            java.net.URI myposterImage = new java.net.URI("https://sdks.support.brightcove.com/assets/images/general/Great-Blue-Heron.png");
            video.getProperties().put(Video.Fields.STILL_IMAGE_URI, myposterImage);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        // Add video
        brightcoveVideoView.add(video);
        // Start playback
        brightcoveVideoView.start();
    }
}