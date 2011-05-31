package com.lyndir.lhunath.portal.webapp.page;

import static com.lyndir.lhunath.opal.system.util.ObjectUtils.ifNotNullElse;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.lyndir.lhunath.opal.wayward.behavior.CSSClassAttributeAppender;
import com.lyndir.lhunath.opal.wayward.behavior.CSSStyleAttributeAppender;
import com.lyndir.lhunath.portal.webapp.model.StripItem;
import com.lyndir.lhunath.portal.webapp.model.Tab;
import java.util.HashMap;
import java.util.Map;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.behavior.StringHeaderContributor;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.*;
import org.apache.wicket.util.template.JavaScriptTemplate;
import org.apache.wicket.util.template.PackagedTextTemplate;
import org.jetbrains.annotations.Nullable;


/**
 * <h2>{@link LayoutPage}<br> <sub>[in short] (TODO).</sub></h2>
 *
 * <p> <i>05 02, 2010</i> </p>
 *
 * @author lhunath
 */
public abstract class LayoutPage extends WebPage {

    StripItem activeItem;

    protected LayoutPage() {

        add( new Label( "pageTitle", getPageTitle() ) );
        add(
                new ListView<StripItem>(
                        "strip", ImmutableList.of(
                        activeItem = new StripItem(
                                Model.of( "Lyndir" ), "images/icon.lyndir.png", "images/logo.lyndir.png", "http://www.lyndir.com",
                                Model.of( "Modern Design and Development" ), null ), //
                        new StripItem(
                                Model.of( "Gorillas" ), "images/icon.gorillas.png", "images/logo.gorillas.png",
                                "http://gorillas.lyndir.com",
                                Model.of( "Gorillas is a resurrection of the classic QBasic game shipped with MS-DOS 5." ), null ), //
                        new StripItem(
                                Model.of( "DeBlock" ), "images/icon.deblock.png", "images/logo.deblock.png", "http://deblock.lyndir.com",
                                Model.of(
                                        "DeBlock is a block destruction game in the style of Bejuweled or Destruct-o-Block, but unique altogether." ),
                                null ), //
                        new StripItem(
                                Model.of( "Opal" ), "images/icon.opal.png", "images/logo.lyndir.png", "http://opal.lyndir.com",
                                Model.of( "Collection of Java convenience libraries." ), null ), //
                        new StripItem(
                                Model.of( "iLibs" ), "images/icon.ilibs.png", "images/logo.lyndir.png", "http://ilibs.lyndir.com",
                                Model.of( "Collection of iOS convenience libraries." ), null ) ) ) {

                    @Override
                    protected void populateItem(final ListItem<StripItem> item) {

                        item.add(
                                new CSSStyleAttributeAppender(
                                        "background-image", new LoadableDetachableModel<String>() {
                                    @Override
                                    protected String load() {

                                        return String.format( "url('%s')", item.getModelObject().getIcon() );
                                    }
                                } ) );
                        item.add( new Label( "caption", item.getModelObject().getName() ) );
                        item.add( new Label( "description", item.getModelObject().getDescription() ) );
                        item.add( new ExternalLink( "link", item.getModelObject().getLink() ) );
                        item.add(
                                CSSClassAttributeAppender.of(
                                        new LoadableDetachableModel<String>() {
                                            @Nullable
                                            @Override
                                            protected String load() {

                                                return item.getModelObject().equals( activeItem )? "active": null;
                                            }
                                        } ) );
                    }
                } );
        add(
                new WebMarkupContainer( "logo" ).add(
                        new CSSStyleAttributeAppender(
                                "background-image", new LoadableDetachableModel<String>() {
                            @Override
                            protected String load() {

                                return String.format( "url('%s')", activeItem.getLogo() );
                            }
                        } ) ) );
        add(
                new ListView<Tab>(
                        "menu", ImmutableList.of(
                        new Tab( AboutPage.class, "♔", Model.of( "About" ) ), //
                        new Tab( ContactPage.class, "♗", Model.of( "Contact" ) ), //
                        new Tab( CreationsPage.class, "♖", Model.of( "Creations" ) ) //
                ) ) {

                    @Override
                    protected void populateItem(final ListItem<Tab> item) {

                        item.add(
                                new Link<Void>( "link" ) {
                                    @Override
                                    protected void onInitialize() {

                                        super.onInitialize();

                                        add( new Label( "icon", item.getModelObject().getIcon() ) );
                                        add( new Label( "name", item.getModelObject().getName() ) );
                                    }

                                    @Override
                                    public void onClick() {

                                        throw new RestartResponseException( item.getModelObject().getPage() );
                                    }
                                } );
                        item.add(
                                new CSSClassAttributeAppender(
                                        item.getModelObject().getPage().isAssignableFrom( getPageClass() )? "active": "" ) );
                    }
                } );
        //add( new Label( "pageHeading", getPageHeading() ) );
        //add( new ContextImage( "pageImage", getPageImage() ) );
        add(
                new Label(
                        "foot", new LoadableDetachableModel<String>() {
                    @Override
                    protected String load() {

                        return ifNotNullElse(
                                activeItem.getFooter(), new Function<Model<String>, String>() {
                                    @Override
                                    public String apply(final Model<String> from) {

                                        return from.getObject();
                                    }
                                }, "Copyright 2011, lhunath" );
                    }
                } ) );

        Map<String, Object> trackVariables = new HashMap<String, Object>();
        trackVariables.put( "googleAnalyticsID", "UA-90535-8" );
        trackVariables.put( "pageView", getPageClass().getSimpleName() );
        add(
                new StringHeaderContributor(
                        new JavaScriptTemplate( new PackagedTextTemplate( LayoutPage.class, "trackPage.js" ) ).asString(
                                trackVariables ) ) );
    }

    protected abstract IModel<String> getPageTitle();

    protected IModel<String> getPageHeading() {

        return getPageTitle();
    }

    protected abstract IModel<String> getPageImage();
}