package com.collegiate.collegiateapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.instructure.canvasapi.model.CanvasError;
import com.instructure.canvasapi.utilities.APIHelpers;
import com.instructure.canvasapi.utilities.APIStatusDelegate;
import com.instructure.canvasapi.utilities.CanvasRestAdapter;
import com.instructure.canvasapi.utilities.ErrorDelegate;

import retrofit.RetrofitError;

public class MainActivity extends AppCompatActivity implements APIStatusDelegate, ErrorDelegate {

    public final static String DOMAIN = "corpuschristiisd.instructure.com";
    public final static String TOKEN = "1769~4iSFVwWe5LlRfQke93ZPHIIpFzgGiQFenR35acGXu6XWV3VgLhhAqFztoRZU79CX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpCanvasAPI();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void setUpCanvasAPI() {
        CanvasRestAdapter.setupInstance(this, TOKEN, DOMAIN);
        APIHelpers.setDefaultErrorDelegateClass(this, this.getClass().getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    @Override
    public void onCallbackFinished() {

    }

    @Override
    public void onNoNetwork() {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void noNetworkError(RetrofitError retrofitError, Context context) {
        Log.d(APIHelpers.LOG_TAG, "There was no network");
    }

    @Override
    public void notAuthorizedError(RetrofitError retrofitError, CanvasError canvasError, Context context) {
        Log.d(APIHelpers.LOG_TAG, "HTTP 401 (Not Authorized)");
    }

    @Override
    public void invalidUrlError(RetrofitError retrofitError, Context context) {
        Log.d(APIHelpers.LOG_TAG, "HTTP 404 (Invalid URL)");
    }

    @Override
    public void serverError(RetrofitError retrofitError, Context context) {
        Log.d(APIHelpers.LOG_TAG, "HTTP 500 (Server Error)");
    }

    @Override
    public void generalError(RetrofitError retrofitError, CanvasError canvasError, Context context) {
        Log.d(APIHelpers.LOG_TAG, "HTTP 200 but something went wrong. Probably a GSON parse error.");
    }

}