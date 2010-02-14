/*
 *   Copyright 2009, Maarten Billemont
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.lyndir.lhunath.apps.listener;

import java.util.Collections;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lyndir.lhunath.apps.model.App;
import com.lyndir.lhunath.apps.model.AppVersion;
import com.lyndir.lhunath.apps.model.GitHubSourceProvider;


/**
 * <h2>{@link GorillasListener}<br>
 * <sub>[in short] (TODO).</sub></h2>
 * 
 * <p>
 * [description / usage].
 * </p>
 * 
 * <p>
 * <i>Jun 12, 2009</i>
 * </p>
 * 
 * @author lhunath
 */
public class GorillasListener implements ServletContextListener {

    private static final String COCOS2D_IPHONE = "Cocos2D-iPhone";


    /**
     * {@inheritDoc}
     */
    public void contextInitialized(ServletContextEvent sce) {

        App gorillas = new App( "lhunath", "Gorillas", "gorillas", new GitHubSourceProvider(), "UA-90535-5" );
        AppVersion.register( new AppVersion( gorillas, "100", "1.0", null, null,
                Collections.singletonList( COCOS2D_IPHONE ), new Date( 1231931604 * 1000L ), "Hu4Y8eJLqkI",
                "Eye candy worthy of your iPhone / iPod touch", //
                "Wind and weather effects, such as rain and snow", //
                "A range of excellent retro remixes", //
                "Challenge the AI or a friend", //
                "Dynamically adjusted or configurable difficulty", // 
                "Pick from several different cities / worlds", //
                "Fully Open Source" //
        ) );

        AppVersion.register( new AppVersion( gorillas, "110", "1.1", null, null,
                Collections.singletonList( COCOS2D_IPHONE ), new Date( 1236986395 * 1000L ), "kOd6fI2Cm7c",
                "Sound effects, vibration and shaking", //
                "Game modes: Dynamic, Team Game & Last Man Standing", //
                "Camera action and game field zooming", //
                "Many gorillas together in the game", //
                "Throwing, cheering and dancing animations", //
                "Kill shot replays" //
        ) );

        AppVersion.register( new AppVersion( gorillas, "122", "1.2", null, null,
                Collections.singletonList( COCOS2D_IPHONE ), new Date( 1240302082 * 1000L ), "kOd6fI2Cm7c",
                "Holiday themed player models" //
        ) );

        AppVersion.register( new AppVersion( gorillas, "131", "1.3", null, null,
                Collections.singletonList( COCOS2D_IPHONE ), new Date( 1247060791 * 1000L ), "Q-s8bimr1GY",
                "Show metrics while aiming", //
                "Added another player model", //
                "Voice effects and new (higher quality) audio tracks", //
                "Localized (currently English, Dutch and Arabic)", //
                "Auto-zoom when aiming at the very top of the screen" //
        ) );
    }

    /**
     * {@inheritDoc}
     */
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
