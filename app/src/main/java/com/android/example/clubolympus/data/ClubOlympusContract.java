package com.android.example.clubolympus.data;

import android.provider.BaseColumns;

public final class ClubOlympusContract {

    private ClubOlympusContract(){

    }

    public static final class MemberEntry implements BaseColumns {
        public static final String TABLE_NAME = "members";
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "olympusDB";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_FIRST_NAME = "firstName"; // TEXT
        public static final String COLUMN_LAST_NAME = "lastName"; // TEXT
        public static final String COLUMN_GENDER = "gender"; // INTEGER NOT NULL
        public static final String COLUMN_SPORT = "sport"; // TEXT

        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

    }

}
