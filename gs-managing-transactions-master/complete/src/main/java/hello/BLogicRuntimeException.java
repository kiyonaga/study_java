package hello;

public class BLogicRuntimeException extends RuntimeException
{
	public BLogicRuntimeException()
	{

	}

	public BLogicRuntimeException(String string)
	{
		super(string);
	}

	public BLogicRuntimeException(String string, Throwable cause)
	{
		super(string, cause);
	}
}
