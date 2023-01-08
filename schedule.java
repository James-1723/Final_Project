import java.util.ArrayList;
public class schedule {
    private int timeBegin;
    private int timeEnd;
    private String type;
    private String detail;
    private Double date;
    private ArrayList<schedule> scheduleList = new ArrayList<>();

    public schedule () {

    }

    public schedule(Double date, int tB, int tE, String reci_Type, String reci_Detail) {
        this.date = date;
        this.timeBegin = tB;
        this.timeEnd = tE;
        this.type = reci_Type;
        this.detail = reci_Detail;
    }

    public void classifier () {
        schedule orderElement;

        //排日期
        for (int k = 0; k < scheduleList.size()-1; k++) {
            int a = scheduleList.size();

            for (int n = 0; n < a-1; n++) {
                if (scheduleList.size() > 1) {    
                    if (scheduleList.get(n).getDate()*100 > scheduleList.get(n+1).getDate()*100) {
                        orderElement = scheduleList.get(n+1);
                        scheduleList.set(n+1, scheduleList.get(n));
                        scheduleList.set(n, orderElement);
    
                    } 
                }
            }
            a--;
        }

        //排時間
        for (int k = 0; k < scheduleList.size()-1; k++) {
            int a = scheduleList.size();

            for (int n = 0; n < a-1; n++) {
                if (scheduleList.size() > 1) {    
                    if (scheduleList.get(n).getDate() == scheduleList.get(n+1).getDate()) {
                        if (scheduleList.get(n).getTimeBegin() > scheduleList.get(n+1).getTimeBegin()) {
                            orderElement = scheduleList.get(n+1);
                            scheduleList.set(n+1, scheduleList.get(n));
                            scheduleList.set(n, orderElement);
        
                        } 
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

    public double getDate () {
        return this.date;
    }

    public String getInfo () {
        double i = 0;
        String allSchedule = "All types schedule List: \n";

        for (schedule schedule : scheduleList) {
            if (schedule.getDate() != i) {
                allSchedule += " ".repeat(4) + "Date: " + schedule.getDate() + "\n";
            }
            allSchedule += " ".repeat(8) + schedule.getTimeBegin() + " ~ " +schedule.getTimeEnd() + ": " + schedule.getDetail() + "\n";
            i = schedule.getDate();
        }

        allSchedule += "-".repeat(40);
        return allSchedule;
    }

}
