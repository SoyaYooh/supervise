package com.linkcheers.supervise.file.util;

import com.linkcheers.supervise.file.dto.BatchAttach;
import com.linkcheers.supervise.file.service.IBatchAttachService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author Soya
 */
public class FileClient {
	/**
	 * 本地上传文件
	 *
	 * @param  file  文件
	 * @param  localPath 本地路径
	 * @param  fileName 文件名称
	 * @throws Exception
	 */
	public static String uploadFile(MultipartFile file, String localPath, String fileName) throws IOException {
		String result = "";
		if (!file.isEmpty()) {
			if(StringUtils.isBlank(fileName)){
				 fileName = rename(file.getOriginalFilename());
			}
			File savedFile = new File(localPath, fileName);
			boolean isCreateSuccess;
			try {
				isCreateSuccess = savedFile.createNewFile();
				if (isCreateSuccess) {
					file.transferTo(savedFile);
				} else {
					result = "请修改文件名,重新上传";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			result = "文件不能为空";
		}
		return result;
	}


	public static String getFileSize(long size) {
		if (size < 1024) {
			return String.valueOf(size) + "B";
		} else {
			size = size / 1024;
		}
		if (size < 1024) {
			return String.valueOf(size) + "KB";
		} else {
			size = size / 1024;
		}
		if (size < 1024) {
			size = size * 100;
			return String.valueOf((size / 100)) + "."
					+ String.valueOf((size % 100)) + "MB";
		} else {
			size = size * 100 / 1024;
			return String.valueOf((size / 100)) + "."
					+ String.valueOf((size % 100)) + "GB";
		}
	}

	/**
	 * 判断文件是否存在
	 *
	 * @param filePath
	 * @throws Exception
	 */
	public static boolean exists(String filePath) {
		File targetFile = new File(filePath);
		return targetFile.exists();
	}

	/**
	 * 删除文件
	 *
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static String rename(String fileName) {
		return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	public static String rename(MultipartFile file) {
		return UUID.randomUUID() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
	}

	public static void download(HttpServletResponse res, String path, String fileName) {
		downloadLocal(res, path, fileName);
	}

	/**
	 * 下载本地文件
	 *
	 * @param response
	 * @param path
	 * @param fileName
	 */
	public static void downloadLocal(HttpServletResponse response, String path, String fileName) {
		FileInputStream fileIn = null;
		ServletOutputStream out = null;
		try {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			File file;
			String filePathString = path + fileName;
			file = new File(filePathString);
			fileIn = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] outputByte = new byte[1024];
			int readTmp = 0;
			while ((readTmp = fileIn.read(outputByte)) != -1) {
				out.write(outputByte, 0, readTmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileIn.close();
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
