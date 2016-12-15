package com.example.houcem.natco;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.houcem.natco.fragments.AcceuilFragment;
import com.example.houcem.natco.fragments.AgendaFragment;
import com.example.houcem.natco.fragments.ChairFragment;
import com.example.houcem.natco.fragments.FaciTeamFragment;
import com.example.houcem.natco.fragments.HotelFragment;
import com.example.houcem.natco.fragments.OCTeamFragment;
import com.example.houcem.natco.fragments.PartnersFragment;
import com.example.houcem.natco.fragments.QuestionsFragment;
import com.example.houcem.natco.services.DownloadImageTask;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainPage extends AppCompatActivity {
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CallbackManager callbackManager;
    Uri photoURI;



    static final int REQUEST_TAKE_PHOTO = 1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 1: {

                if (resultCode == RESULT_OK) {
                    ShareDialog shareDialog = new ShareDialog(this);



                            SharePhoto photo = new SharePhoto.Builder()
                                    .setImageUrl(photoURI)
                                    .build();

                            SharePhotoContent content = new SharePhotoContent.Builder()
                                    .addPhoto(photo)
                                    .setShareHashtag(new ShareHashtag.Builder()
                                            .setHashtag("#NatCo_2k16")
                                            .build())
                                    .build();
                            shareDialog.show(content);
                            Log.e("fuck", "what the fuck i am supposed to be here");
                            callbackManager.onActivityResult(requestCode, resultCode, data);
                            break;


                }


            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir =getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        CoordinatorLayout mainlayout = (CoordinatorLayout)findViewById(R.id.main_layout_bg);

        mPlanetTitles = getResources().getStringArray(R.array.menu_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        callbackManager = CallbackManager.Factory.create();

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {

                        photoURI = FileProvider.getUriForFile(getApplicationContext(),
                                "com.example.android.fileprovider",
                                photoFile);
                        Intent intent = takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                        List<ResolveInfo> resInfoList = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                        for (ResolveInfo resolveInfo : resInfoList) {
                            String packageName = resolveInfo.activityInfo.packageName;
                            grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        }
                    }
                }



            }
        });

        //initialize sdk
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        //set initial fragment

        if (savedInstanceState == null) {

            Fragment newFragment = new AcceuilFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.main_fragment,newFragment).commit();
        }

        //-------------------------------------------

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MainPage Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {


        mDrawerList.setItemChecked(position, false);
        mDrawerLayout.closeDrawer(mDrawerList);

        Fragment newFragment;
        switch (position){
            case 0 :{
                newFragment = new AcceuilFragment();
                break;
            }
            case 1 :{
                newFragment = new AgendaFragment();
                break;
            }
            case 2:{
                newFragment = new HotelFragment();
                break;
            }
            case 3:{
                newFragment = new PartnersFragment();
                break;
            }
            case 4:{
                newFragment = new OCTeamFragment();
                break;
            }
            case 5:{
                newFragment = new FaciTeamFragment();
                break;
            }
            case 6:{
                newFragment = new ChairFragment();
                break;
            }
//            case 7:{
//                newFragment = new QuestionsFragment();
//                break;
//            }
            default:{
                newFragment = new AcceuilFragment();
                break;
            }
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment,newFragment)
                .commit();

    }




}
