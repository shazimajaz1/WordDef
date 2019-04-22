package com.practice.projects.save_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practice.projects.R;
import com.practice.projects.database.Definitions;

import java.util.List;

public class SaveListAdapter extends RecyclerView.Adapter<SaveListAdapter.SaveListViewHolder> {

    /*
        Field Variables
     */
    private final LayoutInflater layoutInflater;
    private List<Definitions> definitionsList;

    /*
        Default Constructor: Inflates the layout based on teh provided context
     */
    public SaveListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);

    }

    /*
        This method is invoked when the view for each item is created.
     */

    @NonNull
    @Override
    public SaveListAdapter.SaveListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.save_list_recycler_view_item, viewGroup, false);
        return new SaveListViewHolder(view);
    }

    /*
        This method binds the data to the view
     */
    @Override
    public void onBindViewHolder(@NonNull SaveListAdapter.SaveListViewHolder saveListViewHolder, int position) {
        if (definitionsList != null) { //If there are any values in the list
            Definitions currentDefinitions = definitionsList.get(position);
            saveListViewHolder.saveListItemQueryText.setText(currentDefinitions.getQueryString());
            saveListViewHolder.saveListItemResultText.setText(currentDefinitions.getDefinitionsString());

        } else {
            saveListViewHolder.saveListItemQueryText.setText("None");
            saveListViewHolder.saveListItemResultText.setText("None");
        }
    }

    /*
        This allows the functionality of changing the data set as a whole
     */
    void setDefinitions(List<Definitions> definitions) {
        definitionsList = definitions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (definitionsList != null) {
            return definitionsList.size();
        } else {
            return 0;
        }
    }

    /*
        Inner class is used to create a holder for each of the recycler view item.
        The object of this class then servers as an individual view in a recycler view.
        A recycler view may consist of none to many view holders.
     */
    class SaveListViewHolder extends RecyclerView.ViewHolder {
        /*
            Field variables and class constants
         */
        public final TextView saveListItemQueryText;
        public final TextView saveListItemResultText;

        /*
            Default Constructor: Initiates the saveListItemTextView and calls the constructor of the super
            class
         */
        public SaveListViewHolder(@NonNull View itemView) {
            super(itemView);
            saveListItemQueryText = itemView.findViewById(R.id.recycler_view_list_item_query);
            saveListItemResultText = itemView.findViewById(R.id.recycler_view_list_item_result);

        }
    }
}
