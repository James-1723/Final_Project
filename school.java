import java.util.ArrayList;
public class school extends schedule{
    private ArrayList<school> schools = new ArrayList<>();   
    
    public school () {
        
    }

    public void classifier () {
        school orderElement;

        //排日期
        for (int k = 0; k < schools.size()-1; k++) {
            int a = schools.size();

            for (int n = 0; n < a-1; n++) {
                if (schools.size() > 1) {    
                    if (schools.get(n).getDate()*100 > schools.get(n+1).getDate()*100) {
                        orderElement = schools.get(n+1);
                        schools.set(n+1, schools.get(n));
                        schools.set(n, orderElement);
    
                    } 
                }
            }
            a--;
        }

        //排時間
        for (int k = 0; k < schools.size()-1; k++) {
            int a = schools.size();

            for (int n = 0; n < a-1; n++) {
                if (schools.size() > 1) {    
                    if (schools.get(n).getDate() == schools.get(n+1).getDate()) {
                        if (schools.get(n).getTimeBegin() > schools.get(n+1).getTimeBegin()) {
                            orderElement = schools.get(n+1);
                            schools.set(n+1, schools.get(n));
                            schools.set(n, orderElement);
        
                        } 
                    }
                }
            }
            a--;
        }
    }
    
    public school (Double date, String type, int timeBegin, int timeEnd, String detail) {
        super(date, timeBegin, timeEnd, type, detail);
    }

    public void adder (school schools) {
        this.schools.add(schools);
    }

    public String getInfo () {
        double i = 0;
        String allSchool = "School's List: \n";

        for (school schedule : schools) {
            if (schedule.getDate() != i) {
                allSchool += " ".repeat(4) + "Date: " + schedule.getDate() + "\n";
            }
            allSchool += " ".repeat(8) + schedule.getTimeBegin() + " ~ " +schedule.getTimeEnd() + ": " + schedule.getDetail() + "\n";
            i = schedule.getDate();
        }

        allSchool += "-".repeat(40);
        return allSchool;
    }

}