import java.util.ArrayList;
public class schedule {
    private int timeBegin;
    private int timeEnd;
    private String type;
    private String detail;
    private ArrayList<schedule> scheduleList = new ArrayList<>();

    public schedule () {

    }

    public schedule(int tB, int tE, String reci_Type, String reci_Detail) {
        this.timeBegin = tB;
        this.timeEnd = tE;
        this.type = reci_Type;
        this.detail = reci_Detail;
    }

    public void classifier () {
        schedule orderElement;
        for (int k = 0; k < scheduleList.size()-1; k++) {
            int a = scheduleList.size();

            for (int n = 0; n < a-1; n++) {
                if (scheduleList.size() > 1) {
                    //System.out.println("n = " + n + "\nlist size = " + scheduleList.size());
    
                    if (scheduleList.get(n).getTimeBegin() > scheduleList.get(n+1).getTimeBegin()) {
                        orderElement = scheduleList.get(n+1);
                        scheduleList.set(n+1, scheduleList.get(n));
                        scheduleList.set(n, orderElement);
    
                    } 
                }
            }
            a--;
        }
    }

    public void adder (schedule schedules) {
        scheduleList.add(schedules);
    }

    public String getType () {
        return this.type;
    }

    public String getDetail () {
        return detail;
    }

    public int getTimeBegin () {
        return this.timeBegin;
    }
    
    public int getTimeEnd () {
        return this.timeEnd;
    }

    public String getInfo () {
        String allSchedule = "All types schedule List: \n";

        for (schedule schedule : scheduleList) {
            allSchedule += " ".repeat(4) + schedule.getDetail() + "\n" + " ".repeat(8) + "Time: " + schedule.getTimeBegin() + " - " + schedule.getTimeEnd() + "\n";
        }

        allSchedule += "-".repeat(40);
        return allSchedule;
    }

}
