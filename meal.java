import java.util.ArrayList;
public class meal extends schedule{
    private ArrayList<meal> meals = new ArrayList<>();

    public meal () {

    }

    public meal (String type, int timeBegin, int timeEnd, String detail) {
        super(timeBegin, timeEnd, type, detail);
        this.meals = new ArrayList<>();
    }

    public void controller () {

    }

    public void adder (meal meals) {
        this.meals.add(meals);
    }

    public String getInfo () {
        String allMeal= "Meal's List: ";
        int k = 0;
        for (meal meal : meals) {
            if (k>0) {
                allMeal += ", ";
            }
            allMeal += meal.getDetail();
            k++;
        }
        return allMeal;
    }

    /*
     * String allSchedule = "Schedule List: ";
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
     */
    
}
