package logger;

import java.io.PrintStream;

@SuppressWarnings({ "WeakerAccess" })
public class LoggerPrintStream extends PrintStream
{
    protected final boolean isOutputStream;
    private transient int depth;

    public LoggerPrintStream(PrintStream original)
    {
        super(original);
        this.isOutputStream = original.equals(System.out);
        depth = 0;
    }

    public void outputln(String s) { super.println(s); }

    @Override
    public void println(String msg)
    {
        Log.log(isOutputStream ? Log.LogType.STD_OUT : Log.LogType.STD_ERR, msg, 1 + depth);
        depth = 0;
    }

    @Override
    public void println(Object msg)
    {
        ++depth;
        println(msg.toString());
    }
}