package com.itany.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.itany.entity.Cup;

/**
 * 对象流的读写
 *
 * @author Thinkpad 2018年3月2日
 */
public class ObjectStream {

	@Test
	public void testWriteObj() {
		FileOutputStream outputStream = null;
		ObjectOutputStream objStream = null;
		try {
			File file = new File("e://obj.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			outputStream = new FileOutputStream(file);
			objStream = new ObjectOutputStream(outputStream);
			Cup cup = new Cup();
			cup.setName("水杯");
			cup.setPrice(5.0);
			objStream.writeObject(cup);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
				objStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Test
	public void testReadObj() {

		ObjectInputStream obj = null;
		try {
			// FileInputStream in = new FileInputStream("a/obj.txt");
			Resource rc = new FileSystemResource("e://obj.txt");
			File file = rc.getFile();
			FileInputStream in = new FileInputStream(file);
			obj = new ObjectInputStream(in);
			try {
				Cup cup = (Cup) obj.readObject();
				System.out.println(cup.getName());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (obj != null) {
					obj.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
