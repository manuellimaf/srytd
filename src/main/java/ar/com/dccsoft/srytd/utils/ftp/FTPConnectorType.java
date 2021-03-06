package ar.com.dccsoft.srytd.utils.ftp;

public enum FTPConnectorType {
	FTP {
		@Override
		public FTPConnector getInstance() {
			return new PlainFTPConnector();
		}
	},
	FTPS {
		@Override
		public FTPConnector getInstance() {
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
