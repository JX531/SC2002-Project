public class Enquiry {
	private String question, answer;
	private Camp sentTo;
	private Student createdBy;

	public Enquiry(String question, Student createdBy, Camp sendTo) {
		this.question = question;
		this.answer = null;
		this.sentTo = sendTo;
		this.createdBy = createdBy;
	}
	//Overloaded
	public Enquiry(String question, String answer, Student createdBy, Camp sendTo) {
		this.question = question;
		this.answer = answer;
		this.sentTo = sendTo;
		this.createdBy = createdBy;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Camp getSentTo(){
		return this.sentTo;
	}

	public void setSentTo(Camp sentTo){
		this.sentTo = sentTo;
	}

	public Student getCreatedBy(){
		return this.createdBy;
	}
	public void setCreatedBy(Student createdBy){
		this.createdBy = createdBy;
	}
}
