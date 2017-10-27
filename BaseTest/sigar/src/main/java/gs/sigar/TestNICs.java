package gs.sigar;

/**
 * Created by aharon on 8/11/15.
 */
import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class TestNICs {

    private static Sigar sigar;

    public static synchronized Sigar getSigar() {
        if (sigar == null) {
            Sigar newSigar = new Sigar();
            sigar = newSigar.getPid() != -1 ? newSigar : null;
        }
        return sigar;
    }

    public static void main(String[] args) throws SigarException {
        System.out.println(" SIGAR --- " + getSigar());

        for (String intr : sigar.getNetInterfaceList()) {
            System.out.println(" SIGAR INTR --- " + intr);
        }
        int numCpus = 0;
        for ( CpuInfo cpuInfo : sigar.getCpuInfoList()) {
            numCpus++;
            System.out.println("============================================");
            System.out.println(" SIGAR cpuInfo str --- " + cpuInfo.toString());
            System.out.println(" SIGAR cpuInfo getCoresPerSocket --- Number of CPU cores per CPU socket " + cpuInfo.getCoresPerSocket());
            System.out.println(" SIGAR cpuInfo getTotalCores --- Total CPU cores (logical) " + cpuInfo.getTotalCores());
            System.out.println(" SIGAR cpuInfo getTotalSockets --- Get the Total CPU sockets (physical) " + cpuInfo.getTotalSockets());

        }

        System.out.println("Number Of cpus:" + numCpus);

//        for ( Cpu cpu : sigar.getCpuList()) {
//            System.out.println(cpu.toString());
//        }

    }

}