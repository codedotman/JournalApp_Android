package com.codedotman.journalapp;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.codedotman.journalapp.data.JournalContract;

public class AddJournalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal);
    }

    /**
     * onClickAddTask is called when the "ADD" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onClickAddTask(View view) {
        // Not yet implemented
        // Check if EditText is empty, if not retrieve input and store it in a ContentValues object
        // If the EditText input is empty -> don't create an entry
        String titleInput = ((EditText) findViewById(R.id.editTextJournalTitle)).getText().toString();
        if (titleInput.length() == 0) {
            return;
        }

        String descriptionInput = ((EditText) findViewById(R.id.editTextJournalDescription)).getText().toString();
        if (descriptionInput.length() == 0) {
            return;
        }


        // Insert new task data via a ContentResolver
        // Create new empty ContentValues object
        ContentValues contentValues = new ContentValues();
        // Put the task description and selected mPriority into the ContentValues
        contentValues.put(JournalContract.JournalEntry.COLUMN_TITLE, titleInput);
        contentValues.put(JournalContract.JournalEntry.COLUMN_DETAILS, descriptionInput);
        // Insert the content values via a ContentResolver
        Uri uri = getContentResolver().insert(JournalContract.JournalEntry.CONTENT_URI, contentValues);

        // Display the URI that's returned with a Toast
        // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete
        if(uri != null) {
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }

        // Finish activity (this returns back to MainActivity)
        finish();

    }


}
