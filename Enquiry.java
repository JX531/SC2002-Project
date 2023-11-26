/**Enquiry class that represents an enquiry
 *
 */
public class Enquiry {
	/**
	 * the question contained in enquiry
	 */
	private String question, /**
	 * answer to the question in enquiry
	 */
 answer;
	/**
	 * the camp the enquiry is sent to
	 */
	private Camp sentTo;
	/**
	 *the student that created this enquiry
	 */
	private Student createdBy;

	/**Constructor to make a new enquiry
	 * @param question the question contained in enquiry
	 * @param createdBy the student that created this enquiry
	 * @param sendTo the camp the enquiry is sent to
	 */
	public Enquiry(String question, Student createdBy, Camp sendTo) {
		this.question = question;
		this.answer = null;
		this.sentTo = sendTo;
		this.createdBy = createdBy;
	}

	/**Overloaded constructor to allow input for all attributes
	 * @param question the question contained in enquiry
	 * @param answer the answer to the question in enquiry
	 * @param createdBy the student that created this enquiry
	 * @param sendTo the camp the enquiry is sent to
	 */
	//Overloaded
	public Enquiry(String question, String answer, Student createdBy, Camp sendTo) {
		this.question = question;
		this.answer = answer;
		this.sentTo = sendTo;
		this.createdBy = createdBy;
	}

	/**getter for question
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**setter for question
	 * @param question new question to set the enquiry's question to
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**getter for answer
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**setter for answer
	 * @param answer new answer to set the enquiry's answer to
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**getter for sentTo
	 * @return the camp enquiry is sent to
	 */
	public Camp getSentTo(){
		return this.sentTo;
	}


	/**getter for createdBy
	 * @return the student that created the enquiry
	 */
	public Student getCreatedBy(){
		return this.createdBy;
	}


}
