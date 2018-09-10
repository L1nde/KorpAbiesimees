package ee.skyhigh.l1nde.korplaitused;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.data.entites.MeetingEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

public class NewMeetingAdapter extends RecyclerView.Adapter<NewMeetingAdapter.ViewHolder> {

    private List<MemberEntity> members;

    private int expandedPos = -1;

    private Context context;

    public NewMeetingAdapter(Context context) {
        this.context = context;

    }


    @Override
    public NewMeetingAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_meeting_member_row_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setTag(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                if (expandedPos >= 0){
                    notifyItemChanged(expandedPos);
                }
                expandedPos = viewHolder.getPosition();
                notifyItemChanged(expandedPos);

            }
        });
        holder.attendance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i(this.getClass().getSimpleName(), holder.firstnameField.getText().toString());
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(NewMeetingAdapter.ViewHolder viewHolder, int pos) {
        MemberEntity memberEntity = members.get(pos);
        viewHolder.getTypeField().setText(memberEntity.getType());
        viewHolder.getFirstnameField().setText(memberEntity.getFirstname());
        viewHolder.getLastnameField().setText(memberEntity.getLastname());

        if (pos == expandedPos){
            viewHolder.getExpandedLinear().setVisibility(View.VISIBLE);
        } else {
            viewHolder.getExpandedLinear().setVisibility(View.GONE);
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

        public ViewHolder(View view) {
            super(view);
            typeField = view.findViewById(R.id.typeField);
            firstnameField = view.findViewById(R.id.firstnameField);
            lastnameField = view.findViewById(R.id.lastnameField);
            expandedLinear = view.findViewById(R.id.expandedLinear);
            attendance = view.findViewById(R.id.attendanceGroup);
            laitusLinear = view.findViewById(R.id.laitusLinear);
            markusLinear = view.findViewById(R.id.markusLinear);
        }

        public LinearLayout getExpandedLinear() {
            return expandedLinear;
        }

        public TextView getTypeField() {
            return typeField;
        }

        public LinearLayout getLaitusLinear() {
            return laitusLinear;
        }

        public LinearLayout getMarkusLinear() {
            return markusLinear;
        }

        public TextView getFirstnameField() {
            return firstnameField;
        }

        public RadioGroup getAttendance() {
            return attendance;
        }

        public TextView getLastnameField() {
            return lastnameField;

        }
    }

}
