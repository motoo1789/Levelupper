package widgets;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import database.SQLite;
import levelupper3.View;
import property.Property;
import youser.Youser;

public class CreateProgressBar {

	private int currentlevel = 0;
	private int nextLevel = 10;
	private int barnum = 0;
	GridData gd;
	ProgressBar probar;
	Property propertyObject = Property.getInstance();
	Youser youser = Youser.getInstance();
	SQLite sqliteObject = SQLite.getInstanse();

	public CreateProgressBar(Composite container) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		gd = new GridData();
		gd.heightHint = 40;
		gd.widthHint = 400;

		if(sqliteObject.hasDB())
		{
			list = SQLite.readDB();
			currentlevel = list.get(0);
			youser.setLevel(list.get(0));

			nextLevel = list.get(1);
			youser.setNextLevel(list.get(1));

			barnum = list.get(2);
			youser.setBarNum(list.get(2));
		}
		else
		{
			currentlevel = Integer.parseInt(propertyObject.getProperty("Level"));
			youser.setLevel(currentlevel);
			nextLevel = Integer.parseInt(propertyObject.getProperty("nextLevel"));
			barnum = Integer.parseInt(propertyObject.getProperty("barnum"));
		}



		probar = new ProgressBar(container,SWT.NULL);
		probar.setMinimum(0);
		probar.setMaximum(nextLevel);
		probar.setSelection(barnum);
	}


	public ProgressBar getProgressBar() {

		return probar;

	}

	public void upBar() {

		barnum++;
		probar.setSelection(barnum);
		isNextLevel();
	}

	private void isNextLevel() {

		if(nextLevel <= barnum)
		{
			nextLevel += 10;
			barnum = 0;
			probar.setMaximum(nextLevel);
			probar.setSelection(barnum);

			try {
				View view = (View) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("Levelupper3.view");
				view.upText();
				youser.setNextLevel(nextLevel);
				youser.setBarNum(barnum);
			} catch (PartInitException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		else {
			youser.setNextLevel(nextLevel);
			youser.setBarNum(barnum);
		}

	}

	public int getNextLevel() {
		return nextLevel;
	}

	public int getBarNum() {
		return barnum;
	}
}
