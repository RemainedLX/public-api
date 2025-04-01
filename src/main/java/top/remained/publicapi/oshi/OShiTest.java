package top.remained.publicapi.oshi;

import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;

import java.util.Arrays;
import java.util.List;

/**
 * @author lx
 * @date 2025/4/1
 * @description
 */
public class OShiTest {
    public static void main(String[] args) {
        getSystemInfo();
        getHardwareInfo();
    }
    public static void getSystemInfo() {
        // 创建 SystemInfo 对象
        SystemInfo systemInfo = new SystemInfo();
        // 获取操作系统信息
        OperatingSystem os = systemInfo.getOperatingSystem();
        System.out.println("操作系统名称: " + os.getFamily());
        System.out.println("操作系统版本: " + os.getVersionInfo().getVersion());
        System.out.println("操作系统位数: " + os.getBitness());
        System.out.println("操作系统启动时间: " + os.getSystemBootTime());
        System.out.println("操作系统启动时间: " + os.getSystemUptime());
        System.out.println("进程总数: " + os.getProcessCount());
        System.out.println("线程总数: " + os.getThreadCount());
        System.out.println("当前进程ID: " + os.getProcessId());

    }
    public static void getHardwareInfo() {
        // 创建 SystemInfo 对象
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        System.out.println("CPU信息:"+hardware.getProcessor());
        CentralProcessor processor = hardware.getProcessor();
        System.out.println("CPU名称:"+processor.getProcessorIdentifier().getName());
        System.out.println("CPU核心数:"+processor.getPhysicalProcessorCount());
        System.out.println("CPU最大频率:"+processor.getMaxFreq());
        System.out.println("CPU当前频率:"+ Arrays.toString(processor.getCurrentFreq()));
        System.out.println("CPU使用率:"+processor.getSystemCpuLoad(1000));
        // 1s内的负载
        System.out.println("1s内CPU负载:"+ Arrays.toString(processor.getProcessorCpuLoad(1000)));
        System.out.println("内存信息:"+hardware.getMemory());
        GlobalMemory memory = hardware.getMemory();
        System.out.println("总内存:"+memory.getTotal());
        System.out.println("剩余内存:"+memory.getAvailable());

        System.out.println("硬盘信息:"+hardware.getDiskStores());
        List<HWDiskStore> diskStores = hardware.getDiskStores();
        for (HWDiskStore diskStore : diskStores) {
            System.out.println("硬盘名称:"+diskStore.getName());
            System.out.println("硬盘型号:"+diskStore.getModel());
            System.out.println("硬盘序列号:"+diskStore.getSerial());
            System.out.println("硬盘大小:"+diskStore.getSize());
            System.out.println("硬盘读写次数:"+diskStore.getReads()+","+diskStore.getWrites());
            System.out.println("硬盘读写字节数:"+diskStore.getReadBytes()+","+diskStore.getWriteBytes());
            System.out.println("硬盘当前队列长度:"+diskStore.getCurrentQueueLength());
            System.out.println("硬盘传输时间:"+diskStore.getTransferTime());
            System.out.println("硬盘分区信息:"+diskStore.getPartitions());
            System.out.println("硬盘时间戳:"+diskStore.getTimeStamp());
            System.out.println("硬盘更新属性:"+diskStore.updateAttributes());
        }

        System.out.println("网卡信息:"+hardware.getNetworkIFs());
        List<NetworkIF> networkIFs = hardware.getNetworkIFs();
        for (NetworkIF networkIF : networkIFs) {
            System.out.println("网卡名称:"+networkIF.getName());
            System.out.println("网卡mac地址:"+networkIF.getMacaddr());
            System.out.println("网卡ip地址:"+networkIF.getIPv4addr());
            System.out.println("网卡流量:"+networkIF.getBytesRecv()+","+networkIF.getBytesSent());
            System.out.println("网卡时间戳:"+networkIF.getTimeStamp());
        }

        System.out.println("显示器信息:"+hardware.getDisplays());
        System.out.println("声卡信息:"+hardware.getSoundCards());
        System.out.println("显卡信息:"+hardware.getGraphicsCards());
        System.out.println("USB设备信息:"+hardware.getUsbDevices(true));
        System.out.println("电源信息:"+hardware.getPowerSources());
        System.out.println("传感器信息:"+hardware.getSensors());
    }
}