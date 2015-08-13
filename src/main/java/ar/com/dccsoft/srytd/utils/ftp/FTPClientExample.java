package ar.com.dccsoft.srytd.utils.ftp;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

/**
 * see http://commons.apache.org/proper/commons-net/apidocs/org/apache/commons/net/ftp/FTPClient.html
 */
public final class FTPClientExample {

	public static void main(String[] args) throws UnknownHostException {
		FTPClient ftp = new FTPClient();
		FTPClientConfig config = new FTPClientConfig();
		// ftp.setControlKeepAliveTimeout(300);
		// config.setXXX(YYY); // change required options
		ftp.configure(config);
		boolean error = false;
		try {
			int reply;
			String server = "ftp.foobar.com";
			String username = "";
			String password = "";

			ftp.connect(server);
			System.out.println("Connected to " + server + ".");
			System.out.print(ftp.getReplyString());

			// After connection attempt, you should check the reply code to verify
			// success.
			reply = ftp.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				System.err.println("FTP server refused connection.");
				System.exit(1);
			}

			ftp.login(username, password);

			// TODO . transfer files
			// InputStream input = new FileInputStream(localFile);
			// ftp.storeFile("/remoteFileName", input);
			// input.close();

			ftp.logout();
		} catch (IOException e) {
			error = true;
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					// do nothing
				}
			}
			System.exit(error ? 1 : 0);
		}
	}
}
