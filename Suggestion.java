public class Suggestion {
	private String suggestion;
	private Boolean approved, processed;
	private Student createdby;
	private Camp sentTo;
	
	public Suggestion(String suggestion, Student createdby, Camp sentTo) {
		this.sentTo = sentTo;
		this.suggestion = suggestion;
		this.createdby = createdby;
		this.processed = false;
		this.approved = false;
	}
	
	//Overloaded
	public Suggestion(String suggestion, Student createdby, Camp sentTo, Boolean processed, Boolean approved) {
		this.sentTo = sentTo;
		this.suggestion = suggestion;
		this.createdby = createdby;
		this.processed = processed;
		this.approved = approved;
	}
	
	public String getSuggestion() {
		return suggestion;
	}
	
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved){
		this.approved = approved;
	}

	public Boolean getProcessed() {
		return processed;
	}
	public void setProccessed(Boolean processed){
		this.processed = processed;
	}

	//move approve & disapprove to suggestions manager for staff, add in the automatic add point into approve
	public void approve() {
		this.processed = true;
		this.approved = true;
	}
	public void disapprove() {
		this.processed = true;
		this.approved = false;
	}
	
	public Student getCreatedby() {
		return this.createdby;
	}
	public void setCreatedBy(Student createdBy){
		this.createdby = createdBy;
	}
	public Camp getSentTo(){
		return this.sentTo;
	}
	public void setSentTo(Camp sentTo){
		this.sentTo = sentTo;
	}
}
