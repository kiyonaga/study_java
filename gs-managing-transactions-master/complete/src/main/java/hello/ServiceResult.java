package hello;

public class ServiceResult {
	private String resultMessage;
	private boolean hasError;

	public String getResultMessage() {
		return resultMessage;
	}

	public boolean isFail() {
		return hasError;
	}

	public static ServiceResult success() {
		return ServiceResult.success("");
	}

	public static ServiceResult success(String resultMessage) {
		return new ServiceResult(resultMessage, false);
	}

	public static ServiceResult fail(String resultMessage) {
		return new ServiceResult(resultMessage, true);
	}

	private ServiceResult() {
		this("", false);
	}

	private ServiceResult(String resultMessage, boolean hasError) {
		this.hasError = hasError;
		this.resultMessage = resultMessage;
	}

	@Override
	public String toString() {
		return "ServiceResult [resultMessage=" + resultMessage + ", hasError=" + hasError + "]";
	}

}
