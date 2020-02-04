package com.yobuligo.timeTracker.activity.timeFrameOverviewActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yobuligo.timeTracker.R;
import com.yobuligo.timeTracker.model.subject.ISubjectContext;
import com.yobuligo.timeTracker.service.timeTracker.ITimeFrame;

public class TimeFrameOverviewAdapter extends RecyclerView.Adapter<TimeFrameOverviewAdapter.TimeFrameViewHolder> {

    private Context context;
    private ISubjectContext subjectContext;

    public TimeFrameOverviewAdapter(Context context, ISubjectContext subjectContext) {
        this.context = context;
        this.subjectContext = subjectContext;
    }

    @NonNull
    @Override
    public TimeFrameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_time_frame, parent, false);
        return new TimeFrameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeFrameViewHolder holder, int position) {
        holder.timeframe = subjectContext.getTimeTracker().getTimeFrameList().getTimeFrames().get(position);
        holder.renderElements();
    }

    @Override
    public int getItemCount() {
        return subjectContext.getTimeTracker().getTimeFrameList().getTimeFrames().size();
    }

    class TimeFrameViewHolder extends RecyclerView.ViewHolder {

        private ITimeFrame timeframe;
        private TextView textViewSubjectDescription;

        public TimeFrameViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSubjectDescription = itemView.findViewById(R.id.ui_txt_subject_description);
        }

        public void renderElements() {
            textViewSubjectDescription.setText(timeframe.getSubject().getDescription());
        }
    }
}
