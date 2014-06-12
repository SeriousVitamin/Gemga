package ru.gemgame;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.os.SystemClock;

public class GameManager extends Thread
{
	static final long MAX_FPS = 60;

	/** ���� �������� � �� = 10 */
	static long FPS;

	/** ������ ������ GameView */
	private GameView view;

	/** ������ ��������� ������ */
	private boolean running = false;

	/** ����������� ������ */
	public GameManager(GameView view)
	{
		this.view = view;
	}

	/** ������� ��������� ������ */
	public void setRunning(boolean run)
	{
		running = run;
	}

	/** ��������, ����������� � ������ */
	@SuppressLint("WrongCall")
	@Override
	public void run()
	{
		long ticksPS = 1000 / MAX_FPS;
		long startTime;
		long sleepTime;
		while (running)
		{
			Canvas canvas = null;
			startTime = System.currentTimeMillis();
			try
			{
				CalculateFrameRate();
				view.fps = FPS;
				canvas = view.getHolder().lockCanvas();
				synchronized (view.getHolder())
				{
					view.onDraw(canvas);
				}
			} finally
			{
				if (canvas != null)
				{ // c -> canvas
					view.getHolder().unlockCanvasAndPost(canvas);
				}
			}
			sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
			try
			{
				if (sleepTime > 0)
					sleep(sleepTime);
				else
					sleep(10);
			} catch (Exception e)
			{
			}
		}
	}

	static long framesPerSecond = 0;
	static float lastTime = 0.0f;

	void CalculateFrameRate()
	{
		float currentTime = SystemClock.elapsedRealtime() * 0.001f;

		++framesPerSecond;

		if (currentTime - lastTime > 1.0f)
		{
			lastTime = currentTime;
			FPS = framesPerSecond;
			framesPerSecond = 0;
		}
	}
}
