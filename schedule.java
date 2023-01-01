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

    public void classifier (schedule schedules) {
        //addInArray.scheduleAddIn(schedules);
        scheduleList.add(schedules);
        if (schedules.getType().equals("meal")) {
            meal meals = new meal(this.type, this.timeBegin, this.timeEnd, this.detail);
            meals.adder(meals);
            //addInArray.mealAddIn(meals);
        }
        else if (schedules.getType().equals("leisure")) {
            leisure leisures = new leisure(this.type, this.timeBegin, this.timeEnd, this.detail);
            leisures.adder(schedules);
            //addInArray.leisureAddIn(leisures);
        }
        else if (schedules.getType().equals("work")) {
            work works = new work(this.type, this.timeBegin, this.timeEnd, this.detail);
            works.adder(works);
            //addInArray.workAddIn(works);
        }
        else if (schedules.getType().equals("school")) {
            school schools = new school(this.type, this.timeBegin, this.timeEnd, this.detail);
            schools.adder(schools);
            //addInArray.schoolAddIn(schools);
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
