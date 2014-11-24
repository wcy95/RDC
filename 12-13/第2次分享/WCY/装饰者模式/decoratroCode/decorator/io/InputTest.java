package design.decorator.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class InputTest {

	public static void main(String[] args) {
		int c;
		InputStream in;
		try {
			in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("/home/wnag/桌面/新建文件")));
			while ((c = in.read()) >= 0){
				System.out.print((char)c);
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
