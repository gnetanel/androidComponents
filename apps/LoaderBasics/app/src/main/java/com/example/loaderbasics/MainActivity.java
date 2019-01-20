package com.example.loaderbasics;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MY_LOADER_ID = 1;
    TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        LoaderManager loaderManager = getSupportLoaderManager();

        Loader<String> loader = loaderManager.getLoader(MY_LOADER_ID);
        // If the Loader was null, initialize it. Else, restart it.
        if(loader==null){
            Log.i(TAG, "calling initLoader");
            loaderManager.initLoader(MY_LOADER_ID, null, this);
        }else{
            Log.i(TAG, "calling restartLoader");
            loaderManager.restartLoader(MY_LOADER_ID, null, this);
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        Log.i(TAG, "onCreateLoader, creating new loader...");
        return new AsyncTaskLoader<String>(this) {
            @Nullable
            @Override
            public String loadInBackground() {
                Log.i(TAG, "loadInBackground");
                String xxx = "xxxaaa";
                return xxx;
            }

            @Override
            protected void onStartLoading() {
                Log.i(TAG, "onStartLoading");
                forceLoad();
            }

        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        Log.i(TAG, "onLoadFinished, returning string s=..." + s);
        textView.setText(s);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.i(TAG, "onLoaderReset");
        textView.setText("xxx");
    }
}