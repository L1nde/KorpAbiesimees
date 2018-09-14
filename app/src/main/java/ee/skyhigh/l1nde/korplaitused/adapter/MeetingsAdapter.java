package ee.skyhigh.l1nde.korplaitused.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.ContextListener;
import ee.skyhigh.l1nde.korplaitused.Meetings;
import ee.skyhigh.l1nde.korplaitused.NewMeeting;
import ee.skyhigh.l1nde.korplaitused.R;
import ee.skyhigh.l1nde.korplaitused.data.entites.MeetingEntity;


public class MeetingsAdapter extends RecyclerView.Adapter<MeetingsAdapter.ViewHolder> {

    private List<MeetingEntity> meetings;

    private ContextListener contextListener;

    public MeetingsAdapter(ContextListener contextListener) {
        this.contextListener = contextListener;

    }


    @Override
    public MeetingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.meeting_row_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contextListener.getContext(), NewMeeting.class);
                intent.putExtra("meetingId", meetings.get(i).getId());
                contextListener.getContext().startActivity(intent);
            }
        });
        return holder;
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
