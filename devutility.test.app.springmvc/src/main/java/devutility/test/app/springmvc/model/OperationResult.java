package devutility.test.app.springmvc.model;

public class OperationResult {
	private boolean succeeded;
	private StringBuffer message;
	private Object data;

	public OperationResult() {
		succeeded = true;
		message = new StringBuffer("");
	}

	public void append(String message) {
		this.message.append(message);
	}

	public void appendError(String message) {
		setSucceeded(false);
		append(message);
	}

	public boolean isSucceeded() {
		return succeeded;
	}

	public void setSucceeded(boolean succeeded) {
		this.succeeded = succeeded;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public StringBuffer getMessage() {
		return message;
	}
}