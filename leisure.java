import java.util.ArrayList;
public class leisure extends schedule{
    private ArrayList<leisure> leisures = new ArrayList<>(); 

    public leisure () {

    }

    public leisure (Double date, String type, int timeBegin, int timeEnd, String detail) {
        super(date, timeBegin, timeEnd, type, detail);
    }

    public void classifier () {
        leisure orderElement;
        for (int k = 0; k < leisures.size()-1; k++) {
            int a = leisures.size();

            for (int n = 0; n < a-1; n++) {
                if (leisures.size() > 1) {
    
                    if (leisures.get(n).getTimeBegin() > leisures.get(n+1).getTimeBegin()) {
                        orderElement = leisures.get(n+1);
                        leisures.set(n+1, leisures.get(n));
                        leisures.set(n, orderElement);
    
                    } 
                }
            }
            a--;
        }
    }

    public void adder (leisure leisures) {
        this.leisures.add(leisures);
    }

    public String getInfo () {
        String allLeisure = "Leisure's List: \n";

        for (leisure leisure : leisures) {
            allLeisure += " ".repeat(4) + leisure.getDetail() + "\n" + " ".repeat(8) + "Time: " + leisure.getTimeBegin() + " - " + leisure.getTimeEnd() + "\n";
        }

        allLeisure += "-".repeat(40);
        return allLeisure;
    }

}
