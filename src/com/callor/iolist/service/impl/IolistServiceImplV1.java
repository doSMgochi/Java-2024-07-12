package com.callor.iolist.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.iolist.models.IolistVO;
import com.callor.iolist.service.IolistService;
import com.callor.iolist.utils.Contract;
import com.callor.iolist.utils.Line;

public class IolistServiceImplV1 implements IolistService {

	protected String[] iolistTitle;
	protected final String iolistDataFile;
	protected final List<IolistVO> iolistList;
	protected final Scanner fileReader;

	public IolistServiceImplV1(String iolistDataFile) throws FileNotFoundException {
		super();
		this.iolistDataFile = iolistDataFile;
		this.iolistList = new ArrayList<>();
		InputStream in = new FileInputStream(this.iolistDataFile);
		this.fileReader = new Scanner(in);
	}

	@Override
	public void loadIolistData() {
		iolistTitle = fileReader.nextLine().split(",");
		while (fileReader.hasNext()) {
			String line = fileReader.nextLine();
			String[] lines = line.split(",");
			IolistVO vo = new IolistVO();

			vo.date = Date.valueOf(lines[Contract.ioList.date]);
			vo.time = lines[Contract.ioList.time];
			vo.type = Integer.valueOf(lines[Contract.ioList.type]);
			vo.name = lines[Contract.ioList.name];
			vo.quantity = Integer.valueOf(lines[Contract.ioList.quantity]);
			vo.price = Integer.valueOf(lines[Contract.ioList.price]);

			iolistList.add(vo);
		}
	}

	@Override
	public void printIolistList() {
		int count = 0;
		int buyAll = 0;
		int sellAll = 0;
		System.out.println(Line.dLine(100));
		System.out.println("거래일자\t거래구분\t상품이름\t매입금액\t매출금액");
		System.out.println(Line.sLine(100));
		for (IolistVO vo : iolistList) {
			System.out.printf("%10s\t", String.valueOf(vo.date));
			System.out.printf("%4s\t", vo.type);
			System.out.printf("%15s\t", vo.name);

			if (vo.type == 1) {
				int buy = vo.price * vo.quantity;
				System.out.printf("%10d\t", buy);
				System.out.printf("%10s\t\n", " ");
				buyAll += buy;
			} else {
				int sell = vo.price * vo.quantity;
				System.out.printf("%10s\t", " ");
				System.out.printf("%10d\t\n", sell);
				sellAll += sell;
			}
			count++;
		}
		System.out.println(Line.sLine(100));
		System.out.print("거래건수");
		System.out.printf("%15s\t", count + "건");
		System.out.printf("%15s\t", " ");
		System.out.printf("%10d\t", buyAll);
		System.out.printf("%10d\t", sellAll);
		System.out.println("\n" + Line.dLine(100));
	}

}
