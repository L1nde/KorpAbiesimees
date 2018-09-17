package ee.skyhigh.l1nde.korplaitused.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.MemberStatistic;
import ee.skyhigh.l1nde.korplaitused.R;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;
import ee.skyhigh.l1nde.korplaitused.listeners.MemberListener;


public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.ViewHolder> {

    private List<MemberEntity> members;

    private int expandedPos = -1;

    private MemberListener listener;

    public MembersAdapter(MemberListener listener) {
        this.listener = listener;

    }


    @Override
    public MembersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.member_row_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setTag(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHolder viewHolder = ((ViewHolder) v.getTag());
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
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {
        MemberEntity memberEntity = members.get(pos);
        viewHolder.getTypeField().setText(memberEntity.getType());
        viewHolder.getFirstnameField().setText(memberEntity.getFirstname());
        viewHolder.getLastnameField().setText(memberEntity.getLastname());

        if(pos == expandedPos){
            viewHolder.expandedLinear.setVisibility(View.VISIBLE);
            MemberStatistic statistic = listener.getMemberStatistic(memberEntity.getId());
            viewHolder.laitusNr.setText(String.valueOf(statistic.getLaitused()));
            viewHolder.markusNr.setText(String.valueOf(statistic.getMarkused()));
            viewHolder.privateNr.setText(String.valueOf(statistic.getAttendanceP() + "/" + statistic.getTotalP()));
            viewHolder.generalNr.setText(String.valueOf(statistic.getAttendanceG() + "/" + statistic.getTotalG()));
            viewHolder.lateNr.setText(String.valueOf(statistic.getLate()));
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
        private TextView privateNr;
        private TextView generalNr;
        private TextView laitusNr;
        private TextView markusNr;
        private TextView lateNr;

        public ViewHolder(View view) {
            super(view);
            typeField = view.findViewById(R.id.typeField);
            firstnameField = view.findViewById(R.id.firstnameField);
            lastnameField = view.findViewById(R.id.lastnameField);
            expandedLinear = view.findViewById(R.id.expandedLinear);
            privateNr = view.findViewById(R.id.privateNr);
            generalNr = view.findViewById(R.id.generalNr);
            laitusNr = view.findViewById(R.id.laitusNr);
            markusNr = view.findViewById(R.id.markusNr);
            lateNr = view.findViewById(R.id.lateNr);
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
