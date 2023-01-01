import java.util.ArrayList;
public class school extends schedule{
    private ArrayList<school> schools = new ArrayList<>();   
    
    public school () {
        
    }
    
    public school (String type, int timeBegin, int timeEnd, String detail) {
        super(timeBegin, timeEnd, type, detail);
    }

    public void adder (school schools) {
        this.schools.add(schools);
    }

    public String getInfo () {
        String allSchool = "School List: ";
        int k = 0;

        for (school school : schools) {
            if (k > 0) {
                allSchool += ", ";
            }

            allSchool += school.getDetail();
            k++;
        }
        return allSchool;
    }

}