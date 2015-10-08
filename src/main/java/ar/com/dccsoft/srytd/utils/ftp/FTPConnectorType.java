package ar.com.dccsoft.srytd.utils.ftp;

public enum FTPConnectorType {
	FTP {
		@Override
		public FTPConnector getInstance() {
			//return new PlainFTPConnector();
			return new FTPSConnector();
		}
	},
	SFTP {
		@Override
		public FTPConnector getInstance() {
			return new SFTPConnector();
		}
	};
	
	public abstract FTPConnector getInstance();
}
