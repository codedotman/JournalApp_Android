package com.codedotman.journalapp.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by USER on 30/06/2018.
 */

public class JournalContract {

    // The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "com.codedotman.journalapp";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Define the possible paths for accessing data in this contract
    // This is the path for the "tasks" directory
    public static final String PATH_JOURNALS = "journals";

    /* TaskEntry is an inner class that defines the contents of the task table */
    public static final class JournalEntry implements BaseColumns {

        // TaskEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_JOURNALS).build();


        // Task table and column names
        public static final String TABLE_NAME = "journals";

        // Since TaskEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below
        public static final String COLUMN_TITLE = "description";
        public static final String COLUMN_DETAILS = "details";


    }
}
