package ru.gemgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// без заголовка
		// todo Перетащить в манифест
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(new GameView(this));
	}
}
