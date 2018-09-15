package ee.skyhigh.l1nde.korplaitused;

public class MemberStatistic {

    private int laitused;
    private int markused;
    private int attendance;
    private int late;

    public MemberStatistic(int laitused, int markused, int attendance, int late) {

        this.laitused = laitused;
        this.markused = markused;
        this.attendance = attendance;
        this.late = late;
    }

    public int getLaitused() {
        return laitused;
    }

    public int getMarkused() {
        return markused;
    }

    public int getAttendance() {
        return attendance;
    }

    public int getLate() {
        return late;
    }
}
