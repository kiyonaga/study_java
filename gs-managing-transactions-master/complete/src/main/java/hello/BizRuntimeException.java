package hello;

public class BizRuntimeException extends RuntimeException
{
	public BizRuntimeException()
	{

	}

	public BizRuntimeException(String string)
	{
		super(string);
	}

	public BizRuntimeException(String string, Throwable cause)
	{
		super(string, cause);
	}
}
