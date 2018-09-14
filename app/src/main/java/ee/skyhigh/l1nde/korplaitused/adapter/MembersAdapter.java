package ee.skyhigh.l1nde.korplaitused.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.R;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;


public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.ViewHolder> {

    private List<MemberEntity> members;

    private int expandedPos = -1;

    public MembersAdapter() {

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
                expandedPos = viewHolder.getPosition(); //todo something else
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
