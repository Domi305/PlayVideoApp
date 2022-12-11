package com.dominiks.playvideos;

import android.os.Bundle;

import com.brightcove.player.analytics.Analytics;
import com.brightcove.player.edge.Catalog;
import com.brightcove.player.edge.VideoListener;
import com.brightcove.player.event.EventEmitter;
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

        EventEmitter eventEmitter = brightcoveVideoView.getEventEmitter();
        String account = getString(R.string.account);
        Catalog catalog = new Catalog.Builder(eventEmitter, account)
                .setBaseURL(Catalog.DEFAULT_EDGE_BASE_URL)
                .setPolicy(getString(R.string.policy))
                .build();

        catalog.findVideoByID(getString(R.string.videoId), new VideoListener() {
            @Override
            public void onVideo(Video video) {
                brightcoveVideoView.add(video);
                brightcoveVideoView.start();
            }
        });


    }
}