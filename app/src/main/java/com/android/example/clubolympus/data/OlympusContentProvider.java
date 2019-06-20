package com.android.example.clubolympus.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

import com.android.example.clubolympus.data.ClubOlympusContract.MemberEntry;

public class OlympusContentProvider extends ContentProvider {

    private static final int MEMBERS = 111;
    private static final int MEMBER_ID = 222;
    OlympusDbOpenHelper dbOpenHelper;

    //Creates a UriMatcher object.
    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        uriMatcher.addURI(ClubOlympusContract.AUTHORITY, ClubOlympusContract.PATH_MEMBERS , MEMBERS);
        uriMatcher.addURI(ClubOlympusContract.AUTHORITY, ClubOlympusContract.PATH_MEMBERS + "/#" , MEMBER_ID);

    }


    @Override
    public boolean onCreate() {
        dbOpenHelper = new OlympusDbOpenHelper(getContext());
        return false;
    }

    // content://com.android.example.clubolympus/members/34
    // projection = { "lastName", "gender" }
    @Override
    public Cursor query( Uri uri,  String[] projection,  String selection,  String[] selectionArgs,  String sortOrder) {
        SQLiteDatabase db= dbOpenHelper.getReadableDatabase();
        Cursor cursor;
        int match = uriMatcher.match(uri);

        switch (match){
            case MEMBERS:
                cursor = db.query(MemberEntry.TABLE_NAME, projection, selection , selectionArgs , null, null, sortOrder);
                break;

                //selection = "_id=?"
                //selectionArgs = 34
            case MEMBER_ID:
                selection = MemberEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(MemberEntry.TABLE_NAME, projection, selection , selectionArgs , null, null, sortOrder);
                break;


                default:
                    Toast.makeText(getContext(), "Incorrect URI", Toast.LENGTH_SHORT);
                    throw new IllegalArgumentException("Can't query incorrect URI " + uri);

        }
        return cursor;
    }


    @Override
    public Uri insert( Uri uri,  ContentValues values) {
        

        return null;
    }

    @Override
    public int delete( Uri uri,  String selection,  String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update( Uri uri,  ContentValues values,  String selection,  String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType( Uri uri) {
        return null;
    }
}


// URI - Unified Resource Identifier
// content://com.android.example.clubolympus/members/id
// URL - Unified Recourse Locator
// http://google.com
// content://com.android.contacts/contacts
// content://com.android.calendar/events
// content://user_dictionary/words

// content:// - scheme
// com.android.contacts - content authority
// contacts - type of data