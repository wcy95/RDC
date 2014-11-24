package design.decorator.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IoOut {

	public static void main(String[] args) {
		
		byte buffer [] = new byte[] {'1','a'};
		String string = "dddddd";
		buffer = string.getBytes();
		String file = "/home/wnag/桌面/新建文件";
		try {
			OutputStream out = new DataOutputStream(new FileOutputStream(file));
			InputStream in = new DataInputStream(new FileInputStream(file));
			out.write(buffer);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
