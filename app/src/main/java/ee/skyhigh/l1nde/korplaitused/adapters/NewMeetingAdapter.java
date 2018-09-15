package ee.skyhigh.l1nde.korplaitused.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.listeners.MeetingListener;
import ee.skyhigh.l1nde.korplaitused.R;
import ee.skyhigh.l1nde.korplaitused.data.entites.LaitusedEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

public class NewMeetingAdapter extends RecyclerView.Adapter<NewMeetingAdapter.ViewHolder> {

    private List<MemberEntity> members;

    private int expandedPos = -1;

    private MeetingListener listener;

    public NewMeetingAdapter(MeetingListener listener) {
        this.listener = listener;

    }


    @Override
    public NewMeetingAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_meeting_member_row_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view, listener, this);
        holder.itemView.setTag(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                if (expandedPos >= 0){
                    notifyItemChanged(expandedPos);
                }
                expandedPos = viewHolder.getAdapterPosition();
                notifyItemChanged(expandedPos);
            }
        });

        return holder;
    }



    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int pos) {
        MemberEntity memberEntity = members.get(pos);
        viewHolder.typeField.setText(memberEntity.getType());
        viewHolder.firstnameField.setText(memberEntity.getFirstname());
        viewHolder.lastnameField.setText(memberEntity.getLastname());
        viewHolder.memberId = memberEntity.getId();


        if (pos == expandedPos){
            // T2idab expanded view
            final long memberId = memberEntity.getId();
            LaitusedEntity laitus = listener.createFindLaitus(memberId);
            if (laitus.isKohal()){
                viewHolder.attendance.check(R.id.attendance);
            } else if(laitus.isVabandamine()){
                viewHolder.attendance.check(R.id.excuse);
            } else {
                viewHolder.attendance.check(R.id.absent);
            }
            ((CheckBox) viewHolder.expandedLinear.findViewById(R.id.late)).setChecked(laitus.isHilinemine());
            viewHolder.laitusNr.setText(String.valueOf(laitus.getLaitused()));
            viewHolder.markusNr.setText(String.valueOf(laitus.getMarkused()));
            // expanded view nähtavaks
            viewHolder.expandedLinear.setVisibility(View.VISIBLE);
        } else {
            viewHolder.expandedLinear.setVisibility(View.GONE);
        }
    }

    public void setMembers(List<MemberEntity> members){
        this.members = members;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return members == null ? 0 : members.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView typeField;
        private TextView firstnameField;
        private TextView lastnameField;
        private LinearLayout expandedLinear;
        private RadioGroup attendance;
        private LinearLayout laitusLinear;
        private LinearLayout markusLinear;
        private TextView laitusNr;
        private TextView markusNr;

        private long memberId = -1;

        private MeetingListener listener;
        private NewMeetingAdapter adapter;


        public ViewHolder(View view, MeetingListener listener2, final NewMeetingAdapter adapter) {
            super(view);
            typeField = view.findViewById(R.id.typeField);
            firstnameField = view.findViewById(R.id.firstnameField);
            lastnameField = view.findViewById(R.id.lastnameField);
            expandedLinear = view.findViewById(R.id.expandedLinear);
            attendance = view.findViewById(R.id.attendanceGroup);
            laitusLinear = view.findViewById(R.id.laitusLinear);
            markusLinear = view.findViewById(R.id.markusLinear);
            laitusNr = view.findViewById(R.id.laitusNr);
            markusNr = view.findViewById(R.id.markusNr);

            this.listener = listener2;
            this.adapter = adapter;

            attendance.findViewById(R.id.attendance).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.updateAttendanceDB(memberId, ((RadioButton) attendance.findViewById(attendance.getCheckedRadioButtonId())).getText().toString());
                }
            });
            attendance.findViewById(R.id.excuse).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.updateAttendanceDB(memberId, ((RadioButton) attendance.findViewById(attendance.getCheckedRadioButtonId())).getText().toString());
                }
            });
            attendance.findViewById(R.id.absent).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.updateAttendanceDB(memberId, ((RadioButton) attendance.findViewById(attendance.getCheckedRadioButtonId())).getText().toString());
                }
            });
            ((CheckBox) expandedLinear.findViewById(R.id.late)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.updateLateDB(memberId, ((CheckBox) v).isChecked());
                }
            });
            laitusLinear.findViewById(R.id.addLButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.updateLaitusIncDB(memberId);
                    adapter.notifyItemChanged(getAdapterPosition());
                }
            });
            laitusLinear.findViewById(R.id.removeLButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.updateLaitusDecDB(memberId);
                    adapter.notifyItemChanged(getAdapterPosition());
                }
            });
            markusLinear.findViewById(R.id.addMButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.updateMarkusIncDB(memberId);
                    adapter.notifyItemChanged(getAdapterPosition());
                }
            });
            markusLinear.findViewById(R.id.removeMButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.updateMarkusDecDB(memberId);
                    adapter.notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }



}
