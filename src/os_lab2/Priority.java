package os_lab2;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

// Implementing priority between processes with Comparator
public class Priority implements Comparator {
    private int prior1;
    private int prior2;
    @Override
    public int compare(Object o1, Object o2) {
        sProcess process1 = (sProcess) o1;
        sProcess process2 = (sProcess) o2;
        if ((process1.priority == process2.priority) && (process1.ioblocking == process2.ioblocking)
                && (process1.cputime == process2.cputime))
            return 0;
        else if ((process1.priority == process2.priority) && (process1.ioblocking == process2.ioblocking)){
            prior1 = process1.cputime;
            prior2 = process2.cputime;
            if ((prior1 - prior2) > 0)
                return 1;
            else return -1;
        }
        else if (process1.priority == process2.priority){
             prior1 = process1.ioblocking;
             prior2 = process2.ioblocking;
            if (prior1 - prior2 > 0)
                return 1;
            else return -1;
        }
        else if (process1.priority > process2.priority){
            return 1;
        }
        else return -1;
    }
    public static void sortBurstTime(Vector<sProcess> processVector) {

        Collections.sort(processVector, (o1, o2) -> {
            sProcess pr1 = o1;
            sProcess pr2 = o2;
            int result = (pr2.ioblocking - pr1.ioblocking);
            return  result;
        });

        setPriority(processVector);

    }
    public static void setPriority(Vector<sProcess> processVector) {
        int i = 0;
        for(sProcess process : processVector) {
            process.priority = i++;
        }
    }
//    public static void sort(Vector<sProcess> processVector) {
//        Collections.sort(processVector, (o1, o2) -> {
//            sProcess pr1 =  o1;
//            sProcess pr2 =  o2;
//            int result = pr1.cputime - pr2.cputime;
//            return result;
//        });
//    }
}
