package os_lab2;

public class sProcess {
    public int cputime;
    public int ioblocking;
    public int cpudone;
    public int ionext;
    public int numblocked;
    public int priority;
    public int processID;
    public sProcess(int cputime, int ioblocking, int cpudone, int ionext, int numblocked, int priority, int processID) {
        this.cputime = cputime;
        this.ioblocking = ioblocking;
        this.cpudone = cpudone;
        this.ionext = ionext;
        this.numblocked = numblocked;
        this.priority = priority;
        this.processID = processID;
    }
}