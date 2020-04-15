package com.linkcheers.supervise.file.util;

import com.linkcheers.supervise.file.dto.BatchAttach;
import com.linkcheers.supervise.file.service.IBatchAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@Component
public class AttachUtil {
   @Autowired
   private static IBatchAttachService batchAttachService;

	public static void save(String fileName,
							String localPath,
							String ftpFileName,
							String ftpPath,
							String moduleId,
							String moduleType,
							String fileDesc,
							File file){
       try {
		   BatchAttach batchAttach=new BatchAttach();
		   batchAttach.setLocalPath(localPath);
		   batchAttach.setUpFileName(fileName);
		   batchAttach.setFtpFileName(ftpFileName);
		   batchAttach.setFtpPath(ftpPath);
		   batchAttach.setModuleId(moduleId);
		   batchAttach.setModuleType(moduleType);
		   String fileType = file.getName().substring(0,file.getName().lastIndexOf("."));
		   batchAttach.setAttachType(fileType);
		   batchAttach.setAttachDesc(fileDesc);
		   batchAttach.setSize( FileClient.getFileSize(file.length()));
		   batchAttachService.save(batchAttach);
	   }catch (Exception e){
       	e.printStackTrace();
	   }
	}
	public static void save(String fileName,
							String localPath,
							String ftpFileName,
							String ftpPath,
							String moduleId,
							String moduleType,
							String fileDesc,
							MultipartFile file){
		try {
			BatchAttach batchAttach=new BatchAttach();
			batchAttach.setLocalPath(localPath);
			batchAttach.setUpFileName(fileName);
			batchAttach.setFtpFileName(ftpFileName);
			batchAttach.setFtpPath(ftpPath);
			batchAttach.setModuleId(moduleId);
			batchAttach.setModuleType(moduleType);
			batchAttach.setAttachType(file.getContentType());
			batchAttach.setAttachDesc(fileDesc);
			batchAttach.setSize( FileClient.getFileSize(file.getSize()));
			batchAttachService.save(batchAttach);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
