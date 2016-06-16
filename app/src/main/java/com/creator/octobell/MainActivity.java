package com.creator.octobell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {


    //save our header or result
    private Drawer resultDrawer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*toolbar added */
getSupportActionBar().hide();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    //    setSupportActionBar(toolbar);
     //   getSupportActionBar().setTitle("Octobell");
        resultDrawer=   new DrawerBuilder().withActivity(this).build();

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withDisplayBelowStatusBar(false)
                .withToolbar(toolbar)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new SecondaryDrawerItem().withName("Contact").withIcon(FontAwesome.Icon.faw_bullhorn).withIdentifier(2)
                )
                .withSavedInstance(savedInstanceState)
                .build();
        result.getDrawerLayout().setFitsSystemWindows(false);
        result.getSlider().setFitsSystemWindows(false);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = resultDrawer.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (resultDrawer != null && resultDrawer.isDrawerOpen()) {
            resultDrawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
