package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestCopy {
	
	public static void main(String[] args) {
		int b = 0;
		FileInputStream in = null;
		FileOutputStream out = null;
		
		try {
			in = new FileInputStream("/home/wnag/桌面/新建文件");
			out = new FileOutputStream("/home/wnag/桌面/新建文件2");
			
			while((b=in.read())!=-1) {
				out.write(b);
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有找到文件");
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("文件传输错误");
			System.exit(-1);
		} finally {
			try{
				if(in!=null)
					in.close();
				if(out!=null)
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
		}
		
	}

}
