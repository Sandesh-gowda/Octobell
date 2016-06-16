package com.creator.octobell;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;

public class TestActivity extends AppCompatActivity {
    private Drawer result = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Octobell");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);



        //Create the drawer
        result = new DrawerBuilder(this)
                //this layout have to contain child layouts

                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Mobile & Tabs").withIcon(FontAwesome.Icon.faw_mobile),
                        new PrimaryDrawerItem().withName("Electronic").withIcon(FontAwesome.Icon.faw_television),
                        new PrimaryDrawerItem().withName("Home & Living").withIcon(FontAwesome.Icon.faw_shopping_basket),
                        new SectionDrawerItem().withName("Quick links"),
                        new SecondaryDrawerItem().withName("Collections").withIcon(FontAwesome.Icon.faw_list),
                        new SecondaryDrawerItem().withName("Track Your Order").withIcon(FontAwesome.Icon.faw_map_marker).withEnabled(false),
                        new SecondaryDrawerItem().withName("Refer & Earn").withIcon(FontAwesome.Icon.faw_share).withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700)).withBadge("new"),
                                new SecondaryDrawerItem().withName("Help Center").withIcon(FontAwesome.Icon.faw_question_circle),
                                new SecondaryDrawerItem().withName("Rate Octobell App").withIcon(FontAwesome.Icon.faw_star),
                                new ExpandableDrawerItem().withName("More").withIcon(GoogleMaterial.Icon.gmd_more_horiz).withIdentifier(19).withSelectable(false).withSubItems(
                                        new SecondaryDrawerItem().withName("Trust Pay").withLevel(2).withIcon(FontAwesome.Icon.faw_check_circle).withIdentifier(2000),
                                        new SecondaryDrawerItem().withName("Terms & Conditions ").withLevel(2).withIcon(FontAwesome.Icon.faw_file_text_o).withIdentifier(2001),
                                        new SecondaryDrawerItem().withName("Follow us on Facebook").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_facebook).withIdentifier(2001),
                                        new SecondaryDrawerItem().withName("Follow us on Twitter ").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_twitter).withIdentifier(2001)
                                )
                        )
                                .withSavedInstance(savedInstanceState)
                                .build();


       /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
