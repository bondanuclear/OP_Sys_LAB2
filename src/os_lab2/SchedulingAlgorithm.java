package os_lab2;

import java.util.Comparator;
import java.util.Vector;
import java.io.*;

public class SchedulingAlgorithm {

    public static Results Run(int runtime, Vector processVector, Results result) {
        int i = 0;
        int comptime = 0;
        int currentProcess = 0;
        int previousProcess = 0;
        int size = processVector.size();
        int completed = 0;
        String resultsFile = "Summary-Processes";

        result.schedulingType = "Batch (Nonpreemptive)";
        result.schedulingName = "Shortest Job First";
        try {
            //BufferedWriter out = new BufferedWriter(new FileWriter(resultsFile));
            //OutputStream out = new FileOutputStream(resultsFile);

            PrintStream out = new PrintStream(new FileOutputStream(resultsFile));
            setID(processVector);
            Priority.sortBurstTime(processVector);
            Priority.setPriority(processVector);
            Comparator comp = new Priority();
            processVector.sort(comp);
            sProcess process = (sProcess) processVector.elementAt(currentProcess);
            out.println("Process: " + currentProcess + " registered... (" + process.cputime + " "
                    + process.ioblocking + " " + process.cpudone + " " + process.cpudone + ")");
            while (comptime < runtime) {
                if (process.cpudone == process.cputime) {
                    completed++;
                    out.println("Process: " + currentProcess + " completed... (" + process.cputime + " " + process.ioblocking + " " + process.cpudone + " " + process.cpudone + " " + process.priority + ") ");
                    if (completed == size) {
                        result.compuTime = comptime;
                        out.close();
                        return result;
                    }
                    //addProcess(processVector);
                    if (processVector.isEmpty())
                        break;
                    currentProcess++;
                    if (currentProcess >= processVector.size())
                        currentProcess = 0;
                    process = (sProcess) processVector.elementAt(currentProcess);

                    if (currentProcess == previousProcess && process.cpudone >= process.cputime) {
                        out.println("Process: " + currentProcess + " completed... (" + process.cputime + " " + process.ioblocking + " " + process.cpudone + " " + process.cpudone + " " + process.priority +")");
                        break;
                    }
                    out.println("Process: " + currentProcess + " completed... (" + process.cputime + " " + process.ioblocking + " " + process.cpudone + " " + process.cpudone + " " + process.priority + ")");
                }
                if (process.ioblocking == process.ionext) {
                    out.println("Process: " + currentProcess + " completed... (" + process.cputime + " " + process.ioblocking + " " + process.cpudone + " " + process.cpudone + " " + process.priority + ") ");
                    process.numblocked++;
                    process.ionext = 0;
                    previousProcess = currentProcess;
                    processVector = filterProcess(processVector);
                    if (processVector.isEmpty())
                        break;
                    currentProcess++;
                    if (currentProcess >= processVector.size())
                        currentProcess = 0;
                    process =(sProcess)  processVector.elementAt(currentProcess);

                    if (currentProcess == previousProcess && process.cpudone >= process.cputime) {
                        break;
                    }
                    out.println("Process: " + currentProcess + " completed... (" + process.cputime + " " + process.ioblocking + " " + process.cpudone + " " + process.cpudone + " " + process.priority + ") ");

                }
                process.cpudone++;
                if (process.ioblocking > 0) {
                    process.ionext++;
                }
                comptime++;
            }
            out.close();
        } catch (IOException e) { /* Handle exceptions */ }
        result.compuTime = comptime;
        return result;
    }

    private static Vector<sProcess> filterProcess(Vector<sProcess> processVector) {
        Vector<sProcess> vector = new Vector<>();
        for (sProcess process : processVector) {
            //System.out.println(process.priority + " " + process.ioblocking + " " + process.cputime);
            if (process.cputime > process.cpudone)
                vector.add(process);
        }
        return vector;
    }
private static void setID(Vector<sProcess> processVector) {
        for(int i = 0; i < processVector.size(); i++ ) {
            processVector.elementAt(i).processID = i;
        }
}


}