public class ResultChecker {

    // Returns the final result of a student
    public static String getResult(int marks, int attendance) {
        String result;                                  // node 1
        if (attendance < 75) {                          // node 1 (decision)
            result = "Not eligible";                    // node 2
        } else if (marks >= 80) {                       // node 3 (decision)
            result = "Distinction";                     // node 4
        } else if (marks >= 50) {                       // node 5 (decision)
            result = "Pass";                            // node 6
        } else {
            result = "Fail";                            // node 7
        }
        return result;                                  // node 8
    }

    // main(): reads marks and attendance from the command line
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java ResultChecker.java <marks> <attendance>");
            return;
        }
        int marks = Integer.parseInt(args[0]);
        int attendance = Integer.parseInt(args[1]);
        System.out.println("Final result: " + getResult(marks, attendance));
    }
}
