package finalproj;

public class Timer
{
	GamePanel game_panel;

	private long start_time;
	private long elapsed_seconds;
	private long seconds_display;

	private boolean stopped;

	public Timer(GamePanel game_panel)
	{
		this.game_panel = game_panel;
		this.start_time = game_panel.seconds_display;
		this.stopped = false;
	}

	public void setSecondsElapsed(long elapsed_seconds)
	{
		this.elapsed_seconds = elapsed_seconds;
	}

	public long getSecondsElapsed()
	{
		return this.seconds_display = this.elapsed_seconds-this.start_time;
	}

	public void reset()
	{
		this.elapsed_seconds=0;
		this.stopped=true;
	}

	public void go()
	{
		this.start_time = game_panel.seconds_display;
		this.stopped=false;
	}

	public boolean isStopped()
	{
		return this.stopped;
	}
}
