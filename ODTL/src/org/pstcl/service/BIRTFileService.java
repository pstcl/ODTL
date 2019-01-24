package org.pstcl.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import org.pstcl.birt.spring.odtl.BirtViewOilReport;
import org.springframework.beans.factory.annotation.Autowired;

public class BIRTFileService {

	@Autowired
	//BirtViewOilReport birtViewOilReport;

	public static String urlToDownload="http://pstcl.org/OO-CS/2017_O/2018060616073188.pdf";
	public static String destinationFolder="D:\\TEST_FILES_DOWNLOAD\\";
	public static String defaultFileName="test";

	public void download() throws IOException {


		URL url=new URL(urlToDownload);
		File file=new File(destinationFolder+defaultFileName);
		ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());


		FileOutputStream fileOutputStream = new FileOutputStream(file);
		FileChannel fileChannel = fileOutputStream.getChannel();
		fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
	}

}
