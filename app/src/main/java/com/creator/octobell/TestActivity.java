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
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class TestActivity extends AppCompatActivity {
    private Drawer result = null;
    private AccountHeader headerResult = null;

    private static final int PROFILE_SETTING = 100000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Octobell");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

  /*   */

        final IProfile profile = new ProfileDrawerItem().withName("DevOpp").withEmail("DevOpp@rocketmail.com").withIcon("").withIdentifier(100);


        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile,
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_plus).actionBar().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(PROFILE_SETTING)
                        )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {


                        //// this is a common header.

                        if (profile instanceof IDrawerItem && profile.getIdentifier() == PROFILE_SETTING) {
                            int count = 100 + headerResult.getProfiles().size() + 1;
                            IProfile newProfile = new ProfileDrawerItem().withNameShown(true).withName("Batman" + count).withEmail("batman" + count + "@gmail.com").withIcon(R.drawable.profile5).withIdentifier(count);
                            if (headerResult.getProfiles() != null) {
                                //we know that there are 2 setting elements. set the new profile above them ;)
                                headerResult.addProfile(newProfile, headerResult.getProfiles().size() - 2);
                            } else {
                                headerResult.addProfiles(newProfile);
                            }
                        }

                        //false if you have not consumed the event and it should close the drawer
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new DrawerBuilder(this)
                //this layout have to contain child layouts

                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withHasStableIds(true)

                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Mobile & Tabs").withIcon(FontAwesome.Icon.faw_mobile),
                        new PrimaryDrawerItem().withName("Electronic").withIcon(FontAwesome.Icon.faw_television),
                        new PrimaryDrawerItem().withName("Home & Living").withIcon(R.drawable.funiture),
                        new PrimaryDrawerItem().withName("Daily Needs").withIcon(FontAwesome.Icon.faw_shopping_basket),

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
