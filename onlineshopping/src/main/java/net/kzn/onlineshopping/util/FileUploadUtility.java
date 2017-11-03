package net.kzn.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	
	private static final String ABS_PATH = "E:\\project\\spring\\online_shopping\\onlineshopping\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH = "";
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		
		
		//Get thr real Path
		
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		
		//make sure directory alreay exist
		//please create
		if(!new File(ABS_PATH).exists()) {
			//create absolute path
			new File(ABS_PATH).mkdirs();
		}
		
		if(!new File(REAL_PATH).exists()) {
			//create absolute path
			new File(REAL_PATH).mkdirs();
		}
		
		//After checking directory need to transfer multpart
		
		try {
			// server upload
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			// project directory upload
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
			
		}catch (IOException e) {
			// TODO: handle exception
		}
		
	}

}
