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
package com.lyndir.lhunath.portal.apps.page;

import com.google.common.collect.ImmutableMap;
import com.lyndir.lhunath.portal.apps.model.AppVersion;
import org.apache.wicket.behavior.StringHeaderContributor;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.template.JavaScriptTemplate;
import org.apache.wicket.util.template.PackagedTextTemplate;


/**
 * <h2>{@link DemoPage}<br> <sub>The interface panel that shows a video demonstration of the game.</sub></h2>
 *
 * <p> <i>May 31, 2009</i> </p>
 *
 * @author lhunath
 */
public class DemoPage extends AppsPage {

    public DemoPage() {

        add(
                new StringHeaderContributor(
                        new LoadableDetachableModel<String>() {
                            @Override
                            protected String load() {

                                if (AppVersion.getLatest().hasVideo())
                                    return new JavaScriptTemplate(
                                            new PackagedTextTemplate(
                                                    getPageClass(), "showMovie.js" ) ).asString(
                                            ImmutableMap.<String, Object>builder() //
                                                    .put( "movieLink", AppVersion.getLatest().getFLVLink() )
                                                    .put( "pageTrackCode", AppVersion.getLatest().getFLVPageTrackCode() )
                                                    .build() );

                                return "";
                            }
                        } ) );

        add(
                new Label(
                        "tagline", new AbstractReadOnlyModel<String>() {

                    @Override
                    public String getObject() {

                        return AppVersion.getLatest().getTagLine();
                    }
                } ) {

                    @Override
                    public boolean isVisible() {

                        return AppVersion.getLatest().getTagLine() != null;
                    }
                } );

        add(
                new Label(
                        "description", new AbstractReadOnlyModel<String>() {

                    @Override
                    public String getObject() {

                        return AppVersion.getLatest().getDescription();
                    }
                } ) {

                    @Override
                    public boolean isVisible() {

                        return AppVersion.getLatest().getDescription() != null;
                    }
                }.setEscapeModelStrings( false ) );

        add(
                new WebMarkupContainer( "youtube" ) {

                    @Override
                    protected void onComponentTag(final ComponentTag tag) {

                        tag.put(
                                "href", //
                                AppVersion.getLatest().getYouTubeLink() );
                        tag.put(
                                "onclick", //
                                AppVersion.getLatest().getYouTubePageTrackCode() );

                        super.onComponentTag( tag );
                    }

                    @Override
                    public boolean isVisible() {

                        return AppVersion.getLatest().hasVideo();
                    }
                } );
        add(
                new WebMarkupContainer( "mpeg" ) {

                    @Override
                    protected void onComponentTag(final ComponentTag tag) {

                        tag.put(
                                "href", //
                                AppVersion.getLatest().getMP4Link() );
                        tag.put(
                                "onclick", //
                                AppVersion.getLatest().getMP4PageTrackCode() );

                        super.onComponentTag( tag );
                    }

                    @Override
                    public boolean isVisible() {

                        return AppVersion.getLatest().hasVideo();
                    }
                } );

        add(
                new WebMarkupContainer( "iphone-youtube" ) {

                    @Override
                    protected void onComponentTag(final ComponentTag tag) {

                        tag.put(
                                "href", //
                                AppVersion.getLatest().getYouTubeLink() );
                        tag.put(
                                "onclick", //
                                AppVersion.getLatest().getYouTubePageTrackCode() );

                        super.onComponentTag( tag );
                    }

                    @Override
                    public boolean isVisible() {

                        return AppVersion.getLatest().hasVideo();
                    }
                } );
    }
}
