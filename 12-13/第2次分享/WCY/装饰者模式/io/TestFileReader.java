package io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestFileReader {

	public static void main(String[] args) {
		int b = 0;
		FileReader in = null;
		
		try {
			in = new FileReader("/home/wnag/桌面/新建文件");
			while((b = in.read())!=-1) {
				System.out.print((char)b);
			}
		} catch (FileNotFoundException e) {
			System.out.println("找不到文件");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件读取错误");
			e.printStackTrace();
		}

	}

}
