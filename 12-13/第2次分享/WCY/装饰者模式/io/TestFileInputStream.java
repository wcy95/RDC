package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestFileInputStream {

	public static void main(String[] args) {
		int b = 0;
		FileInputStream in = null ;
		
		try {
			String path = "/home/wnag/桌面/新建文件";
//			System.out.println(path);
			in = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			System.out.println("文件未找到！");
//			e.printStackTrace();
			System.exit(-1);
		}
		
		try {
			long num = 0;
			while ((b=in.read())!=-1) {
				System.out.print((char)b);
				num++;
			}
			in.close();
			System.out.println();
			System.out.println("共读取了" + num + "个字符");
		} catch (IOException e) {
			System.out.println("文件传输出错");
//			e.printStackTrace();
			System.exit(-1);
		}

	}

}
