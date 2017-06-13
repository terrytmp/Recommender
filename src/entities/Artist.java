package entities;

public class Artist implements Comparable<Artist> {
	
	private Integer id;
	private String name;
	private String url;
	private String picUrl;
	private Integer weight;
	public Object parseInt;
	
	public Artist() {
	}
	
	public Artist(String n, String u) {
		name = n;
		url = u;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Artist compareArtist) {
		int compareWeight = ((Artist) compareArtist).getWeight();
		return this.weight - compareWeight;
	}
	
	public String toString() { 
	    return "ID : " + getId().toString() + " Weight : " + getWeight().toString();
	} 
	
}
