package ee.skyhigh.l1nde.korplaitused;

public class MemberStatistic {

    private int laitused;
    private int markused;
    private int attendanceP;
    private int attendanceG;
    private int totalP;
    private int totalG;
    private int late;


    public MemberStatistic(int laitused, int markused, int attendanceP, int attendanceG, int late, int totalP, int totalG) {

        this.laitused = laitused;
        this.markused = markused;
        this.attendanceP = attendanceP;
        this.attendanceG = attendanceG;
        this.late = late;
        this.totalP = totalP;
        this.totalG = totalG;
    }

    public int getLaitused() {
        return laitused;
    }

    public int getMarkused() {
        return markused;
    }

    public int getTotalP() {
        return totalP;
    }

    public int getTotalG() {
        return totalG;
    }

    public int getAttendanceP() {
        return attendanceP;
    }

    public int getAttendanceG() {
        return attendanceG;
    }

    public int getLate() {
        return late;
    }
}
