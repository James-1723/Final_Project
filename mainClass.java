import java.util.Scanner;
public class mainClass {
    public static void main(String[] args) {
        String detail = ""; //細節
        String type = ""; //行事曆的類別
        String tem = ""; //用來存A/B/C/D
        String m = ""; //用來存退出與否
        String outputDecider = ""; //Deciding what to outout

        int timeBegin = 0; //開始的時間
        int timeEnd = 0; //結束的時間
        boolean control = true; //控制迴圈
        Scanner scn = new Scanner(System.in);
        int count = 0;

        schedule middle = new schedule();
        meal mealed = new meal();
        leisure leisured = new leisure();
        work worked = new work();
        school schooled = new school();

        while (control == true) {
            count++;
            System.out.println("-".repeat(10) + "Edit_Time: " + count + "-".repeat(10));
            System.out.println("Please input your schedule, input 'A' for leisure, 'B' for work, 'C' for class, 'D' for eating: ");
            tem = scn.next();

            if (tem.equals("A")) {
                type = "Leisure";
                System.out.println("Please input the begin time (2400): ");
                timeBegin = scn.nextInt(); //時間
                System.out.println("Please input the end time (2400): ");
                timeEnd = scn.nextInt();
                System.out.println("What are you planning to do at this time?");
                detail = scn.next(); //細節

                schedule localSchedule = new schedule(timeBegin, timeEnd, type, detail);
                middle.adder(localSchedule);

                leisure leisurein = new leisure(type, timeBegin, timeEnd, detail);
                leisured.adder(leisurein);
            }
            else if (tem.equals("B")) {
                type = "Work";
                System.out.println("Please input the begin time (2400): ");
                timeBegin = scn.nextInt(); //時間
                System.out.println("Please input the end time (2400): ");
                timeEnd = scn.nextInt();
                System.out.println("What are you planning to do at this time?");
                detail = scn.next(); //細節

                schedule localSchedule = new schedule(timeBegin, timeEnd, type, detail);
                middle.adder(localSchedule);

                work workin = new work(type, timeBegin, timeEnd, detail);
                worked.adder(workin);
                
            }
            else if (tem.equals("C")) {
                type = "School";
                System.out.println("Please input the begin time (2400): ");
                timeBegin = scn.nextInt(); //時間
                System.out.println("Please input the end time (2400): ");
                timeEnd = scn.nextInt();
                System.out.println("What are you planning to do at this time?");
                detail = scn.next(); //細節

                schedule localSchedule = new schedule(timeBegin, timeEnd, type, detail);
                middle.adder(localSchedule);

                school schoolin = new school(type, timeBegin, timeEnd, detail);
                schooled.adder(schoolin);

            }
            else if (tem.equals("D")) {
                type = "Eating";
                System.out.println("Please input the begin time (2400): ");
                timeBegin = scn.nextInt(); //時間
                System.out.println("Please input the end time (2400): ");
                timeEnd = scn.nextInt();
                System.out.println("What are you planning to do at this time?");
                detail = scn.next(); //細節

                schedule localSchedule = new schedule(timeBegin, timeEnd, type, detail);
                middle.adder(localSchedule);

                meal mealin = new meal(type, timeBegin, timeEnd, detail);
                mealed.adder(mealin);
                
            }

            System.out.println("-".repeat(40));

            
            //Keep Editing?
            System.out.println("Do you want to keep editing? yes/no");
            m = scn.next();
            if (m.equals("no")) {
                control = false;
            }
            
            System.out.println("-".repeat(40));
            
            middle.classifier();

            //See the schedule or not
            System.out.println("\nDo you want see the schedules? yes/no");
            String k = scn.next();

            while (k.equals("yes")) {                
                System.out.printf("What to Outout: \n");
                outputDecider = scn.next();
                
                switch (outputDecider) {
                    case "All":
                        System.out.println(middle.getInfo());
                        break;
                
                    case "meal":
                        System.out.println(mealed.getInfo());
                        break;
                
                    case "leisure":
                        System.out.println(leisured.getInfo());
                        break;
        
                    case "work":
                        System.out.println(worked.getInfo());
                        break;
        
                    case "school":
                        System.out.println(schooled.getInfo());
                        break;
        
                    default:
                        break;
                }

                System.out.println("\nDo you want see the schedules? yes/no");
                k = scn.next();
    
            }

            System.out.println();

        }
        

        scn.close();
    }
}
