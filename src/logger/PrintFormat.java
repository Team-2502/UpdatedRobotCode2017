package logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({ "WeakerAccess", "StringBufferReplaceableByString" })
public class PrintFormat
{
    private static final Pattern TIMESTAMP = Pattern.compile("timestamp", Pattern.LITERAL);
    private static final Pattern TYPE = Pattern.compile("type", Pattern.LITERAL);
    private static final Pattern CALLER_CLASS = Pattern.compile("caller_class", Pattern.LITERAL);
    private static final Pattern MSG = Pattern.compile("msg", Pattern.LITERAL);

    private String format;
    private int[] timeOrganizer;
    private Timer timer;

    public PrintFormat(String format, String timeFormat)
    {
        this.format = format;
        this.timeOrganizer = new int[] { 0, 0, 0, 0, 0 };
        for(String spl : timeFormat.split(":"))
        {
            if(spl.contains("M")) { timeOrganizer[0] = spl.length(); }
            else if(spl.contains("s")) { timeOrganizer[1] = spl.length(); }
            else if(spl.contains("m")) { timeOrganizer[2] = spl.length(); }
            else if(spl.contains("h")) { timeOrganizer[3] = spl.length(); }
            else if(spl.contains("d")) { timeOrganizer[4] = spl.length(); }
        }
        timer = new Timer();
    }

    public <T> String getPrintString(String type, String caller, T msg) { return MSG.matcher(CALLER_CLASS.matcher(TYPE.matcher(TIMESTAMP.matcher(format).replaceAll(Matcher.quoteReplacement(timer.getTime()))).replaceAll(Matcher.quoteReplacement(type))).replaceAll(Matcher.quoteReplacement(caller))).replaceAll(Matcher.quoteReplacement(String.valueOf((Object) msg))); }

    protected class Timer
    {
        protected long[] times; /* [ milli, seconds, minutes, hours, days ] */
        protected long[] lastTimes;
        protected String[] stringTimes;
        protected final long startTime;

        static final boolean USE_SYSTEM_CLOCK = false;

        private Timer()
        {
            times = new long[5];
            lastTimes = new long[] { -1, -1, -1, -1, -1 };
            stringTimes = new String[5];
            startTime = System.currentTimeMillis();
        }

        protected String formatTime()
        {
            StringBuilder out = new StringBuilder(format.length());
            for(int i = 0; i < timeOrganizer.length; ++i)
            {
                if(timeOrganizer[i] == 0) { continue; }
                if(times[i] == lastTimes[i]) { out.append(stringTimes[i]); }
                else { out.append(stringTimes[i] = String.format(new StringBuilder(3 + timeOrganizer[i]).append("%0").append(timeOrganizer[i]).append('d').toString(), times[i])); }
                out.append(':');
            }
            return out.toString().substring(0, out.length() - 1);
        }

        protected String getTime()
        {
            times[0] = System.currentTimeMillis() - startTime;
            long totalSeconds = times[0] / 1000;
            times[0] %= 1000;
            times[1] = totalSeconds % 60;
            times[2] = (totalSeconds / 60) % 60;
            times[3] = (totalSeconds / 3600) % 24;
            /* The robot can't run this long, if it is needed uncomment it. */
//            times[4] = totalSeconds / 86400;
            return formatTime();
        }
    }
}