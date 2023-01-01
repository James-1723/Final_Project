import java.util.ArrayList;
public class leisure extends schedule{
    private ArrayList<leisure> leisures = new ArrayList<>(); 

    public leisure () {

    }

    public leisure (String type, int timeBegin, int timeEnd, String detail) {
        super(timeBegin, timeEnd, type, detail);
    }

    public void adder (leisure leisures) {
        this.leisures.add(leisures);
    }

    public String getInfo () {
        String allLeisure= "Leisure's List: ";
        int k = 0;
        for (leisure leisure : leisures) {
            if (k > 0) {
                allLeisure += ", ";
            }
            allLeisure += leisure.getDetail();
            k++;
        }
        return allLeisure;
    }
}
