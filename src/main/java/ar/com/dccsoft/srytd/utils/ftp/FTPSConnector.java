package ar.com.dccsoft.srytd.utils.ftp;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static java.lang.String.format;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.services.AppPropertyService;
import ar.com.dccsoft.srytd.services.AppPropertyService.FTPConfig;

public class FTPSConnector implements FTPConnector {

	private static final Logger logger = LoggerFactory.getLogger(FTPSConnector.class);
	private AppPropertyService propertyService = new AppPropertyService();

	@Override
	public void transfer(String data, String fileName) {
		FTPSClient ftp = null;
		try {
			ftp = doTransfer(IOUtils.toInputStream(data), fileName);
		} finally {
			if (ftp != null) {
				if (ftp.isConnected()) {
					try {
						ftp.disconnect();
					} catch (IOException ioe) {
						logger.warn(format("Error disconnecting from FTP server: %s", ioe.getMessage()));
					}
				}
			}
		}
	}

	private FTPSClient doTransfer(InputStream file, String fileName) {
		return tryAndInform("Error transferring file to FTP Server", () -> {
			FTPConfig conf = propertyService.getFTPConfig();
			FTPSClient ftp = connect(conf.getServer(), conf.getPort());
			login(ftp, conf.getUsername(), conf.getPassword());
			sendFile(ftp, file, fileName);
			logout(ftp);
			return ftp;
		});
	}

	private FTPSClient connect(String server, Integer port) {
		FTPSClient ftpClient = new FTPSClient(false);
		try {
			logger.info(format("Connecting to FTP server at %s:%d", server, port));
			ftpClient.connect(server, port);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		int reply = ftpClient.getReplyCode();
		logger.info(format("FTP server connection response: %d", reply));

		if (!FTPReply.isPositiveCompletion(reply)) {
			throw new RuntimeException("FTP server refused connection.");
		}

		logger.info(format("Connected to %s:%d", server, port));
		return ftpClient;
	}

	private void login(FTPSClient ftp, String username, String password) {
		logger.info(format("About to login to FTP Server (username: '%s')", username));
		try {
			if (!ftp.login(username, password)) {
				throw new RuntimeException(format("Access denied for '%s'", username));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		logger.info("Successfully loged in");
	}

	private void logout(FTPSClient ftp) {
		try {
			ftp.logout();
		} catch (IOException e) {
			logger.error("Error tring to logout from FTP server", e);
		}
	}

	private void sendFile(FTPSClient ftp, InputStream is, String fileName) {
		logger.info(format("Starting to send file %s", fileName));
		try {
			ftp.execPBSZ(0);
			ftp.execPROT("P");
			ftp.enterLocalPassiveMode();			
			boolean success = ftp.storeFile(fileName, is);
			if(!success) {
				throw new RuntimeException("File could not been correctly stored");
			}
		} catch (IOException e) {
			throw new RuntimeException("Error transferring file", e);
		}
		logger.info("File transfer completed");
	}
}