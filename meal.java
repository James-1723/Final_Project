import java.util.ArrayList;
public class meal extends schedule{
    private ArrayList<meal> meals = new ArrayList<>();

    public meal () {

    }

    public meal (Double date, String type, int timeBegin, int timeEnd, String detail) {
        super(date, timeBegin, timeEnd, type, detail);
        this.meals = new ArrayList<>();
    }

    public void classifier () {
        meal orderElement;
        for (int k = 0; k < meals.size()-1; k++) {
            int a = meals.size();

            for (int n = 0; n < a-1; n++) {
                if (meals.size() > 1) {
    
                    if (meals.get(n).getTimeBegin() > meals.get(n+1).getTimeBegin()) {
                        orderElement = meals.get(n+1);
                        meals.set(n+1, meals.get(n));
                        meals.set(n, orderElement);
    
                    } 
                }
            }
            a--;
        }
    }

    public void adder (meal meals) {
        this.meals.add(meals);
    }
    
    public String getInfo () {
        String allMeal = "Meal's List: \n";

        for (meal meal : meals) {
            allMeal += " ".repeat(4) + meal.getDetail() + "\n" + " ".repeat(8) + "Time: " + meal.getTimeBegin() + " - " + meal.getTimeEnd() + "\n";
        }

        allMeal += "-".repeat(40);
        return allMeal;
    }
}
