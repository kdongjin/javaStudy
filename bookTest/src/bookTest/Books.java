package bookTest;

public class Books {
	private int id;    
	private String title;  
	private String publisher;  
	private String year;  
	private int price;
	
	public Books(int id, String title, String publisher, String year, int price) {
		super();
		this.id = id;
		this.title = title;
		this.publisher = publisher;
		this.year = year;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", title=" + title + ", publisher=" + publisher + ", year=" + year + ", price="
				+ price + "]";
	} 
}
