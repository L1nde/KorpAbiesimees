package ee.skyhigh.l1nde.korplaitused.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.R;
import ee.skyhigh.l1nde.korplaitused.data.entites.MeetingEntity;


public class MeetingsAdapter extends RecyclerView.Adapter<MeetingsAdapter.ViewHolder> {

    private List<MeetingEntity> meetings;

    public MeetingsAdapter() {

    }


    @Override
    public MeetingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.meeting_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        MeetingEntity meetingEntity = meetings.get(i);
        viewHolder.getTypeField().setText(meetingEntity.getType());
        viewHolder.getDateField().setText(meetingEntity.getDate());
    }

    public void setMeetings(List<MeetingEntity> meetings){
        this.meetings = meetings;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return meetings == null ? 0 : meetings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView typeField;
        private TextView dateField;

        public ViewHolder(View view) {
            super(view);
            typeField = view.findViewById(R.id.meetingType);
            dateField = view.findViewById(R.id.meetingDate);

        }

        public TextView getTypeField() {
            return typeField;
        }

        public TextView getDateField() {
            return dateField;
        }
    }
}
