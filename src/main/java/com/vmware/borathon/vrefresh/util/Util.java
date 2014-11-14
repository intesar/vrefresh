/* Copyright VMware, Inc. All rights reserved. -- VMware Confidential */

package com.vmware.borathon.vrefresh.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

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
import com.vmware.borathon.vrefresh.entity.VMTask;
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

	public static void runLinuxCommand(VMTask vmTask) throws Exception {

		// set the start-time, end-time, error, result, status (as complete,
		// processing)
		// save the task

		vmTask.setStartTime(new Date());
		Host host = vmTask.getHost();
		Task scriptTask = vmTask.getTask();
		String logMsg;

		String hostname = host.getHost();
		String username = host.getUsername();
		String password = host.getPassword();

		String vCenterHostName = vmTask.getHost().getVcenter().getHost();
		String vCenterUserName = vmTask.getHost().getVcenter().getUsername();
		String vCenterPassword = vmTask.getHost().getVcenter().getPassword();

		/*
		 * String hostname = "https://10.20.141.195/sdk"; String username =
		 * "root"; String password = "ca$hc0w";
		 */
		// MyVirtualMachine virtualMachine = new MyVirtualMachine(
		// new URL(hostname), "Nitro-AppD-2", username, password);

		MyVirtualMachine virtualMachine = new MyVirtualMachine(new URL(
				vCenterHostName), hostname, vCenterUserName, vCenterPassword);

		SSHExec ssh = null;
		try {

			virtualMachine.start();
			logMsg = "Virtual machine started";
			System.out.println(logMsg);
			virtualMachine.waitForGuest();
			logMsg += "\nGuest started";
			System.out.println("Guest started");
			String ipAddress = virtualMachine.getGuestIpAddress();
			logMsg += "\nIp Address is:" + ipAddress;
			System.out.println("Ip Address is:" + ipAddress);

			SSHExec.setOption(IOptionName.HALT_ON_FAILURE, true);
			SSHExec.setOption(IOptionName.SSH_PORT_NUMBER, 22);
			SSHExec.setOption(IOptionName.TIMEOUT, 36000l);
			SSHExec.showEnvConfig();

			ConnBean cb = new ConnBean(ipAddress, username, password);
			ssh = SSHExec.getInstance(cb);
			String scriptContent = scriptTask.getScript();
			CustomTask task = new ExecCommand(scriptContent);
			ssh.connect();

			Result taskResult = ssh.exec(task);
			logMsg += "\n" + taskResult.sysout;
			logMsg += "\nReturn code: " + taskResult.rc + ", error msg: "
					+ taskResult.error_msg;

			vmTask.setResult(taskResult.sysout);
			vmTask.setStatus("Return code: " + taskResult.rc
					+ ", error msg: " + taskResult.error_msg);

			virtualMachine.shutdown();
			logMsg += "\nGuest shutdown";
			vmTask.setLog(logMsg);
			System.out.println("Guest shutdown");

		} catch (TaskExecFailException e) {
			vmTask.setError(true);
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			vmTask.setError(true);
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			ssh.disconnect();
			virtualMachine.disconnect();
		}
		vmTask.setEndTime(new Date());
	}

	public static void main(String[] args) throws Exception {
		Host host = new Host();
		Task task = new Task();
		task.setScript("ls /tmp");
		// runLinuxCommand(host, task);
	}
}
