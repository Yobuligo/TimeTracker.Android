package com.yobuligo.timeTracker.activity.timeFrameOverviewActivity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.yobuligo.timeTracker.R;
import com.yobuligo.timeTracker.model.subject.ISubjectContext;
import com.yobuligo.timeTracker.model.subject.Location;
import com.yobuligo.timeTracker.service.timeTracker.ITimeFrame;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
        holder.timeFrame = subjectContext.getTimeTracker().getTimeFrameList().getTimeFrames().get(position);
        holder.renderElements();
    }

    @Override
    public int getItemCount() {
        return subjectContext.getTimeTracker().getTimeFrameList().getTimeFrames().size();
    }

    class TimeFrameViewHolder extends RecyclerView.ViewHolder {

        private ITimeFrame timeFrame;
        private TextView textViewSubjectDescription;
        private ImageView imageViewSubjectLocation;
        private TextView textViewSubjectStartTime;
        private TextView textViewSubjectEndTime;
        private TextView textViewSubjectStartDate;
        private final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.GERMANY);
        private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY);

        public TimeFrameViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSubjectDescription = itemView.findViewById(R.id.ui_txt_subject_description);
            imageViewSubjectLocation = itemView.findViewById(R.id.ui_img_view_subject_location);
            textViewSubjectStartTime = itemView.findViewById(R.id.ui_txt_subject_start_time);
            textViewSubjectEndTime = itemView.findViewById(R.id.ui_txt_subject_end_time);
            textViewSubjectStartDate = itemView.findViewById(R.id.ui_txt_subject_date);
        }

        public void renderElements() {
            textViewSubjectDescription.setText(timeFrame.getSubject().getDescription());

            if (timeFrame.getLocation() == Location.home) {
                imageViewSubjectLocation.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_home_black_32dp));
            } else {
                imageViewSubjectLocation.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_location_on_black_32dp));
            }

            int color = context.getResources().getIdentifier(timeFrame.getSubject().getColorCode(), "color", context.getPackageName());
            imageViewSubjectLocation.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, color)));

            textViewSubjectStartDate.setText(dateFormat.format(timeFrame.getStartTime()));
            textViewSubjectStartTime.setText(timeFormat.format(timeFrame.getStartTime()));
            if (timeFrame.getEndTime() != null) {
                textViewSubjectEndTime.setText(timeFormat.format(timeFrame.getEndTime()));
            } else {
                textViewSubjectEndTime.setText(R.string.running);
            }
        }
    }
}
