public class Enquiry {
	private String question, answer;
	private Camp sentTo;

	public Enquiry(String question, Camp sendTo) {
		this.question = question;
		this.answer = null;
		this.sentTo = sendTo;
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
}
