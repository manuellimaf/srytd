package ar.com.dccsoft.srytd.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/process")
public class ProcessController {

	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "ok";
	}

	@GET
	@Path("test-json")
	@Produces(MediaType.APPLICATION_JSON)
	public TestJson testJson() {
		return new TestJson();
	}

	public class TestJson {
		private String result = "ok";

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

	}
}
