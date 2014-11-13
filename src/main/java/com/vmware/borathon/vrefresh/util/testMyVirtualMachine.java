package com.vmware.borathon.vrefresh.util;

import java.net.URL;

import com.vmware.borathon.vrefresh.entity.Task;


public class testMyVirtualMachine {

	public static void main(String[] args) throws Exception {

		//https://prme-vc2.eng.vmware.com:9443/vsphere-client/
		//String hostname = "https://esxi.example.com/sdk";
		
	    String hostname = "https://10.20.141.195/sdk";
	    String username = "root";
	    String password = "ca$hc0w";
	    Task task = new Task();
	    task.setScript("ls /tmp");
	    
	    
	    MyVirtualMachine virtualMachine = new MyVirtualMachine(new URL(hostname), "Nitro-AppD-2", username, password);
	    try {
	        //virtualMachine.revertToSnapshot("Baseline");
	        //System.out.println("Virtual machine reverted to snapshot '" + "Baseline" + "'");
	        virtualMachine.start();
	        System.out.println("Virtual machine started");
	        virtualMachine.waitForGuest();
	        System.out.println("Guest started");
	        String ipAddress = virtualMachine.getGuestIpAddress();
	        System.out.println("Ip Address is:" + ipAddress);
	        virtualMachine.shutdown();
	        System.out.println("Guest shutdown");
	        //DateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmm");
	        //String nowAsString = df.format(new Date());
	        //virtualMachine.takeSnapshot("snapshot " + nowAsString);
	        //System.out.println("Created snapshot");
	        //virtualMachine.start();
	        //System.out.println("Started virtual machine");
	        //virtualMachine.powerOff();
	        //System.out.println("Powered off virtual machine");
	    }
	    finally {
	        virtualMachine.disconnect();
	    }
	}
}
