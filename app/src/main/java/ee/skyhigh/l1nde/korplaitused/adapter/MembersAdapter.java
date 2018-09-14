package ee.skyhigh.l1nde.korplaitused.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ee.skyhigh.l1nde.korplaitused.R;
import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;


public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.ViewHolder> {

    private List<MemberEntity> members;

    public MembersAdapter() {

    }


    @Override
    public MembersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.member_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        MemberEntity memberEntity = members.get(i);
        viewHolder.getTypeField().setText(memberEntity.getType());
        viewHolder.getFirstnameField().setText(memberEntity.getFirstname());
        viewHolder.getLastnameField().setText(memberEntity.getLastname());
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

        public ViewHolder(View view) {
            super(view);
            typeField = view.findViewById(R.id.typeField);
            firstnameField = view.findViewById(R.id.firstnameField);
            lastnameField = view.findViewById(R.id.lastnameField);
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
