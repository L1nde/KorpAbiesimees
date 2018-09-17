package ee.skyhigh.l1nde.korplaitused.listeners;

import android.content.Context;

import ee.skyhigh.l1nde.korplaitused.MemberStatistic;

public interface MemberListener {
    MemberStatistic getMemberStatistic(long memberId);
    Context getContext();
}
