import java.util.ArrayList;
public class school extends schedule{
    private ArrayList<school> schools = new ArrayList<>();   
    
    public school () {
        
    }

    public void classifier () {
        school orderElement;
        for (int k = 0; k < schools.size()-1; k++) {
            int a = schools.size();

            for (int n = 0; n < a-1; n++) {
                if (schools.size() > 1) {
    
                    if (schools.get(n).getTimeBegin() > schools.get(n+1).getTimeBegin()) {
                        orderElement = schools.get(n+1);
                        schools.set(n+1, schools.get(n));
                        schools.set(n, orderElement);
    
                    } 
                }
            }
            a--;
        }
    }
    
    public school (String type, int timeBegin, int timeEnd, String detail) {
        super(timeBegin, timeEnd, type, detail);
    }

    public void adder (school schools) {
        this.schools.add(schools);
    }

    public String getInfo () {
        String allschool = "School's List: \n";

        for (school school : schools) {
            allschool += " ".repeat(4) + school.getDetail() + "\n" + " ".repeat(8) + "Time: " + school.getTimeBegin() + " - " + school.getTimeEnd() + "\n";
        }

        allschool += "-".repeat(40);
        return allschool;
    }

}