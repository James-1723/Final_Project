import java.util.ArrayList;
public class work extends schedule{
    private ArrayList<work> works = new ArrayList<>();
    
    public work () {

    }

    public void classifier () {
        work orderElement;
        for (int k = 0; k < works.size()-1; k++) {
            int a = works.size();

            for (int n = 0; n < a-1; n++) {
                if (works.size() > 1) {
    
                    if (works.get(n).getTimeBegin() > works.get(n+1).getTimeBegin()) {
                        orderElement = works.get(n+1);
                        works.set(n+1, works.get(n));
                        works.set(n, orderElement);
    
                    } 
                }
            }
            a--;
        }
    }

    public work (Double date, String type, int timeBegin, int timeEnd, String detail) {
        super(date, timeBegin, timeEnd, type, detail);
    }

    public void adder (work works) {
        this.works.add(works);
    }

    public String getInfo () {
        String allWork = "Work's List: \n";

        for (work work : works) {
            allWork += " ".repeat(4) + work.getDetail() + "\n" + " ".repeat(8) + "Time: " + work.getTimeBegin() + " - " + work.getTimeEnd() + "\n";
        }

        allWork += "-".repeat(40);
        return allWork;
    }

}
