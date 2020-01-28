package com.yobuligo.timeTracker.Subject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yobuligo.timeTracker.R;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private Context context;
    private SubjectList subjectList;

    public SubjectAdapter(Context context, SubjectList subjectList) {
        this.context = context;
        this.subjectList = subjectList;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_subject, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        holder.textViewSubjectDescription.setText(subjectList.getSubjects().get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return subjectList.getSubjects().size();
    }

    class SubjectViewHolder extends RecyclerView.ViewHolder {
        final TextView textViewSubjectDescription;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSubjectDescription = itemView.findViewById(R.id.TextSubjectDescription);
        }
    }
}
