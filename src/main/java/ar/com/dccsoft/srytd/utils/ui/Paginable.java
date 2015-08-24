package ar.com.dccsoft.srytd.utils.ui;

import java.util.List;

@SuppressWarnings("rawtypes")
public class Paginable {

	private List items;
	private Integer total;
	private Boolean success = true;

	public Paginable() {
	}

	public Paginable(List items) {
		this.items = items;
		this.total = items.size();
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
