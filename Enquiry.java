public class Enquiry {
	private String question, answer;

	public Enquiry(String question) {
		this.question = question;
		this.answer = null;
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
}
