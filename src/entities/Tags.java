package entities;

public class Tags {
	
	private Integer id;
	private String value;
	
	public Tags(Integer tagId) {
		this.id = tagId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
