/**
 * A class that represents a suggestion
 */
public class Suggestion {
	/**
	 * the suggestion content that a committee member is suggesting
	 */
	private String suggestion;
	/**
	 * If a suggestion has been approved or not
	 */
	private Boolean approved, /**
	 * if a suggestion has been processed by a staff
	 */
 processed;
	/**
	 * the student that submitted the suggestion
	 */
	private Student createdBy;
	/**
	 * The camp the suggestion is sent to, is the creator's committee camp
	 */
	private Camp sentTo;

	/**constructs a new suggestion object
	 * @param suggestion the suggestion
	 * @param createdBy student that submitted suggestion
	 * @param sentTo camp the suggestion is sent to
	 */
	public Suggestion(String suggestion, Student createdBy, Camp sentTo) {
		this.sentTo = sentTo;
		this.suggestion = suggestion;
		this.createdBy = createdBy;
		this.processed = false;
		this.approved = false;
	}

	/**Overloaded constructor to include every attribute as input
	 * @param suggestion the suggestion
	 * @param createdBy student that submitted suggestion
	 * @param sentTo camp the suggestion is sent to
	 * @param processed processed status of suggestion
	 * @param approved approval status of suggestion
	 */
	//Overloaded
	public Suggestion(String suggestion, Student createdBy, Camp sentTo, Boolean processed, Boolean approved) {
		this.sentTo = sentTo;
		this.suggestion = suggestion;
		this.createdBy = createdBy;
		this.processed = processed;
		this.approved = approved;
	}

	/**getter for suggestion content
	 * @return returns the suggestion content
	 */
	public String getSuggestion() {
		return suggestion;
	}

	/**setter for suggestion content
	 * @param suggestion new suggestion to set suggestion content to
	 */
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	/**getter for suggestion approval status
	 * @return returns suggestion's approval status
	 */
	public Boolean getApproved() {
		return approved;
	}

	/**setter for suggestion's approval status
	 * @param approved  status to set suggestion's approval status to
	 */
	public void setApproved(Boolean approved){
		this.approved = approved;
	}

	/**getter for suggestion's processed status
	 * @return Returns suggestion's processed status
	 */
	public Boolean getProcessed() {
		return processed;
	}

	/**setter for suggestion's processed status
	 * @param processed status to set suggestion's processed status to
	 */
	public void setProccessed(Boolean processed){
		this.processed = processed;
	}

	/**
	 * Function to approve a suggestion
	 */
	//move approve & disapprove to suggestions manager for staff, add in the automatic add point into approve
	public void approve() {
		this.processed = true;
		this.approved = true;
	}

	/**
	 * Function to disapprove a suggestion
	 */
	public void disapprove() {
		this.processed = true;
		this.approved = false;
	}

	/**getter for suggestion's createdBy
	 * @return returns student that created suggestion
	 */
	public Student getCreatedby() {
		return this.createdBy;
	}



	/**getter for suggestion's sentTo
	 * @return returns camp that suggestion was sent to
	 */
	public Camp getSentTo(){
		return this.sentTo;
	}



}
