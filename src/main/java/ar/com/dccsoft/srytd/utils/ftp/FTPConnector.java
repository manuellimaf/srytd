package ar.com.dccsoft.srytd.utils.ftp;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.services.AppPropertyService;
import ar.com.dccsoft.srytd.services.AppPropertyService.FTPConfig;

public class FTPConnector {

	private static final Logger logger = LoggerFactory.getLogger(FTPConnector.class);
	private AppPropertyService propertyService = new AppPropertyService();

	public void transfer(String data, String fileName) {
		FTPClient ftp = null;
		try {
			ftp = doTransfer(IOUtils.toInputStream(data), fileName);
		} finally {
			if (ftp != null) {
				if (ftp.isConnected()) {
					try {
						ftp.disconnect();
					} catch (IOException ioe) {
						logger.warn(String.format("Error disconnecting from FTP server: %s", ioe.getMessage()));
					}
				}
			}
		}

	}

	private FTPClient doTransfer(InputStream file, String fileName) {
		return tryAndInform("Error transferring file to FTP Server", () -> {
			FTPConfig conf = propertyService.getFTPConfig();
			FTPClient ftp = connect(conf.getServer(), conf.getPort());
			login(ftp, conf.getUsername(), conf.getPassword());
			sendFile(ftp, file, fileName);
			logout(ftp);
			return ftp;
		});
	}

	private FTPClient connect(String server, Integer port) {
		FTPClient ftpClient = new FTPClient();
		try {
			logger.info(String.format("Connecting to FTP server at %s:%d", server, port));
			ftpClient.connect(server, port);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		int reply = ftpClient.getReplyCode();
		logger.info(String.format("FTP server connection response: %d", reply));

		if (!FTPReply.isPositiveCompletion(reply)) {
			throw new RuntimeException("FTP server refused connection.");
		}

		logger.info(String.format("Connected to %s:%d", server, port));
		return ftpClient;
	}

	private void login(FTPClient ftp, String username, String password) {
		logger.info(String.format("About to login to FTP Server (username: '%s')", username));
		try {
			if (!ftp.login(username, password)) {
				throw new RuntimeException(String.format("Access denied for '%s'", username));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		logger.info("Successfully loged in");
	}

	private void logout(FTPClient ftp) {
		try {
			ftp.logout();
		} catch (IOException e) {
			logger.error("Error tring to logout from FTP server", e);
		}
	}

	private void sendFile(FTPClient ftp, InputStream is, String fileName) {
		logger.info(String.format("Starting to send file %s", fileName));
		try {
			ftp.storeFile(fileName, is);
		} catch (IOException e) {
			throw new RuntimeException("Error transferring file", e);
		}
		logger.info("File transfer completed");
	}

}
