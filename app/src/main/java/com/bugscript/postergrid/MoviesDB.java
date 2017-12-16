package com.bugscript.postergrid;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.bugscript.postergrid.data.MovieContract;
import com.bugscript.postergrid.data.MovieContract.MovieEntry;

public class MoviesDB {
    static final String AUTHORITY_Uri = "content://" + MovieContract.AUTHORITY;

    public boolean isMovieFavorited(ContentResolver contentResolver, int id){
        boolean ret = false;
        Cursor cursor = contentResolver.query(Uri.parse(AUTHORITY_Uri + "/" + id), null, null, null, null, null);
        if (cursor != null && cursor.moveToNext()){
            ret = true;
            cursor.close();
        }
        return ret;
    }

    public void addMovie(ContentResolver contentResolver, int i){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieEntry.COLUMN_ID, MainActivity.id[i]);
        contentValues.put(MovieEntry.COLUMN_NAME, MainActivity.movies[i]);
        contentValues.put(MovieEntry.COLUMN_OVERVIEW, MainActivity.summary[i]);
        contentValues.put(MovieEntry.COLUMN_POSTER, MainActivity.poster[i]);
        contentValues.put(MovieEntry.COLUMN_BACKDROP, MainActivity.backdrop[i]);
        contentValues.put(MovieEntry.COLUMN_RATING, MainActivity.votes[i]);
        contentValues.put(MovieEntry.COLUMN_RELEASE, MainActivity.dates[i]);
        contentResolver.insert(Uri.parse(AUTHORITY_Uri + "/movies"), contentValues);
    }

    public void removeMovie(ContentResolver contentResolver, int id){
        Uri uri = Uri.parse(AUTHORITY_Uri + "/" + id);
        contentResolver.delete(uri, null, new String[]{id + ""});
    }

}