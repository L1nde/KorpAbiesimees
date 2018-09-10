package ee.skyhigh.l1nde.korplaitused;

import ee.skyhigh.l1nde.korplaitused.data.entites.MemberEntity;

public interface ActivityListener {
    void updateDatabase(NewMeetingAdapter.ViewHolder holder, MemberEntity member);
}
