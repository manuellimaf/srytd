package ar.com.dccsoft.srytd.utils.ftp;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.services.AppPropertyService;
import ar.com.dccsoft.srytd.services.AppPropertyService.FTPConfig;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SFTPConnector implements FTPConnector {

	private static final Logger logger = LoggerFactory.getLogger(SFTPConnector.class);
	private AppPropertyService propertyService = new AppPropertyService();

	@Override
	public void transfer(String data, String fileName) {
		FTPConfig conf = propertyService.getFTPConfig();

		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		logger.info("Preparing the host information for sftp.");
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(conf.getUsername(), conf.getServer(), conf.getPort());
			session.setPassword(conf.getPassword());
			
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			
			session.connect();
			logger.info("Host connected.");
			
			channel = session.openChannel("sftp");
			channel.connect();
			logger.info("SFTP channel opened and connected.");
			
			channelSftp = (ChannelSftp) channel;
			channelSftp.put(new ByteArrayInputStream(data.getBytes()), fileName);
			logger.info("File transfered successfully to host.");

		} catch (Exception ex) {
			logger.error("Error transerring file.");
		} finally {
			channelSftp.exit();
			logger.info("SFTP Channel exited.");
			channel.disconnect();
			logger.info("Channel disconnected.");
			session.disconnect();
			logger.info("SFTP Session disconnected.");
		}

	}

}
