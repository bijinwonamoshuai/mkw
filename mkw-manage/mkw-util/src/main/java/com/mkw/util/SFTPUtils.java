package com.mkw.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class SFTPUtils {

	private static Logger log = LoggerFactory.getLogger(SFTPUtils.class);

	private String host;// 服务器连接ip
	private String username;// 用户名
	private String password;// 密码
	private int port = 22;// 端口号
	private ChannelSftp sftp = null;
	private Session sshSession = null;

	public SFTPUtils(String host, int port, String username, String password) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.port = port;
	}

	public SFTPUtils(String host, String username, String password) {
		this.host = host;
		this.username = username;
		this.password = password;
	}

	/**
	 * 通过SFTP连接服务器
	 */
	public void connect() {
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			sshSession = jsch.getSession(username, host, port);
			if (log.isInfoEnabled()) {
				log.info("Session created.");
			}
			sshSession.setPassword(password);
			sshSession.setTimeout(60 * 1000);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			log.info("--------->>>" + "");
			if (log.isInfoEnabled()) {
				log.info("Session connected.");
			}
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			if (log.isInfoEnabled()) {
				log.info("Opening Channel.");
			}
			sftp = (ChannelSftp) channel;
			if (log.isInfoEnabled()) {
				log.info("Connected to " + host + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接
	 */
	public void disconnect() {
		if (this.sftp != null) {
			if (this.sftp.isConnected()) {
				this.sftp.disconnect();
				if (log.isInfoEnabled()) {
					log.info("sftp is closed already");
				}
			}
		}
		if (this.sshSession != null) {
			if (this.sshSession.isConnected()) {
				this.sshSession.disconnect();
				if (log.isInfoEnabled()) {
					log.info("sshSession is closed already");
				}
			}
		}
	}

	/**
	 * 上传单个文件
	 * 
	 * @param remotePath：远程保存目录
	 * @param remoteFileName：保存文件名
	 * @param localPath：本地上传目录(以路径符号结束)
	 * @param localFileName：上传的文件名
	 * @return
	 */
	public boolean uploadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
		FileInputStream in = null;
		try {
			createDir(remotePath);
			File file = new File((localPath.endsWith("/") ? localPath : localPath + "/") + localFileName);
			in = new FileInputStream(file);
			sftp.put(in, remoteFileName);
			return true;
		} catch (FileNotFoundException e) {
			log.info("未找到该文件");
			e.printStackTrace();
		} catch (SftpException e) {
			log.info("sftp异常");
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.info("上传失败。");
					e.printStackTrace();
					disconnect();
				}
			}
		}
		return false;
	}

	public boolean uploadFile(String remotePath, String remoteFileName, InputStream in) {
		try {
			createDir(remotePath);
			sftp.put(in, remoteFileName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.info("文件异常");
					e.printStackTrace();
					disconnect();
				}
			}
		}
		return false;
	}
	
	/**
	 * 列出目录下的文件
	 * 
	 * @param directory
	 *            要列出的目录
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	@SuppressWarnings("rawtypes")
	public File getFiles(String directory, String zipName) {
		try {
			// 进入指定目录
			sftp.cd(directory);
			// 获取该目录的文件列表
			Vector ls = sftp.ls(directory);
			if (null != ls && 0 < ls.size()) {
				File zipFile = new File(zipName);
				FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
		        ZipOutputStream out = new ZipOutputStream(fileOutputStream);
		        byte[] buf = new byte[1024];
		        
				for (Object object : ls) {
					LsEntry entry = (LsEntry) object;
					String filename = entry.getFilename();
					if (null != filename && !".".equals(filename) && !"..".equals(filename)) {
		                InputStream in = null;
		                try {
		                    in = sftp.get(filename);
			                out.putNextEntry(new ZipEntry(filename));
			                int len;
			                while ((len = in.read(buf)) > 0) {
			                    out.write(buf, 0, len);
			                }
			                out.closeEntry();
			                in.close();
		                } catch (SftpException e) {
		                    log.error("下载失败,资料不存在!");
		                }
		            }
				}
		        out.close();
		        fileOutputStream.close();
		        buf = null;
		        return zipFile;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			disconnect();
		}
		return null;
	}
	
	/**
	 * @Description: 获取文件名
	 *
	 * @author xiaojiayi
	 * @date 2018年7月1日 下午5:41:11
	 * @param directory
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<JSONObject> getFileInfo(String directory) {
		try {
			// 进入指定目录
			sftp.cd(directory);
			// 获取该目录的文件列表
			Vector ls = sftp.ls(directory);
			List<JSONObject> list = new ArrayList<>();
			for (Object object : ls) {
				LsEntry entry = (LsEntry) object;
				String filename = entry.getFilename();
				long size = entry.getAttrs().getSize();
				if (null != filename && !".".equals(filename) && !"..".equals(filename)) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("fileName", filename);
					jsonObject.put("size", size);
					list.add(jsonObject);
				}
			}
			return list;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			disconnect();
		}
		return null;
	}

	/**
	 * 创建目录
	 * 
	 * @param createpath
	 * @return
	 */
	public boolean createDir(String createpath) {
		try {
			if (isDirExist(createpath)) {
				this.sftp.cd(createpath);
				return true;
			}
			String pathArry[] = createpath.split("/");
			StringBuffer filePath = new StringBuffer("/");
			for (String path : pathArry) {
				if (path.equals("")) {
					continue;
				}
				filePath.append(path + "/");
				if (isDirExist(filePath.toString())) {
					sftp.cd(filePath.toString());
				} else {
					// 建立目录
					sftp.mkdir(filePath.toString());
					// 进入并设置为当前目录
					sftp.cd(filePath.toString());
				}

			}
			this.sftp.cd(createpath);
			return true;
		} catch (SftpException e) {
			e.printStackTrace();
			disconnect();
		}
		return false;
	}

	/**
	 * 判断目录是否存在
	 * 
	 * @param directory
	 * @return
	 */
	public boolean isDirExist(String directory) {
		boolean isDirExistFlag = false;
		try {
			SftpATTRS sftpATTRS = sftp.lstat(directory);
			isDirExistFlag = true;
			return sftpATTRS.isDir();
		} catch (Exception e) {
			if (e.getMessage().toLowerCase().equals("no such file")) {
				isDirExistFlag = false;
			}
		}
		return isDirExistFlag;
	}
	
	/**
     * 删除文件
     *
     * @param directory
     *            要删除文件所在目录
     * @param deleteFile
     *            要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/** 测试 */
	public static void main(String[] args) {
		SFTPUtils sftp = null;

		/**
		 * * @param remotePath：远程保存目录
		 * 
		 * @param remoteFileName：保存文件名
		 * @param localPath：本地上传目录(以路径符号结束)
		 * @param localFileName：上传的文件名
		 */
		try {
			sftp = new SFTPUtils("39.104.98.46", 22, "root", "Czs-tech");
			sftp.connect();
			//boolean uploadFile = sftp.uploadFile("/opt/static/video/20180605", "6.jpg", "C://Users/Administrator/Pictures/", "6.jpg");
			//System.out.println(uploadFile);
			sftp.delete("/mnt/czs/static/apk/20180616", "03a1b780b00c4f4f9973eff58cb1e3c3.apk");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sftp.disconnect();
		}
		
	}
}
