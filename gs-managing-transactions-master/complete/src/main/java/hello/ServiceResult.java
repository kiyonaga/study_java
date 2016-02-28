package hello;

public class ServiceResult
{
	public String resultMessage = "";

	public String getResultMessage()
	{
		return resultMessage;
	}

	public void setResultMessage(String resultMessage)
	{
		this.resultMessage = resultMessage;
	}

	public ServiceResult() {}

	public ServiceResult(String resultMessage) { setResultMessage(resultMessage); }

	@Override
	public String toString()
	{
		return "ServiceResult [resultMessage=" + resultMessage + "]";
	}

}
