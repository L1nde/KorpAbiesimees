package ee.skyhigh.l1nde.korplaitused;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.data.entites.MeetingEntity;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

public class NewMeetingAdapter extends RecyclerView.Adapter<NewMeetingAdapter.ViewHolder> {

    private List<MemberEntity> members;

    private int expandedPos = -1;

    public NewMeetingAdapter() {

    }


    @Override
    public NewMeetingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_meeting_member_row_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
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
        holder.itemView.setTag(holder);
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

        public ViewHolder(View view) {
            super(view);
            typeField = view.findViewById(R.id.typeField);
            firstnameField = view.findViewById(R.id.firstnameField);
            lastnameField = view.findViewById(R.id.lastnameField);
            expandedLinear = view.findViewById(R.id.expandedLinear);
        }

        public LinearLayout getExpandedLinear() {
            return expandedLinear;
        }

        public TextView getTypeField() {
            return typeField;
        }

        public TextView getFirstnameField() {
            return firstnameField;
        }

        public TextView getLastnameField() {
            return lastnameField;
        }
    }

}
