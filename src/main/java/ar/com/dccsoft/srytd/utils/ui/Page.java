package ar.com.dccsoft.srytd.utils.ui;

import java.util.List;

@SuppressWarnings("rawtypes")
public class Page {

	private List items;
	private Long total;
	private Boolean success = true;

	/** required by serializer */
	public Page() {}

	public Page(List items, Long results) {
		this.items = items;
		this.total = results;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}


}
