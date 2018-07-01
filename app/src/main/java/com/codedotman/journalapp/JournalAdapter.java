package com.codedotman.journalapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codedotman.journalapp.data.JournalContract;

/**
 * Created by USER on 30/06/2018.
 */

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.JournalViewHolder> implements OnClickListener{

    // Class variables for the Cursor that holds task data and the Context
    private Cursor mCursor;
    private Context mContext;
    final private ListItemClickListener mOnClickListener;
    private static int viewHolderCount;

    private int mNumberItems;

    @Override
    public void onClick(View v) {


    }


    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }




    public JournalAdapter(int numberOfItems, ListItemClickListener listener) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }


    @Override
    public JournalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_layout, parent, false);

        return new JournalViewHolder(view);
    }


    @Override
    public void onBindViewHolder(JournalViewHolder holder, int position) {

        // Indices for the _id, description, and priority columns
        int idIndex = mCursor.getColumnIndex(JournalContract.JournalEntry._ID);
        int titleIndex = mCursor.getColumnIndex(JournalContract.JournalEntry.COLUMN_TITLE);
        int detailsIndex = mCursor.getColumnIndex(JournalContract.JournalEntry.COLUMN_DETAILS);

        mCursor.moveToPosition(position); // get to the right location in the cursor

        // Determine the values of the wanted data
        final int id = mCursor.getInt(idIndex);
        String title = mCursor.getString(titleIndex);
        String details = mCursor.getString(detailsIndex);

        //Set values
        holder.itemView.setTag(id);
        holder.journalTitleView.setText(title);
        holder.journalView.setText(details);

    }




    /**
     * Returns the number of items to display.
     */
    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }


    /**
     * When data changes and a re-query occurs, this function swaps the old Cursor
     * with a newly updated Cursor (Cursor c) that is passed in.
     */
    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }


    // Inner class for creating ViewHolders
    class JournalViewHolder extends RecyclerView.ViewHolder {

        // Class variables for the task description and priority TextViews
        TextView journalTitleView;
        TextView journalView;

        /**
         * Constructor for the TaskViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public JournalViewHolder(View itemView) {
            super(itemView);

            journalTitleView = (TextView) itemView.findViewById(R.id.list_title);
            journalView = (TextView) itemView.findViewById(R.id.list_details);
        }
    }

}
