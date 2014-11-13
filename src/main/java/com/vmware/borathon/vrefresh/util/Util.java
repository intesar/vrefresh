/* Copyright VMware, Inc. All rights reserved. -- VMware Confidential */

package com.vmware.borathon.vrefresh.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.IOptionName;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;

import org.springframework.beans.factory.annotation.Autowired;

import com.vmware.borathon.vrefresh.entity.Host;
import com.vmware.borathon.vrefresh.entity.Task;
import com.vmware.borathon.vrefresh.repository.TaskRepository;

public class Util {
	
	@Autowired
	TaskRepository taskRepo;

	public static String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	public static void runLinuxCommand(Host host, Task scriptTask)
			throws Exception {

		String hostname = "https://10.20.141.195/sdk";
		String username = "root";
		String password = "ca$hc0w";
		MyVirtualMachine virtualMachine = new MyVirtualMachine(
				new URL(hostname), "Nitro-AppD-2", username, password);

		SSHExec ssh = null;
		try {

			virtualMachine.start();
			System.out.println("Virtual machine started");
			virtualMachine.waitForGuest();
			System.out.println("Guest started");
			String ipAddress = virtualMachine.getGuestIpAddress();
			System.out.println("Ip Address is:" + ipAddress);

			SSHExec.setOption(IOptionName.HALT_ON_FAILURE, true);
			SSHExec.setOption(IOptionName.SSH_PORT_NUMBER, 22);
			SSHExec.setOption(IOptionName.TIMEOUT, 36000l);
			SSHExec.showEnvConfig();

			ConnBean cb = new ConnBean(ipAddress, "root",
					"ca$hc0w");
			ssh = SSHExec.getInstance(cb);
			String scriptContent = scriptTask.getScript();
			CustomTask task = new ExecCommand(scriptContent);
			ssh.connect();

			Result taskResult = ssh.exec(task);
			scriptTask.setResult(taskResult.sysout);
			scriptTask.setTaskStatus("Return code: " + taskResult.rc
					+ ", error msg: " + taskResult.error_msg);

			virtualMachine.shutdown();
			System.out.println("Guest shutdown");

		} catch (TaskExecFailException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			ssh.disconnect();
			virtualMachine.disconnect();
		}
	}

	public static void main(String[] args) throws Exception {
		Host host = new Host();
		Task task = new Task();
		task.setScript("ls /tmp");
		runLinuxCommand(host, task);
	}
}
