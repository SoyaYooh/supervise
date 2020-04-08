package com.linkcheers.supervise.freemarker;

import com.linkcheers.supervise.freemarker.db.Condition;
import com.linkcheers.supervise.freemarker.db.Convert;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * @author bxk
 */
public class Generator {

	private static final String TEMPLATES_PATH = "templates";
	private static final String TARGET_PATH = "/templates/output/";
	private static final String TARGET_PATH_RESOURCE = "/templates/output/resource/";
	private static final String JAVA_SUFFIX = ".java";
	private static final String XML_SUFFIX = ".xml";
	private static final String MAPPER = "mybatis/mapper";
	private static final String SERVICE = "service";
	private static final String CONTROLLER = "controller";
	private static final String REPOSITORY = "repository";
	private static final String[] SERVICE_IMPL = {"serviceImpl", "impl"};
	private static final String[] ENTITY = {"dto", "entity", "domain"};
	private static final String MAPPER_XML = "mapper-xml";
	private String ROOT_PATH = "";

	public Generator() throws IOException {
		ROOT_PATH = ResourceUtils.getURL("classpath:").getPath();
	}

	public void generate(Map<String, Object> root, String templateName) throws IOException {
		String targetPath = TARGET_PATH;
		FileWriter fw = null;
		BufferedWriter bw = null;
		File outputFile;
		try {
			Condition condition = (Condition) root.get("entity");
			File uploadPath = new File(ROOT_PATH);
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
			cfg.setDirectoryForTemplateLoading(new File(uploadPath.getAbsolutePath(), TEMPLATES_PATH));
			cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_28));
			freemarker.template.Template temp = cfg.getTemplate(templateName);
			String packageName = condition.getPackageName().replace(".", "/");
			String fileName = condition.getTable().getEntityName();
			templateName = Convert.subString(templateName, ".");
			if (StringUtils.isNotBlank(condition.getIsFilePath()) && "Y".equalsIgnoreCase(condition.getIsFilePath())) {
				if (SERVICE.equals(templateName)) {
					fileName = "I" + fileName + Convert.initCap(templateName) + JAVA_SUFFIX;
				} else if (isContains(SERVICE_IMPL, templateName)) {
					fileName = SERVICE_IMPL[1] + "/" + fileName + Convert.initCap(templateName) + JAVA_SUFFIX;
					templateName = SERVICE;
				} else if (isContains(ENTITY, templateName)) {
					fileName = fileName + JAVA_SUFFIX;
				} else if (MAPPER_XML.equals(templateName)) {
					templateName = Convert.subString(templateName, "-");
					fileName = fileName + Convert.initCap(templateName) + XML_SUFFIX;
					targetPath = TARGET_PATH_RESOURCE;
					packageName = "";
				} else {
					fileName = fileName + Convert.initCap(templateName) + JAVA_SUFFIX;
				}
				outputFile = new File(ROOT_PATH + targetPath + packageName + "/" + templateName + "/" + fileName);
			} else {
				if (SERVICE.equals(templateName)) {
					fileName = "I" + fileName + Convert.initCap(templateName) + JAVA_SUFFIX;
				} else if (SERVICE_IMPL.equals(templateName)) {
					fileName = fileName + Convert.initCap(SERVICE_IMPL[1]) + JAVA_SUFFIX;
				} else if (MAPPER.equals(templateName)) {
					fileName = fileName + Convert.initCap(templateName) + JAVA_SUFFIX;
				} else if (CONTROLLER.equals(templateName)) {
					fileName = fileName + Convert.initCap(templateName) + JAVA_SUFFIX;
				} else if (REPOSITORY.equals(templateName)) {
					fileName = fileName + Convert.initCap(templateName) + JAVA_SUFFIX;
				} else if (MAPPER_XML.equals(templateName)) {
					templateName = Convert.subString(templateName, "-");
					fileName = fileName + Convert.initCap(templateName) + XML_SUFFIX;
				} else {
					fileName = fileName + JAVA_SUFFIX;
				}
				outputFile = new File(ROOT_PATH + targetPath + "/" + fileName);
			}
			if (!outputFile.exists()) {
				outputFile.getParentFile().mkdirs();//创建父级文件路径
				outputFile.createNewFile();//创建文件
			}
			fw = new FileWriter(outputFile);
			bw = new BufferedWriter(fw);
			temp.process(root, bw);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bw.flush();
			fw.close();
		}
	}

	private boolean isContains(String[] param, String target) {
		return Arrays.asList(param).contains(target);
	}

	public void traverseFolder(File dir, Map<String, Object> root) throws Exception {
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				System.out.println(file.getName());
				generate(root, file.getName());
			}
		}
	}

	public void loadTemplate(Map<String, Object> root) throws Exception {
		String templatePath = ROOT_PATH + TEMPLATES_PATH;
		File templateFolder = new File(templatePath);
		traverseFolder(templateFolder, root);
	}

}
