package com.callor.iolist.exec;

import java.io.FileNotFoundException;

import com.callor.iolist.service.IolistService;
import com.callor.iolist.service.impl.IolistServiceImplV1;

public class IolistExecA {
	public static void main(String[] args) {

		IolistService iolistService = null;
		String iolistDataFile = "src/com/callor/iolist/iolist.txt";
		try {
			iolistService = new IolistServiceImplV1(iolistDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		iolistService.loadIolistData();
		iolistService.printIolistList();
	}
}
