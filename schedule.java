import java.util.ArrayList;
public class schedule {
    private int timeBegin;
    private int timeEnd;
    private String type;
    private String detail;
    private ArrayList<schedule> scheduleList = new ArrayList<>();
    private ArrayList<schedule> orderList = new ArrayList<>();

    public schedule () {

    }

    public schedule(int tB, int tE, String reci_Type, String reci_Detail) {
        this.timeBegin = tB;
        this.timeEnd = tE;
        this.type = reci_Type;
        this.detail = reci_Detail;
    }

    public void classifier () {
        //int largestIndex = 0;
        //int i = 1;
        schedule orderElement;

        /*for (schedule schedule : scheduleList) {
            if (scheduleList.size() > 1) {
                if (schedule.getTimeBegin() > scheduleList.get(i).getTimeBegin()) {
                    orderElement = scheduleList.get(i);
                    scheduleList.set(i, schedule);
                    scheduleList.set(i-1, orderElement);
                } 
            }size = 5: 0,1,2,3
            i++;
        }*/

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

        /*for (int n = 0; n < scheduleList.size(); n++) {

            if (scheduleList.size() == 1) {
                
            } 
            else if () {

                for (int i = 0; i < scheduleList.size(); i++) {
                    if (scheduleList.get(i).getTimeBegin() > scheduleList.get(i+1).getTimeBegin()) {
                        largestIndex = i;
                    }
                }
                orderList.add(scheduleList.get(largestIndex));
                scheduleList.remove(scheduleList.get(largestIndex));

            }
        }*/
    }

    public void adder (schedule schedules) {
        scheduleList.add(schedules);
        orderList = scheduleList;
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
        System.out.println("size = " + scheduleList.size());
        String allSchedule = "Schedule List: ";
        int k = 0;

        for (schedule schedule : scheduleList) {
            if (k > 0) {
                allSchedule += ", ";
            }
            allSchedule += schedule.getDetail();
            k++;
            System.out.println(k);
        }

        return allSchedule;
    }

}
