import java.util.ArrayList;
public class work extends schedule{
    private ArrayList<work> works = new ArrayList<>();
    
    public work () {

    }

    public work (String type, int timeBegin, int timeEnd, String detail) {
        super(timeBegin, timeEnd, type, detail);
    }

    public void adder (work works) {
        this.works.add(works);
    }

    public String getInfo () {
        String allWork = "Work List: ";
        int k = 0;
        for (work work : works) {
            if (k > 0) {
                allWork += ", ";;
            }
            allWork += work.getDetail();
            k++;
        }
        return allWork;
    }

}
