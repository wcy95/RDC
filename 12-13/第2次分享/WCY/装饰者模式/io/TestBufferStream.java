package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestBufferStream {

	public static void main(String[] args) {
		try {
//			int b = 0;
			BufferedWriter bw = new BufferedWriter(new FileWriter("/home/wnag/桌面/新建文件"));
			String random;
			
			for(int i = 0; i <= 100; i++) {
				random = String.valueOf(Math.random());
				bw.write(random);
				bw.newLine();
			}
			
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
