package ar.com.dccsoft.srytd.utils.ftp;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.services.AppPropertyService;
import ar.com.dccsoft.srytd.services.AppPropertyService.FTPConfig;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPConnector implements FTPConnector {

	private static final Logger logger = LoggerFactory.getLogger(SFTPConnector.class);
	private AppPropertyService propertyService = new AppPropertyService();

	@Override
	public void transfer(String data, String fileName) {
		tryAndInform("Error transferring file to FTP Server", () -> {
			FTPConfig conf = propertyService.getFTPConfig();

			Session session = null;
			Channel channel = null;
			ChannelSftp channelSftp = null;
			logger.info("Preparing the host information for sftp.");
			try {
				session = preapareSession(conf);
				channel = connect(session);
				channelSftp = (ChannelSftp) channel;
				transfer(data, fileName, channelSftp);
			} finally {
				if(channelSftp != null && channelSftp.isConnected()) {
					channelSftp.exit();
					logger.info("SFTP Channel exited.");
					channel.disconnect();
					logger.info("Channel disconnected.");
				}
				if(session != null && session.isConnected()) {
					session.disconnect();
					logger.info("SFTP Session disconnected.");
				}
			}
			return null;
		});
	}

	public void transfer(String data, String fileName, ChannelSftp channelSftp) {
		try {
			channelSftp.put(new ByteArrayInputStream(data.getBytes()), fileName);
			logger.info("File transfered successfully to host.");
		} catch (SftpException ex) {
			throw new RuntimeException("Error transferring file.", ex);
		}
	}

	public Channel connect(Session session) {
		try {
			session.connect();
			logger.info("Host connected.");

			Channel channel = session.openChannel("sftp");
			channel.connect();
			logger.info("SFTP channel opened and connected.");
			return channel;
		} catch (JSchException ex) {
			throw new RuntimeException("Error connecting to SFTP server.", ex);
		}

	}

	public Session preapareSession(FTPConfig conf) {
		Session session;
		try {
			session = new JSch().getSession(conf.getUsername(), conf.getServer(), conf.getPort());
		} catch (JSchException ex) {
			throw new RuntimeException("Error creating SFTP session.", ex);
		}
		session.setPassword(conf.getPassword());

		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		return session;
	}

}
