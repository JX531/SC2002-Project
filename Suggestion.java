public class Suggestion {
	private String suggestion;
	private boolean approved, processed;
	private Student createdby;
	
	public Suggestion(String suggestion, Student createdby) {
		this.suggestion = suggestion;
		this.createdby = createdby;
		this.processed = false;
		this.approved = false;
	}
	
	public String getSuggestion() {
		return suggestion;
	}
	
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
	public boolean getApproved() {
		return approved;
	}
	
	public boolean getProcessed() {
		return processed;
	}
	
	public void approve() {
		this.processed = true;
		this.approved = true;
	}
	
	public void disapprove() {
		this.processed = true;
		this.approved = false;
	}
	
	public Student getCreatedby() {
		return createdby;
	}
}
