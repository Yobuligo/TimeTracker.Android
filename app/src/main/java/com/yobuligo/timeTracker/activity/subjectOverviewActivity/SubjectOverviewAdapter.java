package com.yobuligo.timeTracker.activity.subjectOverviewActivity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.yobuligo.timeTracker.R;
import com.yobuligo.timeTracker.activity.ColorList;
import com.yobuligo.timeTracker.model.subject.ISubject;
import com.yobuligo.timeTracker.model.subject.ISubjectContext;
import com.yobuligo.timeTracker.model.subject.Location;

public class SubjectOverviewAdapter extends RecyclerView.Adapter<SubjectOverviewAdapter.SubjectViewHolder> {

    private Context context;
    private ISubjectContext subjectContext;
    private RecyclerView recyclerView;
    private ColorList colorList = new ColorList();

    public SubjectOverviewAdapter(Context context, ISubjectContext subjectContext) {
        this.context = context;
        this.subjectContext = subjectContext;
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
        holder.subject = subjectContext.getSubjectList().getSubjects().get(position);
        int color = holder.getColor();
        holder.textViewSubjectDescription.setText(subjectContext.getSubjectList().getSubjects().get(position).getDescription());
        holder.constraintLayoutSubject.setBackgroundColor(context.getResources().getColor(color));
        holder.imageButtonSubjectLocation.setBackgroundColor(context.getResources().getColor(color));
        holder.subjectContext = subjectContext;
        holder.renderElements();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public int getItemCount() {
        return subjectContext.getSubjectList().getSubjects().size();
    }

    class SubjectViewHolder extends RecyclerView.ViewHolder {
        final TextView textViewSubjectDescription;
        final ConstraintLayout constraintLayoutSubject;
        final ImageButton imageButtonSubjectLocation;
        final ImageView imageViewIsSubjectActiveIndicator;
        ISubjectContext subjectContext;
        ISubject subject;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewIsSubjectActiveIndicator = itemView.findViewById(R.id.ui_img_subject_run_indicator);
            textViewSubjectDescription = itemView.findViewById(R.id.ui_txt_subject_description);
            constraintLayoutSubject = itemView.findViewById(R.id.ui_lay_item_subject);
            constraintLayoutSubject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleTimeTracking();
                    renderViewHolders();
                }
            });

            imageButtonSubjectLocation = itemView.findViewById(R.id.ui_btn_location);
            imageButtonSubjectLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleLocation();
                }
            });
        }

        public void renderElements() {
            if (subject.isLocationable()) {
                if (subject.getLocation() == Location.onSite) {
                    imageButtonSubjectLocation.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_location_on_black_48dp));
                } else {
                    imageButtonSubjectLocation.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_home_black_48dp));
                }
            } else {
                imageButtonSubjectLocation.setVisibility(View.INVISIBLE);
            }

            if (subject.supportsVariableTimeTracking()) {
                if (subjectContext.getTimeTracker().isSubjectActive(subject)) {
                    imageViewIsSubjectActiveIndicator.setVisibility(View.VISIBLE);
                    startAnimation(imageViewIsSubjectActiveIndicator);
                } else {
                    imageViewIsSubjectActiveIndicator.setVisibility(View.INVISIBLE);
                }
            } else {
                imageViewIsSubjectActiveIndicator.setVisibility(View.INVISIBLE);
            }
        }

        private int getColor() {
            String colorCode = subject.getColorCode();
            if (colorCode != null) {
                return context.getResources().getIdentifier(colorCode, "color", context.getPackageName());
            } else {
                return colorList.getNext();
            }
        }

        private void startAnimation(ImageView imageViewIsSubjectActiveIndicator) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageViewIsSubjectActiveIndicator, "rotation", 0f, 360f);
            objectAnimator.setDuration(1000);
            objectAnimator.setRepeatCount(Animation.INFINITE);
            objectAnimator.setInterpolator(new LinearInterpolator());
            objectAnimator.start();
        }

        private void toggleLocation() {
            if (subject.getLocation() == Location.home) {
                subject.setLocation(Location.onSite);
            } else {
                subject.setLocation(Location.home);
            }

            renderElements();
        }

        private void toggleTimeTracking() {
            if (subjectContext.getTimeTracker().isSubjectActive(subject)) {
                subjectContext.getTimeTracker().stopTracking();
            } else {
                subjectContext.getTimeTracker().startTracking(subject);
            }

            renderElements();
        }

        private void renderViewHolders() {
            for (int i = 0; i < recyclerView.getChildCount(); i++) {
                SubjectViewHolder subjectViewHolder = (SubjectViewHolder) recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
                subjectViewHolder.renderElements();
            }

        }
    }

}
